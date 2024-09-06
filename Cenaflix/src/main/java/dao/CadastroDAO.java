package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.cenaflix.model.Cadastro;

/**
 * Classe responsável pelas operações de acesso aos dados no banco de dados para
 * a entidade {@link Cadastro}.
 *
 * <p>
 * A classe {@code CadastroDAO} fornece métodos para realizar operações CRUD
 * (Criar, Ler, Atualizar, Excluir) na tabela de cadastro de filmes do banco de
 * dados. Ela utiliza JDBC para se conectar e manipular o banco de dados
 * MySQL.</p>
 *
 * @author Michelle Borges
 */
public class CadastroDAO {

    /**
     * URL de conexão com o banco de dados MySQL.
     */
    private static final String URL = "jdbc:mysql://localhost:3306/cenaflix";

    /**
     * Nome de usuário para a conexão com o banco de dados MySQL.
     */
    private static final String USER = "root";

    /**
     * Senha para a conexão com o banco de dados MySQL.
     */
    private static final String PASSWORD = "1234";

    /**
     * Estabelece uma conexão com o banco de dados MySQL.
     *
     * @return A conexão com o banco de dados
     * @throws SQLException Se ocorrer um erro ao tentar estabelecer a conexão
     */
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /**
     * Adiciona um novo filme à tabela de cadastro no banco de dados.
     *
     * <p>
     * Este método insere um novo registro de filme na tabela de cadastro
     * utilizando os dados fornecidos na instância {@link Cadastro}.</p>
     *
     * @param cadastro A instância de {@link Cadastro} contendo os dados do
     * filme a ser adicionado
     */
    public void adicionarFilme(Cadastro cadastro) {
        String sql = "INSERT INTO cadastro (nome_filme, data, categoria) VALUES (?, ?, ?)";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cadastro.getNomeFilme());

            // Conversão de java.util.Date para java.sql.Date
            java.util.Date utilDate = (java.util.Date) cadastro.getData();
            if (utilDate != null) {
                Date sqlDate = new Date(utilDate.getTime());
                stmt.setDate(2, sqlDate);
            } else {
                stmt.setNull(2, java.sql.Types.DATE);
            }

            stmt.setString(3, cadastro.getCategoria());

            stmt.executeUpdate();
            System.out.println("Filme adicionado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao adicionar o filme: " + e.getMessage());
        }
    }

    /**
     * Busca todos os filmes cadastrados na tabela de cadastro.
     *
     * @return Uma lista de instâncias de {@link Cadastro} representando todos
     * os filmes na tabela
     */

    public List<Cadastro> buscarTodosFilmes() {
        List<Cadastro> filmes = new ArrayList<>();
        String sql = "SELECT id, nome_filme, data, categoria FROM cadastro";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Cadastro cadastro = new Cadastro();
                cadastro.setId(rs.getInt("id"));
                cadastro.setNomeFilme(rs.getString("nome_filme"));
                cadastro.setData(rs.getDate("data"));
                cadastro.setCategoria(rs.getString("categoria"));
                filmes.add(cadastro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filmes;
    }

    /**
     * Busca todas as categorias únicas de filmes na tabela de cadastro.
     *
     * @return Uma lista de categorias de filmes
     */
    public List<String> buscarCategorias() {
        List<String> categorias = new ArrayList<>();
        String sql = "SELECT DISTINCT categoria FROM cadastro"; // Consulta para obter categorias únicas
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                categorias.add(rs.getString("categoria"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categorias;
    }

    /**
     * Busca filmes que pertencem a uma categoria específica.
     *
     * @param categoria A categoria de filmes a ser pesquisada
     * @return Uma lista de instâncias de {@link Cadastro} representando os
     * filmes na categoria especificada
     */
    public List<Cadastro> buscarFilmesPorCategoria(String categoria) {
        List<Cadastro> filmes = new ArrayList<>();
        String sql = "SELECT * FROM cadastro WHERE categoria = ?"; // Ajuste conforme necessário
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, categoria);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Cadastro cadastro = new Cadastro();
                    cadastro.setId(rs.getInt("id")); // Ajuste conforme necessário
                    cadastro.setNomeFilme(rs.getString("nome_filme")); // Ajuste conforme necessário
                    cadastro.setData(rs.getDate("data")); // Ajuste conforme necessário
                    cadastro.setCategoria(rs.getString("categoria")); // Ajuste conforme necessário
                    filmes.add(cadastro);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filmes;
    }

    /**
     * Busca um filme específico pelo ID.
     *
     * @param id O ID do filme a ser pesquisado
     * @return A instância de {@link Cadastro} representando o filme com o ID
     * especificado, ou {@code null} se não encontrado
     */
    public Cadastro buscarCadastroPorId(int id) {
        Cadastro cadastro = null;
        String sql = "SELECT * FROM cadastro WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    cadastro = new Cadastro();
                    cadastro.setId(rs.getInt("id"));
                    cadastro.setNomeFilme(rs.getString("nome_filme"));
                    cadastro.setData(rs.getDate("data"));
                    cadastro.setCategoria(rs.getString("categoria"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cadastro;
    }

    /**
     * Exclui um filme da tabela de cadastro pelo ID.
     *
     * @param id O ID do filme a ser excluído
     */
    public void excluirFilme(int id) {
        String sql = "DELETE FROM cadastro WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Atualiza os dados de um filme na tabela de cadastro.
     *
     * @param cadastro A instância de {@link Cadastro} contendo os novos dados
     * do filme e o ID para identificar o registro a ser atualizado
     */
    public void atualizarFilme(Cadastro cadastro) {
        String sql = "UPDATE cadastro SET nome_filme = ?, data = ?, categoria = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cadastro.getNomeFilme());
            stmt.setDate(2, new java.sql.Date(cadastro.getData().getTime()));
            stmt.setString(3, cadastro.getCategoria());
            stmt.setInt(4, cadastro.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
