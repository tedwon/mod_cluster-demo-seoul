package org.jboss.example;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
   private final String userID = "a";
   private final String password = "a";

   protected void doPost(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

      String user = request.getParameter("user");
      String pwd = request.getParameter("pwd");

      if (userID.equals(user) && password.equals(pwd)) {
         HttpSession session = request.getSession();
         final String sessionCookieName = request.getServletContext().getSessionCookieConfig().getName();
         System.out.println("[LoginServlet] " + sessionCookieName + "=" + session.getId());

         session.setAttribute("user", user + "-session-attribute");
         //setting session to expiry in 30 mins
         session.setMaxInactiveInterval(30 * 60);
         Cookie userName = new Cookie("user", user);
         userName.setMaxAge(30 * 60);
         response.addCookie(userName);

         response.sendRedirect("LoginSuccess.jsp");
      } else {
         RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
         PrintWriter out = response.getWriter();
         out.println("<font color=red>Either user name or password is wrong.</font>");
         rd.include(request, response);
      }
   }
}