package beans;

public class Pedido {
    private int id;
    private String cliente;
    private double total;
    
    public Pedido(int id, String cliente, double total) {
        this.id = id;
        this.cliente = cliente;
        this.total = total;
    }

    public Pedido() {
        
    }
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }
    
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
}