/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hunggg.daos;

import hunggg.dtos.ResourceDTO;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author giang
 */
public class CartObjectDAO implements Serializable {

    private HashMap<ResourceDTO, Integer> customerCart;

    public CartObjectDAO() {

    }

    private ResourceDTO findResourceDTOWithID(int ID) {
        for (Map.Entry<ResourceDTO, Integer> entry : customerCart.entrySet()) {
            ResourceDTO key = entry.getKey();
            if (key.getItemID() == ID) {
                return key;
            }
        }
        return null;
    }

    public boolean deleteToCart(int itemID) {
        boolean result = false;
        if (findResourceDTOWithID(itemID) != null) {
            customerCart.remove(findResourceDTOWithID(itemID));
            result = true;
        }
        return result;
    }

    public boolean updateToCart(int itemID, int quantity) {

        boolean result = false;
        if (findResourceDTOWithID(itemID) != null) {
            customerCart.put(findResourceDTOWithID(itemID), quantity);
            result = true;
        }
        return result;
    }

    public void addToCart(ResourceDTO inputItem) {

        if (customerCart == null) {
            customerCart = new HashMap();
        }
        if (findResourceDTOWithID(inputItem.getItemID()) != null) {
            int quantity = customerCart.get(findResourceDTOWithID(inputItem.getItemID())) + 1;
            customerCart.put(findResourceDTOWithID(inputItem.getItemID()), quantity);
        } else {
            customerCart.put(inputItem, 1);
        }
    }

    public void removeAllToCart() {
        customerCart.clear();
        if (customerCart.isEmpty()) {
            customerCart = null;
        }
    }

    public HashMap<ResourceDTO, Integer> getCustomerCart() {
        return customerCart;
    }

    public void setCustomerCart(HashMap<ResourceDTO, Integer> customerCart) {
        this.customerCart = customerCart;
    }

}
