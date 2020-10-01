/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hunggg.controllers;

import hunggg.daos.ColorDAO;
import hunggg.daos.RegistrationDAO;
import hunggg.utils.GoogleRECAPTCHA;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author giang
 */
@Controller
public class LoginController {

    private static final Logger LOGGER = Logger.getLogger(LoginController.class);

    @RequestMapping("/loginForm")
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        PropertyConfigurator.configure(getClass().getResourceAsStream("/hunggg/utils/log4j.properties"));
        String url = "login";

        try {
            if (request.getParameter("txtUsername") != null
                    && request.getParameter("txtPassword") != null
                    && request.getParameter("g-recaptcha-response") != null) {

                String username = request.getParameter("txtUsername").trim();
                String password = request.getParameter("txtPassword").trim();
                String googleRecaptcha = request.getParameter("g-recaptcha-response");
                String statusName = "Accept";

                RegistrationDAO registrationDAO = new RegistrationDAO();

                boolean loginStatus = registrationDAO.gainAccess(username, password, statusName);
                if (loginStatus && GoogleRECAPTCHA.verify(googleRecaptcha)) {
                    HttpSession session = request.getSession(true);

                    if (registrationDAO.getRoleNameFromUserID(username).equals("Manager")) {
                        url = "searchForAdmin";
                        session.setAttribute("USER_INFO", registrationDAO.getUserFullNameAndEmail(username));
                    } else {
                        url = "searchForOtherRoles";
                        session.setAttribute("USER_INFO_2", registrationDAO.getUserFullNameAndEmail(username));
                    }
                    ColorDAO colorDAO = new ColorDAO();
                    ArrayList<String> theColorList = colorDAO.getTheColorList();
                    session.setAttribute("COLOR_LIST", theColorList);

                } else if (!loginStatus) {
                    request.setAttribute("USER_LOGIN_ERROR", "Your account is not found !");
                } else if (!GoogleRECAPTCHA.verify(googleRecaptcha)) {
                    request.setAttribute("USER_LOGIN_ERROR", "The RECAPTCHA is not found !");
                }
            }

        } catch (SQLException e) {
            LOGGER.error("Login Controller SQL Exception: " + e.getMessage());
        } catch (NamingException e) {
            LOGGER.error("Login Controller Naming Exception: " + e.getMessage());
        }
        return url;
    }
}
