/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exercicio_2_22024;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;

public class GastoCalorico {

	private JFrame telaGastoCalorico;
	private JTextField textPeso;
	private JTextField textAltura;
	private JTextField textIdade;
	private JLabel lblGastoBasal;
	private JLabel lblGastoTotal;
	private JRadioButton rdbtnHomem;
	private JRadioButton rdbtnMulher;
	private JComboBox<String> comboBox;
	private ButtonGroup sexoGroup;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GastoCalorico window = new GastoCalorico();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GastoCalorico() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		telaGastoCalorico = new JFrame();
		telaGastoCalorico.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				NutriSoft.frmNutrisoft.setVisible(true);
			}
		});
		telaGastoCalorico = new JFrame();
		telaGastoCalorico.setTitle("GastoCalorico");
		telaGastoCalorico.setBounds(100, 100, 464, 646);
		telaGastoCalorico.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		telaGastoCalorico.getContentPane().setLayout(null);

		JLabel lblClculoGastoCalrico = new JLabel("Cálculo Gasto Calórico");
		lblClculoGastoCalrico.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblClculoGastoCalrico.setBounds(26, 22, 207, 42);
		telaGastoCalorico.getContentPane().add(lblClculoGastoCalrico);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(26, 74, 373, 345);
		telaGastoCalorico.getContentPane().add(panel);

		textPeso = new JTextField();
		textPeso.setColumns(10);
		textPeso.setBounds(184, 81, 158, 32);
		panel.add(textPeso);

		textAltura = new JTextField();
		textAltura.setColumns(10);
		textAltura.setBounds(183, 123, 159, 32);
		panel.add(textAltura);

		JLabel lblPeso = new JLabel("Peso (kg):");
		lblPeso.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPeso.setBounds(11, 85, 98, 23);
		panel.add(lblPeso);

		JLabel lblAltura = new JLabel("Altura (m):");
		lblAltura.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAltura.setBounds(11, 124, 98, 23);
		panel.add(lblAltura);

		JButton btnCalcular = new JButton("Calcular");
		btnCalcular.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCalcular.setBounds(196, 291, 123, 32);
		btnCalcular.addActionListener(e -> calcularGastoCalorico());
		panel.add(btnCalcular);

		JLabel lblIdade = new JLabel("Idade:");
		lblIdade.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblIdade.setBounds(10, 166, 98, 23);
		panel.add(lblIdade);

		textIdade = new JTextField();
		textIdade.setColumns(10);
		textIdade.setBounds(183, 162, 159, 32);
		panel.add(textIdade);

		JLabel lblNivelAtividade = new JLabel("Nível de Atividade:");
		lblNivelAtividade.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNivelAtividade.setBounds(11, 211, 172, 23);
		panel.add(lblNivelAtividade);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Sedentário", "Leve  (1 a 3 dias/semana)",
				"Moderado  (3 a 5 dias/semana)", "Ativo  (5 a 6 dias/semana)", "Extremamente Ativo (pesado diário)" }));
		comboBox.setBounds(184, 204, 158, 33);
		panel.add(comboBox);

		sexoGroup = new ButtonGroup();
		sexoGroup.add(rdbtnMulher);
		sexoGroup.add(rdbtnHomem);

		rdbtnMulher = new JRadioButton("Mulher");
		rdbtnMulher.setFont(new Font("Tahoma", Font.PLAIN, 20));
		rdbtnMulher.setBounds(11, 29, 103, 21);
		panel.add(rdbtnMulher);
		sexoGroup.add(rdbtnMulher);

		rdbtnHomem = new JRadioButton("Homem");
		rdbtnHomem.setFont(new Font("Tahoma", Font.PLAIN, 20));
		rdbtnHomem.setBounds(160, 29, 103, 21);
		panel.add(rdbtnHomem);
		sexoGroup.add(rdbtnHomem);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(24, 426, 373, 159);
		telaGastoCalorico.getContentPane().add(panel_1);

		lblGastoBasal = new JLabel("Gasto Basal:");
		lblGastoBasal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGastoBasal.setBounds(24, 30, 275, 23);
		panel_1.add(lblGastoBasal);

		lblGastoTotal = new JLabel("Gasto Total:");
		lblGastoTotal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGastoTotal.setBounds(24, 82, 275, 48);
		panel_1.add(lblGastoTotal);

		telaGastoCalorico.setVisible(true);
	}

	private void calcularGastoCalorico() {
		try {
			double peso = Double.parseDouble(textPeso.getText());
			double altura = Double.parseDouble(textAltura.getText());
			int idade = Integer.parseInt(textIdade.getText());

			double calBasal;
			if (rdbtnHomem.isSelected()) {
				calBasal = 66 + (13.8 * peso) + (5 * altura) - (6.8 * idade);
			} else if (rdbtnMulher.isSelected()) {
				calBasal = 655 + (9.6 * peso) + (1.9 * altura) - (4.7 * idade);
			} else {
				throw new Exception("Por favor, selecione o sexo.");
			}

			String nivelAtividade = (String) comboBox.getSelectedItem();
			if (nivelAtividade == null || nivelAtividade.isEmpty()) {
				throw new Exception("Selecione um nível de atividade.");
			}

			double calTotal;
			switch (nivelAtividade) {
			case "Sedentário":
				calTotal = calBasal * 1.2;
				break;
			case "Leve  (1 a 3 dias/semana)":
				calTotal = calBasal * 1.375;
				break;
			case "Moderado  (3 a 5 dias/semana)":
				calTotal = calBasal * 1.55;
				break;
			case "Ativo  (5 a 6 dias/semana)":
				calTotal = calBasal * 1.725;
				break;
			case "Extremamente Ativo (pesado diário)":
				calTotal = calBasal * 1.9;
				break;
			default:
				throw new Exception("Selecione um nível de atividade.");
			}

			lblGastoBasal.setText(String.format("Gasto Basal: %.2f cal", calBasal));
			lblGastoTotal.setText(String.format("Gasto Total: %.2f cal", calTotal));
		} catch (NumberFormatException ex) {
			lblGastoBasal.setText("Gasto Basal: Valor inválido");
			lblGastoTotal.setText("Gasto Total: Valor inválido");
		} catch (Exception ex) {
			lblGastoBasal.setText(ex.getMessage());
			lblGastoTotal.setText("");
		}
	}

	public void setVisible(boolean b) {
		telaGastoCalorico.setVisible(true);
	}

}