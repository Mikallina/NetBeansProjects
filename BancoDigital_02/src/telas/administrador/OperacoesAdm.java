package telas.administrador;

import telas.cliente.Extrato;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.CadastroCliente;
import model.ContaCliente;
import model.ContaCorrente;
import model.ContaPoupanca;
import telas.Main;



public class OperacoesAdm {

	private JFrame telaOperacoesAdm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OperacoesAdm window = new OperacoesAdm();
					window.telaOperacoesAdm.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public OperacoesAdm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		telaOperacoesAdm = new JFrame();
		telaOperacoesAdm.setBounds(100, 100, 474, 466);
		telaOperacoesAdm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		telaOperacoesAdm.getContentPane().setLayout(null);
		
		JButton btnCadastrarCliente = new JButton("Cadastrar Cliente");
		btnCadastrarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastrarCliente cadastrarCliente = new CadastrarCliente();
				cadastrarCliente.setVisible(true);
				
			}
		});
		btnCadastrarCliente.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnCadastrarCliente.setBounds(95, 102, 282, 54);
		telaOperacoesAdm.getContentPane().add(btnCadastrarCliente);
		
		JButton btnConsultarCliente = new JButton("Consultar Cliente");
		btnConsultarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConsultarCliente consultarCliente = new ConsultarCliente();
				consultarCliente.setVisible(true);
			}
		});
		btnConsultarCliente.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnConsultarCliente.setBounds(95, 178, 282, 54);
		telaOperacoesAdm.getContentPane().add(btnConsultarCliente);
	}

	public void setVisible(boolean b) {
		telaOperacoesAdm.setVisible(true);
		
	}

}