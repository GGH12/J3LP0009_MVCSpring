/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hunggg.dtos;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Hung
 */
public class RequestDTO implements Serializable {

    private int requestID;
    private String userID;
    private Date requestDate;

    public RequestDTO() {
    }

    public RequestDTO(int requestID, String userID, Date requestDate) {
        this.requestID = requestID;
        this.userID = userID;
        this.requestDate = requestDate;
    }

    public RequestDTO(String userID, Date requestDate) {
        this.userID = userID;
        this.requestDate = requestDate;
    }

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

}
