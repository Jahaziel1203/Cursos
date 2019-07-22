/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author ACER
 */
public class Curso {
    private int id, idProf, duracion;
    private String nombre, hInicio, hFin, fInicio, fFin, aula;
    private boolean estado;
    
    public Curso(){}
    
    public Curso(int i, String n, String fIn, String fF, int idP, String au, String hI, String hF, boolean est){
        id = i;
        nombre = n;
        fInicio = fIn;
        fFin = fF;
        idProf = idP;
        aula = au;
        hInicio = hI;
        hFin = hF;
        estado = est;
    }
    
    public Curso(String n, String fIn, String fF, int idP, String au, String hI, String hF){
        nombre = n;
        fInicio = fIn;
        fFin = fF;
        idProf = idP;
        aula = au;
        hInicio = hI;
        hFin = hF;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProf() {
        return idProf;
    }

    public void setIdProf(int idProf) {
        this.idProf = idProf;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String gethInicio() {
        return hInicio;
    }

    public void sethInicio(String hInicio) {
        this.hInicio = hInicio;
    }

    public String gethFin() {
        return hFin;
    }

    public void sethFin(String hFin) {
        this.hFin = hFin;
    }

    public String getfInicio() {
        return fInicio;
    }

    public void setfInicio(String fInicio) {
        this.fInicio = fInicio;
    }

    public String getfFin() {
        return fFin;
    }

    public void setfFin(String fFin) {
        this.fFin = fFin;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    public boolean getEstado() {
        return estado;
    }
}