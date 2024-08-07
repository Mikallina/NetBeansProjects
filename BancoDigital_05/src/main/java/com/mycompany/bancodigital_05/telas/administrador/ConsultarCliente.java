package com.mycompany.bancodigital_05.telas.administrador;

import com.mycompany.bancodigital_05.DAO.CadastroClienteDAO;
import com.mycompany.bancodigital_05.model.CadastroCliente;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mi_bo
 */
public class ConsultarCliente {

    private JFrame consultarCliente;
    private JTable table;
    private JButton btnEditar;
    private JButton btnExcluir;
    private CadastroCliente clienteSelecionado;
    private final CadastroClienteDAO clienteDAO;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ConsultarCliente window = new ConsultarCliente();
                window.consultarCliente.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public ConsultarCliente() {
        clienteDAO = new CadastroClienteDAO();
        initialize();
    }

    private void initialize() {
        consultarCliente = new JFrame();
        consultarCliente.setBounds(100, 100, 610, 509);
        consultarCliente.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        consultarCliente.getContentPane().setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 92, 576, 255);
        consultarCliente.getContentPane().add(scrollPane);

        table = new JTable();
        DefaultTableModel model = new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "CPF", "Nome", "Telefone"
                }
        );
        table.setModel(model);
        scrollPane.setViewportView(table);

        btnEditar = new JButton("Editar");
        btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 24));
        btnEditar.setBounds(126, 385, 151, 31);
        consultarCliente.getContentPane().add(btnEditar);

        btnExcluir = new JButton("Excluir");
        btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 24));
        btnExcluir.setBounds(299, 385, 151, 31);
        consultarCliente.getContentPane().add(btnExcluir);

        JLabel lblNewLabel = new JLabel("Consulta de Clientes");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblNewLabel.setBounds(186, 28, 215, 29);
        consultarCliente.getContentPane().add(lblNewLabel);

        // Preencher tabela com dados de clientes
        carregarClientesNaTabela();

         btnEditar.addActionListener((ActionEvent e) -> {
             int selectedRow = table.getSelectedRow();
             if (selectedRow >= 0) {
                 String cpf = table.getValueAt(selectedRow, 0).toString();
                 CadastroCliente cliente = clienteDAO.buscarClientePorCpf(cpf);
                 if (cliente != null) {
                     CadastrarCliente cadastrarCliente = new CadastrarCliente(cliente, this::carregarClientesNaTabela);
                     cadastrarCliente.setVisible(true);        
                 } else {
                     JOptionPane.showMessageDialog(null,"Cliente não encontrado.");
                 }
             } else {
                 JOptionPane.showMessageDialog(null, "Por favor, selecione um cliente.");
             }
        });

        btnExcluir.addActionListener((ActionEvent e) -> {
            if (clienteSelecionado != null) {
                excluirCliente(clienteSelecionado);
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um cliente para excluir.");
            }
        });

         table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        String cpf = (String) table.getValueAt(selectedRow, 0);
                        System.out.println("CPF selecionado: " + cpf);  // Log CPF selecionado
                        clienteSelecionado = clienteDAO.buscarClientePorCpf(cpf);
                      
                        if (clienteSelecionado != null) {
                            System.out.println("Cliente selecionado: " + clienteSelecionado.getCpf());  // Log cliente selecionado
                        } else {
                            System.out.println("Cliente não encontrado para o CPF: " + cpf);
                        }
                    } else {
                        clienteSelecionado = null;
                    }
                    
                }
                 
            }
            
        });
    }
    
    void carregarClientesNaTabela() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Limpar tabela
        List<CadastroCliente> clientes = clienteDAO.buscarTodosClientes();
        for (CadastroCliente cadastroCliente : clientes) {
            model.addRow(new Object[]{
                cadastroCliente.getCpf(),
                cadastroCliente.getNome(),
                cadastroCliente.getTelefone()
            });
        }
    }

    private CadastroCliente buscarClientePorCpf(String cpf) {
        return clienteDAO.buscarClientePorCpf(cpf);
    }


    private void excluirCliente(CadastroCliente cliente) {
        int resposta = JOptionPane.showConfirmDialog(null, "Tem certeza de que deseja excluir o cliente?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (resposta == JOptionPane.YES_OPTION) {
            clienteDAO.excluirCliente(cliente.getCpf());
            JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso.");
            carregarClientesNaTabela(); // Atualizar a tabela
        }
    }

    public void setVisible(boolean b) {
        consultarCliente.setVisible(b);
    }
}