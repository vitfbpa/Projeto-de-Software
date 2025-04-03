package beans;

public class Produto {
    private int id;
    private String nome;
    private double preco;
    private String tipo; // 'Comida' ou 'Bebida'

    public Produto() {}

    public Produto(int id, String nome, double preco, String tipo) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.tipo = tipo;
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

    @Override
    public String toString() {
        return "Produto{" + "id=" + id + ", nome='" + nome + '\'' + ", preco=" + preco + ", tipo='" + tipo + '\'' + '}';
    }
}