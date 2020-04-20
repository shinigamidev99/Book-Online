/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.BillDB;
import dao.BillDentailDB;
import dao.BookDB;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.BillDentail;
import model.Bills;
import model.Book;
import model.Cart;
import model.Customer;
import model.Item;

/**
 *
 * @author Administrator
 */
public class PayController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("cart");
            if (cart != null) {
                Account account = (Account) session.getAttribute("login");

                List<Item> items = cart.getItems();
                Customer customer = account.getCustomerByUsername();

                BillDB billdb = new BillDB();
                BillDentailDB billDentaildb = new BillDentailDB();
                BookDB bdb = new BookDB();

                billdb.insert(new Bills(customer.getCustomerId(), customer.getStreet() + ", " + customer.getCity(), cart.getTotalAmount(), 0));

                for (Item item : items) {
                    String bookId = item.getId();
                    int quantity = item.getQuantity();
                    billDentaildb.insert(new BillDentail(item.getNumber(), bookId, quantity, item.getPrice()));
                    bdb.updateQuantity(new Book(bookId, (Integer.parseInt(bdb.selectBookByBookId(bookId).getQuantity()) - quantity) + ""));
                }

                session.setAttribute("payment", "Payment success");
                response.sendRedirect("cart");
            }
        } catch (Exception ex) {
            response.getWriter().println(ex);
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
