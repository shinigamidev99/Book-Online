/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.BookDiscountDB;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Parameter;

/**
 *
 * @author Administrator
 */
public class SaleController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            BookDiscountDB bddb = new BookDiscountDB();
            request.setAttribute("discounts", bddb.selectBookDiscount());

            Enumeration enumeration = request.getParameterNames();
            List<Parameter> parameters = new ArrayList<>();
            while (enumeration.hasMoreElements()) {
                String parameterName = enumeration.nextElement().toString();
                parameters.add(new Parameter(parameterName, request.getParameter(parameterName)));
            }
            request.setAttribute("parameters", parameters);
            request.getRequestDispatcher("/view/home.jsp").forward(request, response);
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
