/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exercicio_2_22024;

/**
 *
 * @author mi_bo
 */

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class Recomendacoes {

	private JFrame telaRecomendacoes;
	private JTextField textCalorias;
	private JLabel lblCarboidratosResult;
	private JLabel lblProteinaResult;
	private JLabel lblGorduraResult;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Recomendacoes window = new Recomendacoes();
					window.telaRecomendacoes.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Recomendacoes() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		telaRecomendacoes = new JFrame();
		telaRecomendacoes.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				NutriSoft.frmNutrisoft.setVisible(true);
			}
		});
		telaRecomendacoes = new JFrame();
		telaRecomendacoes.setTitle("Recomendações");
		telaRecomendacoes.setBounds(100, 100, 450, 458);
		telaRecomendacoes.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		telaRecomendacoes.getContentPane().setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(10, 227, 416, 184);
		telaRecomendacoes.getContentPane().add(panel_1);

		JLabel lblCarboidratos = new JLabel("Carboidratos (50%):");
		lblCarboidratos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCarboidratos.setBounds(24, 10, 382, 58);
		panel_1.add(lblCarboidratos);

		JLabel lblProteina = new JLabel("Proteína (25%):");
		lblProteina.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblProteina.setBounds(24, 63, 382, 48);
		panel_1.add(lblProteina);

		JLabel lblGordura = new JLabel("Gordura (25%):");
		lblGordura.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGordura.setBounds(24, 106, 382, 48);
		panel_1.add(lblGordura);

		lblCarboidratosResult = new JLabel("");
		lblCarboidratosResult.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCarboidratosResult.setBounds(218, 10, 188, 58);
		panel_1.add(lblCarboidratosResult);

		lblProteinaResult = new JLabel("");
		lblProteinaResult.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblProteinaResult.setBounds(218, 63, 188, 48);
		panel_1.add(lblProteinaResult);

		lblGorduraResult = new JLabel("");
		lblGorduraResult.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGorduraResult.setBounds(218, 106, 188, 48);
		panel_1.add(lblGorduraResult);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 10, 416, 214);
		telaRecomendacoes.getContentPane().add(panel);

		textCalorias = new JTextField();
		textCalorias.setColumns(10);
		textCalorias.setBounds(218, 46, 188, 32);
		panel.add(textCalorias);

		JLabel lblCalorias = new JLabel("Calorias diárias (kcal):");
		lblCalorias.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCalorias.setBounds(10, 49, 198, 23);
		panel.add(lblCalorias);

		JButton btnCalcular = new JButton("Calcular");
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calcularRecomendacoes();
			}
		});
		btnCalcular.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCalcular.setBounds(244, 115, 123, 32);
		panel.add(btnCalcular);

	}

	public void setVisible(boolean b) {
		telaRecomendacoes.setVisible(true);

	}

	private void calcularRecomendacoes() {
		try {
			double calorias = Double.parseDouble(textCalorias.getText());
			double carboidratos = calorias * 0.5 / 4;
			double proteina = calorias * 0.25 / 4;
			double gordura = calorias * 0.25 / 9;

			lblCarboidratosResult.setText(String.format("%.2f g", carboidratos));
			lblProteinaResult.setText(String.format("%.2f g", proteina));
			lblGorduraResult.setText(String.format("%.2f g", gordura));
		} catch (NumberFormatException ex) {
			lblCarboidratosResult.setText("Informe um valor válido");
			lblProteinaResult.setText("");
			lblGorduraResult.setText("");
		}
	}

}
