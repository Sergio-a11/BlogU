/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import control.FeedCTO;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.utilidades.Conexion;
import modelo.dto.UsuarioDTO;
import modelo.utilidades.CrudDAO;

/**
 *
 * @author Sergio Cruz
 */
public class UsuarioDAO implements CrudDAO<UsuarioDTO> {

    private final static String SQL_CREATE = "INSERT INTO public.usuario (nombre, email, contrasena) VALUES (?,?,?)";
    private final static String SQL_READ = "SELECT * FROM usuario WHERE email = ? AND contrasena = ?";
    private final static String SQL_UPDATE = "UPDATE usuario SET nombre = ? WHERE id = ?";
    private final static String SQL_DELETE = "DELETE FROM usuario WHERE id = ?";
    private final static String SQL_CREATEV = "INSERT INTO public.verificar (nombre, email, contrasena,codigo,fecha) VALUES (?,?,?,?,?);";
    private final static String SQL_VERIFICARTIEMPO = "SELECT id, current_date::DATE - fecha::DATE as diff from verificar";
    private final static String SQL_ELIMINARVERIFICAR = "DELETE FROM verificar WHERE id = ?";
    private final static String SQL_BUSCARID = "SELECT id, nombre, email FROM usuario WHERE id=?";
    private final static String SQL_VERIFICAR = "SELECT nombre, email, contrasena FROM verificar WHERE nombre= ? AND codigo = ?";
    private final static String SQL_BORRARVERIFICAR = "DELETE FROM verificar where email = ?";
    private final static String SQL_USUARIOREPETIDO = "SELECT email FROM usuario WHERE email = ?";
    private final static String SQL_ELIMINARPOST = "DELETE FROM post WHERE usuario_id = ?";
    private final static String SQL_ELIMINARCOMENTARIO = "DELETE FROM comentario WHERE post_id = ?";

    UsuarioDTO objU;

    public UsuarioDAO(UsuarioDTO objU) {
        this.objU = objU;
    }

    public UsuarioDAO() {
        this.objU = null;
    }

    public UsuarioDTO getObjU() {
        return objU;
    }

    public void setObjU(UsuarioDTO objU) {
        this.objU = objU;
    }

