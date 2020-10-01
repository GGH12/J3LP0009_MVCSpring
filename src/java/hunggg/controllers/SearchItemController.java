/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hunggg.controllers;

import hunggg.daos.ResourceDAO;
import hunggg.dtos.ResourceDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author giang
 */
public class SearchItemController implements Controller {

    private static final Logger LOGGER = Logger.getLogger(SearchItemController.class);

    @Override
    @RequestMapping(value = "/searchInfoForm")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        PropertyConfigurator.configure(getClass().getResourceAsStream("/hunggg/utils/log4j.properties"));
        ModelAndView modelView = null;
        int pageNumber = 1, numOfRowsPerPage = 5;

        try {
            if (request != null) {

                String txtItemName = request.getParameter("txtSearchValue").trim();
                String txtColorName = request.getParameter("txtColorName");

                ResourceDTO resourceDTO = new ResourceDTO("", "");

                if (!txtItemName.isEmpty()) {
                    resourceDTO.setItemName(txtItemName);
                }
                if (!txtColorName.isEmpty()) {
                    resourceDTO.setColor(txtColorName);
                }

                if (request.getParameter("page") != null) {
                    pageNumber = Integer.parseInt(request.getParameter("page"));
                }

                ResourceDAO resourceDAO = new ResourceDAO();
                ArrayList<ResourceDTO> theResourceList = resourceDAO.searchTheResource(resourceDTO, (pageNumber - 1) * numOfRowsPerPage, numOfRowsPerPage);

                request.getSession().setAttribute("RESOURCE_LIST", theResourceList);
                request.getSession().setAttribute("NUM_OF_PAGES", Math.ceil((double) resourceDAO.getTheNumOfRowsBasedOnSearch(resourceDTO) / (double) numOfRowsPerPage));

            }
            modelView = new ModelAndView("searchForAdmin");
        } catch (SQLException e) {
            LOGGER.error("Search Item Controller SQL Exception: " + e.getMessage());
        } catch (NamingException e) {
            LOGGER.error("Search Item Controller Naming Exception: " + e.getMessage());
        }
        return modelView;
    }

}
