/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.sql.*;
import java.util.ArrayList;
import modelo.utilidades.Conexion;
import modelo.utilidades.Fecha;
import modelo.dto.PostDTO;
import modelo.utilidades.CrudDAO;

/**
 *
 * @author dadxc
 */
public class PostDAO implements CrudDAO<PostDTO> {

    private final static String SQL_CREATE = "INSERT INTO post (titulo, fecha_publicacion, contenido, usuario_id, palabras, multimedia) VALUES (?,?,?,?,?,?)";
    private final static String SQL_READ = "SELECT ps.*, us.nombre FROM post ps, usuario us WHERE ps.id = ? AND us.id = ps.usuario_id";
    private final static String SQL_UPDATE = "UPDATE post SET titulo = ?, contenido = ?, multimedia = ? WHERE id = ? ";
    private final static String SQL_DELETE = "DELETE FROM post WHERE id = ?";
    private final static String SQL_READALL = "SELECT ps.id, ps.titulo, ps.fecha_publicacion, left(ps.contenido,280) as contenido, ps.usuario_id, ps.palabras ,us.nombre FROM post ps, usuario us WHERE ps.usuario_id = us.id ORDER BY id DESC LIMIT ?";
    private final static String SQL_BUSCAR_PARA_PERFIL = "SELECT ps.id, ps.titulo, ps.fecha_publicacion, left(ps.contenido,280) as contenido, ps.usuario_id, ps.palabras, us.nombre FROM post ps, usuario us WHERE us.id = ? AND ps.usuario_id = us.id ORDER BY id DESC";
    private final static String SQL_BUSCAR = "SELECT ps.id, ps.titulo, ps.fecha_publicacion, left(ps.contenido,280) as contenido, ps.usuario_id, ps.palabras, us.nombre FROM post ps, usuario us WHERE ps.palabras LIKE '%?%' AND ps.usuario_id = us.id ORDER BY id DESC LIMIT 20";
    private final static String SQL_ELIMINAR_COMENTARIO = "DELETE FROM comentario WHERE post_id = ?";
    private final static String SQL_BUSCAR_TODO = "SELECT id, titulo, contenido, multimedia FROM post WHERE id = ?";
    private final static String SQL_CANTIDAD_POST = "select count(id) from post";

    public PostDTO objP;

    public PostDAO() {
        this.objP = null;
    }

    public PostDAO(PostDTO objP) {
        this.objP = objP;
    }

    public PostDTO getObjP() {
        return objP;
    }

    public void setObjP(PostDTO objP) {
        this.objP = objP;
    }

