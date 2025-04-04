package view;

import model.dao.ComandaDAO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuPrincipal extends JFrame {

    // Componentes da interface
    private JPanel panelPrincipal;
    private JPanel panelBotoes;
    private JButton btnNovaComanda;
    private JButton btnAdicionarItem;
    private JButton btnVisualizarComanda;
    private JButton btnGerarCSV;
    private JLabel lblStatus;
    private JLabel lblTitulo;
    private JPanel panelRodape;
    
    private int comandaAtualId = -1;

    public MenuPrincipal() {
        // Configuração do JFrame
        setTitle("Restaurante");
        setPreferredSize(new Dimension(500, 500));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
        personalizarComponentes();
        atualizarStatus();
    }

    private void initComponents() {
        // Painel principal com gradiente
        panelPrincipal = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                Color color1 = new Color(52, 143, 80);
                Color color2 = new Color(86, 180, 211);
                GradientPaint gp = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panelPrincipal.setLayout(new BorderLayout());

        // Painel de título
        JPanel panelTitulo = new JPanel();
        panelTitulo.setOpaque(false);
        lblTitulo = new JLabel("SISTEMA RESTAURANTE");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE);
        panelTitulo.add(lblTitulo);
        panelPrincipal.add(panelTitulo, BorderLayout.NORTH);

        // Painel de botões centralizado
        panelBotoes = new JPanel();
        panelBotoes.setOpaque(false);
        panelBotoes.setLayout(new GridLayout(4, 1, 10, 15));
        panelBotoes.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        // Botões com ícones
        btnNovaComanda = criarBotao("Nova Comanda", new ImageIcon(getClass().getResource("/images/add.png")));
        btnAdicionarItem = criarBotao("Adicionar Item", new ImageIcon(getClass().getResource("/images/food.png")));
        btnVisualizarComanda = criarBotao("Visualizar Comanda", new ImageIcon(getClass().getResource("/images/list.png")));
        btnGerarCSV = criarBotao("Gerar CSV", new ImageIcon(getClass().getResource("/images/save.png")));

        panelBotoes.add(btnNovaComanda);
        panelBotoes.add(btnAdicionarItem);
        panelBotoes.add(btnVisualizarComanda);
        panelBotoes.add(btnGerarCSV);

        panelPrincipal.add(panelBotoes, BorderLayout.CENTER);

        // Painel de rodapé
        panelRodape = new JPanel();
        panelRodape.setOpaque(false);
        panelRodape.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        lblStatus = new JLabel();
        lblStatus.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblStatus.setForeground(Color.WHITE);
        panelRodape.add(lblStatus);
        panelPrincipal.add(panelRodape, BorderLayout.SOUTH);

        // Adiciona o painel principal ao JFrame
        getContentPane().add(panelPrincipal);
        pack();
        setLocationRelativeTo(null);
    }

    private JButton criarBotao(String texto, Icon icone) {
        JButton botao = new JButton(texto, icone);
        botao.setFont(new Font("Segoe UI", Font.BOLD, 16));
        botao.setForeground(new Color(50, 50, 50));
        botao.setBackground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Efeito hover
        botao.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                botao.setBackground(new Color(240, 240, 240));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                botao.setBackground(Color.WHITE);
            }
        });

        return botao;
    }

    private void personalizarComponentes() {
        // Configuração dos listeners
        btnNovaComanda.addActionListener(e -> criarNovaComanda());
        btnAdicionarItem.addActionListener(e -> adicionarItem());
        btnVisualizarComanda.addActionListener(e -> visualizarComanda());
        btnGerarCSV.addActionListener(e -> gerarCSV());
    }

    // Métodos de funcionalidade
    private void criarNovaComanda() {
        ComandaDAO comandaDAO = new ComandaDAO();
        comandaAtualId = comandaDAO.criarComanda();
        if (comandaAtualId != -1) {
            JOptionPane.showMessageDialog(this, "Nova comanda criada com ID: " + comandaAtualId);
            atualizarStatus();
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao criar comanda!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void adicionarItem() {
        if (comandaAtualId == -1) {
            JOptionPane.showMessageDialog(this, "Crie uma comanda primeiro!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        new AdicionarItemDialog(this, comandaAtualId).setVisible(true);
    }

    private void visualizarComanda() {
        if (comandaAtualId == -1) {
            JOptionPane.showMessageDialog(this, "Nenhuma comanda aberta!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        new VisualizarComandaFrame(comandaAtualId).setVisible(true);
    }

    private void gerarCSV() {
        if (comandaAtualId == -1) {
            JOptionPane.showMessageDialog(this, "Nenhuma comanda aberta!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        new GerarCSVDialog(this, comandaAtualId).setVisible(true);
    }

    private void atualizarStatus() {
        if (comandaAtualId == -1) {
            lblStatus.setText("Nenhuma comanda aberta");
        } else {
            lblStatus.setText("Comanda atual: #" + comandaAtualId);
        }
    }

    public static void main(String args[]) {
        try {
            // Define o look and feel do sistema
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        // Cria e exibe a janela principal
        java.awt.EventQueue.invokeLater(() -> {
            new MenuPrincipal().setVisible(true);
        });
    }
}
