package model;

public class Produto {
    private int id;
    private String nome;
    private double preco;
    private String tipo;
    private int estoque;

    public Produto() {
    }

    public Produto(int id, String nome, double preco, String tipo, int estoque) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.tipo = tipo;
        this.estoque = estoque;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getEstoque() {
    return estoque;
}

public void setEstoque(int estoque) {
    this.estoque = estoque;
    }

    @Override
public String toString() {
    return nome + " (" + estoque + " em estoque)";
}
}