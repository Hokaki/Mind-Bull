/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package support;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author huub
 */
public class Dispatcher {
  
  public static void dispatch(String address, 
          HttpServletRequest request, HttpServletResponse response) 
          throws ServletException, IOException {
    request.getRequestDispatcher(address).forward(request, response);
  }
}
