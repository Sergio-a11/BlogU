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
import modelo.dao.UsuarioDAO;
import modelo.dto.UsuarioDTO;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author dadxc
 */
public class PerfilCTO extends HttpServlet {

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
        switch(opc){
            case "perfil" :{
                String id_usuario = request.getParameter("id");
                PostDAO objPDAO = new PostDAO();
                ArrayList<PostDTO> lista = objPDAO.buscarParaPerfil(id_usuario);
                request.setAttribute("lista_pato", lista);
                request.getRequestDispatcher("Perfil.jsp").forward(request, response);
                break;
            }       
            case "cambio" :{
                String id = request.getParameter("ahiru");
                String nombre = request.getParameter("usuario");

                UsuarioDAO objUDAO = new UsuarioDAO();
                UsuarioDTO objUDTO = objUDAO.buscarID(Integer.parseInt(id));
                objUDTO.setNombre(nombre);
                objUDAO.update(objUDTO);

                PostDAO objPDAO = new PostDAO();
                ArrayList<PostDTO> lista = objPDAO.buscarParaPerfil(id);

                request.setAttribute("lista_pato", lista);
                
                objUDTO = objUDAO.buscarID(Integer.parseInt(id));
                HttpSession session = request.getSession();
                session.setAttribute("user", objUDTO);
                request.getRequestDispatcher("Perfil.jsp").forward(request, response);
                break;
            }
            case "borrar":{
                String id_usuario = request.getParameter("ahiru");
                String id_post = request.getParameter("id_post");
                PostDAO objPDAO = new PostDAO();                
                PostDTO objPDTO = new PostDTO();
                objPDTO.setId(Integer.parseInt(id_post));
                objPDAO.eliminarComentario(objPDTO);
                objPDAO.delete(objPDTO);
                ArrayList<PostDTO> lista = objPDAO.buscarParaPerfil(id_usuario);
                request.setAttribute("lista_pato", lista);
                request.getRequestDispatcher("Perfil.jsp").forward(request, response);
                break;
            }
            case "vBorrar":{
                String id_usuario = request.getParameter("ahiru");
                String id_post = request.getParameter("id_post");
                request.setAttribute("ahiru", id_usuario);
                request.setAttribute("id_post", id_post);
                request.getRequestDispatcher("vBorrar.jsp").forward(request, response);
                break;
            }
            case "eliminar":{
                String id = request.getParameter("ahiru");
                String contrasena = request.getParameter("contrasena");
                
                UsuarioDAO objUDAO = new UsuarioDAO();
                UsuarioDTO objUDTO = objUDAO.buscarID(Integer.parseInt(id));
                objUDTO.setContrasena(asegurarClave(contrasena));
                objUDTO = objUDAO.read(objUDTO);
                
                if(objUDTO.getId()==Integer.parseInt(id)){
                    PostDAO objPDAO = new PostDAO(); 
                    ArrayList<PostDTO> lista = objPDAO.buscarParaPerfil(id);
                    for(int i=0; i<lista.size();i++){
                    objUDAO.eliminarComentario(lista.get(i).getId());
                    }
                    objUDAO.eliminarPost(Integer.parseInt(id));
                    objUDAO.delete(objUDTO);
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }else{
                    request.getRequestDispatcher("inicio.jsp").forward(request, response);
                }
                
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

    
    private String asegurarClave(String clave)
    {
        String texto=clave;
        String encriptMD5=DigestUtils.md5Hex(texto);
        return encriptMD5;
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
