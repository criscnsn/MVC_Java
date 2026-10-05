package modelo.services.filtros;


import modelo.Pedido;
import modelo.Producto;

public class CalcularSubtotal implements Filtro{

    @Override
    public Pedido procesar(Pedido pedido) {
        if (pedido == null || pedido.getListaProductos() == null) {
            return pedido;
        }

        double subtotal = 0.0;
        for (Producto producto : pedido.getListaProductos()) {
            subtotal += (producto.getPrecio() * producto.getCantidad());
        }

        pedido.setSubtotal(subtotal);
        return pedido;
    }
}
