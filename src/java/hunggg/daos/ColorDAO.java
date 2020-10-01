/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hunggg.daos;

import hunggg.utils.DBConnector;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;

/**
 *
 * @author giang
 */
public class ColorDAO implements Serializable {

    private ArrayList<String> theColorList;
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet result;

    public ColorDAO() {

    }

    public ArrayList<String> getTheColorList()
            throws SQLException, NamingException {
        try {
            conn = DBConnector.getTheConnection();
            String statement = "SELECT ColorName FROM tbl_Color";

            ps = conn.prepareStatement(statement);
            result = ps.executeQuery();
            while (result.next()) {
                String colorName = result.getString("ColorName");
                if (theColorList == null) {
                    theColorList = new ArrayList();
                }
                theColorList.add(colorName);
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
        return theColorList;
    }
}
