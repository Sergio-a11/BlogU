/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.utilidades.Mail;
import modelo.dao.UsuarioDAO;
import modelo.dto.UsuarioDTO;

/**
 *
 * @author PC
 */
public class VerificacionCTO extends HttpServlet {

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
        String nombre = request.getParameter("ahiru");
        String codigo = request.getParameter("codec");
        
        String aux2 = nombre.replaceAll("_", " ");             
      
        UsuarioDAO objUDAO = new UsuarioDAO();
        UsuarioDTO objUDTO = objUDAO.verificar(aux2, codigo);
        
        if(objUDTO!=null){
           objUDAO.create(objUDTO);           
           Mail objM = new Mail();
           objM.enviarCorreo(objUDTO.getEmail(), "Verificacion de Registro BlogUDP Exitosa", "Gracias por registrarte en nuestra pagina, esperamos que disfrutes tu experiencia.");
           UsuarioDTO objUDTO2=objUDAO.read(objUDTO);
           HttpSession session = request.getSession();
           session.setAttribute("user", objUDTO2);
           objUDAO.borrar(objUDTO2);
           request.getRequestDispatcher("Verificado.jsp").forward(request, response);
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

}
