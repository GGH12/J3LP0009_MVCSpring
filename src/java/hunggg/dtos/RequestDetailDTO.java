/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hunggg.dtos;

import java.io.Serializable;

/**
 *
 * @author Hung
 */
public class RequestDetailDTO implements Serializable {

    private int requestDetailID;
    private int requestID;
    private int itemID;
    private int quantity;
    private int statusID;

    public RequestDetailDTO() {
    }

    public RequestDetailDTO(int requestDetailID, int requestID, int itemID, int quantity, int statusID) {

        this.requestDetailID = requestDetailID;
        this.requestID = requestID;
        this.itemID = itemID;
        this.quantity = quantity;
        this.statusID = statusID;
    }

    public int getRequestDetailID() {
        return requestDetailID;
    }

    public void setRequestDetailID(int requestDetailID) {
        this.requestDetailID = requestDetailID;
    }

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }
    
    
}
