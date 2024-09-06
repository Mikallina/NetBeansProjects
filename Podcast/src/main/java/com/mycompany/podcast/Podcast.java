/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.podcast;

import com.mycompany.podcast.telas.TelaLogin;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * A classe {@code Podcast} é a classe principal da aplicação de podcast.
 * 
 * <p>Esta classe contém o método {@code main}, que é o ponto de entrada da aplicação. O método {@code main}
 * realiza as seguintes operações:
 * <ul>
 *     <li>Carrega o driver JDBC para MySQL.</li>
 *     <li>Estabelece uma conexão com um banco de dados MySQL localizado em {@code localhost} com o nome {@code podcast},</li>
 *     <li>Exibe uma janela principal da aplicação, que é a tela de login.</li>
 * </ul>
 * 
 * <p>O método {@code main} lida com as exceções que podem ocorrer durante o carregamento do driver JDBC ou durante
 * a conexão com o banco de dados, e garante que a conexão seja fechada adequadamente no bloco {@code finally}.
 * 
 * @author mi_bo
 */
public class Podcast {

    /**
     * O ponto de entrada da aplicação {@code Podcast}.
     * 
     * <p>Este método carrega o driver JDBC do MySQL, estabelece uma conexão com o banco de dados, e então exibe a janela
     * principal da aplicação, que é a tela de login. Se houver um erro ao carregar o driver ou ao conectar-se ao banco
     * de dados, a exceção é tratada e uma mensagem apropriada é exibida. A conexão com o banco de dados é fechada no
     * bloco {@code finally} para garantir o gerenciamento adequado dos recursos.
     * 
     * @param args argumentos de linha de comando passados para a aplicação, não utilizados nesta implementação.
     * @throws SQLException se ocorrer um erro ao tentar estabelecer a conexão com o banco de dados.
     */
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
