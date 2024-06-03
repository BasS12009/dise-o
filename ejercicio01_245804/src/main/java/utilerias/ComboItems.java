/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilerias;

/**
 *
 * @author Diana Sofia Bastidas Osuna-245804
 */
public class ComboItems {
    private String nombre;
    private String id;
    
    public ComboItems(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    
     public ComboItems(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String aNombre) {
        nombre = aNombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String aId) {
        id = aId;
    }
    
    @Override
    public String toString() {
        return this.nombre;
    }
    
    @Override
    public boolean equals(Object item) {
        return  this.id.equals(((ComboItems) item).id);
    }
}
