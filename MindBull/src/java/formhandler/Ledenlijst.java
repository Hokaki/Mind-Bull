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
@WebServlet(name = "LedenlijstHandler", urlPatterns = {"/ledenlijsthandler"})
public class Ledenlijst extends HttpServlet {


  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    String edit = request.getParameter("edit");
    String nextPage = null;
    if ( edit != null ) {
      Long id = Long.parseLong(edit);
      request.setAttribute("id", id);
      nextPage = "wijziglid.jsp";
    }
    request.getRequestDispatcher(nextPage).forward(request, response);
  }
}
