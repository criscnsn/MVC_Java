package modelo.services.filtros;


import modelo.EstadoPedido;
import modelo.Pedido;
import modelo.Producto;

public class ValidarDatos implements Filtro{
    @Override
    public Pedido procesar(Pedido pedido) {
        if (pedido == null) {
            throw new IllegalArgumentException("El pedido no puede ser nulo");
        }

        final boolean isClienteValido = pedido.getCliente() != null && !pedido.getCliente().trim().isEmpty();
        final boolean isListaProductosValida = pedido.getListaProductos() != null && !pedido.getListaProductos().isEmpty();

        if (!isClienteValido) {
            throw new IllegalArgumentException("El cliente no puede estar vacio");
        } else if (!isListaProductosValida) {
            pedido.setEstado(EstadoPedido.PEDIDO_SIN_PRODUCTOS);
            throw new IllegalArgumentException("El pedido no puede estar vacio");
        }

        // La cantidad solicitada debe ser mayor que cero para cada producto
        for (Producto producto : pedido.getListaProductos()) {
            if (producto == null || producto.getCantidad() <= 0) {
                throw new IllegalArgumentException("La cantidad solicitada debe ser mayor que cero para cada producto");
            }
        }

        pedido.setEstado(EstadoPedido.PEDIDO_VALIDO);
        return pedido;
    }
}
