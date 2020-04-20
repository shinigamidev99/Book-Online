/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.PublisherDB;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Publisher;

/**
 *
 * @author Administrator
 */
public class AddPublisherController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try {
            if (request.getParameter("submit") != null) {
                String publicationId = request.getParameter("publicationId");
                String publicationName = request.getParameter("publicationName");
                String publicationAddress = request.getParameter("publicationAddress");

                if (!publicationId.isEmpty() && !publicationName.isEmpty()) {
                    PublisherDB pdb = new PublisherDB();
                    publicationId = "PUB_" + publicationId.toUpperCase();
                    pdb.insert(new Publisher(publicationId, publicationName, publicationAddress));
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
