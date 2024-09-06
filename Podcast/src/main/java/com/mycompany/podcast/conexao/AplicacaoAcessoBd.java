package com.mycompany.podcast.conexao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe para demonstração de acesso ao banco de dados.
 * 
 * <p>A classe {@code AplicacaoAcessoBd} é uma aplicação simples que demonstra a conexão com um banco de dados e a execução
 * de uma consulta SQL. Ela utiliza a classe {@link Conexao} para estabelecer a conexão com o banco e realiza uma consulta
 * para contar o número de registros na tabela {@code cadastro}.</p>
 * 
 * <p>O método {@link #main(String[])} é o ponto de entrada da aplicação. Ele estabelece uma conexão com o banco de dados,
 * executa uma consulta SQL para contar o número de registros na tabela e exibe o resultado no console. Caso ocorra um erro
 * durante o processo, uma mensagem de erro é registrada no log.</p>
 * 
 * @author Michelle Borges
 */
public class AplicacaoAcessoBd {

    /**
     * Método principal que executa a aplicação.
     * 
     * <p>O método {@code main} cria uma instância da classe {@link Conexao} para conectar ao banco de dados, cria um
     * {@code Statement} para executar uma consulta SQL e obtém o número de registros na tabela {@code cadastro}. O resultado
     * é exibido no console. Em caso de erro durante a execução da consulta, o erro é registrado usando o {@link Logger}.</p>
     * 
     * @param args os argumentos de linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        try {
            // Cria uma instância da classe Conexao e estabelece a conexão com o banco de dados
            Conexao conector = new Conexao();
            conector.conectar();
            
            // Cria um Statement para executar a consulta SQL
            Statement st = conector.conn.createStatement();
            // Executa a consulta para contar o número de registros na tabela cadastro
            ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM usuario");
            // Exibe o número de registros no console
            if (rs.next()) {
                System.out.println("Número de registros: " + rs.getInt(1));
            }
        } catch (SQLException ex) {
            // Registra a exceção em caso de erro ao acessar o banco de dados
            Logger.getLogger(AplicacaoAcessoBd.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
