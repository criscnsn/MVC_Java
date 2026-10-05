package modelo;

public class Producto {
    private final String nombre;
    private final double precio;
    private int cantidad;
    private int existencia;

    public Producto(String nombre, double precio, int cantidad, int existencia) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.existencia = existencia;
    }

    public int getExistencia() {
        return existencia;
    }

    /**
     * Alias de compatibilidad. Se recomienda usar getExistencia().
     */
    public int getCantidadExistencia() {
        return getExistencia();
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }


}
