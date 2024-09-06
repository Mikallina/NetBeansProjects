package com.mycompany.podcast.telas;

import com.mycompany.podcast.conexao.model.PodcastCadastro;
import com.mycompany.podcast.dao.CadastroDAO;
import com.mycompany.podcast.conexao.model.Usuario;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Tela de listagem de podcasts.
 * 
 * <p>A classe {@code TelaListagem} fornece uma interface gráfica para visualizar e gerenciar a listagem de podcasts cadastrados.
 * A interface inclui uma tabela para exibir os podcasts e opções para cadastrar novos podcasts e excluir os existentes.
 * Também permite filtrar os podcasts por produtor através de uma caixa de combinação.</p>
 * 
 * <p>Esta classe também controla a visibilidade dos botões com base no tipo de usuário logado e atualiza a tabela e os filtros conforme necessário.</p>
 * 
 * @author mi_bo
 */
public class TelaListagem {

    private JFrame frame;
    private JTable table;
    private JButton btnExcluir;
    private JButton btnCadastrar;
    private PodcastCadastro podcastSelecionado; // Variável para armazenar o podcast selecionado
    private JComboBox<String> comboBoxProdutor;
    private final CadastroDAO cadastroDAO;

    /**
     * Lança a aplicação.
     * 
     * <p>Este método cria e exibe a janela da aplicação {@code TelaListagem}.</p>
     * 
     * @param args argumentos da linha de comando.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TelaListagem window = new TelaListagem();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Cria a aplicação {@code TelaListagem}.
     */
    public TelaListagem() {
        cadastroDAO = new CadastroDAO();
        initialize();
    }

    /**
     * Inicializa o conteúdo da janela.
     * 
     * <p>Este método configura os componentes da interface gráfica, incluindo a tabela para exibição dos podcasts, 
     * a caixa de combinação para filtrar podcasts por produtor, e os botões para ações de exclusão e cadastro.</p>
     */
    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setBounds(100, 100, 782, 624);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("LISTAGEM");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblNewLabel.setBounds(309, 81, 126, 29);
        frame.getContentPane().add(lblNewLabel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 205, 748, 253);
        frame.getContentPane().add(scrollPane);

