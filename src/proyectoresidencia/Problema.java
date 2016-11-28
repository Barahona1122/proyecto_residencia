/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoresidencia;

/**
 *
 * @author Carlos Barahona
 */
public class Problema {
   int id_problema;
   String nombre_problema;

    public int getId_problema() {
        return id_problema;
    }

    public void setId_problema(int id_problema) {
        this.id_problema = id_problema;
    }

    public String getNombre_problema() {
        return nombre_problema;
    }

    public void setNombre_problema(String nombre_problema) {
        this.nombre_problema = nombre_problema;
    }

    @Override
    public String toString() {
        return nombre_problema;
    }
   
}
