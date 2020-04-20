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
import model.BillDentail;
import model.Bills;
import model.Book;

/**
 *
 * @author Administrator
 */
public class OrderController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            BillDentailDB billDentailDB = new BillDentailDB();
            BillDB billdb = new BillDB();
            BookDB bdb = new BookDB();

            if (request.getParameter("action").equalsIgnoreCase("delete")) {
                int itemId = Integer.parseInt(request.getParameter("itemId"));
                String bookId = request.getParameter("bookId");
                int billId = Integer.parseInt(request.getParameter("billId"));
                int quantity = Integer.parseInt(request.getParameter("quantity"));

                double total = billdb.selectBillByBillId(billId).getTotal() - quantity * billDentailDB.selectBillDentailByBillIdAndItemId(new BillDentail(billId, itemId)).getPrice();

                billdb.updateTotal(new Bills(billId, total));
                billDentailDB.delete(itemId, billId);
                bdb.updateQuantity(new Book(bookId, (Integer.parseInt(bdb.selectBookByBookId(bookId).getQuantity()) + quantity) + ""));
            }
            if (request.getParameter("action").equalsIgnoreCase("status")) {
                int status = Integer.parseInt(request.getParameter("status"));
                int billId = Integer.parseInt(request.getParameter("billId"));

                if (status == -1) {
                    List<BillDentail> list = billDentailDB.selectBillDentailByBillId(billId);
                    for (BillDentail billDentail : list) {
                        String bookId = billDentail.getBookId();
                        int quantity = Integer.parseInt(bdb.selectBookByBookId(bookId).getQuantity()) + billDentail.getQuantity();
                        bdb.updateQuantity(new Book(bookId, quantity + ""));
                    }
                }

                if (status == 1) {
                    //send mail
                }
                billdb.updateStatus(new Bills(billId, status));
            }

            response.sendRedirect("admin");
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
