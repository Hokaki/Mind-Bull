/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formhandler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author huub
 */
@WebServlet(name = "MenuHandler", urlPatterns = {"/menuhandler"})
public class Menu extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    String command = request.getParameter("command");
    String nextPageAddress = null;
    switch (command) {
      case "0":
        nextPageAddress = "ledenlijst.jsp";
        break;
      case "1":
        nextPageAddress = "teamlijst.jsp";
        break;
      case "2":
        nextPageAddress = "wedstrijdkalender.jsp";
        break;
    }
    request.getRequestDispatcher(nextPageAddress).forward(request, response);
  }
}
