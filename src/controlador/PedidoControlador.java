package controlador;

import modelo.Pedido;
import modelo.PedidoModelo;
import vista.PedidoVista;

/**
 * Controlador que coordina la interacción entre la Vista y el Modelo.
 * NO contiene reglas de negocio ni lógica de cálculo.
 */
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

    /**
     * Flujo de registro:
     * 1. Capturar datos desde la Vista.
     * 2. Enviar datos al Modelo para validación, cálculo y almacenamiento.
     * 3. Pasar el resultado a la Vista para su presentación.
     * 4. Si ocurre un error de validación, informar a la Vista.
     */
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

    /**
     * Flujo de registro recibiendo directamente un objeto pedido (útil para pruebas/evidencias programáticas).
     */
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

    /**
     * Flujo de consulta con ID:
     * Vista -> id -> Controlador -> Modelo.consultarPedido(id) -> Controlador -> Vista.mostrarPedido()
     * Si no existe: Vista.mostrarError("Pedido no encontrado")
     */
    public void consultarPedido(int id) {
        Pedido pedido = modelo.consultarPedido(id);
        if (pedido != null) {
            vista.mostrarPedido(pedido);
        } else {
            vista.mostrarError("Pedido no encontrado");
        }
    }

    /**
     * Flujo de consulta interactivo: solicita ID a la Vista y realiza la consulta.
     */
    public void consultarPedido() {
        try {
            int id = vista.capturarIdPedido();
            consultarPedido(id);
        } catch (NumberFormatException e) {
            vista.mostrarError("El ID ingresado no es un número válido.");
        }
    }
}
