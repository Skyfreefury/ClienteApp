/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClienteApp.Model;

/**
 * @author christianmogena3
 */
public class Client {
    private int id;
    private String nombre;
    private String apellido;
    private int edad;
    private double compra1;
    private double compra2;
    private double compra3;
    private double promedioCompras;
    private String foto;
    
    // 🟢 NUEVO CAMPO: Relación con TipoCliente
    private TipoCliente tipoCliente;

    public Client() {
        // Inicializamos para evitar NullPointerException al mostrar el formulario
        this.tipoCliente = new TipoCliente();
    }

    // ... (Mantén tus otros constructores si los tienes)

    // 🟢 NUEVOS MÉTODOS: Getter y Setter para TipoCliente
    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    // --- TUS GETTERS Y SETTERS ACTUALES ---
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    public double getCompra1() { return compra1; }
    public void setCompra1(double compra1) { this.compra1 = compra1; }

    public double getCompra2() { return compra2; }
    public void setCompra2(double compra2) { this.compra2 = compra2; }

    public double getCompra3() { return compra3; }
    public void setCompra3(double compra3) { this.compra3 = compra3; }

    public double getPromedioCompras() { return promedioCompras; }
    public void setPromedioCompras(double promedioCompras) { this.promedioCompras = promedioCompras; }

    public String getFoto() { return foto; }
    public void setFoto(String foto) { this.foto = foto; }

    public void CalcularPromedioCompras() {
        this.promedioCompras = (compra1 + compra2 + compra3) / 3;
    }
}
