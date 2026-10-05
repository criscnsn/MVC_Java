package modelo.services;




import modelo.Pedido;
import modelo.services.filtros.Filtro;

import java.util.ArrayList;
import java.util.List;

public class Tuberia {
    private final List<Filtro> pipeline;
    public Tuberia(){
        this.pipeline = new ArrayList<>();
    }

    public Tuberia(List<Filtro> pipeline){
        this.pipeline = new ArrayList<>(pipeline);
    }


    public Pedido procesarPedido(Pedido pedido){
        if (pedido == null) return null;
        for (Filtro filtro : pipeline) {
            pedido = filtro.procesar(pedido);

//            System.out.println(" Tras " + filtro.getClass().getSimpleName() +
//                               " | Estado: " + pedido.getEstado() +
//                               " | Subtotal: $" + pedido.getSubtotal());
        }
        return pedido;
    }
    public void agregarFiltro(Filtro filtro){
        pipeline.add(filtro);
    }
}
