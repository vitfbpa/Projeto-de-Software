package model.dao;

import dao.ProdutoDAO;
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

    public boolean adicionarItem(int comandaId, int produtoId, int quantidade, double precoUnitario) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = new Conexao().getConexao();

            stmt = conn.prepareStatement("INSERT INTO ItensComanda (comanda_id, produto_id, quantidade, preco_unitario) VALUES (?, ?, ?, ?)");
            stmt.setInt(1, comandaId);
            stmt.setInt(2, produtoId);
            stmt.setInt(3, quantidade);
            stmt.setDouble(4, precoUnitario);
            stmt.executeUpdate();

            atualizarTotalComanda(conn, comandaId, precoUnitario * quantidade);

            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao adicionar item: " + e.getMessage());
            return false;
        } finally {
            fecharRecursos(conn, stmt, null);
        }
    }

    private void atualizarTotalComanda(Connection conn, int comandaId, double valor) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("UPDATE Comandas SET total = total + ? WHERE id = ?");
            stmt.setDouble(1, valor);
            stmt.setInt(2, comandaId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar total da comanda: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                System.out.println("Erro ao fechar statement: " + e.getMessage());
            }
        }
    }

    public List<ItemComanda> getItensComanda(int comandaId) {
        List<ItemComanda> itens = new ArrayList<>();
        String sql = "SELECT * FROM ItensComanda WHERE comanda_id = ?";
        try (Connection conn = new Conexao().getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, comandaId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produto produto = produtoDAO.getProdutoById(rs.getInt("produto_id"));
                itens.add(new ItemComanda(
                    rs.getInt("id"),
                    comandaId,
                    rs.getInt("produto_id"),
                    rs.getInt("quantidade"),
                    rs.getDouble("preco_unitario"),
                    produto != null ? produto.getNome() : "Produto"
                ));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar itens da comanda: " + e.getMessage());
        }

        return itens;
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

    public void adicionarItem(int comandaId, int id, int quantidade) {
    }
}