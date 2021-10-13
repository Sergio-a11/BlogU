/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.dao.PostDAO;
import modelo.dto.PostDTO;
import modelo.dto.UsuarioDTO;
import modelo.dao.UsuarioDAO;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Sergio Cruz
 */
public class InicioCTO extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        String opc = request.getParameter("opc");
        UsuarioDAO objU = new UsuarioDAO();
        switch(opc)
        {
            case "iniciar":
            {
                UsuarioDTO objUser = new UsuarioDTO();
                objUser.setEmail(request.getParameter("email"));
                objUser.setContrasena(asegurarClave(request.getParameter("contrasena")));
                objUser = objU.read(objUser);
                if(objUser != null)
                {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", objUser);                    
                    request.getRequestDispatcher("inicio.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("InicioFallido.html").forward(request, response);
                }            
                break;
            }
            case "salir":
            {
                HttpSession session = request.getSession();
                session.invalidate();
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;
            }
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
        processRequest(request, response);
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
        processRequest(request, response);        
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

    
    
    private String asegurarClave(String clave)
    {
        String texto=clave;
        String encriptMD5=DigestUtils.md5Hex(texto);
        return encriptMD5;
    }
}
