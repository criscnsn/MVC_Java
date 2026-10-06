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
    }

    public void setVista(PedidoVista vista) {
        this.vista = vista;
    }

    public void registrarPedido() {
        try {
            Pedido pedido = vista.capturarPedido();
            Pedido resultado = modelo.registrarPedido(pedido);
            vista.mostrarResultado(resultado);
        } catch (IllegalArgumentException e) {
            vista.mostrarError(e.getMessage());
        } catch (Exception e) {
            vista.mostrarError("Error inesperado al procesar pedido: " + e.getMessage());
        }
    }


    public void registrarPedido(Pedido pedido) {
        try {
            Pedido resultado = modelo.registrarPedido(pedido);
            vista.mostrarResultado(resultado);
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
