/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.AuthorDB;
import dao.BookDiscountDB;
import dao.CategoryDB;
import dao.PublisherDB;
import java.text.DecimalFormat;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class Book {

    private String bookId;
    private String categoryId;
    private String publicationId;
    private String title;
    private String publicationDate;
    private String price;
    private String quantity;
    private String notes;
    private String image;
    private String pod;
    private String authorId;

    public Book() {
    }

    public Book(String bookId, String categoryId, String publicationId, String title, String publicationDate, String price, String quantity, String notes, String image, String pod, String authorId) {
        this.bookId = bookId;
        this.categoryId = categoryId;
        this.publicationId = publicationId;
        this.title = title;
        this.publicationDate = publicationDate;
        this.price = price;
        this.quantity = quantity;
        this.notes = notes;
        this.image = image;
        this.pod = pod;
        this.authorId = authorId;
    }

    public Book(String bookId, String categoryId, String publicationId, String title, String publicationDate, String price, String quantity, String notes, String image, String authorId) {
        this.bookId = bookId;
        this.categoryId = categoryId;
        this.publicationId = publicationId;
        this.title = title;
        this.publicationDate = publicationDate;
        this.price = price;
        this.quantity = quantity;
        this.notes = notes;
        this.image = image;
        this.authorId = authorId;
    }

    public Book(String bookId, String quantity) {
        this.bookId = bookId;
        this.quantity = quantity;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(String publicationId) {
        this.publicationId = publicationId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPod() {
        return pod;
    }

    public void setPod(String pod) {
        this.pod = pod;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public List<Category> getCategoryNameById() throws Exception {
        return new CategoryDB().selectByCategoryId(getCategoryId());
    }

    public List<Publisher> getPublicationNameById() throws Exception {
        return new PublisherDB().selectBublisherById(getPublicationId());
    }

    public List<Author> getAuthorNameById() throws Exception {
        return new AuthorDB().selectAuthorNameById(getAuthorId());
    }

    public String getPriceReal() throws Exception {
        String priceReal = getPrice();
        List<BookDiscount> list = new BookDiscountDB().selectBookDiscount();
        for (BookDiscount bookDiscount : list) {
            if (bookDiscount.getBookId().equals(getBookId())) {
                priceReal = new DecimalFormat("#.0######").format(Double.parseDouble(getPrice()) * (100 - bookDiscount.getDiscount()) / 100);
            }
        }
        return priceReal;
    }

    public List<BookDiscount> getAllBookDiscount() throws Exception {
        return new BookDiscountDB().select();
    }

    public BookDiscount getDiscount() throws Exception {
        return new BookDiscountDB().selectDiscountByBookId(getBookId());
    }
}
