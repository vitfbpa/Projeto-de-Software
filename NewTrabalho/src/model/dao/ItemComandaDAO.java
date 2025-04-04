package model.dao;

import model.ItemComanda;
import model.Produto;
import model.Conexao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemComandaDAO {
    private ProdutoDAO produtoDAO;
    
    public ItemComandaDAO() {
        this.produtoDAO = new ProdutoDAO();
    }
    
    public boolean adicionarItemComanda(int comandaId, int produtoId, int quantidade) {
        Connection conn = null;
        PreparedStatement stmtInsert = null;
        
        try {
            conn = new Conexao().getConexao();
            
            // Obter preço do produto
            double preco = produtoDAO.getPrecoProduto(produtoId);
            
            // Inserir item
            stmtInsert = conn.prepareStatement(
                "INSERT INTO ItensComanda (comanda_id, produto_id, quantidade, preco_unitario) VALUES (?, ?, ?, ?)");
            stmtInsert.setInt(1, comandaId);
            stmtInsert.setInt(2, produtoId);
            stmtInsert.setInt(3, quantidade);
            stmtInsert.setDouble(4, preco);
            stmtInsert.executeUpdate();
            
            // Atualizar total da comanda
            atualizarTotalComanda(conn, comandaId, preco * quantidade);
            
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar item à comanda: " + e.getMessage());
            return false;
        } finally {
            fecharRecursos(conn, stmtInsert, null);
        }
    }
    
    public List<ItemComanda> getItensComanda(int comandaId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<ItemComanda> itens = new ArrayList<>();
        
        try {
            conn = new Conexao().getConexao();
            stmt = conn.prepareStatement(
                "SELECT i.id, i.comanda_id, i.produto_id, i.quantidade, i.preco_unitario " +
                "FROM ItensComanda i WHERE i.comanda_id = ?");
            stmt.setInt(1, comandaId);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Produto produto = produtoDAO.getProdutoById(rs.getInt("produto_id"));
                itens.add(new ItemComanda(
                    rs.getInt("id"),
                    rs.getInt("comanda_id"),
                    rs.getInt("produto_id"),
                    rs.getInt("quantidade"),
                    rs.getDouble("preco_unitario"),
                    produto != null ? produto.getNome() : "Produto não encontrado"
                ));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar itens da comanda: " + e.getMessage());
        } finally {
            fecharRecursos(conn, stmt, rs);
        }
        return itens;
    }
    
    private void atualizarTotalComanda(Connection conn, int comandaId, double valor) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(
                "UPDATE Comandas SET total = total + ? WHERE id = ?");
            stmt.setDouble(1, valor);
            stmt.setInt(2, comandaId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar total da comanda: " + e.getMessage());
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar statement: " + e.getMessage());
                }
            }
        }
    }
    
    private void fecharRecursos(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.out.println("Erro ao fechar recursos: " + e.getMessage());
        }
    }
}