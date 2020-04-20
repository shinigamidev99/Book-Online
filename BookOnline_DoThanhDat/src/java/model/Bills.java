/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.CustomerDB;

/**
 *
 * @author Administrator
 */
public class Bills {
    private int billId;
    private String customerId;
    private String dateCreated;
    private String address;
    private double total;
    private int status;

    public Bills() {
    }

    public Bills(int billId, String customerId, String dateCreated, String address, double total, int status) {
        this.billId = billId;
        this.customerId = customerId;
        this.dateCreated = dateCreated;
        this.address = address;
        this.total = total;
        this.status = status;
    }

    public Bills(String customerId, String address, double total, int status) {
        this.customerId = customerId;
        this.address = address;
        this.total = total;
        this.status = status;
    }

    public Bills(int billId, double total) {
        this.billId = billId;
        this.total = total;
    }

    public Bills(int billId, int status) {
        this.billId = billId;
        this.status = status;
    }
    
    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public Customer getCustomerById() throws Exception {
        return new CustomerDB().selectCustomerById(getCustomerId());
    }
}
