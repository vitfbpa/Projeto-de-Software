/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Restaurante;

import java.sql.Connection;
import java.sql.*;


public class Conexao {

    // Criando o método para se conectar ao banco de dados.
    public Connection getConexao(){ 

        try{
            // Instanciando o objeto Connection conn, objeto da biblioteca adicionada aos arquivos do programa e passando o URL através do getConnection.
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurante?Timezone=true&serverTimezone=UTC", "root", "laboratorio");
            // "jdbc:mysql://localhost:3306/nomeBanco?Timezone=true&serverTimezone=UTC", "root", "senha"

            System.out.println("Conexão realizada com sucesso!");
            return conn;

        }catch(Exception e){
            System.out.println("Erro ao conectar-se ao Banco de Dados" +e.getMessage());
            return null;
        }
    }
}
