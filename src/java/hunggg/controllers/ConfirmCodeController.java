/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hunggg.controllers;

import hunggg.daos.RegistrationDAO;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
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
public class ConfirmCodeController {

    private static final Logger LOGGER = Logger.getLogger(ConfirmCodeController.class);

    private boolean checkUserInput(String userInput, String sentCode) {
        return userInput.equals(sentCode);
    }

    @RequestMapping("/confirmCodeForm")
    public String processTheRequest(HttpServletRequest request)
            throws ServletException {

        PropertyConfigurator.configure(getClass().getResourceAsStream("/hunggg/utils/log4j.properties"));
        String url = "confirmCode";

        try {
            if (request.getParameter("txtConfirmCode").trim() != null) {
                String userInput = request.getParameter("txtConfirmCode").trim();
                String sentCode = request.getSession().getAttribute("CONFIRM_CODE").toString();

                boolean checkStatus = checkUserInput(userInput, sentCode);
                if (checkStatus) {

                    RegistrationDAO regisDAO = new RegistrationDAO();
                    boolean updateStat = regisDAO.activateTheAccount(request.getSession(false).getAttribute("NEW_EMAIL").toString());
                    if (updateStat) {
                        url = "login";
                        request.getSession().invalidate();
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Confirm Code Controller SQL Exception: " + e.getMessage());
        } catch (NamingException e) {
            LOGGER.error("Confirm Code Controller Naming Exception: " + e.getMessage());
        }
        return url;
    }
}
