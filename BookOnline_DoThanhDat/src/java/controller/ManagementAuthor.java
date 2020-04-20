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
public class ManagementAuthor extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            AuthorDB pdb = new AuthorDB();
            String action = request.getParameter("action");
            String authorId = request.getParameter("authorId");

            if (action.equalsIgnoreCase("delete")) {
                pdb.delete(authorId);
            } else if (action.equalsIgnoreCase("update") && request.getParameter("submit") != null) {
                String authorName = request.getParameter("authorName");
                String authorAddress = request.getParameter("authorAddress");

                if (!authorName.isEmpty()) {
                    pdb.update(new Author(authorId, authorName, authorAddress));
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
