/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClienteApp.Model;

/**
 *
 * @author christianmogena3
 */
public class Client {
    private int id;
    private String nombre, apellido;
    private int edad;
    private double compra1;
    private double compra2;
    private double compra3;
    private double promedioCompras;
    private String foto;
    
    
    //Constructor
    public Client(int id, String nombre, String apellido, int edad, double compra1, double compra2, double compra3, String foto) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.compra1 = compra1;
        this.compra2 = compra2;
        this.compra3 = compra3;
        this.foto = foto;
    }
    public Client() {
    }
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
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }
    public double getCompra1() {
        return compra1;
    }
    public void setCompra1(double compra1) {
        this.compra1 = compra1;
    }
    public double getCompra2() {
        return compra2;
    }
    public void setCompra2(double compra2) {
        this.compra2 = compra2;
    }
    public double getCompra3() {
        return compra3;
    }
    public void setCompra3(double compra3) {
        this.compra3 = compra3;
    }
    public double getPromedioCompras() {
        return promedioCompras;
    }
    public void setPromedioCompras(double promedioCompras) {
        this.promedioCompras = promedioCompras;
    }

    public String getFoto() {
        return foto;
    }
    public void setFoto(String foto) {
        this.foto = foto;
    }
    //To String
    @Override
    public String toString() {
        return "Client{" + "id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", edad=" + edad + '}';
    }
    public void CalcularPromedioCompras(){
        this.promedioCompras = (compra1+compra2+compra3)/3;
    }
    
}
