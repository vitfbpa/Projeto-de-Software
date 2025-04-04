package view;

import model.dao.ItemComandaDAO;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import model.ItemComanda;
import javax.swing.*;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class GerarCSVDialog extends JDialog {
    private JTextField txtDiretorio;
    private JButton btnGerar;
    private JButton btnCancelar;
    
    private int comandaId;

    public GerarCSVDialog(JFrame parent, int comandaId) {
        super(parent, "Gerar Comanda", true);
        this.comandaId = comandaId;
        initComponents();
    }

    private void initComponents() {
        setSize(400, 150);
        setLocationRelativeTo(getParent());
        
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        txtDiretorio = new JTextField(System.getProperty("user.home"));
        btnGerar = new JButton("Gerar Comanda");
        btnCancelar = new JButton("Cancelar");
        
        btnGerar.addActionListener(e -> gerarCSV());
        btnCancelar.addActionListener(e -> dispose());
        
        panel.add(new JLabel("Diretório para salvar:"));
        panel.add(txtDiretorio);
        
        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotoes.add(btnGerar);
        panelBotoes.add(btnCancelar);
        panel.add(panelBotoes);
        
        add(panel);
    }

    private void gerarCSV() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String fileName = txtDiretorio.getText() + "/comanda_" + comandaId + "_" + dateFormat.format(new Date()) + ".txt";
        
        try (FileWriter writer = new FileWriter(fileName)) {
            // Escrever cabeçalho
            writer.append("Comanda ID,Produto,Quantidade,Preço Unitário,Total Item\n");
            
            // Obter itens da comanda
            ItemComandaDAO itemDAO = new ItemComandaDAO();
            List<ItemComanda> itens = itemDAO.getItensComanda(comandaId);
            double total = 0;
            
            for (ItemComanda item : itens) {
                writer.append(String.valueOf(comandaId));
                writer.append(",");
                writer.append(item.getProdutoNome());
                writer.append(",");
                writer.append(String.valueOf(item.getQuantidade()));
                writer.append(",");
                writer.append(String.format("%.2f", item.getPrecoUnitario()));
                writer.append(",");
                writer.append(String.format("%.2f", item.getTotalItem()));
                writer.append("\n");
                
                total += item.getTotalItem();
            }
            
            // Escrever total
            writer.append("\n");
            writer.append(",,,TOTAL,");
            writer.append(String.format("%.2f", total));
            writer.append("\n");
            
            JOptionPane.showMessageDialog(this, "Arquivo gerado com sucesso:\n" + fileName);
            dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao gerar arquivo:\n" + e.getMessage(), 
                "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}