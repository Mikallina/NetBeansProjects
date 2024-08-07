/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bancodigital_05.telas.cliente;

import com.mycompany.bancodigital_05.model.CadastroCliente;
import com.mycompany.bancodigital_05.model.ContaCliente;
import com.mycompany.bancodigital_05.model.ContaCorrente;
import com.mycompany.bancodigital_05.model.ContaPoupanca;
import com.mycompany.bancodigital_05.model.telas.telasIniciais.Main;
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


/**
 *
 * @author mi_bo
 */
public class OperacoesBancarias {

	private JFrame telaOperacoes;
	private JTextField textDepositar;
	private JTextField textSacar;
	private JTextField textTransferir;
	private JTextField textCorrente;
	private JTextField textPoupanca;
	private ContaCliente contaCorrente;
	private ContaCliente contaPoupanca;
	private static CadastroCliente cliente;
	private Extrato extrato;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OperacoesBancarias window = new OperacoesBancarias(cliente);
					window.telaOperacoes.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public OperacoesBancarias(CadastroCliente cliente) {
		this.cliente = cliente;
		contaCorrente = new ContaCorrente(null);
		contaPoupanca = new ContaPoupanca(null);
		// extrato = new Extrato(contaCorrente, contaPoupanca);
		initialize();
		extrato = new Extrato();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		telaOperacoes = new JFrame();
		telaOperacoes.setBounds(100, 100, 474, 466);
		telaOperacoes.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		telaOperacoes.getContentPane().setLayout(null);

		JButton btnDepositar = new JButton("Depositar");
		btnDepositar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDepositar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String valorDepositoStr = textDepositar.getText();
				if (valorDepositoStr.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Digite um valor para depositar.");
					return;
				}

				double valorDeposito = Double.parseDouble(valorDepositoStr);

				contaCorrente.depositar(valorDeposito);

				textCorrente.setText(String.valueOf(contaCorrente.getSaldo()));

				extrato.adicionarTransacao(obterData(), 0.0, valorDeposito, contaCorrente.getSaldo(),
						"Depósito realizado");
				textDepositar.setText("0.00");

			}
		});
		btnDepositar.setBounds(253, 102, 111, 21);
		telaOperacoes.getContentPane().add(btnDepositar);

		JButton btnSacar = new JButton("Sacar");
		btnSacar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String valorSacarStr = textSacar.getText();
				if (valorSacarStr.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Digite um valor para sacar.");
					return;
				}

				double valorSaque = Double.parseDouble(valorSacarStr);

				if (contaCorrente.getSaldo() >= valorSaque) {
					contaCorrente.sacar(valorSaque);
					textCorrente.setText(String.valueOf(contaCorrente.getSaldo()));

					extrato.adicionarTransacao(obterData(), valorSaque, 0.0, contaCorrente.getSaldo(),
							"Saque realizado");
					textSacar.setText("0.00");
				} else {
					JOptionPane.showMessageDialog(null, "Saldo insuficiente na conta corrente.");
				}
			}
		});
		btnSacar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSacar.setBounds(253, 140, 111, 21);
		telaOperacoes.getContentPane().add(btnSacar);

		JButton btnTransferir = new JButton("Transferir");
		btnTransferir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String valorTransferenciaStr = textTransferir.getText();
				if (valorTransferenciaStr.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Digite um valor para transferir.");
					return;
				}

				double valorTransferencia = Double.parseDouble(valorTransferenciaStr);

				if (contaCorrente.getSaldo() >= valorTransferencia) {
					contaCorrente.sacar(valorTransferencia);
					contaPoupanca.depositar(valorTransferencia);

					textCorrente.setText(String.valueOf(contaCorrente.getSaldo()));
					textPoupanca.setText(String.valueOf(contaPoupanca.getSaldo()));

					extrato.adicionarTransacao(obterData(), valorTransferencia, valorTransferencia,
							contaCorrente.getSaldo(), "Transferência realizada");
					textTransferir.setText("0.00");

				} else {
					JOptionPane.showMessageDialog(null, "Saldo insuficiente na conta corrente.");
				}
			}
		});

		btnTransferir.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnTransferir.setBounds(253, 179, 111, 21);
		telaOperacoes.getContentPane().add(btnTransferir);

		JLabel lblNewLabel = new JLabel("Cliente: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(26, 10, 60, 13);
		telaOperacoes.getContentPane().add(lblNewLabel);

		textDepositar = new JTextField();
		textDepositar.setBounds(122, 105, 111, 19);
		telaOperacoes.getContentPane().add(textDepositar);
		textDepositar.setColumns(10);

		textSacar = new JTextField();
		textSacar.setColumns(10);
		textSacar.setBounds(122, 143, 111, 19);
		telaOperacoes.getContentPane().add(textSacar);

		textTransferir = new JTextField();
		textTransferir.setColumns(10);
		textTransferir.setBounds(122, 182, 111, 19);
		telaOperacoes.getContentPane().add(textTransferir);

		JLabel lblNewLabel_1 = new JLabel("Saldo Conta Corrente:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(39, 297, 166, 13);
		telaOperacoes.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Saldo Conta Poupança:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(39, 322, 166, 21);
		telaOperacoes.getContentPane().add(lblNewLabel_1_1);

		JTextArea textCliente = new JTextArea();
		textCliente.setEnabled(false);
		textCliente.setEditable(false);
		textCliente.setBounds(96, 6, 325, 22);
		textCliente.setText(cliente.getNome());
		telaOperacoes.getContentPane().add(textCliente);

		textCorrente = new JTextField();
		textCorrente.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textCorrente.setBounds(215, 293, 96, 19);
		telaOperacoes.getContentPane().add(textCorrente);
		textCorrente.setColumns(10);

		textPoupanca = new JTextField();
		textPoupanca.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textPoupanca.setColumns(10);
		textPoupanca.setBounds(215, 322, 96, 19);
		telaOperacoes.getContentPane().add(textPoupanca);

		JLabel lblAgncia = new JLabel("Ag\u00EAncia");
		lblAgncia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAgncia.setBounds(26, 45, 60, 21);
		telaOperacoes.getContentPane().add(lblAgncia);

		JLabel lblNewLabel_2_1 = new JLabel("Conta: ");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2_1.setBounds(255, 46, 60, 19);
		telaOperacoes.getContentPane().add(lblNewLabel_2_1);

		JTextArea textAgencia = new JTextArea();
		textAgencia.setEnabled(false);
		textAgencia.setEditable(false);
		textAgencia.setBounds(96, 45, 104, 22);
		textAgencia.setText(Integer.toString(contaCorrente.getAgencia()));
		telaOperacoes.getContentPane().add(textAgencia);

		JTextArea textConta = new JTextArea();
		textConta.setEnabled(false);
		textConta.setEditable(false);
		textConta.setBounds(317, 45, 104, 22);
		textConta.setText(Integer.toString(contaCorrente.getNumero()));
		telaOperacoes.getContentPane().add(textConta);

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main telaInicial = new Main();
				telaInicial.setVisible(true);
			}
		});
		btnSair.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSair.setBounds(310, 382, 111, 21);
		telaOperacoes.getContentPane().add(btnSair);

		JButton btnExtrato = new JButton("Consultar Extrato");
		btnExtrato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				extrato.mostrarExtrato();
			}
		});
		btnExtrato.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnExtrato.setBounds(122, 227, 242, 21);
		telaOperacoes.getContentPane().add(btnExtrato);
	}

	private String obterData() {
		// TODO Auto-generated method stub
		return "DD/MM/AAAA";
	}

	public void setVisible(boolean b) {
		telaOperacoes.setVisible(true);

	}
}