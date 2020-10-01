/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hunggg.controllers;

import hunggg.daos.CartObjectDAO;
import hunggg.dtos.ResourceDTO;
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
public class AddToCartController {

    private static final Logger LOGGER = Logger.getLogger(AddToCartController.class);

    @RequestMapping("/updateQuantityCartForm")
    public String updateQuantityFromCart(HttpServletRequest request)
            throws Exception {

        String url = "login";
        PropertyConfigurator.configure(getClass().getResourceAsStream("/hunggg/utils/log4j.properties"));
        
        try {
            if (request.getParameter("txtItemID") != null) {

                CartObjectDAO cartObject = (CartObjectDAO) request.getSession().getAttribute("CART_OBJECT");
                if (request.getParameter("btnAction").equals("Update")) {

                    int itemID = Integer.parseInt(request.getParameter("txtItemID"));
                    int itemQuantity = Integer.parseInt(request.getParameter("txtItemQuantity"));
                    
                    if (cartObject != null) {
                        boolean status = cartObject.updateToCart(itemID, itemQuantity);
                        if (status) {
                            url = "showCart";
                        }
                    }
                } else if (request.getParameter("btnAction").equals("Delete")) {
                    int itemID = Integer.parseInt(request.getParameter("txtItemID2"));
                    if (cartObject != null) {
                        boolean deleteStatus = cartObject.deleteToCart(itemID);
                        if (deleteStatus) {
                            url = "showCart";
                        }
                    }
                }

            }
        } catch (Exception e) {
            LOGGER.error("Update Quantity From Cart Exception: " + e.getMessage());
        }
        return url;
    }

    @RequestMapping("/addToCartForm")
    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String url = "login";
        PropertyConfigurator.configure(getClass().getResourceAsStream("/hunggg/utils/log4j.properties"));
        try {
            if (request != null) {
                HttpSession session = request.getSession();
                CartObjectDAO cartObject = (CartObjectDAO) session.getAttribute("CART_OBJECT");

                if (cartObject == null) {
                    cartObject = new CartObjectDAO();
                }

                int itemID = Integer.parseInt(request.getParameter("txtItemID"));
                String itemName = request.getParameter("txtItemName");
                String itemColor = request.getParameter("txtItemColor");
                String itemCategory = request.getParameter("txtItemCategory");

                int availableQuantity = Integer.parseInt(request.getParameter("txtAvailableQuantity"));
                int latestPage = Integer.parseInt(session.getAttribute("pageIndex").toString());

                String lastSearchValue = request.getParameter("lastSearchValue");
                String lastColorValue = request.getParameter("lastColorValue");

                ResourceDTO resourceDTO = new ResourceDTO(itemID, itemName, itemColor, itemCategory, availableQuantity);
                cartObject.addToCart(resourceDTO);

                session.setAttribute("CART_OBJECT", cartObject);
                url = "searchInfoForOtherRolesForm?txtSearchValue=" + lastSearchValue + "&txtColorName=" + lastColorValue + "&page=" + latestPage;
            }
        } catch (Exception e) {
            LOGGER.error("Add To Cart Controller Error: " + e.getMessage());
        } finally {
            response.sendRedirect(url);
        }
    }
}
