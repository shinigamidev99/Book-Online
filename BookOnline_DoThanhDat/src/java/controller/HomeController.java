/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.BookDB;
import dao.BookDiscountDB;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrator
 */
public class HomeController extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            HttpSession session = request.getSession();
            session.removeAttribute("register");
            session.removeAttribute("error");
            
            if(session.getAttribute("payment") != null) {
                session.removeAttribute("cart");
                session.removeAttribute("payment");
            }
            
            if (request.getAttribute("categories") == null && request.getAttribute("publishers") == null && request.getAttribute("authors") == null) {
                request.getRequestDispatcher("/header").forward(request, response);
            } else {
                if (request.getParameter("isSearch") != null || request.getParameter("btnSearch") != null) {
                    request.getRequestDispatcher("/search-list").forward(request, response);
                } else if (request.getParameter("sale") != null) {
                    request.getRequestDispatcher("/sale").forward(request, response);
                } else {
                    BookDB bdb = new BookDB();
                    BookDiscountDB bddb = new BookDiscountDB();
                    request.setAttribute("books", bdb.selectTop(6));
                    request.setAttribute("discounts", bddb.selectBookDiscount());
                    request.getRequestDispatcher("/view/home.jsp").forward(request, response);
                }
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
