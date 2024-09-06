package com.mycompany.podcast.telas;

import com.mycompany.podcast.conexao.Criptografia;
import com.mycompany.podcast.conexao.UsuarioBd;
import com.mycompany.podcast.conexao.model.Usuario;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.sql.SQLException;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Tela de login da aplicação.
 * 
 * <p>A classe {@code TelaLogin} fornece a interface gráfica para o login do usuário na aplicação. Ela permite que o usuário insira seu login e senha, 
 * realiza a autenticação e, em caso de sucesso, redireciona o usuário para a tela de listagem.</p>
 * 
 * <p>O login e a senha são verificados contra o banco de dados e a permissão do usuário logado é armazenada em uma variável estática.</p>
 * 
 * @author mi_bo
 */
public class TelaLogin {

    private JFrame frame;
    private JTextField textLogin;
    private JTextField textSenha;
    private static Usuario usuarioLogado;

    /**
     * Lança a aplicação.
     * 
     * <p>Este método cria e exibe a janela da aplicação {@code TelaLogin}.</p>
     * 
     * @param args argumentos da linha de comando.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaLogin window = new TelaLogin();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Cria a aplicação {@code TelaLogin}.
     */
    public TelaLogin() {
        initialize();
    }

    /**
     * Obtém o usuário logado.
     * 
     * @return O usuário atualmente logado na aplicação.
     */
    public static Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    /**
     * Define o usuário logado.
     * 
     * @param usuario O usuário a ser definido como logado.
     */
    public static void setUsuarioLogado(Usuario usuario) {
        usuarioLogado = usuario;
    }

    /**
     * Inicializa o conteúdo da janela.
     * 
     * <p>Este método configura os componentes da interface gráfica, incluindo campos de entrada para login e senha, 
     * e o botão de login. Também define a lógica para autenticar o usuário e redirecionar para a tela de listagem em caso de sucesso.</p>
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 721, 556);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("CENAFLIX");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblNewLabel.setBounds(269, 29, 153, 37);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Login:");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_1.setBounds(181, 143, 50, 20);
        frame.getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("Senha:");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_1_1.setBounds(181, 255, 56, 20);
        frame.getContentPane().add(lblNewLabel_1_1);

        textLogin = new JTextField();
        textLogin.setBounds(181, 173, 367, 25);
        frame.getContentPane().add(textLogin);
        textLogin.setColumns(10);

        textSenha = new JTextField();
        textSenha.setColumns(10);
        textSenha.setBounds(181, 285, 367, 25);
        frame.getContentPane().add(textSenha);

        JButton btnNewButton = new JButton("LOGIN");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Usuario usuario = new Usuario();
                usuario.setLogin(textLogin.getText());
                usuario.setSenha(textSenha.getText());

                try {
                    usuario = UsuarioBd.validarUsuarioSeguro(usuario);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

                if (usuario != null) {
                    // Armazena o usuário logado
                    setUsuarioLogado(usuario);

                    JOptionPane.showMessageDialog(null, "Olá " + usuario.getLogin() + " sua permissão é de " + usuario.getTipo() + ". Seja Bem Vindo");
                    TelaListagem telaListagem = new TelaListagem();
                    telaListagem.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Erro de autenticação! Verifique se os dados estão corretos.");
                }
            }
        });
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnNewButton.setBounds(282, 368, 153, 33);
        frame.getContentPane().add(btnNewButton);
    }

    /**
     * Define a visibilidade da janela de login.
     * 
     * @param b {@code true} para tornar a janela visível, {@code false} para ocultá-la.
     */
    public void setVisible(boolean b) {
        frame.setVisible(b);
    }
}
