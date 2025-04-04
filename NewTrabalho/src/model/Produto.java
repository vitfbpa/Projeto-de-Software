package model;

public class Produto {
    private int id;
    private String nome;
    private double preco;
    private String tipo;

    public Produto(int id, String nome, double preco, String tipo) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.tipo = tipo;
    }

    // Getters e Setters
    public int getId() { return id; }
    public String getNome() { return nome; }
    public double getPreco() { return preco; }
    public String getTipo() { return tipo; }
}