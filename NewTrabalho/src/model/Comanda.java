package model;

public class Comanda {
    private int id;
    private double total;

    public Comanda(int id, double total) {
        this.id = id;
        this.total = total;
    }

    // Getters e Setters
    public int getId() { return id; }
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
}