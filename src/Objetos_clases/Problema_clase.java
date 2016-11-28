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
public class Problema_clase {
int id_subcategoria;
String nombre_problema;

    public int getId_subcategoria() {
        return id_subcategoria;
    }

    public void setId_subcategoria(int id_subcategoria) {
        this.id_subcategoria = id_subcategoria;
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
