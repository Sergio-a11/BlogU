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
import modelo.dao.ComentarioDAO;
import modelo.dto.ComentarioDTO;
import modelo.dao.PostDAO;
import modelo.dto.PostDTO;

/**
 *
 * @author dadxc
 */
public class VerPublicacionCTO extends HttpServlet {

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
        PostDAO objPDAO = new PostDAO();
        String id = request.getParameter("id");
        PostDTO objPDTO = new PostDTO();
        objPDTO.setId(Integer.parseInt(id));
        objPDTO = objPDAO.read(objPDTO);
        
        ComentarioDTO objCDTO = new ComentarioDTO();
        ComentarioDAO objCDAO = new ComentarioDAO();
        objCDAO.setObjC(objCDTO);
        
        
        if(!id.equals(""))
        {
            objCDTO.setPost_id(Integer.parseInt(id));
            ArrayList<ComentarioDTO> comentarios = objCDAO.buscar(objCDTO);
            request.setAttribute("ver", objPDTO);
            request.setAttribute("comentario_pato", comentarios);
        }
        else
        {
            ComentarioDTO comentario = new ComentarioDTO("No se encontro la publicaiï¿œn", 0, "Sistema");
            request.setAttribute("comentario_pato",comentario); 
        }
        
        
        String opc = request.getParameter("opc");
        switch(opc)
        {
            case "iniciado":
            {
                request.getRequestDispatcher("VerPublicacionInicio.jsp").forward(request, response); 
                break;
            }
            case "cerrado":
            {
               request.getRequestDispatcher("VerPublicacion.jsp").forward(request, response);  
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

}
