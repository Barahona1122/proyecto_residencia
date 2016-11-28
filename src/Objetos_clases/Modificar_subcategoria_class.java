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
public class Modificar_subcategoria_class {
    int id_subcategoria;
    String nombre_categoria;

    public int getId_subcategoria() {
        return id_subcategoria;
    }

    public void setId_subcategoria(int id_subcategoria) {
        this.id_subcategoria = id_subcategoria;
    }

    public String getNombre_categoria() {
        return nombre_categoria;
    }

    public void setNombre_categoria(String nombre_categoria) {
        this.nombre_categoria = nombre_categoria;
    }
 



   

    @Override
    public String toString() {
        return nombre_categoria;
    }
    
}
