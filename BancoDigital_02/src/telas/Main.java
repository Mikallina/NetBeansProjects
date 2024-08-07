package telas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import telas.administrador.CadastrarCliente;



public class Main {

	private JFrame telaMain;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.telaMain.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		telaMain = new JFrame();
		telaMain.setBounds(100, 100, 588, 467);
		telaMain.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		telaMain.getContentPane().setLayout(null);
		
		JButton btnCliente = new JButton("Cliente");
		btnCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login telaLogin = new Login();
				telaLogin.setVisible(true);
			}
		});
		btnCliente.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnCliente.setBounds(98, 171, 191, 35);
		telaMain.getContentPane().add(btnCliente);
		
		JButton btnAdministrador = new JButton("Administrador");
		btnAdministrador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Login telaLogin = new Login();
			        telaLogin.setVisible(true);
			        telaLogin.loginAsAdmin();
			}
		});
		btnAdministrador.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnAdministrador.setBounds(314, 171, 191, 35);
		telaMain.getContentPane().add(btnAdministrador);
	}

	public void setVisible(boolean b) {
		telaMain.setVisible(true);
		
	}
}
