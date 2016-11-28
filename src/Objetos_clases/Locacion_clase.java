/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos_clases;

/**
 *
 * @author Carlos Barahona
 */
public class Locacion_clase {
  int id_locacion;
  String nombre_locacion;

    public int getId_locacion() {
        return id_locacion;
    }

    public void setId_locacion(int id_locacion) {
        this.id_locacion = id_locacion;
    }

    public String getNombre_locacion() {
        return nombre_locacion;
    }

    public void setNombre_locacion(String nombre_locacion) {
        this.nombre_locacion = nombre_locacion;
    }



    @Override
    public String toString() {
        return nombre_locacion;
    }
  
}
