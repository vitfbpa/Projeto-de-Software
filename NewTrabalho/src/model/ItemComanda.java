package model;

public class ItemComanda {
    private int id;
    private int comandaId;
    private int produtoId;
    private int quantidade;
    private double precoUnitario;
    private String produtoNome;

    public ItemComanda(int id, int comandaId, int produtoId, int quantidade, double precoUnitario, String produtoNome) {
        this.id = id;
        this.comandaId = comandaId;
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.produtoNome = produtoNome;
    }

    // Getters e Setters
    public int getId() { return id; }
    public int getComandaId() { return comandaId; }
    public int getProdutoId() { return produtoId; }
    public int getQuantidade() { return quantidade; }
    public double getPrecoUnitario() { return precoUnitario; }
    public String getProdutoNome() { return produtoNome; }
    public double getTotalItem() { return quantidade * precoUnitario; }
}