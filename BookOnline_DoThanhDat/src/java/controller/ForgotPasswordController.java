/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import SendMail.SendGmail;
import dao.AccountDB;
import dao.CustomerDB;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Customer;

/**
 *
 * @author Administrator
 */
public class ForgotPasswordController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            if (request.getParameter("submit") != null) {
                SendGmail send = new SendGmail();
                CustomerDB cdb = new CustomerDB();
                AccountDB adb = new AccountDB();

                String mail = request.getParameter("mail");

                List<Customer> list = cdb.selectByEmail(mail);

                if (list.isEmpty()) {
                    request.setAttribute("mailStatus", "Email is not correct!");
                } else {
                    String inforMail = "This is information of your account:\n";
                    List<Account> acc = adb.selectByEmail(mail);
                    inforMail += "Your Username: " + acc.get(0).getUsername() + "\n" + "Your Password: " + acc.get(0).getPassword();
                    request.setAttribute("mailStatus", "We sended to your mail. Please check your mail!");
                    send.sendText(mail, "Password of your account", inforMail);
                }
            }
            request.getRequestDispatcher("/view/forgot_password.jsp").forward(request, response);
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
