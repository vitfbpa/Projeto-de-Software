package dao;

import model.Comanda;
import model.Conexao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComandaDAO {
    private Connection connection;

    public ComandaDAO() {
        this.connection = new Conexao().getConexao();
    }

    public int criarComanda() {
        try {
            PreparedStatement stmt = connection.prepareStatement(
                "INSERT INTO Comandas (total) VALUES (0)", Statement.RETURN_GENERATED_KEYS);
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao criar comanda: " + e.getMessage());
        } finally {
            fecharConexao();
        }
        return -1;
    }

    public Comanda getComanda(int id) {
        try {
            PreparedStatement stmt = connection.prepareStatement(
                "SELECT id, total FROM Comandas WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Comanda(rs.getInt("id"), rs.getDouble("total"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar comanda: " + e.getMessage());
        } finally {
            fecharConexao();
        }
        return null;
    }

    public boolean atualizarTotal(int comandaId, double valor) {
        try {
            PreparedStatement stmt = connection.prepareStatement(
                "UPDATE Comandas SET total = total + ? WHERE id = ?");
            stmt.setDouble(1, valor);
            stmt.setInt(2, comandaId);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar total da comanda: " + e.getMessage());
            return false;
        } finally {
            fecharConexao();
        }
    }

    public List<Comanda> getTodasComandas() {
        List<Comanda> comandas = new ArrayList<>();
        String sql = "SELECT * FROM Comandas";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Comanda comanda = new Comanda();
                comanda.setId(rs.getInt("id"));
                comanda.setTotal(rs.getDouble("total"));
                comandas.add(comanda);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar comandas: " + e.getMessage());
        } finally {
            fecharConexao();
        }

        return comandas;
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