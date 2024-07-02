/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exercicio_3_22024.telas;

import exercicio_3_22024.model.Cadastro;
import exercicio_3_22024.model.Consulta;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public class Listagem {

    private JFrame frmListagem;
    private JScrollPane scrollPane;
    private JTable table;
    private JButton btnExcluirConsulta;
    private JButton btnFinalizarConsulta;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Listagem window = new Listagem();
                window.frmListagem.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Listagem() {
        initialize();
    }

    private void initialize() {
        frmListagem = new JFrame();
        frmListagem.setTitle("Listagem");
        frmListagem.setBounds(100, 100, 918, 697);
        frmListagem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmListagem.getContentPane().setLayout(null);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 97, 884, 438);
        frmListagem.getContentPane().add(scrollPane);

        table = new JTable();
        table.setFillsViewportHeight(true);
        table.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Paciente", "CPF", "Telefone", "Data", "Já era Paciente", "Consulta Realizada"
                }
        ));
        table.getColumnModel().getColumn(4).setPreferredWidth(85);
        table.getColumnModel().getColumn(5).setPreferredWidth(100);
        scrollPane.setViewportView(table);

        JButton btnNovaConsulta = new JButton("Nova Consulta");
        btnNovaConsulta.addActionListener((ActionEvent e) -> {
            CadastrarConsulta cadastrar = new CadastrarConsulta(this);
            cadastrar.setVisible(true);
        });
        btnNovaConsulta.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnNovaConsulta.setBounds(10, 563, 180, 48);
        frmListagem.getContentPane().add(btnNovaConsulta);

        btnExcluirConsulta = new JButton("Excluir Consulta");
        btnExcluirConsulta.addActionListener((ActionEvent e) -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                excluirConsulta(selectedRow);
            } else {
                // Mostrar mensagem de erro se nenhuma linha estiver selecionada
                JOptionPane.showMessageDialog(frmListagem, "Selecione uma consulta para excluir.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        btnExcluirConsulta.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnExcluirConsulta.setBounds(223, 563, 180, 48);
        frmListagem.getContentPane().add(btnExcluirConsulta);

        btnFinalizarConsulta = new JButton("Finalizar Consulta");
        btnFinalizarConsulta.addActionListener((ActionEvent e) -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                Cadastro consultaSelecionada = obterConsultaSelecionada(selectedRow);
                DetalhesConsulta detalhes = new DetalhesConsulta(this, consultaSelecionada);
                detalhes.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(frmListagem, "Selecione uma consulta para finalizar.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnFinalizarConsulta.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnFinalizarConsulta.setBounds(440, 563, 180, 48);
        frmListagem.getContentPane().add(btnFinalizarConsulta);

        JLabel lblNewLabel = new JLabel("Boas Vindas ao sistema de agendamento");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel.setBounds(10, 52, 381, 35);
        frmListagem.getContentPane().add(lblNewLabel);
    }

    private Consulta obterConsultaSelecionada(int rowIndex) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        String nome = (String) model.getValueAt(rowIndex, 0);
        String cpf = (String) model.getValueAt(rowIndex, 1);
        String telefone = (String) model.getValueAt(rowIndex, 2);
        String dataStr = (String) model.getValueAt(rowIndex, 3);
        boolean jaPaciente = "Sim".equals(model.getValueAt(rowIndex, 4));
        boolean consultaRealizada = "Sim".equals(model.getValueAt(rowIndex, 5));

        Consulta consulta = new Consulta();
        consulta.setNome(nome);
        consulta.setCpf(cpf);
        consulta.setTelefone(telefone);
        consulta.setCadastro(jaPaciente);
        consulta.setConsultaRealizada(consultaRealizada);

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dataConsulta = sdf.parse(dataStr);
            consulta.setData(dataConsulta);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return consulta;
    }

    public void atualizarTabela(Cadastro cadastro) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dataStr = sdf.format(cadastro.getData());

        model.addRow(new Object[]{
            cadastro.getNome(),
            cadastro.getCpf(),
            cadastro.getTelefone(),
            dataStr,
            cadastro.isCadastro() ? "Sim" : "Não",
            cadastro.isConsultaRealizada() ? "Sim" : "Não"
        });
    }

    public void excluirConsulta(int rowIndex) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.removeRow(rowIndex);
    }

    public JTable getTable() {
        return table;
    }

    public void limparTabela() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
    }

    public void atualizarConsulta(Cadastro cadastro) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 1).equals(cadastro.getCpf())) {
                model.setValueAt("Sim", i, 5); 
                //model.setValueAt(cadastro.getReceita(), i, 6);
                break;
            }
        }
    }
}
