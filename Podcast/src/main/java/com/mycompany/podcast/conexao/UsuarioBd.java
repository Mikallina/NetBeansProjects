package com.mycompany.podcast.conexao;

import com.mycompany.podcast.conexao.model.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A classe {@code UsuarioBd} é responsável por interagir com o banco de dados para operações relacionadas a usuários.
 * 
 * <p>Esta classe contém métodos para validar usuários, consultando o banco de dados para verificar se as credenciais fornecidas
 * correspondem a um usuário registrado.
 * 
 * @author mi_bo
 */
public class UsuarioBd {

    /**
     * Valida um usuário comparando o login e a senha fornecidos com os dados armazenados no banco de dados.
     * 
     * <p>Este método executa uma consulta SQL para verificar se existe um usuário no banco de dados com o login e senha fornecidos.
     * Se um usuário correspondente for encontrado, um objeto {@code Usuario} é criado e retornado. Caso contrário, o método retorna {@code null}.
     * 
     * @param usuario um objeto {@code Usuario} contendo o login e a senha a serem validados.
     * @return um objeto {@code Usuario} correspondente ao login e senha fornecidos, ou {@code null} se o usuário não for encontrado.
     * @throws SQLException se ocorrer um erro ao acessar o banco de dados ou executar a consulta SQL.
     */
    public static Usuario validarUsuarioSeguro(Usuario usuario) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE login = ? AND senha = ?";
        Usuario usuarioEncontrado = null;
        
        try (Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/podcast", "root", "1234");
             PreparedStatement statement = conexao.prepareStatement(sql)) {
             
            // Atribuindo os valores na consulta
            statement.setString(1, usuario.getLogin());
            statement.setString(2, usuario.getSenha());
            
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    // Se encontrou o usuário
                    usuarioEncontrado = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("tipo"),
                        rs.getString("login"),
                        rs.getString("senha")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; // Repassa a exceção para o chamador
        }
        
        return usuarioEncontrado;
    }
}
