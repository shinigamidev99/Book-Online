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
public class ManagementPublisher extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            PublisherDB pdb = new PublisherDB();
            String action = request.getParameter("action");
            String publicationId = request.getParameter("publicationId");

            if (action.equalsIgnoreCase("delete")) {
                pdb.delete(publicationId);
            } else if (action.equalsIgnoreCase("update") && request.getParameter("submit") != null) {
                String publicationName = request.getParameter("publicationName");
                String publicationAddress = request.getParameter("publicationAddress");

                if (!publicationName.isEmpty()) {
                    pdb.update(new Publisher(publicationId, publicationName, publicationAddress));
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
