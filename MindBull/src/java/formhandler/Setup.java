package formhandler;

import dao.MemberDao;
import dao.ExcerciseDao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Member;
import model.Excercise;

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

    List<Excercise> excercises = new ArrayList<>();
    Excercise excercise = new Excercise("Hip sideways", "This helps improve the motion capabilities of the hip and inner thigh muscles.");
    excercises.add(excercise);
    excercise.addMember(members.get(0));
    excercise.addMember(members.get(2));
    excercise.addMember(members.get(7));

    excercise = new Excercise("Hip backwards", "The backwards motion helps building strength in the front thigh muscles.");
    excercise.addMember(members.get(1));
    excercise.addMember(members.get(3));
    excercises.add(excercise);

    excercise = new Excercise("Knee stretch", "Strengthen the front thigh muscle.");
    excercises.add(excercise);
    
    ExcerciseDao.storeAllExcercises(excercises);
    MemberDao.storeAllMembers(members);
  }
}