    @Override
    public UsuarioDTO create(UsuarioDTO objU2) {
        Conexion conexion = new Conexion();
        try {
            conexion.conectar();
            PreparedStatement consulta = null;
            String comando = SQL_CREATE;
            consulta = conexion.getConexion().prepareStatement(comando);
            consulta.setString(1, objU2.getNombre());
            consulta.setString(2, objU2.getEmail());
            consulta.setString(3, objU2.getContrasena());
            consulta.execute();
            consulta.close();
            conexion.getConexion().close();
            return objU2;
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }

    @Override
    public UsuarioDTO read(UsuarioDTO filter) {
        Conexion conexion = new Conexion();
        UsuarioDTO objU2 = null;
        try {
            conexion.conectar();
            PreparedStatement consulta = conexion.getConexion().prepareStatement(SQL_READ);
            consulta.setString(1, filter.getEmail());
            consulta.setString(2, filter.getContrasena());
            ResultSet datos = consulta.executeQuery();
            datos.next();
            objU2 = new UsuarioDTO(datos.getInt(1), datos.getString(2), datos.getString(3), datos.getString(4));
            datos.close();
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
        return objU2;
    }

    @Override
    public void update(UsuarioDTO obj) {
        Conexion conexion = new Conexion();
        try {
            conexion.conectar();
            PreparedStatement consulta = conexion.getConexion().prepareStatement(SQL_UPDATE);
            consulta.setString(1, obj.getNombre());
            consulta.setInt(2, obj.getId());
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
    public void delete(UsuarioDTO obj) {
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

    public UsuarioDTO createV(String codigo) {
        Conexion conexion = new Conexion();
        try {
            conexion.conectar();
            PreparedStatement consulta = null;
            conexion.conectar();
            String comando = SQL_CREATEV;
            consulta = conexion.getConexion().prepareStatement(comando);
            consulta.setString(1, objU.getNombre());
            consulta.setString(2, objU.getEmail());
            consulta.setString(3, objU.getContrasena());
            consulta.setString(4, codigo);
            long currentDateTime = System.currentTimeMillis();
            consulta.setDate(5, new Date(currentDateTime));
            consulta.execute();
            consulta.close();
            conexion.getConexion().close();
            return objU;
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }

    public void verificarTiempo() {
        Conexion conexion = new Conexion();
        try {
            conexion.conectar();
            PreparedStatement consulta = conexion.getConexion().prepareStatement(SQL_VERIFICARTIEMPO);
            ResultSet datos = consulta.executeQuery();
            while (datos.next()) {
                if (datos.getInt(2) > 15) {
                    eliminarVerificar(datos.getInt(1));
                }
            }
            datos.close();
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

    public void eliminarVerificar(int i) {
        Conexion conexion = new Conexion();
        try {
            conexion.conectar();
            PreparedStatement consulta = conexion.getConexion().prepareStatement(SQL_ELIMINARVERIFICAR);
            consulta.setInt(1, i);
            ResultSet datos = consulta.executeQuery();
            datos.close();
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

    public UsuarioDTO buscarID(int id) {
        Conexion conexion = new Conexion();
        UsuarioDTO objU2 = null;
        try {
            conexion.conectar();
            PreparedStatement consulta = conexion.getConexion().prepareStatement(SQL_BUSCARID);
            consulta.setInt(1, id);
            ResultSet datos = consulta.executeQuery();
            datos.next();
            objU2 = new UsuarioDTO(datos.getInt(1), datos.getString(2), datos.getString(3));
            datos.close();
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
        return objU2;
    }

    public UsuarioDTO verificar(String nombre, String codigo) {
        Conexion conexion = new Conexion();
        UsuarioDTO objU2 = null;
        try {
            conexion.conectar();
            PreparedStatement consulta = conexion.getConexion().prepareStatement(SQL_VERIFICAR);
            consulta.setString(1, nombre);
            consulta.setString(2, codigo);
            ResultSet datos = consulta.executeQuery();
            datos.next();
            objU2 = new UsuarioDTO(datos.getString(1), datos.getString(2), datos.getString(3));
            datos.close();
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
        return objU2;
    }

    public void borrar(UsuarioDTO objU2) {
        Conexion conexion = new Conexion();
        try {
            conexion.conectar();
            PreparedStatement consulta = conexion.getConexion().prepareStatement(SQL_BORRARVERIFICAR);
            consulta.setString(1, objU2.getEmail());
            ResultSet datos = consulta.executeQuery();
            datos.close();
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

    public boolean usuarioRepetido(String palabra) {
        boolean repe = false;
        Conexion conexion = new Conexion();
        try {
            conexion.conectar();
            PreparedStatement consulta = conexion.getConexion().prepareStatement(SQL_USUARIOREPETIDO);
            consulta.setString(1, palabra);
            ResultSet datos = consulta.executeQuery();
            //System.out.println(palabra);
            //.out.println(datos.getString(1));
            if (datos.next()) {
                datos.close();
                consulta.close();
                repe = true;//repetido
            } else {
                datos.close();
                consulta.close();
                repe = false;//son diferentes
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            try {
                conexion.getConexion().close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        return repe;
    }

    public void eliminarPost(int id) {
        Conexion conexion = new Conexion();
        try {
            conexion.conectar();
            PreparedStatement consulta = conexion.getConexion().prepareStatement(SQL_ELIMINARPOST);
            consulta.setInt(1, id);
            consulta.executeQuery();
            consulta.close();
            conexion.getConexion().close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void eliminarComentario(int id) {
        Conexion conexion = new Conexion();
        try {
            conexion.conectar();
            PreparedStatement consulta = conexion.getConexion().prepareStatement(SQL_ELIMINARCOMENTARIO);
            consulta.setInt(1, id);
            consulta.executeQuery();
            consulta.close();
            conexion.getConexion().close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

}
