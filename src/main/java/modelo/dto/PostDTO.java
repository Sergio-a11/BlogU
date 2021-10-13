/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dto;

import java.sql.Date;
import java.time.LocalDate;
import modelo.utilidades.Fecha;

/**
 *
 * @author dadxc
 */
public class PostDTO {
    public int id;
    public String titulo,contenido,autor,palabras,multimedia;
    public Fecha fecha_publicacion;

    public PostDTO(int id, String titulo, Fecha fecha_publicacion,String contenido, String autor, String palabras, String multimedia) {
        this.id = id;
        this.titulo = titulo;
        this.contenido = contenido;
        this.fecha_publicacion = fecha_publicacion;
        this.autor = autor;
        this.palabras = palabras;
        this.multimedia = multimedia;
    }
    
    public PostDTO(String titulo, Fecha fecha_publicacion,String contenido, String palabras, int id_usuario, String multimedia) {
        this.titulo = titulo;
        this.contenido = contenido;
        this.fecha_publicacion = fecha_publicacion;
        this.palabras = palabras;
        this.id = id_usuario;
        this.multimedia = multimedia;
    }
    
    public PostDTO() {
        this.id = 0;
        this.titulo = "";
        this.contenido = "";
        this.fecha_publicacion = new Fecha();
        this.autor = "";
        this.palabras = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Fecha getFecha_publicacion() {
        return fecha_publicacion;
    }

    public void setFecha_publicacion(Fecha fecha_publicacion) {
        this.fecha_publicacion = fecha_publicacion;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getPalabras() {
        return palabras;
    }

    public void setPalabras(String palabras) {
        this.palabras = palabras;
    }

    public String getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(String multimedia) {
        this.multimedia = multimedia;
    }
    
    @Override
    public String toString() {
        return "PostsDTO{" + "id=" + id + ", titulo=" + titulo + ", contenido=" + contenido + ", autor=" + autor + ", palabras=" + palabras + ", fecha_publicacion=" + fecha_publicacion + '}';
    }

    
}
