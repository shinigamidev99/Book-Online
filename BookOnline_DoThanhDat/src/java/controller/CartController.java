/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Cart;
import model.Item;

/**
 *
 * @author Administrator
 */
public class CartController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String action = request.getParameter("action");
            if (action != null) {
                if (action.equalsIgnoreCase("add")) {
                    String id = request.getParameter("id");
                    String name = request.getParameter("name");
                    double price = Double.valueOf(request.getParameter("price"));
                    String image = request.getParameter("image");
                    if (request.getParameter("quantity") != null) {
                        int quantity = Integer.valueOf(request.getParameter("quantity"));
                        HttpSession session = request.getSession(true);
                        if (session.getAttribute("cart") == null) {
                            session.setAttribute("cart", new Cart());
                        }
                        Cart c = (Cart) session.getAttribute("cart");
                        c.add(new Item(c.getItems().size(), id, name, price, quantity, image), quantity);
                        session.setAttribute("cart", c);
                    }
                }

                if (action.equalsIgnoreCase("delete")) {
                    String id = request.getParameter("id");
                    HttpSession session = request.getSession();

                    Cart c = (Cart) session.getAttribute("cart");
                    c.remove(id);
                    session.setAttribute("cart", c);
                }

                if (action.equalsIgnoreCase("update")) {
                    String id = request.getParameter("id");
                    int quantity = Integer.valueOf(request.getParameter("quantity"));
                    HttpSession session = request.getSession();

                    Cart c = (Cart) session.getAttribute("cart");
                    c.update(id, quantity);
                    session.setAttribute("cart", c);
                }
            }
            request.getRequestDispatcher("/view/cart.jsp").forward(request, response);
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
