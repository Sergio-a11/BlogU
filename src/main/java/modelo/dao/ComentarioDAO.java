/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.dto.ComentarioDTO;
import modelo.utilidades.Conexion;
import modelo.utilidades.CrudDAO;

/**
 *
 * @author Sergio Cruz
 */
public class ComentarioDAO {

    private final static String SQL_CREATE ="INSERT INTO comentario (cuerpo, post_id, usuario_id) VALUES (?,?,?)";
    private final static String SQL_READ ="SELECT co.cuerpo, co.post_id, us.nombre FROM comentario co, usuario us WHERE post_id = ? AND co.usuario_id = us.id ORDER BY co.id DESC";
    private final static String SQL_ANTI_SPAM ="SELECT cuerpo FROM comentario WHERE cuerpo = ? AND post_id=? AND usuario_id=?";
    ComentarioDTO objC ;

    public ComentarioDAO(ComentarioDTO objC) {
        this.objC = objC;
    }

    public ComentarioDAO() {
        this.objC = null;
    }

    public ComentarioDTO getObjC() {
        return objC;
    }

    public void setObjC(ComentarioDTO objC) {
        this.objC = objC;
    }

    public ComentarioDTO create(ComentarioDTO objCDTO) {
        Conexion conexion = new Conexion();

        try {
            conexion.conectar();
            PreparedStatement consulta = null;
            String comando = SQL_CREATE;
            consulta = conexion.getConexion().prepareStatement(comando);
            consulta.setString(1, objCDTO.getCuerpo());
            consulta.setInt(2, objCDTO.getPost_id());
            consulta.setInt(3, objCDTO.getUsuario_id());
            if (!antiSpam(objCDTO.getCuerpo(), objCDTO.getPost_id(), objCDTO.getUsuario_id())) {
                consulta.execute();
            }
            consulta.close();
            conexion.getConexion().close();
            return objC;
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }

    public ArrayList<ComentarioDTO> buscar(ComentarioDTO objCDTO) {
        Conexion conexion = new Conexion();
        ComentarioDTO objC2 = null;
        ArrayList<ComentarioDTO> lista = new ArrayList<>();
        try {
            conexion.conectar();
            PreparedStatement consulta = conexion.getConexion().prepareStatement(SQL_READ);
            consulta.setInt(1, objCDTO.getPost_id());
            ResultSet datos = consulta.executeQuery();
            while (datos.next()) {
                objC2 = new ComentarioDTO(datos.getString(1), datos.getInt(2), datos.getString(3));
                lista.add(objC2);
            }

            //datos.next();
            //objC2 = new ComentarioDTO(datos.getString(1), datos.getInt(2), datos.getString(3));
            datos.close();
            consulta.close();
            return lista;
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            try {
                conexion.getConexion().close();
            } catch (SQLException ex) {
                System.out.println(ex);
                return null;
            }
        }
        return null;
    }

    public boolean antiSpam(String comparar, int post, int usuario_id) {
        Conexion conexion = new Conexion();
        ComentarioDTO objC2 = null;
        try {
            conexion.conectar();
            PreparedStatement consulta = conexion.getConexion().prepareStatement(SQL_ANTI_SPAM);
            consulta.setString(1, comparar);
            consulta.setInt(2, post);
            consulta.setInt(3, usuario_id);
            ResultSet datos = consulta.executeQuery();
            datos.next();
            if (datos.getString(1).equals(comparar)) {
                datos.close();
                consulta.close();
                return true;
            }
            datos.close();
            consulta.close();
            return false;
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            try {
                conexion.getConexion().close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        return false;
    }

}
