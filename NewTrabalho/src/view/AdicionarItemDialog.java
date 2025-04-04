package view;

import model.dao.ItemComandaDAO;
import model.Produto;
import model.dao.ProdutoDAO;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AdicionarItemDialog extends JDialog {
    private JComboBox<Produto> comboProdutos;
    private JSpinner spinnerQuantidade;
    private JButton btnAdicionar;
    private JButton btnCancelar;
    
    private int comandaId;

    public AdicionarItemDialog(JFrame parent, int comandaId) {
        super(parent, "Adicionar Item à Comanda", true);
        this.comandaId = comandaId;
        initComponents();
    }

    private void initComponents() {
        setSize(400, 200);
        setLocationRelativeTo(getParent());
        
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Obter produtos do banco de dados
        ProdutoDAO produtoDAO = new ProdutoDAO();
        List<Produto> produtos = produtoDAO.getProdutos();
        comboProdutos = new JComboBox<>(produtos.toArray(new Produto[0]));
        comboProdutos.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Produto) {
                    Produto p = (Produto) value;
                    setText(p.getNome() + " (" + p.getTipo() + ") - R$ " + String.format("%.2f", p.getPreco()));
                }
                return this;
            }
        });
        
        spinnerQuantidade = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        btnAdicionar = new JButton("Adicionar");
        btnCancelar = new JButton("Cancelar");
        
        btnAdicionar.addActionListener(e -> adicionarItem());
        btnCancelar.addActionListener(e -> dispose());
        
        panel.add(new JLabel("Produto:"));
        panel.add(comboProdutos);
        panel.add(new JLabel("Quantidade:"));
        panel.add(spinnerQuantidade);
        panel.add(btnAdicionar);
        panel.add(btnCancelar);
        
        add(panel);
    }

    private void adicionarItem() {
        Produto produto = (Produto) comboProdutos.getSelectedItem();
        int quantidade = (int) spinnerQuantidade.getValue();
        
        ItemComandaDAO itemDAO = new ItemComandaDAO();
        boolean success = itemDAO.adicionarItemComanda(
            comandaId, produto.getId(), quantidade);
        
        if (success) {
            JOptionPane.showMessageDialog(this, "Item adicionado com sucesso!");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao adicionar item!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}