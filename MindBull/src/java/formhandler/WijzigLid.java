/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formhandler;

import dao.MemberDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Member;
import support.Dispatcher;

/**
 *
 * @author huub
 */
@WebServlet(name = "WijzigLidHandler", urlPatterns = {"/wijziglidhandler"})
public class WijzigLid extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    HttpSession httpSession = request.getSession();
    Member member = (Member)httpSession.getAttribute("member");
   
    String firstName = request.getParameter("firstname");
    String lastName = request.getParameter("lastname");

    if (firstName != null && !("".equals(firstName))) {
      member.setFirstName(firstName);
    }
    if (lastName != null && !("".equals(lastName))) {
      member.setLastName(lastName);
    }
    MemberDao.storeMember(member);
    Dispatcher.dispatch("ledenlijst.jsp", request, response);
  }
}