    @Override
    public PostDTO create(PostDTO obj) {
        Conexion conexion = new Conexion();
        try {
            conexion.conectar();
            PreparedStatement consulta = null;
            String comando = SQL_CREATE;
            consulta = conexion.getConexion().prepareStatement(comando);
            consulta.setString(1, obj.getTitulo());
            //mi rar la frecha
            long currentDateTime = System.currentTimeMillis();
            consulta.setDate(2, new Date(currentDateTime));
            consulta.setString(3, obj.getContenido());
            consulta.setInt(4, obj.getId());
            consulta.setString(5, obj.getPalabras());
            consulta.setString(6, obj.getMultimedia());
            consulta.execute();
            consulta.close();
            conexion.getConexion().close();
            conexion = null;
            return obj;
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }

    @Override
    public PostDTO read(PostDTO filter) {
        Conexion conexion = new Conexion();
        try {
            conexion.conectar();
            PreparedStatement consulta = conexion.getConexion().prepareStatement(SQL_READ);
            consulta.setInt(1, filter.getId());
            ResultSet datos = consulta.executeQuery();
            datos.next();
            //PostsDTO objPDTO = new PostsDTO(datos.getInt(1), datos.getString(2), new Fecha(), datos.getString(4), datos.getString(7), datos.getString(6));

            PostDTO objPDTO = new PostDTO();
            objPDTO.setId(datos.getInt(1));
            objPDTO.setTitulo(datos.getString(2));
            String[] fecha = datos.getString(3).split("-");
            objPDTO.setFecha_publicacion(new Fecha(Integer.parseInt(fecha[0]), Integer.parseInt(fecha[1]), Integer.parseInt(fecha[2])));
            objPDTO.setContenido(datos.getString(4));
            objPDTO.setPalabras(datos.getString(6));
            objPDTO.setMultimedia(datos.getString(7));
            objPDTO.setAutor(datos.getString(8));
            //aqui iria el usuario ID con el metodo buscar, to be continue

            //msj+=datos.getString(i)+";";
            datos.close();
            consulta.close();
            conexion.getConexion().close();
            return objPDTO;
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }

    @Override
    public void update(PostDTO obj) {
        Conexion conexion = new Conexion();
        try {
            conexion.conectar();
            PreparedStatement consulta = conexion.getConexion().prepareStatement(SQL_UPDATE);
            consulta.setString(1, obj.getTitulo());
            consulta.setString(2, obj.getContenido());
            consulta.setString(3, obj.getMultimedia());
            consulta.setInt(4, obj.getId());
            consulta.executeQuery();

            consulta.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            try {
                conexion.getConexion().close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    }

    @Override
    public void delete(PostDTO obj) {
        Conexion conexion = new Conexion();
        try {
            conexion.conectar();
            PreparedStatement consulta = conexion.getConexion().prepareStatement(SQL_DELETE);
            consulta.setInt(1, obj.getId());
            consulta.executeQuery();
            consulta.close();
            conexion.getConexion().close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public ArrayList<PostDTO> ReadAll(int n) {
        Conexion conexion = new Conexion();
        ArrayList<PostDTO> lista = new ArrayList<>();
        try {
            conexion.conectar();
            PreparedStatement consulta = null;
            String comando = SQL_READALL;
            consulta = conexion.getConexion().prepareStatement(comando);
            consulta.setInt(1, n);
            ResultSet datos = consulta.executeQuery();
            while (datos.next()) {
                PostDTO objPDTO = new PostDTO();
                objPDTO.setId(datos.getInt(1));
                objPDTO.setTitulo(datos.getString(2));
                String[] fecha = datos.getString(3).split("-");
                objPDTO.setFecha_publicacion(new Fecha(Integer.parseInt(fecha[0]), Integer.parseInt(fecha[1]), Integer.parseInt(fecha[2])));
                objPDTO.setContenido(datos.getString(4));
                objPDTO.setPalabras(datos.getString(6));
                objPDTO.setAutor(datos.getString(7));
                lista.add(objPDTO);
                //msj+=datos.getString(i)+";";
            }
            datos.close();
            consulta.close();
            conexion.getConexion().close();
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }

    public ArrayList<PostDTO> buscarParaPerfil(String id) {
        Conexion conexion = new Conexion();
        ArrayList<PostDTO> lista = new ArrayList<>();
        try {
            conexion.conectar();
            PreparedStatement consulta = conexion.getConexion().prepareStatement(SQL_BUSCAR_PARA_PERFIL);
            consulta.setInt(1, Integer.parseInt(id));
            ResultSet datos = consulta.executeQuery();

            //PostsDTO objPDTO = new PostsDTO(datos.getInt(1), datos.getString(2), new Fecha(), datos.getString(4), datos.getString(7), datos.getString(6));
            for (int i = 1; datos.next(); i++) {
                PostDTO objPDTO = new PostDTO();
                objPDTO.setId(datos.getInt(1));
                objPDTO.setTitulo(datos.getString(2));
                String[] fecha = datos.getString(3).split("-");
                objPDTO.setFecha_publicacion(new Fecha(Integer.parseInt(fecha[0]), Integer.parseInt(fecha[1]), Integer.parseInt(fecha[2])));
                objPDTO.setContenido(datos.getString(4));
                objPDTO.setPalabras(datos.getString(6));
                objPDTO.setAutor(datos.getString(7));
                //aqui iria el usuario ID con el metodo buscar, to be continue
                lista.add(objPDTO);
                //msj+=datos.getString(i)+";";
            }
            datos.close();
            consulta.close();
            conexion.getConexion().close();
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }

    public ArrayList<PostDTO> buscar(String palabra) {
        Conexion conexion = new Conexion();
        ArrayList<PostDTO> lista = new ArrayList<>();
        try {
            conexion.conectar();
            PreparedStatement consulta = conexion.getConexion().prepareStatement(SQL_BUSCAR);
            consulta.setString(1, palabra.toUpperCase());
            ResultSet datos = consulta.executeQuery();
            //datos.next();            
            //PostsDTO objPDTO = new PostsDTO(datos.getInt(1), datos.getString(2), new Fecha(), datos.getString(4), datos.getString(7), datos.getString(6));
            for (int i = 1; datos.next(); i++) {
                PostDTO objPDTO = new PostDTO();
                objPDTO.setId(datos.getInt(1));
                objPDTO.setTitulo(datos.getString(2));
                String[] fecha = datos.getString(3).split("-");
                objPDTO.setFecha_publicacion(new Fecha(Integer.parseInt(fecha[0]), Integer.parseInt(fecha[1]), Integer.parseInt(fecha[2])));
                objPDTO.setContenido(datos.getString(4));
                objPDTO.setPalabras(datos.getString(6));
                objPDTO.setAutor(datos.getString(7));
                //aqui iria el usuario ID con el metodo buscar, to be continue
                lista.add(objPDTO);
                //msj+=datos.getString(i)+";";
            }
            datos.close();
            consulta.close();
            conexion.getConexion().close();
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }

    public void eliminarComentario(PostDTO objPDTO) {
        Conexion conexion = new Conexion();
        try {
            conexion.conectar();
            PreparedStatement consulta = conexion.getConexion().prepareStatement(SQL_ELIMINAR_COMENTARIO);
            consulta.setInt(1, objPDTO.getId());
            consulta.executeQuery();
            consulta.close();
            conexion.getConexion().close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public PostDTO buscarTodo(String id) {
        Conexion conexion = new Conexion();
        try {
            conexion.conectar();
            PreparedStatement consulta = conexion.getConexion().prepareStatement(SQL_BUSCAR_TODO);
            consulta.setInt(1, Integer.parseInt(id));
            ResultSet datos = consulta.executeQuery();
            datos.next();

            PostDTO objPDTO = new PostDTO();
            objPDTO.setId(datos.getInt(1));
            objPDTO.setTitulo(datos.getString(2));
            objPDTO.setContenido(datos.getString(3));
            objPDTO.setMultimedia(datos.getString(4));

            datos.close();
            consulta.close();
            conexion.getConexion().close();
            return objPDTO;
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }

    public int cantidadPost() {
        Conexion conexion = new Conexion();
        int x = 0;
        try {
            conexion.conectar();
            PreparedStatement consulta = conexion.getConexion().prepareStatement(SQL_CANTIDAD_POST);
            ResultSet datos = consulta.executeQuery();
            datos.next();
            x = datos.getInt(1);
            datos.close();
            consulta.close();
            conexion.getConexion().close();
            return x;
        } catch (SQLException ex) {
            System.out.println(ex);
            return x;
        }
    }

}
