package dao;

import model.Produto;
import model.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    private Connection connection;

    public ProdutoDAO() {
        this.connection = new Conexao().getConexao();
    }

    // LISTAR TODOS OS PRODUTOS
    public List<Produto> listarTodos() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT id, nome, preco, tipo FROM Produtos ORDER BY tipo, nome";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Produto p = new Produto(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getDouble("preco"),
                    rs.getString("tipo")
                );
                produtos.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar produtos: " + e.getMessage());
        }

        return produtos;
    }

    // BUSCAR PRODUTO POR ID
    public Produto getProdutoById(int id) {
        try {
            PreparedStatement stmt = connection.prepareStatement(
                "SELECT id, nome, preco, tipo FROM Produtos WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Produto(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getDouble("preco"),
                    rs.getString("tipo")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar produto por ID: " + e.getMessage());
        }
        return null;
    }

    // OBTER PREÇO DO PRODUTO POR ID
    public double getPrecoProduto(int produtoId) {
        try {
            PreparedStatement stmt = connection.prepareStatement(
                "SELECT preco FROM Produtos WHERE id = ?");
            stmt.setInt(1, produtoId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getDouble("preco");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar preço do produto: " + e.getMessage());
        }
        return 0.0;
    }

    // ADICIONAR PRODUTO
    public boolean adicionarProduto(Produto produto) {
        String sql = "INSERT INTO Produtos (nome, preco, tipo) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setString(3, produto.getTipo());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao adicionar produto: " + e.getMessage());
            return false;
        }
    }

    // ATUALIZAR PRODUTO
    public boolean atualizarProduto(Produto produto) {
        String sql = "UPDATE Produtos SET nome = ?, preco = ?, tipo = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setString(3, produto.getTipo());
            stmt.setInt(4, produto.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar produto: " + e.getMessage());
            return false;
        }
    }

    // REMOVER PRODUTO
    public boolean removerProduto(int id) {
        String sql = "DELETE FROM Produtos WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao remover produto: " + e.getMessage());
            return false;
        }
    }

    
}