/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AccountDB;
import dao.CustomerDB;
import dao.UserDB;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Customer;
import model.User;

/**
 *
 * @author Administrator
 */
public class RegisterController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try {
            HttpSession session = request.getSession();

            if (request.getParameter("submit") != null) {
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String rePassword = request.getParameter("rePassword");
                String email = request.getParameter("email");
                String dob = request.getParameter("dob");
                String phone = request.getParameter("phoneNumber");
                String street = request.getParameter("street");
                String city = request.getParameter("city");
                String gender = request.getParameter("gender");
                if (!username.isEmpty() && !password.isEmpty() && !rePassword.isEmpty() && !email.isEmpty() && !phone.isEmpty() && !street.isEmpty()) {
                    AccountDB adb = new AccountDB();
                    List<Account> list = adb.select();
                    for (Account account : list) {
                        if (username.equalsIgnoreCase(account.getUsername())) {
                            session.setAttribute("error", "Username is exist!");
                            request.getRequestDispatcher("/view/register.jsp").forward(request, response);
                            break;
                        }
                    }

                    if (!password.equals(rePassword)) {
                        session.setAttribute("error", "Password is not same confirm password!");
                        request.getRequestDispatcher("/view/register.jsp").forward(request, response);
                    } else {
                        CustomerDB cdb = new CustomerDB();
                        UserDB udb = new UserDB();

                        if (cdb.insert(new Customer("USER", firstName, lastName, phone, email, street, city, phone, Boolean.parseBoolean(gender))) > 0 && adb.insert(new Account(username, password, "user")) > 0) {
                            udb.insert(new User(username, "USER"));
                            session.removeAttribute("error");
                            session.setAttribute("register", "Register done, now you can login!");
                            response.sendRedirect("login");
                        } else {
                            session.setAttribute("error", "Insert Error!");
                            request.getRequestDispatcher("/view/register.jsp").forward(request, response);
                        }
                    }

                } else {
                    session.setAttribute("error", "Please fill all atribute have (*)!");
                    request.getRequestDispatcher("/view/register.jsp").forward(request, response);
                }
            } else {
                session.removeAttribute("error");
                request.getRequestDispatcher("/view/register.jsp").forward(request, response);
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
