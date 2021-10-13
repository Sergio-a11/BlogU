/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.utilidades;

import java.util.List;

/**
 *
 * @author PC
 */
public interface CrudDAO <T> {
    
    public T create(T obj);    
    public T read(T filter);
    public void update(T obj);
    public void delete(T obj);
}
