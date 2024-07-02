package testeoak;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainFrame extends JFrame {
    private List<Produto> listaProdutos;
    private JTable tabelaProdutos;
    private DefaultTableModel model;

    public MainFrame() {
        super("Cadastro e Listagem de Produtos");

        listaProdutos = new ArrayList<>();

        // Criando e configurando os componentes da interface
        JPanel painelCadastro = new JPanel(new GridLayout(5, 2));
        JLabel labelNome = new JLabel("Nome do Produto:");
        JTextField campoNome = new JTextField(20);
        JLabel labelDescricao = new JLabel("Descrição do Produto:");
        JTextField campoDescricao = new JTextField(20);
        JLabel labelValor = new JLabel("Valor do Produto:");
        JTextField campoValor = new JTextField(10);
        JLabel labelDisponivel = new JLabel("Disponível para venda:");
        JComboBox<String> comboDisponivel = new JComboBox<>(new String[]{"Sim", "Não"});
        JButton botaoCadastrar = new JButton("Cadastrar");
        
        // Adicionando os componentes ao painel de cadastro
        painelCadastro.add(labelNome);
        painelCadastro.add(campoNome);
        painelCadastro.add(labelDescricao);
        painelCadastro.add(campoDescricao);
        painelCadastro.add(labelValor);
        painelCadastro.add(campoValor);
        painelCadastro.add(labelDisponivel);
        painelCadastro.add(comboDisponivel);
        painelCadastro.add(new JLabel()); // espaço vazio para layout
        painelCadastro.add(botaoCadastrar);

        // Configurando a tabela de listagem de produtos
        model = new DefaultTableModel();
        model.addColumn("Nome");
        model.addColumn("Valor");

        tabelaProdutos = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tabelaProdutos);

        // Configurando o layout da janela principal
        setLayout(new BorderLayout());
        add(painelCadastro, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Evento do botão cadastrar
        botaoCadastrar.addActionListener((ActionEvent e) -> {
            String nome = campoNome.getText();
            String descricao = campoDescricao.getText();
            double valor = Double.parseDouble(campoValor.getText());
            boolean disponivel = comboDisponivel.getSelectedItem().equals("Sim");
            
            Produto produto = new Produto(nome, descricao, valor, disponivel);
            listaProdutos.add(produto);
            
            // Limpar campos após cadastrar
            campoNome.setText("");
            campoDescricao.setText("");
            campoValor.setText("");
            comboDisponivel.setSelectedIndex(0);
            
            // Atualizar a tabela de produtos
            atualizarTabelaProdutos();
        });

        // Configurações da janela principal
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void atualizarTabelaProdutos() {
        // Limpa os dados atuais da tabela
        model.setRowCount(0);

        // Ordena a lista de produtos pelo valor (do menor para o maior)
        Collections.sort(listaProdutos, (p1, p2) -> Double.compare(p1.getValor(), p2.getValor()));

        // Adiciona os produtos ordenados à tabela
        for (Produto produto : listaProdutos) {
            Object[] row = {produto.getNome(), produto.getValor()};
            model.addRow(row);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame();
        });
    }
}