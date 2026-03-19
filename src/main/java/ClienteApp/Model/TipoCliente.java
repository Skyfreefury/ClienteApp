/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClienteApp.Model;

/**
 *
 * @author christianmogena3
 */
public class TipoCliente {
    private int id;
    private String nombre;
    
    //Getter and Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
        
    //Constructores
    public TipoCliente(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    public TipoCliente() {
    }
    
    //ToString
    @Override
    public String toString() {
        return "TipoCliente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }

}
