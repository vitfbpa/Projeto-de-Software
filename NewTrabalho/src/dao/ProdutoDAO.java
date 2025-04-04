package model.dao;

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
    
    public List<Produto> getProdutos() {
        List<Produto> produtos = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, nome, preco, tipo FROM Produtos ORDER BY tipo, nome");
            
            while (rs.next()) {
                produtos.add(new Produto(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getDouble("preco"),
                    rs.getString("tipo")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar produtos: " + e.getMessage());
        } finally {
            fecharConexao();
        }
        return produtos;
    }
    
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
        } finally {
            fecharConexao();
        }
        return null;
    }
    
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
        } finally {
            fecharConexao();
        }
        return 0.0;
    }
    
    // Métodos adicionais para CRUD completo
    public boolean adicionarProduto(Produto produto) {
        try {
            PreparedStatement stmt = connection.prepareStatement(
                "INSERT INTO Produtos (nome, preco, tipo) VALUES (?, ?, ?)");
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setString(3, produto.getTipo());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar produto: " + e.getMessage());
            return false;
        } finally {
            fecharConexao();
        }
    }
    
    public boolean atualizarProduto(Produto produto) {
        try {
            PreparedStatement stmt = connection.prepareStatement(
                "UPDATE Produtos SET nome = ?, preco = ?, tipo = ? WHERE id = ?");
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setString(3, produto.getTipo());
            stmt.setInt(4, produto.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar produto: " + e.getMessage());
            return false;
        } finally {
            fecharConexao();
        }
    }
    
    public boolean removerProduto(int id) {
        try {
            PreparedStatement stmt = connection.prepareStatement(
                "DELETE FROM Produtos WHERE id = ?");
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao remover produto: " + e.getMessage());
            return false;
        } finally {
            fecharConexao();
        }
    }
    
    private void fecharConexao() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Erro ao fechar conexão: " + e.getMessage());
        }
    }
}