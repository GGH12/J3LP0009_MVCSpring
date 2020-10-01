/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hunggg.controllers;

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
public class LogOutController {

    private static final Logger LOGGER = Logger.getLogger(LogOutController.class);

    @RequestMapping(value = "/logOutForm")
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        PropertyConfigurator.configure(getClass().getResourceAsStream("/hunggg/utils/log4j.properties"));
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
                response.sendRedirect("loginForm");
            }
        } catch (Exception e) {
            LOGGER.error("Log Out Controller Exception: " + e.getMessage());
        }
        return null;
    }

}
