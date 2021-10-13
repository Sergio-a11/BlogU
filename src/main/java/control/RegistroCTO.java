/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.utilidades.Mail;
import modelo.dto.UsuarioDTO;
import modelo.dao.UsuarioDAO;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Sergio Cruz
 */
public class RegistroCTO extends HttpServlet {

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
        String nombre = request.getParameter("usuario");
        String email = request.getParameter("email");
        String contrasena = asegurarClave(request.getParameter("contrasena"));
        String codigo = asegurarClave(email);
        
        String aux2 = nombre.replaceAll("\\s+", "_");
                
        UsuarioDAO objU = new UsuarioDAO(new UsuarioDTO(nombre, email, contrasena));
        objU.verificarTiempo();
        if(objU.usuarioRepetido(email) == true){
            request.getRequestDispatcher("index.jsp").forward(request, response); 
        }else{ 
            Mail objM = new Mail();
            objM.enviarCorreo(email, "Verificacion de Registro BlogUDP", "Gracias por registrarte con nuestra pagina BLOGUDP, por favor verifica tu cuenta haciendo click en el siguiente enlace: https://blogudp.herokuapp.com/VerificacionCTO?ahiru="+aux2+"&codec="+codigo);
            UsuarioDTO objUDTO = objU.createV(codigo);    
            request.getRequestDispatcher("VerificarR.html").forward(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
