package modelo;

import modelo.services.filtros.*;
import modelo.services.Tuberia;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Modelo encargado de la lógica de negocio, validaciones,
 * cálculos matemáticos y persistencia en memoria de los pedidos.
 * NOTA: No debe imprimir datos ni solicitar entrada del usuario.
 */
public class PedidoModelo {
    private final Tuberia tuberia;
    private final Map<Integer, Pedido> pedidos = new HashMap<>();
    private int siguienteId = 1;

    public PedidoModelo(Tuberia tuberia){
        this.tuberia = tuberia;
    }
    public PedidoModelo() {
        this(new Tuberia(List.of(
                new ValidarDatos(),
                new ComprobarDisponibilidad(),
                new CalcularSubtotal(),
                new VerificarFraude(),
                new AplicarDescuento(),
                new CalcularImpuestos(),
                new CalcularTotal(),
                new ConfirmarPedido()
                )));
    }

    public Pedido registrarPedido(Pedido pedido) {
        Pedido pedidoProcesado = tuberia.procesarPedido(pedido);
        pedidoProcesado.setId(siguienteId++);
        pedidos.put(pedidoProcesado.getId(), pedidoProcesado);
        return pedidoProcesado;
    }

    public Pedido consultarPedido(int id) {
        return pedidos.get(id);
    }

    public Map<Integer, Pedido> listarPedidos() {
        return new HashMap<>(pedidos);
    }
}
