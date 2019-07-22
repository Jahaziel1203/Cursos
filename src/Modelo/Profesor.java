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
public class Profesor {
    private int id, cedula;
    private String nombre, apPaterno, apMaterno, nivelMax, carrera, area, correo;
    
    public Profesor(int i, String nom, String ap1, String ap2, int ced, String nM, String ca, String cor){
        id = i;
        nombre = nom;
        apPaterno = ap1;
        apMaterno = ap2;
        cedula = ced;
        nivelMax = nM;
        carrera = ca;
        correo = cor;
    }
    
    public Profesor(String nom, String ap1, String ap2, int ced, String nM, String ca, String cor){
        nombre = nom;
        apPaterno = ap1;
        apMaterno = ap2;
        cedula = ced;
        nivelMax = nM;
        carrera = ca;
        correo = cor;
    }

    public Profesor() { //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApPaterno() {
        return apPaterno;
    }

    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

    public String getApMaterno() {
        return apMaterno;
    }

    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }

    public String getNivelMax() {
        return nivelMax;
    }

    public void setNivelMax(String nivelMax) {
        this.nivelMax = nivelMax;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }    
}