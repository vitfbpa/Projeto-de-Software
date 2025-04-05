package view;

import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        // Configurar look and feel do sistema
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Criar e exibir a janela principal
        java.awt.EventQueue.invokeLater(() -> {
            new MenuRestaurante().setVisible(true);
        });
    }
}