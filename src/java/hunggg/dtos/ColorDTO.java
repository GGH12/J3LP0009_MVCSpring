/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hunggg.dtos;

import java.io.Serializable;

/**
 *
 * @author giang
 */
public class ColorDTO implements Serializable {
    
    private int colorID ;
    private String colorName ;
    public ColorDTO(){
        
    }
    
    public ColorDTO(int colorID , String colorName){
        this.colorID = colorID ;
        this.colorName = colorName ;
    }

    public int getColorID() {
        return colorID;
    }

    public void setColorID(int colorID) {
        this.colorID = colorID;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }
    
}
