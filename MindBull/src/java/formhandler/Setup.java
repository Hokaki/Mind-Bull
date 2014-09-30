/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formhandler;

import dao.MemberDao;
import dao.TeamDao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Member;
import model.Team;

/**
 *
 * @author huub
 */
@WebServlet(name = "SetupHandler", urlPatterns = {"/setuphandler"})
public class Setup extends HttpServlet {

  /**
   * Handles the HTTP <code>POST</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    String command = request.getParameter("command");
    String nextPageAddress = "error.jsp";
    switch (command) {
      case "0":
        nextPageAddress = "index.jsp";
        break;
      case "1":
        dbSetup();
        nextPageAddress = "menu.jsp";
        System.out.println("Setting up database OK");
        break;
    }
    request.getRequestDispatcher(nextPageAddress).forward(request, response);
  }

  /**
   * Returns a short description of the servlet.
   *
   * @return a String containing servlet description
   */
  @Override
  public String getServletInfo() {
    return "Setup database";
  }

  private void dbSetup() {
    List<Member> members = new ArrayList<>();
    Member member = new Member("Adrie", "Aardnoot", null);
    members.add(member);
    member = new Member("Brutus", "Braaf", null);
    members.add(member);
    member = new Member("Charlotte", "Chocola", null);
    members.add(member);
    member = new Member("Dirk", "Draaikont", null);
    members.add(member);
    member = new Member("Elsbeth", "Everzwijn", null);
    members.add(member);
    member = new Member("Erik", "Everzwijn", null);
    members.add(member);
    member = new Member("Eduard", "Everzwijn", null);
    members.add(member);
    member = new Member("Frits", "Fabriek", null);
    members.add(member);

    List<Team> teams = new ArrayList<>();
    Team team = new Team("Les champions du monde", "Du pain, du vin, du jeu de boules!");
    teams.add(team);
    team.addMember(members.get(0));
    team.addMember(members.get(2));
    team.addMember(members.get(7));

    team = new Team("Les champignons du monde", "Go, go, go!");
    team.addMember(members.get(1));
    team.addMember(members.get(3));
    teams.add(team);

    team = new Team("Comme des Francais", "Alors, enfants du jeu de boules!");
    teams.add(team);
    
    TeamDao.storeAllTeams(teams);
    MemberDao.storeAllMembers(members);
  }
}
