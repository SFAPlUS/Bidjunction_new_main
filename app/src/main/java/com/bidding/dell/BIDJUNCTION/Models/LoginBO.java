package com.bidding.dell.BIDJUNCTION.Models;

import com.bidding.dell.BIDJUNCTION.Models.CompanyBO;

import java.io.Serializable;

public class LoginBO implements Serializable {
    private String userid;
    private String usercode;
    private String DepartmentId;
    private String DepartmentName = null;
    private String username;
    private String useraddress = null;
    private String state = null;
    private String PS = null;
    private String majorclients = null;
    private String mainwrkarea = null;
    private String PANNO = null;
    private String GSTNO = null;
    private String websitename = null;
    private String email = null;
    private String contactperson1 = null;
    private String contactno1 = null;
    private String contactperson2 = null;
    private String contactno2 = null;
    private String dateofenrolment = null;
    private String password;
    private String Token = null;
    private boolean isactive;
    private String isemployee = null;
    private String isvendor = null;
    private String iscustomer = null;
    private String createdby = null;
    private String createdon = null;
    private String lastupdtby = null;
    private String lastupdton = null;
    private String roleid = null;
    private String companyid;
    CompanyBO Company;

    public CompanyBO getCompany() {
        return Company;
    }

    public void setCompany(CompanyBO company) {
        Company = company;
    }

    private String RoleInterfaceMap = null;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getDepartmentId() {
        return DepartmentId;
    }

    public void setDepartmentId(String departmentId) {
        DepartmentId = departmentId;
    }

    public String getDepartmentName() {
        return DepartmentName;
    }

    public void setDepartmentName(String departmentName) {
        DepartmentName = departmentName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUseraddress() {
        return useraddress;
    }

    public void setUseraddress(String useraddress) {
        this.useraddress = useraddress;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPS() {
        return PS;
    }

    public void setPS(String PS) {
        this.PS = PS;
    }

    public String getMajorclients() {
        return majorclients;
    }

    public void setMajorclients(String majorclients) {
        this.majorclients = majorclients;
    }

    public String getMainwrkarea() {
        return mainwrkarea;
    }

    public void setMainwrkarea(String mainwrkarea) {
        this.mainwrkarea = mainwrkarea;
    }

    public String getPANNO() {
        return PANNO;
    }

    public void setPANNO(String PANNO) {
        this.PANNO = PANNO;
    }

    public String getGSTNO() {
        return GSTNO;
    }

    public void setGSTNO(String GSTNO) {
        this.GSTNO = GSTNO;
    }

    public String getWebsitename() {
        return websitename;
    }

    public void setWebsitename(String websitename) {
        this.websitename = websitename;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactperson1() {
        return contactperson1;
    }

    public void setContactperson1(String contactperson1) {
        this.contactperson1 = contactperson1;
    }

    public String getContactno1() {
        return contactno1;
    }

    public void setContactno1(String contactno1) {
        this.contactno1 = contactno1;
    }

    public String getContactperson2() {
        return contactperson2;
    }

    public void setContactperson2(String contactperson2) {
        this.contactperson2 = contactperson2;
    }

    public String getContactno2() {
        return contactno2;
    }

    public void setContactno2(String contactno2) {
        this.contactno2 = contactno2;
    }

    public String getDateofenrolment() {
        return dateofenrolment;
    }

    public void setDateofenrolment(String dateofenrolment) {
        this.dateofenrolment = dateofenrolment;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }

    public boolean isIsactive() {
        return isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }

    public String getIsemployee() {
        return isemployee;
    }

    public void setIsemployee(String isemployee) {
        this.isemployee = isemployee;
    }

    public String getIsvendor() {
        return isvendor;
    }

    public void setIsvendor(String isvendor) {
        this.isvendor = isvendor;
    }

    public String getIscustomer() {
        return iscustomer;
    }

    public void setIscustomer(String iscustomer) {
        this.iscustomer = iscustomer;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public String getCreatedon() {
        return createdon;
    }

    public void setCreatedon(String createdon) {
        this.createdon = createdon;
    }

    public String getLastupdtby() {
        return lastupdtby;
    }

    public void setLastupdtby(String lastupdtby) {
        this.lastupdtby = lastupdtby;
    }

    public String getLastupdton() {
        return lastupdton;
    }

    public void setLastupdton(String lastupdton) {
        this.lastupdton = lastupdton;
    }

    public String getRoleid() {
        return roleid;
    }

    public void setRoleid(String roleid) {
        this.roleid = roleid;
    }

    public String getCompanyid() {
        return companyid;
    }

    public void setCompanyid(String companyid) {
        this.companyid = companyid;
    }

    public String getRoleInterfaceMap() {
        return RoleInterfaceMap;
    }

    public void setRoleInterfaceMap(String roleInterfaceMap) {
        RoleInterfaceMap = roleInterfaceMap;
    }
}