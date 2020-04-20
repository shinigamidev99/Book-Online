package controller;

import dao.AccountDB;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author Administrator
 */
public class LoginController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            HttpSession session = request.getSession(true);

            if (request.getParameter("submit") != null) {
                if (username.isEmpty() || password.isEmpty()) {
                    session.setAttribute("error", "Please fill all text box!");
                    request.getRequestDispatcher("/view/login.jsp").forward(request, response);
                } else {
                    boolean isCorrect = false;
                    String role = "";

                    AccountDB adb = new AccountDB();
                    List<Account> list = adb.select();

                    for (Account account : list) {
                        if (username.equalsIgnoreCase(account.getUsername()) && password.equals(account.getPassword())) {
                            role = account.getRole();
                            isCorrect = true;
                            break;
                        }
                    }

                    if (isCorrect) {
                        session.setAttribute("login", new Account(username, password, role));
                        session.removeAttribute("error");

                        if (request.getParameter("remember") != null) {
                            Cookie c = new Cookie("user", new Account(username, password, role).toEncode());
                            c.setMaxAge(24 * 60 * 60 * 7);
                            response.addCookie(c);
                        }

                        if (role.equalsIgnoreCase("admin")) {
                            response.sendRedirect("admin");
                        } else {
                            response.sendRedirect("home");
                        }
                    } else {
                        session.setAttribute("error", "Username or password is not correct!");
                        request.getRequestDispatcher("/view/login.jsp").forward(request, response);
                    }
                }
            } else {
                request.getRequestDispatcher("/view/login.jsp").forward(request, response);
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
