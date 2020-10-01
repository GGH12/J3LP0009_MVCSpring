/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hunggg.utils;

/**
 *
 * @author Hung
 */
public class UserInputValidator {

    public boolean checkUserEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    public boolean checkPassword(String password) {
        return !(password.length() < 5 || password.length() > 255);
    }

    public boolean checkConfirmPassword(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    public boolean checkFullname(String fullname) {
        String regex = "^[a-zA-Z_ ]{5,225}$";
        return fullname.matches(regex);
    }

    public boolean checkPhoneNumber(String phoneNumber) {
        String regex = "^[0-9]{10,11}$";
        return phoneNumber.matches(regex);
    }

    public boolean checkAddress(String address) {
        return (address.length() >= 5 && address.length() <= 225);
    }
}
