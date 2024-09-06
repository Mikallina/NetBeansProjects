package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe responsável pela conexão com o banco de dados MySQL.
 *
 * <p>
 * A classe {@code Conexao} gerencia a conexão com um banco de dados MySQL,
 * fornecendo métodos para conectar e desconectar. Ela utiliza o driver JDBC do
 * MySQL para estabelecer a conexão e permite a execução de operações de banco
 * de dados.</p>
 *
 * <p>
 * A URL de conexão, o nome de usuário e a senha são especificados como
 * propriedades da classe e podem ser ajustados conforme necessário.</p>
 *
 * @author mi_bo
 */
public class Conexao {

    /**
     * A conexão com o banco de dados.
     */
    public Connection conn;

    /**
     * A URL de conexão com o banco de dados.
     */
    public String url = "jdbc:mysql://localhost:3306/cenaflix";

    /**
     * O nome de usuário para a conexão com o banco de dados.
     */
    public String user = "root";

    /**
     * A senha para a conexão com o banco de dados.
     */
    public String password = "1234";

    /**
     * Estabelece a conexão com o banco de dados.
     *
     * <p>
     * Este método carrega o driver JDBC do MySQL, tenta se conectar ao banco de
     * dados utilizando as credenciais fornecidas e imprime uma mensagem de
     * sucesso ou falha. Se a conexão for bem-sucedida, o método retorna
     * {@code true}; caso contrário, retorna {@code false}.</p>
     *
     * @return {@code true} se a conexão for estabelecida com sucesso,
     * {@code false} caso contrário
     * @throws SQLException se ocorrer um erro ao tentar estabelecer a conexão
     */
    public boolean conectar() throws SQLException {
        try {
            // Carrega o driver JDBC do MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Estabelece a conexão com o banco de dados
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conexão realizada com sucesso");
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            // Imprime a mensagem de erro se a conexão falhar
            System.out.println("Falha na conexão com o banco: " + ex.getMessage());
            return false;
        }
    }

    /**
     * Fecha a conexão com o banco de dados.
     *
     * <p>
     * Este método fecha a conexão com o banco de dados se estiver aberta. Caso
     * ocorra um erro ao fechar a conexão, ele é tratado, mas não há mensagens
     * de erro imprimidas para o usuário.</p>
     *
     */
    public void desconectar() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException ex) {
            // Pode registrar o erro se desejar
        }
    }
}
