/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hunggg.daos;

import hunggg.dtos.RequestDTO;
import hunggg.utils.DBConnector;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author Hung
 */
public class RequestDAO implements Serializable {

    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet result = null;

    public RequestDAO() {
    }

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

    public boolean addCartToDatabaseAndGetNewestRequestID(RequestDTO requestDTO)
            throws NamingException, SQLException {

        try {
            conn = DBConnector.getTheConnection();
            String statement = "INSERT INTO tbl_Request(UserID, Request_Date) VALUES(?,?)";

            ps = conn.prepareStatement(statement);
            ps.setString(1, requestDTO.getUserID());
            ps.setDate(2, requestDTO.getRequestDate());

            int affectedRow = ps.executeUpdate();
            if (affectedRow > 0) {
                return true;
            }

        } finally {
            checkAndCloseConn();
        }
        return false;
    }

    public int getNewestRequestID()
            throws NamingException, SQLException {

        int requestID = -1;
        try {
            conn = DBConnector.getTheConnection();
            String statement = "SELECT MAX(Request_ID) AS LastRequestID FROM tbl_Request";

            ps = conn.prepareStatement(statement);
            result = ps.executeQuery();

            if (result.next()) {
                requestID = result.getInt("LastRequestID");
            }
        } finally {
            checkAndCloseConn();
        }
        return requestID;
    }
}
