/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.BookDB;
import dao.BookDiscountDB;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Book;
import model.BookDiscount;

/**
 *
 * @author Administrator
 */
public class UpdateController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try {
            if (request.getParameter("submit") != null) {
                BookDB bdb = new BookDB();

                String bookId = request.getParameter("bookId");
                String categoryId = request.getParameter("categoryId");
                String title = request.getParameter("title");
                String publicationDate = request.getParameter("publicationDate");
                String publicationId = request.getParameter("publicationId");
                String price = request.getParameter("price");
                String quantity = request.getParameter("quantity");
                String notes = request.getParameter("notes");
                String image = request.getParameter("image");
                image = image.isEmpty() ? bdb.selectBookByBookId(bookId).getImage().split("\\\\|/")[1] : image.split("\\\\|/")[image.split("\\\\|/").length - 1];
                String authorId = request.getParameter("authorId");
                String discount = request.getParameter("discount");
                discount = discount.isEmpty() ? "0" : discount;
                String startDate = request.getParameter("startDate");
                String endDate = request.getParameter("endDate");

                bdb.update(new Book(bookId, categoryId, publicationId, title, publicationDate, price, quantity, notes, "image/" + image, notes, authorId));

                BookDiscountDB bddb = new BookDiscountDB();
                if (Double.parseDouble(discount) > 0 && !discount.isEmpty()) {
                    String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                    startDate = startDate.isEmpty() ? date : startDate;
                    endDate = endDate.isEmpty() ? date : endDate;
                    if (bddb.selectDiscountByBookId(bookId) != null) {
                        bddb.update(new BookDiscount(bookId, Double.parseDouble(discount), startDate, endDate));
                    } else {
                        bddb.insert(new BookDiscount(bookId, Double.parseDouble(discount), startDate, endDate));
                    }
                } else {
                    bddb.delete(bookId);
                }
            }

            response.sendRedirect("admin");
        } catch (Exception ex) {
            response.getWriter().println(ex);
            System.out.println(ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
