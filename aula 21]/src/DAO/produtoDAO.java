/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import beans.Produto;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author laboratorio
 */
public class produtoDAO {
    
    
    
     private static final String ARQUIVO_CSV = "produtos.csv";

    // Método para salvar um produto no arquivo CSV
    public static void salvarProduto(Produto produto) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_CSV, true))) {
            writer.write(produto.toCSV());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
}
