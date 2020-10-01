/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hunggg.dtos;

import java.io.Serializable;
import java.sql.Date;
import com.restfb.types.User;

/**
 *
 * @author giang
 */
public class RegistrationDTO implements Serializable {

    private String userID, password, homeAddress, phoneNumber, roleName;

    private String fullname;
    private int StatusID, RoleID;
    private Date createDate;

    public RegistrationDTO() {

    }

    public RegistrationDTO(String userID, String fullname) {
        this.userID = userID;
        this.fullname = fullname;
    }

    public RegistrationDTO(GoogleDTO googleDTO) {
        this.userID = googleDTO.getEmail();
        this.fullname = googleDTO.getName();
    }

    public RegistrationDTO(User facebookUser) {
        this.userID = facebookUser.getEmail();
        this.fullname = facebookUser.getName();
    }

    public RegistrationDTO(String userID, String password, String fullname, String homeAddress, String phoneNumber, String roleName, Date createDate) {
        this.userID = userID;
        this.password = password;
        this.fullname = fullname;
        this.homeAddress = homeAddress;
        this.phoneNumber = phoneNumber;
        this.roleName = roleName;
        this.createDate = createDate;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getStatusID() {
        return StatusID;
    }

    public void setStatusID(int StatusID) {
        this.StatusID = StatusID;
    }

    public int getRoleID() {
        return RoleID;
    }

    public void setRoleID(int RoleID) {
        this.RoleID = RoleID;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
