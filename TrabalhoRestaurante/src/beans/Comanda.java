/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

import java.util.List;

/**
 *
 * @author guede
 */
public class Comanda {
    private int id;
    private ClienteMesa cliente;
    private List<Pedido> pedidos;
    private String status;
    
    public void fecharComanda() {
        this.status = "Fechada";
    }
    
    public double calcularTotal() {
        double total = 0;
        for (Pedido pedido : pedidos) {
        }
        return total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ClienteMesa getCliente() {
        return cliente;
    }

    public void setCliente(ClienteMesa cliente) {
        this.cliente = cliente;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
