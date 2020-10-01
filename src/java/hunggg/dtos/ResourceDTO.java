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
 * @author giang
 */
public class ResourceDTO implements Serializable {

    private int itemID;
    private String itemName;
    private String color;
    private String category, privacyName;
    private int quantity;
    private int availableQuantity, privacyID;
    private Date usingDate, endDate;

    public ResourceDTO() {

    }

    public ResourceDTO(int itemID, String itemName, String color, String category, int availableQuantity) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.color = color;
        this.category = category;
        this.availableQuantity = availableQuantity;
    }

    public ResourceDTO(String itemName, String colorName) {
        this.itemName = itemName;
        this.color = colorName;
    }

    public ResourceDTO(int itemID, String itemName, String color, String category, int quantity, int availableQuantity, Date usingDate, Date endDate) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.color = color;
        this.category = category;
        this.quantity = quantity;
        this.availableQuantity = availableQuantity;
        this.usingDate = usingDate;
        this.endDate = endDate;
    }

    public ResourceDTO(int itemID, String itemName, String color, String category, int quantity, int availableQuantity, String privacyName, Date usingDate, Date endDate) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.color = color;
        this.category = category;
        this.quantity = quantity;
        this.availableQuantity = availableQuantity;
        this.privacyName = privacyName;
        this.usingDate = usingDate;
        this.endDate = endDate;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public int getPrivacyID() {
        return privacyID;
    }

    public void setPrivacyID(int privacyID) {
        this.privacyID = privacyID;
    }

    public Date getUsingDate() {
        return usingDate;
    }

    public void setUsingDate(Date usingDate) {
        this.usingDate = usingDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getPrivacyName() {
        return privacyName;
    }

    public void setPrivacyName(String privacyName) {
        this.privacyName = privacyName;
    }

}
