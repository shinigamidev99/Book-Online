/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.CustomerDB;
import encode.SHAHashing;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Administrator
 */
public class Account {

    private String username;
    private String password;
    private String role;

    public Account() {
    }

    public Account(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Customer getCustomerByUsername() throws Exception {
        return new CustomerDB().selectCustomerByUsername(getUsername());
    }

    public String toEncode() throws NoSuchAlgorithmException {
        return new SHAHashing().encode(getUsername().toLowerCase() + " " + getPassword() + " " + getRole());
    }
}
