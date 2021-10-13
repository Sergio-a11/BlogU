/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dto;

/**
 *
 * @author Sergio Cruz
 */
public class ComentarioDTO {
    int id, post_id, usuario_id;
    String cuerpo, autor;

    
    
    public ComentarioDTO(String cuerpo, int post_id, int usuario_id) {
        this.post_id = post_id;
        this.usuario_id = usuario_id;
        this.cuerpo = cuerpo;
    }
    
    public ComentarioDTO(String cuerpo, int post_id, String autor) {
        this.post_id = post_id;
        this.cuerpo = cuerpo;
        this.autor = autor;
    }

    public ComentarioDTO() {
        this.id = 0;
        this.post_id = 0;
        this.usuario_id = 0;
        this.cuerpo = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String Cuerpo) {
        this.cuerpo = Cuerpo;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
    
    
    
    @Override
    public String toString() {
        return "ComentarioDTO{" + "id=" + id + ", post_id=" + post_id + ", usuario_id=" + usuario_id + ", cuerpo=" + cuerpo + '}';
    }
    
    
}
