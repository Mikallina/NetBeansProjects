/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.cenaflix;

import com.mycompany.cenaflix.telas.TelaCadastro;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe principal da aplicação Cenaflix.
 * 
 * <p>A classe {@code Cenaflix} é o ponto de entrada da aplicação. Ela estabelece uma conexão com o banco de dados MySQL
 * e, em seguida, exibe a janela principal da interface gráfica de usuário (GUI) {@link TelaCadastro}.</p>
 * 
 * <p>O método {@link #main(String[])} é o responsável por iniciar a aplicação. Ele carrega o driver JDBC do MySQL, 
 * estabelece uma conexão com o banco de dados, e trata exceções que podem ocorrer durante o processo de conexão.</p>
 * 
 * @author Michelle Borges
 */
public class Cenaflix {
    
    /**
     * Método principal que inicia a aplicação.
     * 
     * <p>O método {@code main} carrega o driver JDBC do MySQL, estabelece uma conexão com o banco de dados especificado
     * e exibe a janela principal da aplicação. Em caso de falha ao localizar o driver ou ao acessar o banco de dados,
     * mensagens de erro são exibidas no console. Após o uso, a conexão com o banco de dados é fechada.</p>
     * 
     * @param args os argumentos de linha de comando (não utilizados)
     * @throws SQLException se ocorrer um erro ao fechar a conexão com o banco de dados
     */

  public static void main(String[] args) throws SQLException {
        Connection conexao = null;
        try {
            // Carrega o driver JDBC do MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Estabelece a conexão com o banco de dados
            conexao = DriverManager.getConnection("jdbc:mysql://localhost/cenaflix", "root", "1234");
            System.out.println("Conexão estabelecida com o MySQL e com o banco de dados!");
            
            // Exibe a janela principal da aplicação
            TelaCadastro mainWindow = new TelaCadastro();
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