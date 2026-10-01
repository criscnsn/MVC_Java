package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa un pedido dentro del dominio del sistema.
 */
public class Pedido {
    private int id;
    private String cliente;
    private List<Producto> productos;
    private double subtotal;
    private double descuento;
    private double impuestos;
    private double total;
    private String estado;

    public Pedido() {
        this.productos = new ArrayList<>();
    }

    public Pedido(String cliente, List<Producto> productos) {
        this.cliente = cliente;
        this.productos = productos != null ? productos : new ArrayList<>();
    }

    public Pedido(int id, String cliente, List<Producto> productos, double subtotal, double descuento, double impuestos, double total, String estado) {
        this.id = id;
        this.cliente = cliente;
        this.productos = productos != null ? productos : new ArrayList<>();
        this.subtotal = subtotal;
        this.descuento = descuento;
        this.impuestos = impuestos;
        this.total = total;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public void agregarProducto(Producto producto) {
        if (this.productos == null) {
            this.productos = new ArrayList<>();
        }
        this.productos.add(producto);
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(double impuestos) {
        this.impuestos = impuestos;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", cliente='" + cliente + '\'' +
                ", productos=" + productos +
                ", subtotal=" + subtotal +
                ", descuento=" + descuento +
                ", impuestos=" + impuestos +
                ", total=" + total +
                ", estado='" + estado + '\'' +
                '}';
    }
}
