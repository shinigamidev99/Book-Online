/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AuthorDB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Author;

/**
 *
 * @author Administrator
 */
public class AddAuthorController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            if (request.getParameter("submit") != null) {
                String authorId = request.getParameter("authorId");
                String authorName = request.getParameter("authorName");
                String authorAddress = request.getParameter("authorAddress");

                if (!authorId.isEmpty() && !authorName.isEmpty()) {
                    AuthorDB audb = new AuthorDB();
                    authorId = authorId.toUpperCase();
                    audb.insert(new Author(authorId, authorName, authorAddress));
                }  
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
