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
import modelo.utilidades.Fecha;
import modelo.dto.PostDTO;
import modelo.dao.PostDAO;

/**
 *
 * @author dadxc
 */
public class PostsCTO extends HttpServlet {

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
            case "crear":{    
                String palabras = "";
                for(int i = 1; i<6;i++){
                    String msj =  request.getParameter("palabras"+i);

                    if(!"".equals(msj)){
                        if(!"".equals(request.getParameter("palabras"+(i+1))) && i<5)
                        palabras += msj.toUpperCase()+", ";
                        else
                        palabras += msj.toUpperCase(); 
                    }

                }
                String multimedia = request.getParameter("multimedia");
                String[] video = null;
                if(multimedia.contains("youtube")){
                video = multimedia.split("v=");
                    if(video[1].contains("&"))
                    {
                        String aux[] = video[1].split("&");
                        video[1] = aux[0];
                    }
                PostDAO objP = new PostDAO();
                PostDTO objPDTO = new PostDTO(request.getParameter("titulo"), new Fecha(),request.getParameter("contenido"),palabras,Integer.parseInt(request.getParameter("pasteldepato")),video[1]); 
                objP.create(objPDTO);
                }else{
                PostDAO objP = new PostDAO();
                PostDTO objPDTO = new PostDTO(request.getParameter("titulo"), new Fecha(),request.getParameter("contenido"),palabras,Integer.parseInt(request.getParameter("pasteldepato")),null); 
                objP.create(objPDTO);
                }
                request.getRequestDispatcher("inicio.jsp").forward(request, response);
                break;
            }
            
            case "enviar":{
                String id = request.getParameter("id");
                                
                PostDAO objPDAO = new PostDAO();
                PostDTO objPDTO = objPDAO.buscarTodo(id);
                
                request.setAttribute("id", id);
                request.setAttribute("titulo", objPDTO.getTitulo());
                request.setAttribute("contenido", objPDTO.getContenido());
                if(objPDTO.getMultimedia()!=null){
                request.setAttribute("multimedia", "https://www.youtube.com/watch?v="+objPDTO.getMultimedia());
                }else{
                request.setAttribute("multimedia", "Aqui poner el link de un video de youtube"); 
                }
                request.getRequestDispatcher("ActualizarPost.jsp").forward(request, response);
                break;
            }
            case "actualizar":{
                String id_usuario = request.getParameter("ahiru");
                String id = request.getParameter("id");
                String titulo = request.getParameter("titulo");
                String contenido = request.getParameter("contenido");
                String multimedia = request.getParameter("multimedia");
                
                String[] video = multimedia.split("v=");
                PostDAO objPDAO = new PostDAO();
                PostDTO objPDTO = new PostDTO();
                objPDTO.setId(Integer.parseInt(id));
                objPDTO.setTitulo(titulo);
                objPDTO.setContenido(contenido);
                objPDTO.setMultimedia(video[1]);
                objPDAO.update(objPDTO);
                
                
                ArrayList<PostDTO> lista = objPDAO.buscarParaPerfil(id_usuario);
                request.setAttribute("lista_pato", lista);
                request.getRequestDispatcher("Perfil.jsp").forward(request, response);
                
                
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
