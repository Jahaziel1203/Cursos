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
public class CursoProfesor {
    private int id_Profesor, id_Curso;
    
    public CursoProfesor(){}
    
    public CursoProfesor(int idP, int idC){
        id_Profesor = idP;
        id_Curso = idC;
    }
    
    public void setIdP(int idP){
        id_Profesor = idP;
    }
    
    public void setIdC(int idC){
        id_Curso = idC;
    }
    
    public int getIdP(){
        return id_Profesor;
    }
    
    public int getIdC(){
        return id_Curso;
    }
}
