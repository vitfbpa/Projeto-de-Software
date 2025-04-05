package model;

public class Comanda {
    private int id;
    private double total;

    public Comanda(int id, double total) {
        this.id = id;
        this.total = total;
    }

    public Comanda() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    @Override
public String toString() {
    return "Comanda #" + id + " - Total: R$ " + String.format("%.2f", total);
    }
}