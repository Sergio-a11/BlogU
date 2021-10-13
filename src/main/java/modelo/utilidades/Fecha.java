/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.utilidades;

import java.util.Calendar;
import java.util.Locale;

/**
 *
 * @author Usuario
 */
public class Fecha {
    private int dd, mm, aa;

    /**
     *Constructor parametrico
     * @param dd dia
     * @param mm mes 
     * @param aa año
     */
    public Fecha(int dd, int mm, int aa) 
    {
        this.dd = dd;
        this.mm = mm;
        this.aa = aa;
    }
    
    /**
     *Constructor basico
     */
    public Fecha() 
    {
        Calendar fecha = Calendar.getInstance();
        this.dd = fecha.get(Calendar.DAY_OF_MONTH);
        this.mm = fecha.get(Calendar.MONTH);
        this.aa = fecha.get(Calendar.YEAR);
    }

    /**
     *retorna el dia de la fecha
     * @return int
     */
    public int getDd() {
        return dd;
    }

    /**
     *retorna el mes de la fecha
     * @return int
     */
    public int getMm() {
        return mm;
    }

    /**
     *retorna el año de la fecha
     * @return int
     */
    public int getAa() {
        return aa;
    }

    /**
     *establece o modifica el dia de la fecha
     * @param dd dia de la fecha
     */
    public void setDd(int dd) {
        this.dd = dd;
    }

    /**
     *establece o modifica el mes de la fecha
     * @param mm mes de la fecha
     */
    public void setMm(int mm) {
        this.mm = mm;
    }

    /**
     *establece o modifica el año de la fecha
     * @param aa año de la fecha
     */
    public void setAa(int aa) {
        this.aa = aa;
    }
    
    /**
     *retorna todos los valores de la fecha
     * @return String
     */
    @Override
    public String toString() {
        return aa + "-" + mm + "-" + dd;
    }
    
    
}
