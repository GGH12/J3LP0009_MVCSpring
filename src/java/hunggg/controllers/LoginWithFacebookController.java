/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hunggg.controllers;

import com.restfb.types.User;
import hunggg.daos.ColorDAO;
import hunggg.dtos.RegistrationDTO;
import hunggg.utils.FacebookMethods;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Hung
 */
@Controller
public class LoginWithFacebookController implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(LoginWithFacebookController.class);

    @RequestMapping(value = "/loginFacebookForm")
    public String loginWithFacebook(HttpServletRequest request)
            throws Exception {

        String url = "login";
        PropertyConfigurator.configure(getClass().getResourceAsStream("/hunggg/utils/log4j.properties"));
        try {
            String code = request.getParameter("code");
            if (code != null) {
                String accessToken = FacebookMethods.getFacebookToken(code);
                if (accessToken != null) {
                    User facebookUser = FacebookMethods.getFacebookUserInfo(accessToken);
                    if (facebookUser != null) {

                        RegistrationDTO regisDTO = new RegistrationDTO(facebookUser);
                        request.getSession().setAttribute("USER_INFO_2", regisDTO);
                        url = "searchForOtherRoles";

                        ColorDAO colorDAO = new ColorDAO();
                        ArrayList<String> theColorList = colorDAO.getTheColorList();
                        request.getSession(true).setAttribute("COLOR_LIST", theColorList);
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Login With Facebook SQL Exception: " + e.getMessage());
        } catch (NamingException e) {
            LOGGER.error("Login With Facebook Naming Exception: " + e.getMessage());
        }
        return url;
    }
}
