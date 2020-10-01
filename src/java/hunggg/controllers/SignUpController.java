/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hunggg.controllers;

import hunggg.daos.RegistrationDAO;
import hunggg.dtos.RegistrationDTO;
import hunggg.utils.ConfirmCodeSender;
import hunggg.utils.UserInputValidator;
import java.sql.Date;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author giang
 */
@Controller
public class SignUpController {

    private static final Logger LOGGER = Logger.getLogger(SignUpController.class);

    @RequestMapping("/signUpPage")
    public String getToSignUpPage() {
        return "signUp";
    }

    @RequestMapping("/signUpForm")
    public String processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        PropertyConfigurator.configure(getClass().getResourceAsStream("/hunggg/utils/log4j.properties"));

        String url = "signUp";
        boolean checkStatus = true;
        try {
            if (request.getParameter("txtEmailAddressValue") != null) {
                String emailAddress = request.getParameter("txtEmailAddressValue").trim();
                String password = request.getParameter("txtPasswordValue");
                String confirmPassword = request.getParameter("txtConfirmPasswordValue");

                String fullName = request.getParameter("txtFullnameValue").trim();
                String phoneNumber = request.getParameter("txtPhoneNumberValue").trim();
                String homeAddress = request.getParameter("txtHomeAdressValue").trim();

                RegistrationDAO regisDAO = new RegistrationDAO();
                UserInputValidator validator = new UserInputValidator();

                // check to see if the email is already in DB or not
                if (regisDAO.checkEmailExistence(emailAddress)) {
                    request.setAttribute("EMAIL_ADDRESS", "This email has already been taken");
                    checkStatus = false;
                }

                // password length is from 5 to 225
                if (!validator.checkPassword(password)) {
                    request.setAttribute("PASSWORD", "The password length is from 5 to 255");
                    checkStatus = false;
                }

                // check both passwords if they are the same
                if (!validator.checkConfirmPassword(password, confirmPassword)) {
                    request.setAttribute("CONFIRM_PASSWORD", "Both passwords are not the same");
                    checkStatus = false;
                }

                // fullname length is from 5 to 225
                if (!validator.checkFullname(fullName)) {
                    request.setAttribute("FULL_NAME", "The full name length is from 5 to 255 and has letters only");
                    checkStatus = false;
                }

                // check Phone number format
                if (!validator.checkPhoneNumber(phoneNumber)) {
                    request.setAttribute("PHONE_NUMBER", "The phone number has numbers only and from 10 and 11 digits");
                    checkStatus = false;
                }

                if (!validator.checkAddress(homeAddress)) {
                    request.setAttribute("ADDRESS", "The address length is from 5 to 255");
                    checkStatus = false;
                }

                if (checkStatus) {
                    RegistrationDTO regisDTO = new RegistrationDTO(emailAddress, password, fullName, homeAddress, phoneNumber, fullName, new Date(System.currentTimeMillis()));
                    boolean regisStatus = regisDAO.insertNewUser(regisDTO);
                    if (regisStatus) {
                        ConfirmCodeSender codeSender = new ConfirmCodeSender();
                        int code = codeSender.sendConfirmationCode(emailAddress);

                        request.getSession().setAttribute("CONFIRM_CODE", code);
                        request.getSession().setAttribute("NEW_EMAIL", emailAddress);

                        url = "confirmCode";
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Sign Up Controller SQL Exception: " + e.getMessage());
        } catch (NamingException e) {
            LOGGER.error("Sign Up Controller Naming Exception: " + e.getMessage());
        }
        return url;
    }
}
