package vista;

import modelo.Pedido;

/**
 * Vista alternativa que presenta una representación resumida del pedido.
 * Demuestra la separación arquitectónica MVC: diferentes vistas pueden reutilizar
 * el mismo modelo sin modificarlo.
 */
public class PedidoVistaResumida extends PedidoVista {

    @Override
    public void mostrarResultado(Pedido pedido) {
        imprimirResumen(pedido);
    }

    @Override
    public void mostrarPedido(Pedido pedido) {
        imprimirResumen(pedido);
    }

    private void imprimirResumen(Pedido pedido) {
        if (pedido != null) {
            System.out.println("\nPedido: " + pedido.getId());
            System.out.printf("Total: $%.2f%n", pedido.getTotal());
            System.out.println("Estado: " + pedido.getEstado());
        }
    }
}
