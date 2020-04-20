/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.BookDB;

/**
 *
 * @author Administrator
 */
public class BillDentail {
    private int billId;
    private int itemId;
    private String bookId;
    private int quantity;
    private double price;

    public BillDentail() {
    }

    public BillDentail(int billId, int itemId, String bookId, int quantity, double price) {
        this.billId = billId;
        this.itemId = itemId;
        this.bookId = bookId;
        this.quantity = quantity;
        this.price = price;
    }

    public BillDentail(int itemId, String bookId, int quantity, double price) {
        this.itemId = itemId;
        this.bookId = bookId;
        this.quantity = quantity;
        this.price = price;
    }

    public BillDentail(int billId, int itemId) {
        this.billId = billId;
        this.itemId = itemId;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public Book getBookById() throws Exception {
        return new BookDB().selectBookByBookId(getBookId());
    }
}
