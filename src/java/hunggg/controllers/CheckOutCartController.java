/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hunggg.controllers;

import hunggg.daos.CartObjectDAO;
import hunggg.daos.RequestDAO;
import hunggg.daos.RequestDetailDAO;
import hunggg.daos.ResourceDAO;
import hunggg.dtos.RegistrationDTO;
import hunggg.dtos.RequestDTO;
import java.sql.Date;
import java.sql.SQLException;
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
 * @author Hung
 */
@Controller
public class CheckOutCartController {

    private static final Logger LOGGER = Logger.getLogger(CheckOutCartController.class);

    @RequestMapping("/checkOutCartForm")
    public String checkOutCart(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        PropertyConfigurator.configure(getClass().getResourceAsStream("/hunggg/utils/log4j.properties"));
        HttpSession session = request.getSession(false);
        RequestDTO requestDTO = null;
        String url = "showCart";

        try {

            CartObjectDAO cartObject = (CartObjectDAO) session.getAttribute("CART_OBJECT");
            if (cartObject != null) {

                RegistrationDTO registrationDTO = (RegistrationDTO) session.getAttribute("USER_INFO_2");
                requestDTO = new RequestDTO(registrationDTO.getUserID(), new Date(System.currentTimeMillis()));

                boolean checkContentOfCartStatus = new RequestDetailDAO().checkContentOfTheCart(cartObject);
                if (checkContentOfCartStatus) {

                    boolean requestStatus = new RequestDAO().addCartToDatabaseAndGetNewestRequestID(requestDTO);
                    int requestID = new RequestDAO().getNewestRequestID();

                    boolean checkCartToDBStatus = new RequestDetailDAO().addRequestDetailIntoDatabase(requestID, cartObject);
                    if (requestStatus && checkCartToDBStatus) {

                        ResourceDAO resourceDAO = new ResourceDAO();
                        boolean updateStoreStatus = resourceDAO.decreaseItemQuantityAfterCheckout(cartObject);
                        if (updateStoreStatus) {

                            url = "searchForOtherRoles";
                            cartObject.removeAllToCart();
                            session.removeAttribute("CART_OBJECT");
                        }
                    }
                } else {
                    String statement = "One or more items is out of stock";
                    request.setAttribute("CHECK_OUT_ERROR", statement);
                }
            }
        } catch (NamingException e) {
            LOGGER.error("Check Out Cart Controller Naming Exception: " + e.getMessage());
        } catch (SQLException e) {
            LOGGER.error("Check Out Cart Controller SQL Exception: " + e.getMessage());
        }
        return url;
    }
}
