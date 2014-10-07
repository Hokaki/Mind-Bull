/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formhandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.User;
import org.hibernate.Session;
import support.HibernateUtil;

/**
 *
 * @author Mohamed
 */
@WebServlet(name = "loginhandler", urlPatterns = {"/loginhandler"})
public class LoginController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

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
        
        User user = new User();
        
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        
        List<String> errors = new ArrayList<>();

        try {
            errors = validate(user);
            boolean b = checkLogin(user);
            if(b){
            RequestDispatcher rs = request.getRequestDispatcher("menu.jsp");
            rs.forward(request, response);
            }
            else{
                if (!errors.isEmpty()) {
                    request.setAttribute("errors", errors);
                }
            
            RequestDispatcher rs = request.getRequestDispatcher("login.jsp");
            rs.forward(request, response);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        
    }
    
    public boolean checkLogin(User user) throws ClassNotFoundException, SQLException{
        boolean b;
        //loading driver
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mindbull-club", "root", "root");
        PreparedStatement ps = con.prepareStatement("Select * FROM user WHERE username=? and password=?");
        ps.setString(1, user.getUsername());
        ps.setString(2, user.getPassword());
        ResultSet rs = ps.executeQuery();
        b = rs.next();
    
        return b;
    }
    
    List<String> errors = new ArrayList<>();
        
    public List<String> validate(User user) throws ClassNotFoundException, SQLException{
        
        if(!errors.isEmpty()){
            errors.clear();
        }
        
        String username=user.getUsername();
        if ( username== null || username.trim().isEmpty()) {
			errors.add("Username must be filled in");
        }
        
        String password=user.getPassword();
        if ( password== null || password.trim().isEmpty()) {
			errors.add("password must be filled in");
		}
        if(!checkLogin(user)){
            errors.add("Unsuccesvol login");
        }
        
        return errors;
     }
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
