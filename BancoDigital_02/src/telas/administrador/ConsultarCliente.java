/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package telas.administrador;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.CadastroCliente;

/**
 *
 * @author mi_bo
 */
public class ConsultarCliente {

	private JFrame consultarCliente;
	private JTable table;
	private JButton btnEditar;
	private JButton btnExcluir;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultarCliente window = new ConsultarCliente();
					window.consultarCliente.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ConsultarCliente() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
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
            new Object[][] {},
            new String[] {
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
    }

    private void carregarClientesNaTabela() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Limpar tabela

        List<CadastroCliente> clientes = CadastroCliente.getClientesCadastrados();
        for (CadastroCliente cliente : clientes) {
            model.addRow(new Object[]{
                cliente.getCpf(),
                cliente.getNome(),
                cliente.getTelefone()
            });
        }
    }

	public void setVisible(boolean b) {
		consultarCliente.setVisible(true);
		
	}
}