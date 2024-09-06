package com.mycompany.cenaflix.telas;

import dao.CadastroDAO;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

import com.mycompany.cenaflix.model.Cadastro;

/**
 * Tela de Cadastro e Edição de Filmes no sistema CENAFLIX.
 * Esta tela permite cadastrar novos filmes e editar filmes existentes.
 * 
 * <p>Utiliza um formulário para entrada de dados, incluindo o nome do filme, 
 * a data de lançamento e a categoria. Fornece botões para salvar, limpar os campos 
 * e consultar filmes existentes.</p>
 * 
 * @author Michelle Borges
 * @version 1.0
 */

public class TelaCadastro {

    private JFrame frame;
    private JTextField textNome;
    private JFormattedTextField textData;
    private JTextField textCategoria;
    private Cadastro cadastro;
    private Cadastro filmeEditado;
    private CadastroDAO cadastroDAO;
    private Runnable callback;

  /**
     * Lança a aplicação de cadastro de filmes.
     * 
     * <p>Este método cria uma instância de {@link TelaCadastro} e a torna visível.</p>
     * 
     * @param args argumentos da linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaCadastro window = new TelaCadastro();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    
    public TelaCadastro() {
        this(null, null);
    }
    
    /**
     * Cria a aplicação de cadastro de filmes.
     * 
     * <p>Este construtor cria uma instância de {@link TelaCadastro} com um filme editado opcional 
     * e um callback opcional.</p>
     * 
     * @param filmeEditado filme a ser editado, pode ser null para criar um novo filme
     * @param callback função a ser chamada após salvar as alterações
     */

    public TelaCadastro(Cadastro filmeEditado, Runnable callback) {
        this.filmeEditado = filmeEditado;
        this.callback = callback;
        cadastroDAO = new CadastroDAO();
        initialize();
        preencherCampos();
    }
    
      /**
     * Cria a aplicação de cadastro de filmes com um filme editado.
     * 
     * <p>Este construtor cria uma instância de {@link TelaCadastro} com um filme editado e 
     * sem callback.</p>
     * 
     * @param filmeEditado filme a ser editado
     */

    public TelaCadastro(Cadastro filmeEditado) {
        this(filmeEditado, null); // Chama o construtor com Runnable null
    }
    
    /**
     * Preenche os campos do formulário com os dados do filme editado, se disponível.
     * 
     * <p>Se {@link #filmeEditado} não for null, os campos do formulário serão preenchidos com 
     * as informações do filme.</p>
     */

    private void preencherCampos() {
        if (filmeEditado != null) {
            textNome.setText(filmeEditado.getNomeFilme());
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            textData.setText(sdf.format(filmeEditado.getData()));
            textCategoria.setText(filmeEditado.getCategoria());
        }
    }
    
