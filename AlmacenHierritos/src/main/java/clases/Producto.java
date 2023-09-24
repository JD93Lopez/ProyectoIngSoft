
package clases;

public class Producto {
    
    private String codigo;
    private String nombre;
    private String descripcion;
    private double precio;
    private double cantidad;
    private double pDescuento;
    private double pIva;

    public Producto() {
    }

    public Producto(String codigo, String nombre, String descripcion, double precio, double cantidad, double pIva) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
        this.pIva = pIva;
    }

    public Producto(String codigo, String nombre, String descripcion, double precio, double cantidad, double pDescuento, double pIva) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
        this.pDescuento = pDescuento;
        this.pIva = pIva;
    }
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getpDescuento() {
        return pDescuento;
    }

    public void setpDescuento(double pDescuento) {
        this.pDescuento = pDescuento;
    }
    
}
