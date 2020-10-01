/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hunggg.controllers;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author giang
 */
@Controller
public class MainController {

    /**
     *
     * @return @throws Exception
     */
    private static final Logger LOGGER = Logger.getLogger(MainController.class);

    @RequestMapping(value = "/")
    public String handleRequest(HttpServletRequest request) throws Exception {
        return "login";
    }

    @RequestMapping(value = "/loginPage")
    public String getToLoginPage() {
        return "login";
    }

    @RequestMapping("/showCartPage")
    public String showCart() {
        return "showCart";
    }

    @RequestMapping("/confirmCodePage")
    public String getToCodePage() {
        return "confirmCode";
    }
}
