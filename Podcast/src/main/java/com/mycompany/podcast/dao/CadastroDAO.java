package com.mycompany.podcast.dao;

import com.mycompany.podcast.conexao.model.PodcastCadastro;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) para gerenciar operações de persistência de podcasts no banco de dados.
 * 
 * <p>A classe {@code CadastroDAO} fornece métodos para adicionar, excluir e buscar podcasts na tabela {@code cadastro_podcast}.
 * Também permite buscar produtores únicos e podcasts por categoria. As operações de banco de dados são realizadas através de
 * JDBC.
 * 
 * @author mi_bo
 */
public class CadastroDAO {
    private static final String URL = "jdbc:mysql://localhost/podcast";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    /**
     * Obtém uma conexão com o banco de dados.
     * 
     * @return uma conexão com o banco de dados.
     * @throws SQLException se ocorrer um erro ao estabelecer a conexão.
     */
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /**
     * Adiciona um novo podcast ao banco de dados.
     * 
     * @param podcast um objeto {@code PodcastCadastro} contendo os detalhes do podcast a ser adicionado.
     */
    public static void adicionarPodcast(PodcastCadastro podcast) {
        String sql = "INSERT INTO cadastro_podcast (produtor, nomeEpisodio, numEpisodio, duracao, url) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, podcast.getProdutor());
            stmt.setString(2, podcast.getNomeEpisodio());
            stmt.setInt(3, podcast.getNumEpisodio());
            stmt.setInt(4, podcast.getDuracao());
            stmt.setString(5, podcast.getUrl());
            stmt.executeUpdate();
            System.out.println("Podcast adicionado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao adicionar Podcast: " + e.getMessage());
        }
    }

    /**
     * Remove um podcast do banco de dados pelo seu identificador.
     * 
     * @param idPodcast o identificador único do podcast a ser removido.
     */
    public static void excluirPodcast(int idPodcast) {
        String sql = "DELETE FROM cadastro_podcast WHERE idPodcast = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idPodcast);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Busca todos os produtores únicos no banco de dados.
     * 
     * @return uma lista de strings contendo os nomes dos produtores encontrados.
     */
    public List<String> buscarProdutor() {
        List<String> produtores = new ArrayList<>();
        String sql = "SELECT DISTINCT produtor FROM cadastro_podcast";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                produtores.add(rs.getString("produtor"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtores;
    }
    
    /**
     * Busca podcasts associados a um produtor específico.
     * 
     * @param produtor o nome do produtor para filtrar os podcasts.
     * @return uma lista de objetos {@code PodcastCadastro} que correspondem ao produtor fornecido.
     */
    public List<PodcastCadastro> buscarPodcastPorCategoria(String produtor) {
        List<PodcastCadastro> podcast = new ArrayList<>();
        String sql = "SELECT * FROM cadastro_podcast WHERE produtor = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, produtor);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    PodcastCadastro cadastro = new PodcastCadastro();
                    cadastro.setIdPodcast(rs.getInt("idPodcast"));
                    cadastro.setProdutor(rs.getString("produtor"));
                    cadastro.setNomeEpisodio(rs.getString("nomeEpisodio"));
                    cadastro.setNumEpisodio(rs.getInt("numEpisodio"));
                    cadastro.setDuracao(rs.getInt("duracao"));
                    cadastro.setUrl(rs.getString("url"));
                    podcast.add(cadastro);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return podcast;
    }

    /**
     * Busca todos os podcasts cadastrados no banco de dados.
     * 
     * @return uma lista de objetos {@code PodcastCadastro} contendo todos os podcasts encontrados.
     */
    public static List<PodcastCadastro> buscarTodosPodcast() {
        List<PodcastCadastro> podcasts = new ArrayList<>();
        String sql = "SELECT * FROM cadastro_podcast";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                PodcastCadastro podcast = new PodcastCadastro();
                podcast.setIdPodcast(rs.getInt("idPodcast"));
                podcast.setProdutor(rs.getString("produtor"));
                podcast.setNomeEpisodio(rs.getString("nomeEpisodio"));
                podcast.setNumEpisodio(rs.getInt("numEpisodio"));
                podcast.setDuracao(rs.getInt("duracao"));
                podcast.setUrl(rs.getString("url"));
                podcasts.add(podcast);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao buscar podcasts: " + e.getMessage());
        }
        return podcasts;
    }
    
    /**
     * Busca um podcast específico pelo seu identificador.
     * 
     * @param idPodcast o identificador único do podcast a ser buscado.
     * @return um objeto {@code PodcastCadastro} correspondente ao identificador fornecido, ou {@code null} se não encontrado.
     */
    public static PodcastCadastro buscarPodcastPorId(int idPodcast) {
        String sql = "SELECT * FROM cadastro_podcast WHERE idPodcast = ?";
        PodcastCadastro podcast = null;
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPodcast);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    podcast = new PodcastCadastro();
                    podcast.setIdPodcast(rs.getInt("idPodcast"));
                    podcast.setProdutor(rs.getString("produtor"));
                    podcast.setNomeEpisodio(rs.getString("nomeEpisodio"));
                    podcast.setNumEpisodio(rs.getInt("numEpisodio"));
                    podcast.setDuracao(rs.getInt("duracao"));
                    podcast.setUrl(rs.getString("url"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return podcast;
    }
}
