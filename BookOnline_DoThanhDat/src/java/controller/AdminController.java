/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AuthorDB;
import dao.BillDB;
import dao.BillDentailDB;
import dao.BookDB;
import dao.BookDiscountDB;
import dao.CategoryDB;
import dao.PublisherDB;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Book;
import model.Parameter;

/**
 *
 * @author Administrator
 */
public class AdminController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            BookDB bdb = new BookDB();
            
            if (request.getParameter("action") != null) {
                BookDiscountDB bddb = new BookDiscountDB();
                String bookId = request.getParameter("bookId");

                if (bddb.selectDiscountByBookId(bookId) != null) {
                    bddb.delete(bookId);
                }
                bdb.delete(bookId);
            }

            Enumeration enumeration = request.getParameterNames();
            List<Parameter> parameters = new ArrayList<>();
            while (enumeration.hasMoreElements()) {
                String parameterName = enumeration.nextElement().toString();
                if (!parameterName.equalsIgnoreCase("action") && !parameterName.equalsIgnoreCase("bookId")) {
                    parameters.add(new Parameter(parameterName, request.getParameter(parameterName)));
                }
            }
            request.setAttribute("parameters", parameters);

            CategoryDB cdb = new CategoryDB();
            request.setAttribute("categories", cdb.select());

            AuthorDB audb = new AuthorDB();
            request.setAttribute("authors", audb.select());

            PublisherDB pubdb = new PublisherDB();
            request.setAttribute("publishers", pubdb.select());
            
            List<Book> books = bdb.select();
            HashMap<String, Integer> map = new HashMap<>();

            for (Book book : books) {
                String id = book.getBookId().split("_")[0];
                if(map.containsKey(id)) {
                    map.put(id, map.get(id) + 1);
                } else {
                    map.put(id, 1);
                }
            }
            request.setAttribute("bookIdAlivable", map);
            
            BillDentailDB billDentailDB = new BillDentailDB();
            request.setAttribute("billDentails", billDentailDB.select());
            
            BillDB billdb = new BillDB();
            request.setAttribute("bills", billdb.select());

            request.getRequestDispatcher("/view/admin.jsp").forward(request, response);
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
