/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.DecimalFormat;

/**
 *
 * @author Administrator
 */
public class BookDiscount {
    private String bookId;
    private double discount;
    private String startDate;
    private String endDate;

    public BookDiscount() {
    }

    public BookDiscount(String bookId, double discount, String startDate, String endDate) {
        this.bookId = bookId;
        this.discount = discount;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    
    public String getDiscountFormat() {
        return new DecimalFormat("#.#######").format(getDiscount());
    }
}
