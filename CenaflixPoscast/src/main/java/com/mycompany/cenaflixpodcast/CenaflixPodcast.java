/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.cenaflixpodcast;

import com.mycompany.cenaflixpodcast.telas.TelaLogin;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author mi_bo
 */
public class CenaflixPodcast {

    public static void main(String[] args) throws SQLException {
       Connection conexao = null;
        try {
            // Carrega o driver JDBC do MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Estabelece a conexão com o banco de dados
            conexao = DriverManager.getConnection("jdbc:mysql://localhost/podcast", "root", "1234");
            System.out.println("Conexão estabelecida com o MySQL e com o banco de dados!");
            
            // Exibe a janela principal da aplicação
            TelaLogin mainWindow = new TelaLogin();
            mainWindow.setVisible(true);
        } catch (ClassNotFoundException ex) {
            // Trata a exceção caso o driver JDBC não seja encontrado
            System.out.println("Driver do banco de dados não localizado!");
        } catch (SQLException ex) {
            // Trata a exceção caso ocorra um erro ao acessar o banco de dados
            System.out.println("Ocorreu um erro ao acessar o banco: " + ex.getMessage());
        } finally {
            // Garante que a conexão com o banco de dados seja fechada
            if (conexao != null) {
                conexao.close();
            }
        }
    }
}