package io.undertow.clusteringdemo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

/**
 * @author Stuart Douglas
 */
@WebServlet(urlPatterns = "sticky")
public class SimpleServlet extends HttpServlet {

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

      String sessionCookieName = req.getServletContext().getSessionCookieConfig().getName();

      final String serverName = "Server " + System.getProperty("server.name") + " ";
      resp.getWriter().write(serverName);

      HttpSession session = req.getSession(true);
      Integer count = (Integer) session.getAttribute("count");
      if (count == null) {
         count = 1;
      }
      final String reqCount = "Request Count " + count + " ";
      resp.getWriter().write(reqCount);

      final String sessionId = sessionCookieName + "=" + session.getId();
      resp.getWriter().write(sessionId);

      session.setAttribute("count", ++count);

      System.out.println(serverName + reqCount + sessionId);
   }
}
