
package exercicio_2_22024;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class NutriSoft {

	public static JFrame frmNutrisoft;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NutriSoft window = new NutriSoft();
					window.frmNutrisoft.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the application.
	 */
	public NutriSoft() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNutrisoft = new JFrame();
		frmNutrisoft.setTitle("NutriSoft");
		frmNutrisoft.setBounds(100, 100, 526, 426);
		frmNutrisoft.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNutrisoft.getContentPane().setLayout(null);
		
		JButton btnRecomendacoes = new JButton("Recomenda\u00E7\u00F5es");
		btnRecomendacoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Recomendacoes telaRecomendacoes = new Recomendacoes();
				telaRecomendacoes.setVisible(true);
			}
		});
		btnRecomendacoes.setFont(new Font("Dialog", Font.PLAIN, 24));
		btnRecomendacoes.setBounds(125, 266, 243, 64);
		frmNutrisoft.getContentPane().add(btnRecomendacoes);
		
		JButton btnImc = new JButton("IMC");
		btnImc.setFont(new Font("Dialog", Font.PLAIN, 24));
		btnImc.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Imc telaImc = new Imc(); 
			    telaImc.setVisible(true);
			}
			
		});
		btnImc.setBounds(125, 43, 243, 64);
		frmNutrisoft.getContentPane().add(btnImc);
		
		JButton btnGastoCalorico = new JButton("Gasto Calórico");
		btnGastoCalorico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GastoCalorico telaGastoCalorico = new GastoCalorico();
				telaGastoCalorico.setVisible(true);
			}
		});
		btnGastoCalorico.setFont(new Font("Dialog", Font.PLAIN, 24));
		btnGastoCalorico.setBounds(125, 150, 243, 64);
		frmNutrisoft.getContentPane().add(btnGastoCalorico);
	}
}