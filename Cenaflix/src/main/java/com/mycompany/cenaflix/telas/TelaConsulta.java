package com.mycompany.cenaflix.telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.mycompany.cenaflix.model.Cadastro;
import dao.CadastroDAO;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

/**
 * Tela de Consulta de Filmes no sistema CENAFLIX.
 *
 * <p>
 * Esta tela permite visualizar, editar, excluir e cadastrar filmes. Além disso,
 * fornece a funcionalidade de filtrar filmes por categoria.</p>
 *
 * @author Michelle Boges
 * @version 1.0
 */
public class TelaConsulta {

    private JFrame frame;
    private JTable table;
    private JLabel lblNewLabel;
    private final CadastroDAO cadastroDAO;
    private JButton btnEditar;
    private JButton btnCadastrar;
    private JComboBox<String> comboBoxCategoria;

    /**
     * Lança a aplicação de consulta de filmes.
     *
     * <p>
     * Este método cria uma instância de {@link TelaConsulta} e a torna
     * visível.</p>
     *
     * @param args argumentos da linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaConsulta window = new TelaConsulta();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Cria a aplicação de consulta de filmes.
     *
     * <p>
     * Este construtor inicializa a tela de consulta e carrega os dados
     * necessários.</p>
     */
    public TelaConsulta() {
        cadastroDAO = new CadastroDAO();
        initialize();
    }

    /**
     * Interface funcional para callback após operações em filmes.
     *
     * <p>
     * Esta interface define um método {@code execute} que pode ser implementado
     * para executar uma ação após uma operação de filme, como atualização ou
     * exclusão.</p>
     */
    @FunctionalInterface
    public interface Callback {

        void execute();
    }

    /**
     * Inicializa os componentes da interface gráfica.
     *
     * <p>
     * Este método configura o layout da tela, adiciona os componentes e define
     * seus comportamentos, incluindo a tabela de filmes e os botões de
     * ação.</p>
     */
    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(255, 255, 255));
        frame.setBounds(100, 100, 782, 555);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 174, 748, 253);
        frame.getContentPane().add(scrollPane);

        table = new JTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
        table.setModel(
                new DefaultTableModel(new Object[][]{}, new String[]{"ID", "Nome do Filme", "Data", "Categoria"}));
        scrollPane.setViewportView(table); // Adiciona a tabela ao JScrollPane

        lblNewLabel = new JLabel("Consultar Filmes");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblNewLabel.setBounds(274, 50, 174, 29);
        frame.getContentPane().add(lblNewLabel);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    // Obter o ID do filme selecionado (assumindo que o ID é a primeira coluna)
                    int filmId = (int) table.getValueAt(selectedRow, 0);
                    int resposta = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir?", "Confirmação",
                            JOptionPane.YES_NO_OPTION);
                    if (resposta == JOptionPane.YES_NO_OPTION) {
                        cadastroDAO.excluirFilme(filmId);
                    }
                    JOptionPane.showMessageDialog(null, "Filme excluído com sucesso.");
                    // Remover a linha da tabela
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.removeRow(selectedRow);
                } else {
                    // Nenhuma linha foi selecionada
                    System.out.println("Selecione um filme para excluir.");
                }
            }
        });
        btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 24));
        btnExcluir.setBounds(230, 437, 187, 38);
        frame.getContentPane().add(btnExcluir);

        btnEditar = new JButton("Editar");
        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int filmId = (int) table.getValueAt(selectedRow, 0);
                    Cadastro cadastro = cadastroDAO.buscarCadastroPorId(filmId);
                    if (cadastro != null) {
                        TelaCadastro telaCadastro = new TelaCadastro(cadastro, TelaConsulta.this::carregarFilmes);
                        telaCadastro.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao recuperar as informações do filme.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um filme para editar.");
                }
            }
        });
        btnEditar.setFont(new Font("Tahoma", Font.BOLD, 24));
        btnEditar.setBounds(10, 437, 187, 38);
        frame.getContentPane().add(btnEditar);
        carregarFilmes();

        btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TelaCadastro telaInicial = new TelaCadastro();
                telaInicial.setVisible(true);
            }
        });
        btnCadastrar.setFont(new Font("Tahoma", Font.BOLD, 24));
        btnCadastrar.setBounds(571, 437, 187, 38);
        frame.getContentPane().add(btnCadastrar);

        comboBoxCategoria = new JComboBox<>();
        comboBoxCategoria.setBounds(628, 135, 130, 30);
        frame.getContentPane().add(comboBoxCategoria);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(0, 0, 2, 2);
        frame.getContentPane().add(scrollPane_1);

        JLabel lblNewLabel_1 = new JLabel("Buscar por Categoria:");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_1.setBounds(442, 138, 154, 20);
        frame.getContentPane().add(lblNewLabel_1);

        carregarCategorias();
        carregarFilmes("");
    }

    /**
     * Carrega as categorias disponíveis na combobox para filtragem.
     *
     * <p>
     * Este método preenche a combobox com categorias recuperadas do banco de
     * dados e define um listener para carregar filmes filtrados pela categoria
     * selecionada.</p>
     */
    private void carregarCategorias() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("Todas as Categorias");
        List<String> categorias = cadastroDAO.buscarCategorias(); // Método correto que retorna uma lista de categorias
        for (String categoria : categorias) {
            model.addElement(categoria);
        }
        comboBoxCategoria.setModel(model);

        comboBoxCategoria.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedCategory = (String) comboBoxCategoria.getSelectedItem();
                if ("Todas as Categorias".equals(selectedCategory)) {
                    carregarFilmes(""); // Passa uma string vazia para carregar todos os filmes
                } else {
                    carregarFilmes(selectedCategory); // Carrega filmes filtrados pela categoria selecionada
                }
            }
        });
    }
 

    /**
     * Carrega todos os filmes na tabela.
     *
     * <p>
     * Este método chama {@link #carregarFilmes(String)} com um filtro de
     * categoria vazio para carregar todos os filmes.</p>
     */

    private void carregarFilmes() {
        carregarFilmes("");
    }

    /**
     * Carrega filmes na tabela com base no filtro de categoria.
     *
     * @param filtroCategoria a categoria para filtrar filmes. Se vazio, todos
     * os filmes são carregados.
     *
     * <p>
     * Este método limpa a tabela atual e preenche com filmes recuperados do
     * banco de dados com base no filtro de categoria.</p>
     */

    private void carregarFilmes(String filtroCategoria) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Limpa a tabela antes de adicionar os novos dados

        List<Cadastro> filmes;
        if (filtroCategoria == null || filtroCategoria.isEmpty()) {
            filmes = cadastroDAO.buscarTodosFilmes(); // Método que busca todos os filmes
        } else {
            filmes = cadastroDAO.buscarFilmesPorCategoria(filtroCategoria); // Método que busca filmes pela categoria
        }

        for (Cadastro cadastro : filmes) {
            model.addRow(new Object[]{
                cadastro.getId(),
                cadastro.getNomeFilme(),
                cadastro.getData(),
                cadastro.getCategoria()
            });
        }
    }

    /**
     * Define a visibilidade da tela de consulta.
     *
     * @param b verdadeiro para tornar a tela visível, falso para ocultá-la
     */

    public void serVisible(boolean b) {
        frame.setVisible(true);

    }
}
