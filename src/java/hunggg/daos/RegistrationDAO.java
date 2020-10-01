/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hunggg.daos;

import hunggg.dtos.RegistrationDTO;
import hunggg.utils.DBConnector;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author giang
 */
public class RegistrationDAO implements Serializable {

    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet result = null;

    public RegistrationDAO() {

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

    // check to see if the email exists or not
    public boolean checkEmailExistence(String email)
            throws NamingException, SQLException {

        try {
            conn = DBConnector.getTheConnection();
            String statement = "SELECT UserID FROM tbl_Registration WHERE UserID = ?";

            ps = conn.prepareStatement(statement);
            ps.setString(1, email);
            result = ps.executeQuery();
            if (result.next()) {
                return true;
            }

        } finally {
            checkAndCloseConn();
        }
        return false;
    }

    // If the user logs in successfully , we need to find the role of 
    // the account to see which page the user is allowed to go in 
    public String getRoleNameFromUserID(String userID)
            throws NamingException, SQLException {

        try {
            conn = DBConnector.getTheConnection();
            String statement = "SELECT Role "
                    + "FROM tbl_Registration r , tbl_Role rs "
                    + "WHERE r.RoleID = rs.RoleID "
                    + "AND r.UserID = ?";

            ps = conn.prepareStatement(statement);
            ps.setString(1, userID);

            result = ps.executeQuery();
            if (result.next()) {
                return result.getString("Role");
            }
        } finally {
            checkAndCloseConn();
        }
        return null;
    }

    // check the parameters from the users to see if the account is existent or has rights to gain access
    public boolean gainAccess(String userID, String password, String statusName)
            throws NamingException, SQLException {

        try {
            conn = DBConnector.getTheConnection();
            String statement = "SELECT Fullname "
                    + "FROM tbl_Registration r , tbl_Status s "
                    + "WHERE s.StatusID = r.StatusID "
                    + "AND r.UserID = ? AND r.Password = ? "
                    + "AND s.Status = ?";

            ps = conn.prepareStatement(statement);
            ps.setString(1, userID);
            ps.setString(2, password);
            ps.setString(3, statusName);

            result = ps.executeQuery();
            if (result.next()) {
                return true;
            }
        } finally {
            checkAndCloseConn();
        }
        return false;
    }

    // After logging , I should have created the RegistrationDTO , but due to
    // old habits die hard , after the user logs in successfully , the fullname of the user will be searched
    // to say Welcome
    public RegistrationDTO getUserFullNameAndEmail(String userID)
            throws NamingException, SQLException {

        try {
            conn = DBConnector.getTheConnection();
            String statement = "SELECT Fullname "
                    + "FROM tbl_Registration "
                    + "WHERE UserID = ?";

            ps = conn.prepareStatement(statement);
            ps.setString(1, userID);

            result = ps.executeQuery();
            if (result.next()) {
                return new RegistrationDTO(userID, result.getString("Fullname"));
            }
        } finally {
            checkAndCloseConn();
        }
        return null;
    }

    public boolean activateTheAccount(String emailAddress) throws NamingException, SQLException {

        try {
            conn = DBConnector.getTheConnection();
            String statement = "UPDATE tbl_Registration SET StatusID = ? WHERE UserID = ?";

            ps = conn.prepareStatement(statement);
            ps.setInt(1, 1);
            ps.setString(2, emailAddress);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            }
        } finally {
            checkAndCloseConn();
        }
        return false;
    }

    public boolean insertNewUser(RegistrationDTO newUser)
            throws NamingException, SQLException {

        try {
            conn = DBConnector.getTheConnection();
            String statement = "INSERT INTO tbl_Registration(UserID,Password,Fullname,HomeAddress,PhoneNumber,StatusID,RoleID,CreateDate)"
                    + " VALUES(?,?,?,?,?,?,?,?)";

            ps = conn.prepareStatement(statement);

            ps.setString(1, newUser.getUserID());
            ps.setString(2, newUser.getPassword());
            ps.setString(3, newUser.getFullname());
            ps.setString(4, newUser.getHomeAddress());
            ps.setString(5, newUser.getPhoneNumber());
            ps.setInt(6, 3);
            ps.setInt(7, 3);
            ps.setDate(8, newUser.getCreateDate());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            }
        } finally {
            checkAndCloseConn();
        }
        return false;
    }
}
