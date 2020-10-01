/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hunggg.controllers;

import hunggg.daos.ColorDAO;
import hunggg.dtos.GoogleDTO;
import hunggg.dtos.RegistrationDTO;
import hunggg.utils.GoogleMethods;
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
public class LoginWithGoogleController implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(LoginWithGoogleController.class);

    @RequestMapping(value = "/loginGoogleForm")
    public String loginWithGoogle(HttpServletRequest request)
            throws Exception {

        PropertyConfigurator.configure(getClass().getResourceAsStream("/hunggg/utils/log4j.properties"));
        String url = "login";
        try {
            String code = request.getParameter("code");
            if (code != null) {
                String accessToken = GoogleMethods.getToken(code);
                if (accessToken != null) {
                    GoogleDTO googlePojo = GoogleMethods.getUserInfo(accessToken);
                    url = "searchForOtherRoles";

                    RegistrationDTO regisDTO = new RegistrationDTO(googlePojo);
                    request.getSession(true).setAttribute("USER_INFO_2", regisDTO);

                    ColorDAO colorDAO = new ColorDAO();
                    ArrayList<String> theColorList = colorDAO.getTheColorList();
                    request.getSession(true).setAttribute("COLOR_LIST", theColorList);
                }

            }
        } catch (SQLException e) {
            LOGGER.error("Login With Google SQL Exception: " + e.getMessage());
        } catch (NamingException e) {
            LOGGER.error("Login With Google Naming Exception: " + e.getMessage());
        }
        return url;
    }
}
