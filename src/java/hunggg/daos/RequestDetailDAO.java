/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hunggg.daos;

import hunggg.dtos.RequestDTO;
import hunggg.dtos.ResourceDTO;
import hunggg.utils.DBConnector;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.naming.NamingException;

/**
 *
 * @author Hung
 */
public class RequestDetailDAO implements Serializable {

    public RequestDetailDAO() {
    }

    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet result = null;

    private void checkAndCloseConn()
            throws NamingException, SQLException {
        if (result != null) {
            result.close();
        }
        if (ps != null) {
            ps.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public boolean checkContentOfTheCart(CartObjectDAO cartObject)
            throws NamingException, SQLException {

        boolean result = true;
        try {
            conn = DBConnector.getTheConnection();
            ResourceDAO resourceDAO = new ResourceDAO();

            for (Map.Entry<ResourceDTO, Integer> entry : cartObject.getCustomerCart().entrySet()) {
                ResourceDTO resourceDTO = entry.getKey();

                int quantity = entry.getValue();
                int availableQuantity = resourceDAO.checkQuantityOfItem(resourceDTO.getItemID());

                if (availableQuantity < quantity) {
                    result = false;
                }
            }
        } finally {
            checkAndCloseConn();
        }
        return result;
    }

    public boolean addRequestDetailIntoDatabase(int requestID, CartObjectDAO cartObject)
            throws NamingException, SQLException {

        int rowsAffected = 0;

        try {
            conn = DBConnector.getTheConnection();

            for (Map.Entry<ResourceDTO, Integer> en : cartObject.getCustomerCart().entrySet()) {

                ResourceDTO resourceDTO = en.getKey();
                int quantity = en.getValue();
                String statement = "INSERT INTO tbl_Request_Detail(Request_ID,Item_ID,Quantity,StatusID) VALUES(?,?,?,?)";

                ps = conn.prepareStatement(statement);

                ps.setInt(1, requestID);
                ps.setInt(2, resourceDTO.getItemID());
                ps.setInt(3, quantity);
                ps.setInt(4, 3);

                rowsAffected += ps.executeUpdate();
            }
            if (rowsAffected == cartObject.getCustomerCart().size()) {
                return true;
            }
        } finally {
            checkAndCloseConn();
        }
        return false;
    }
}