        table = new JTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
        table.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "ID", "Produtor", "Nome do Episodio", "Nº Episódio", "Duração", "URL do Repo"
                }
        ));
        scrollPane.setViewportView(table);

        JLabel lblCenaflix = new JLabel("CENAFLIX");
        lblCenaflix.setFont(new Font("Tahoma", Font.BOLD, 36));
        lblCenaflix.setBounds(279, 27, 190, 44);
        frame.getContentPane().add(lblCenaflix);

        JLabel lblNewLabel_1 = new JLabel("Pesquisa podcast por produtor:");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_1.setBounds(10, 166, 253, 20);
        frame.getContentPane().add(lblNewLabel_1);

        comboBoxProdutor = new JComboBox<>();
        comboBoxProdutor.setBounds(279, 169, 249, 19);
        frame.getContentPane().add(comboBoxProdutor);
       
        btnExcluir = new JButton("EXCLUIR");
        btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnExcluir.setBounds(598, 493, 160, 44);
        frame.getContentPane().add(btnExcluir);

        btnCadastrar = new JButton("CADASTRAR");
        btnCadastrar.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnCadastrar.setBounds(428, 493, 160, 44);
        frame.getContentPane().add(btnCadastrar);
        
        carregarProdutor();
        carregarPodcast("");

        // Adiciona ActionListener ao botão "Excluir"
        btnExcluir.addActionListener((ActionEvent e) -> {
            if (podcastSelecionado != null) {
                int resposta = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir?", "Confirmação",
                        JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.YES_OPTION) {
                    excluirPodcast(podcastSelecionado);
                    carregarPodcast();
                }
                JOptionPane.showMessageDialog(null, "Podcast excluído com sucesso.");
                
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um podcast para excluir.");
            }
        });

        // Adiciona ListSelectionListener à tabela
        table.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int idPodcast = (Integer) table.getValueAt(selectedRow, 0); // Obtém o ID do podcast
                    System.out.println("ID do Podcast selecionado: " + idPodcast);

                    // Busca o podcast com base no ID
                    podcastSelecionado = CadastroDAO.buscarPodcastPorId(idPodcast);

                    if (podcastSelecionado != null) {
                        System.out.println("Podcast selecionado: " + podcastSelecionado.getNomeEpisodio());
                    } else {
                        System.out.println("Podcast não encontrado para o ID: " + idPodcast);
                    }
                } else {
                    podcastSelecionado = null;
                }
            }
        });

        // Adiciona ActionListener ao botão "Cadastrar"
        btnCadastrar.addActionListener((ActionEvent e) -> {
            TelaCadastrar cadastrar = new TelaCadastrar();
            cadastrar.setVisible(true);
        });

        // Atualiza os botões com base no tipo de usuário logado
        atualizarBotoes();

        // Carrega podcasts na tabela
        carregarPodcast();
    }

    /**
     * Atualiza a habilitação dos botões de acordo com o tipo de usuário logado.
     * 
     * <p>Os botões são habilitados ou desabilitados com base no tipo de usuário logado: Administrador, Operador ou Usuário.</p>
     */
    private void atualizarBotoes() {
        Usuario usuario = TelaLogin.getUsuarioLogado(); // Obtém o usuário logado

        if (usuario != null) {
            if (usuario.getTipo().equals("Administrador")) {
                btnExcluir.setEnabled(true);
                btnCadastrar.setEnabled(true);
            } else if (usuario.getTipo().equals("Operador")) {
                btnExcluir.setEnabled(false);
                btnCadastrar.setEnabled(true);
            } else if (usuario.getTipo().equals("Usuario")) {
                btnExcluir.setEnabled(false);
                btnCadastrar.setEnabled(false);
            }
        }
    }

    /**
     * Exclui um podcast.
     * 
     * @param podcast O podcast a ser excluído.
     * 
     * <p>Remove o podcast da base de dados e atualiza a tabela de podcasts exibida na interface gráfica.</p>
     */
    private void excluirPodcast(PodcastCadastro podcast) {
        if (podcast != null) {
            int idPodcast = podcast.getIdPodcast(); // Obtém o ID do podcast
            CadastroDAO.excluirPodcast(idPodcast); // Passa o ID para o método de exclusão
            carregarPodcast(); // Atualiza a tabela após exclusão
        }
    }

    /**
     * Carrega todos os podcasts na tabela.
     * 
     * <p>Busca todos os podcasts da base de dados e os exibe na tabela.</p>
     */
    void carregarPodcast() {
        carregarPodcast(""); // Carrega todos os podcasts
    }

    /**
     * Carrega podcasts na tabela com base no filtro de produtor.
     * 
     * @param filtroProdutor O filtro de produtor para pesquisar os podcasts. Se vazio, carrega todos os podcasts.
     * 
     * <p>Atualiza a tabela de podcasts com base no filtro fornecido.</p>
     */
    private void carregarPodcast(String filtroProdutor) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Limpa a tabela antes de adicionar os novos dados

        List<PodcastCadastro> podcasts;
        if (filtroProdutor == null || filtroProdutor.isEmpty()) {
            podcasts = cadastroDAO.buscarTodosPodcast(); // Busca todos os podcasts
        } else {
            podcasts = cadastroDAO.buscarPodcastPorCategoria(filtroProdutor); // Busca podcasts pelo produtor
        }

        for (PodcastCadastro cadastro : podcasts) {
            model.addRow(new Object[]{
                cadastro.getIdPodcast(),
                cadastro.getProdutor(),
                cadastro.getNomeEpisodio(),
                cadastro.getNumEpisodio(),
                cadastro.getDuracao(),
                cadastro.getUrl()
            });
        }
    }

    /**
     * Carrega a lista de produtores no filtro de produtores.
     * 
     * <p>Preenche a caixa de combinação com a lista de produtores disponíveis e adiciona um listener para atualizar a tabela de podcasts
     * com base no produtor selecionado.</p>
     */
    private void carregarProdutor() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("Todos os Produtores");
        List<String> produtores = cadastroDAO.buscarProdutor(); // Busca todos os produtores disponíveis
        for (String produtor : produtores) {
            model.addElement(produtor);
        }
        comboBoxProdutor.setModel(model);

        comboBoxProdutor.addActionListener((ActionEvent e) -> {
            String selectedCategory = (String) comboBoxProdutor.getSelectedItem();
            if ("Todos os Produtores".equals(selectedCategory)) {
                carregarPodcast(""); // Carrega todos os podcasts
            } else {
                carregarPodcast(selectedCategory); // Carrega podcasts filtrados pelo produtor selecionado
            }
        });
    }

    /**
     * Define a visibilidade da janela de listagem.
     * 
     * @param b {@code true} para tornar a janela visível, {@code false} para ocultá-la.
     */
    void setVisible(boolean b) {
        frame.setVisible(b);
    }
}
