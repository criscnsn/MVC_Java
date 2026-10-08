package controlador;

import modelo.Pedido;
import modelo.PedidoModelo;
import vista.PedidoVista;

public class PedidoControlador {

    private PedidoModelo modelo;
    private PedidoVista vista;

    public PedidoControlador(PedidoModelo modelo, PedidoVista vista) {
        this.modelo = modelo;
        this.vista = vista;
        this.modelo.agregarObservador(this.vista);
    }

    public void setVista(PedidoVista nuevaVista) {
        this.modelo.removerObservador(this.vista);
        this.vista = nuevaVista;
        this.modelo.agregarObservador(this.vista);
    }

    public void registrarPedido() {
        try {
            Pedido pedido = vista.capturarPedido();
            
            modelo.registrarPedido(pedido);

        } catch (IllegalArgumentException e) {
            vista.mostrarError(e.getMessage());
        } catch (Exception e) {
            vista.mostrarError("Error inesperado al procesar pedido: " + e.getMessage());
        }
    }


    public void registrarPedido(Pedido pedido) {
        try {
            modelo.registrarPedido(pedido);
        } catch (IllegalArgumentException e) {
            vista.mostrarError(e.getMessage());
        } catch (Exception e) {
            vista.mostrarError("Error inesperado al procesar pedido: " + e.getMessage());
        }
    }


    public void consultarPedido(int id) {
        Pedido pedido = modelo.consultarPedido(id);
        if (pedido != null) {
            vista.mostrarPedido(pedido);
        } else {
            vista.mostrarError("Pedido no encontrado");
        }
    }


    public void consultarPedido() {
        try {
            int id = vista.capturarIdPedido();
            consultarPedido(id);
        } catch (NumberFormatException e) {
            vista.mostrarError("El ID ingresado no es un número válido.");
        }
    }
}
