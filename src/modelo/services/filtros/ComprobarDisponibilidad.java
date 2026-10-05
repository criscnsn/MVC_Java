package modelo.services.filtros;


import modelo.EstadoPedido;
import modelo.Pedido;
import modelo.Producto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComprobarDisponibilidad implements Filtro {
    @Override
    public Pedido procesar(Pedido pedido) {
        List<Producto> productos = pedido.getListaProductos();

        Map<String, Integer> cantidadTotalPedida = new HashMap<>();
        for (Producto producto : productos) {
            int cantidadActual = cantidadTotalPedida.getOrDefault(producto.getNombre(), 0);
            cantidadTotalPedida.put(producto.getNombre(), cantidadActual + producto.getCantidad());
        }

        for (Producto producto : productos) {
            int totalPedido = cantidadTotalPedida.get(producto.getNombre());
            if (totalPedido > producto.getExistencia()) {
                pedido.setEstado(EstadoPedido.PRODUCTO_CON_EXISTENCIA_INSUFICIENTE);
                throw new IllegalArgumentException("No hay suficiente stock para el producto: " + producto.getNombre());
            }
        }

        pedido.setEstado(EstadoPedido.PEDIDO_VALIDO);
        return pedido;
    }
}
