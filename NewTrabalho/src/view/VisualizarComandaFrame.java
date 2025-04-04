package view;

import model.dao.ItemComandaDAO;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import model.ItemComanda;
import model.dao.ItemComandaDAO;

public class VisualizarComandaFrame extends JFrame {
    private JTable tableItens;
    private JLabel lblTotal;

    public VisualizarComandaFrame(int comandaId) {
        super("Comanda #" + comandaId);
        initComponents(comandaId);
    }

    private void initComponents(int comandaId) {
        setSize(600, 400);
        setLocationRelativeTo(null);
        
        // Modelo da tabela
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Produto");
        model.addColumn("Quantidade");
        model.addColumn("Preço Unitário");
        model.addColumn("Total");
        
        // Obter itens da comanda
        ItemComandaDAO itemDAO = new ItemComandaDAO();
        List<ItemComanda> itens = itemDAO.getItensComanda(comandaId);
        double total = 0;
        
        for (ItemComanda item : itens) {
            model.addRow(new Object[]{
                item.getProdutoNome(),
                item.getQuantidade(),
                String.format("R$ %.2f", item.getPrecoUnitario()),
                String.format("R$ %.2f", item.getTotalItem())
            });
            total += item.getTotalItem();
        }
        
        tableItens = new JTable(model);
        lblTotal = new JLabel("Total: R$ " + String.format("%.2f", total));
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(tableItens), BorderLayout.CENTER);
        
        JPanel panelTotal = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelTotal.add(lblTotal);
        panel.add(panelTotal, BorderLayout.SOUTH);
        
        add(panel);
    }
}