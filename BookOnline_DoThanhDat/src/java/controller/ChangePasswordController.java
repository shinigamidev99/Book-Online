/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AccountDB;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author Administrator
 */
public class ChangePasswordController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            HttpSession session = request.getSession();
            if (request.getParameter("submit") != null) {
                Account account = (Account) session.getAttribute("login");
                if (!request.getParameter("currentPassword").equals(account.getPassword())) {
                    session.setAttribute("error", "Current password is wrong!");
                } else if (!request.getParameter("newPassword").equals(request.getParameter("rePassword"))) {
                    session.setAttribute("error", "New password diferent confirm password!");
                } else {
                    AccountDB adb = new AccountDB();
                    adb.updatePassword(new Account(account.getUsername(), request.getParameter("newPassword")));
                    session.setAttribute("error", "no");
                }
            }
            request.getRequestDispatcher("/view/change_password.jsp").forward(request, response);
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
