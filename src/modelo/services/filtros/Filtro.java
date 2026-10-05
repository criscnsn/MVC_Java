package modelo.services.filtros;


import modelo.Pedido;

public interface Filtro {
    Pedido procesar(Pedido pedido);
}
