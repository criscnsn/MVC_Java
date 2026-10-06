package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa un pedido dentro del dominio del sistema según la especificación del ADA:
 * - id
 * - cliente
 * - productos
 * - subtotal
 * - descuento
 * - impuestos
 * - total
 * - estado
 */
public class Pedido {
    private int id;
    private String cliente;
    private List<Producto> productos;
    private double subtotal;
    private double descuento;
    private double impuestos;
    private double total;
    private EstadoPedido estado;

    public Pedido() {
        this.productos = new ArrayList<>();
        this.estado = EstadoPedido.PEDIDO_VALIDO;
    }

    public Pedido(String cliente, List<Producto> productos) {
        this.cliente = cliente;
        this.productos = productos != null ? productos : new ArrayList<>();
        this.estado = EstadoPedido.PEDIDO_VALIDO;
    }

    public Pedido(int id, String cliente, List<Producto> productos, double subtotal, double descuento, double impuestos, double total, EstadoPedido estado) {
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

    public List<Producto> getListaProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos != null ? productos : new ArrayList<>();
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

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public void setEstado(String estadoStr) {
        try {
            this.estado = EstadoPedido.valueOf(estadoStr.toUpperCase().trim());
        } catch (Exception e) {
            this.estado = EstadoPedido.PROCESADO;
        }
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", cliente='" + cliente + '\'' +
                ", productos=" + (productos != null ? productos.size() : 0) +
                ", subtotal=" + String.format("%.2f", subtotal) +
                ", descuento=" + String.format("%.2f", descuento) +
                ", impuestos=" + String.format("%.2f", impuestos) +
                ", total=" + String.format("%.2f", total) +
                ", estado=" + estado +
                '}';
    }
}
