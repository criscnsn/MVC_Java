/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package vista;

import modelo.Pedido;

/**
 *
 * @author compu
 */
public interface PedidoObserver {
    void onPedidoRegistrado(Pedido pedido);
}