    /**
     * Inicializa os componentes da interface gráfica.
     * 
     * <p>Este método configura o layout da tela, adiciona os componentes e define seus 
     * comportamentos.</p>
     */
    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(255, 255, 255));
        frame.setBackground(new Color(255, 255, 255));
        frame.setBounds(100, 100, 732, 492);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("CENAFLIX");
        lblNewLabel.setOpaque(true); // Make the background visible
        lblNewLabel.setBackground(new Color(192, 192, 192)); // Light gray background
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblNewLabel.setBounds(292, 28, 134, 37);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel(
                filmeEditado == null ? "<html><u>CADASTRO DE FILME</u></html>" : "<html><u>EDITAR FILME</u></html>");
        lblNewLabel_1.setToolTipText("");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblNewLabel_1.setBounds(243, 86, 229, 29);
        frame.getContentPane().add(lblNewLabel_1);

        JLabel labelNome = new JLabel("Nome do Filme:");
        labelNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
        labelNome.setBounds(140, 166, 142, 25);
        frame.getContentPane().add(labelNome);

        JLabel labelData = new JLabel("Data de Lançamento:");
        labelData.setFont(new Font("Tahoma", Font.PLAIN, 20));
        labelData.setBounds(92, 212, 190, 25);
        frame.getContentPane().add(labelData);

        JLabel labelCategoria = new JLabel("Categoria:");
        labelCategoria.setFont(new Font("Tahoma", Font.PLAIN, 20));
        labelCategoria.setBounds(191, 264, 91, 25);
        frame.getContentPane().add(labelCategoria);

        textNome = new JTextField();
        textNome.setBackground(new Color(189, 192, 196));
        textNome.setBounds(292, 166, 310, 26);
        frame.getContentPane().add(textNome);
        textNome.setColumns(10);

        try {
            MaskFormatter dateFormatter = new MaskFormatter("##/##/####");
            dateFormatter.setPlaceholderCharacter('_');
            textData = new JFormattedTextField(dateFormatter);
            textData.setBackground(new Color(189, 192, 196));
            textData.setBounds(292, 212, 310, 26);
            frame.getContentPane().add(textData);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        textCategoria = new JTextField();
        textCategoria.setBackground(new Color(189, 192, 196));
        textCategoria.setColumns(10);
        textCategoria.setBounds(292, 263, 310, 26);
        frame.getContentPane().add(textCategoria);

        JButton btnCadastrar = new JButton(filmeEditado == null ? "Cadastrar" : "Salvar");
        btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnCadastrar.addActionListener((ActionEvent e) -> {
            Cadastro cadastro = filmeEditado != null ? filmeEditado : new Cadastro();

            cadastro.setNomeFilme(textNome.getText());
            String dataString = textData.getText();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false); // Faz com que o formato seja estrito

            try {
                Date data = sdf.parse(dataString);
                cadastro.setData(data);
            } catch (ParseException ex) {
                Logger.getLogger(TelaCadastro.class.getName()).log(Level.SEVERE, "Data inválida. Por favor, use o formato dd/MM/yyyy.", ex);
            }

            cadastro.setCategoria(textCategoria.getText());

            if (cadastro.getNomeFilme().isEmpty() || cadastro.getCategoria().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Informar todos os campos");
            } else {
                CadastroDAO cadastroDao = new CadastroDAO();
                try {
                    if (filmeEditado != null) {
                        cadastroDao.atualizarFilme(cadastro);
                        JOptionPane.showMessageDialog(null, "Filme atualizado com sucesso");
                    } else {
                        cadastroDao.adicionarFilme(cadastro);
                        JOptionPane.showMessageDialog(null, "Filme cadastrado com sucesso");
                    }
                    if (callback != null) {
                        callback.run();
                    }

                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao processar o filme: " + ex.getMessage());
                }
            }
        });

        btnCadastrar.setBackground(new Color(192, 192, 192));
        btnCadastrar.setBounds(178, 320, 117, 31);
        frame.getContentPane().add(btnCadastrar);

        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.addActionListener((ActionEvent e) -> {
            textNome.setText("");
            textData.setText(""); // Ajuste o texto padrão conforme necessário
            textCategoria.setText("");
        });
        btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnLimpar.setBackground(Color.LIGHT_GRAY);
        btnLimpar.setBounds(325, 320, 111, 31);
        frame.getContentPane().add(btnLimpar);

        JButton btnConsultar = new JButton("Consultar");
        btnConsultar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TelaConsulta telaConsulta = new TelaConsulta();
                telaConsulta.serVisible(true);
            }
        });
        btnConsultar.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnConsultar.setBackground(Color.LIGHT_GRAY);
        btnConsultar.setBounds(465, 320, 111, 31);
        frame.getContentPane().add(btnConsultar);
    }
    
    /**
     * Define a visibilidade da tela de cadastro.
     * 
     * @param b verdadeiro para tornar a tela visível, falso para ocultá-la
     */
    public void setVisible(boolean b) {
        frame.setVisible(true);
    }
}
