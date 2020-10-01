/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hunggg.daos;

import hunggg.dtos.ResourceDTO;
import hunggg.utils.DBConnector;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import javax.naming.NamingException;

/**
 *
 * @author giang
 */
public class ResourceDAO implements Serializable {

    private ArrayList<ResourceDTO> theResourceList;

    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet result = null;

    public ResourceDAO() {

    }

    private void checkAndCloseConnection()
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

    private int getItemQuantityWithItemID(int itemID)
            throws NamingException, SQLException {

        int quantity = 0;
        try {
            conn = DBConnector.getTheConnection();
            String statement = "SELECT AvailableQuantity FROM tbl_Resource WHERE ItemID = ?";

            ps = conn.prepareStatement(statement);
            ps.setInt(1, itemID);

            result = ps.executeQuery();
            if (result.next()) {
                quantity = result.getInt("AvailableQuantity");

            }
        } finally {
            checkAndCloseConnection();
        }
        return quantity;
    }

    public boolean decreaseItemQuantityAfterCheckout(CartObjectDAO cartObject)
            throws NamingException, SQLException {

        try {
            conn = DBConnector.getTheConnection();
            for (Map.Entry<ResourceDTO, Integer> en : cartObject.getCustomerCart().entrySet()) {

                ResourceDTO resourceDTO = en.getKey();
                int quantity = en.getValue();

                String statement = "UPDATE tbl_Resource SET AvailableQuantity = ? WHERE ItemID = ?";
                ps = conn.prepareStatement(statement);

                ps.setInt(1, getItemQuantityWithItemID(resourceDTO.getItemID()) - quantity);
                ps.setInt(2, resourceDTO.getItemID());

            }

        } finally {
            checkAndCloseConnection();
        }
        return false;
    }

    public int checkQuantityOfItem(int itemID)
            throws NamingException, SQLException {
        try {
            conn = DBConnector.getTheConnection();
            String statement = "SELECT AvailableQuantity FROM tbl_Resource WHERE ItemID = ?";

            ps = conn.prepareStatement(statement);
            ps.setInt(1, itemID);

            result = ps.executeQuery();
            if (result.next()) {
                return result.getInt("AvailableQuantity");
            }

        } finally {
            checkAndCloseConnection();
        }
        return -1;
    }

    public int getTheNumOfRowsBasedOnSearch(ResourceDTO resourceDTO)
            throws NamingException, SQLException {

        int numOfRows = 0;
        try {
            conn = DBConnector.getTheConnection();
            String statement = "SELECT COUNT(*) AS NumOfRows "
                    + "FROM tbl_Resource r , tbl_Color c "
                    + "WHERE r.ColorID = c.ColorID "
                    + "AND r.ItemName LIKE ? "
                    + "AND c.ColorName LIKE ? "
                    + "AND r.AvailableQuantity > ?";

            ps = conn.prepareStatement(statement);
            ps.setString(1, "%" + resourceDTO.getItemName() + "%");
            ps.setString(2, "%" + resourceDTO.getColor() + "%");
            ps.setInt(3, 0);

            result = ps.executeQuery();
            if (result.next()) {
                numOfRows = result.getInt("NumOfRows");
            }
        } finally {
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
        return numOfRows;
    }

    public ArrayList<ResourceDTO> searchTheResource(ResourceDTO resourceDTO, int pageNumber, int numOfRowsPerPage)
            throws NamingException, SQLException {

        try {
            conn = DBConnector.getTheConnection();
            String statement = "SELECT ItemID , ItemName , ColorName , Category , Quantity , AvailableQuantity , UsingDate , EndDate , PrivacyName "
                    + "FROM tbl_Resource r , tbl_Privacy p  , tbl_Color c "
                    + "WHERE r.PrivacyID = p.PrivacyID "
                    + "AND r.ColorID = c.ColorID "
                    + "AND r.ItemName LIKE ? "
                    + "AND c.ColorName LIKE ? "
                    + "AND r.AvailableQuantity > ? "
                    + "ORDER BY ItemName "
                    + "OFFSET ? ROWS "
                    + "FETCH NEXT ? ROWS ONLY";

            ps = conn.prepareStatement(statement);
            ps.setString(1, "%" + resourceDTO.getItemName() + "%");
            ps.setString(2, "%" + resourceDTO.getColor() + "%");
            ps.setInt(3, 0);
            ps.setInt(4, pageNumber);
            ps.setInt(5, numOfRowsPerPage);

            result = ps.executeQuery();
            while (result.next()) {
                int itemID = result.getInt("ItemID");

                String itemName = result.getString("ItemName");
                String color = result.getString("ColorName");
                String category = result.getString("Category");
                String privacyName = result.getString("PrivacyName");

                int quantity = result.getInt("Quantity");
                int availableQuantity = result.getInt("AvailableQuantity");

                Date usingDate = result.getDate("UsingDate");
                Date endDate = result.getDate("EndDate");

                ResourceDTO el = new ResourceDTO(itemID, itemName, color, category, quantity, availableQuantity, privacyName, usingDate, endDate);
                if (theResourceList == null) {
                    theResourceList = new ArrayList();
                }
                theResourceList.add(el);
            }
        } finally {
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
        return theResourceList;
    }
}
