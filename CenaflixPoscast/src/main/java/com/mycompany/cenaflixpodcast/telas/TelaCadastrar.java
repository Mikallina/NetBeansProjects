package com.mycompany.cenaflixpodcast.telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class TelaCadastrar {

	private JFrame frame;
	private JTextField textProdutor;
	private JTextField textNomeEpisodio;
	private JTextField textDuracao;
	private JTextField textUrl;
	private JTextField textNumeroEpisodio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastrar window = new TelaCadastrar();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaCadastrar() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 587, 678);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CENAFLIX");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblNewLabel.setBounds(179, 33, 183, 44);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblCadastrarPodcast = new JLabel("CADASTRAR PODCAST");
		lblCadastrarPodcast.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblCadastrarPodcast.setBounds(134, 87, 272, 29);
		frame.getContentPane().add(lblCadastrarPodcast);
		
		JLabel lblProdutor = new JLabel("Produtor");
		lblProdutor.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblProdutor.setBounds(142, 174, 71, 20);
		frame.getContentPane().add(lblProdutor);
		
		JLabel lblNomeEpisodio = new JLabel("Nome do Episódio");
		lblNomeEpisodio.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNomeEpisodio.setBounds(142, 246, 156, 20);
		frame.getContentPane().add(lblNomeEpisodio);
		
		JLabel lblNumero = new JLabel("Nº do Episódio");
		lblNumero.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNumero.setBounds(143, 312, 118, 20);
		frame.getContentPane().add(lblNumero);
		
		JLabel lblDuracao = new JLabel("Duração");
		lblDuracao.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDuracao.setBounds(142, 359, 71, 20);
		frame.getContentPane().add(lblDuracao);
		
		JLabel lblUrl = new JLabel("URL do Episódio");
		lblUrl.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUrl.setBounds(140, 434, 130, 20);
		frame.getContentPane().add(lblUrl);
		
		textProdutor = new JTextField();
		textProdutor.setBounds(142, 198, 264, 26);
		frame.getContentPane().add(textProdutor);
		textProdutor.setColumns(10);
		
		textNomeEpisodio = new JTextField();
		textNomeEpisodio.setColumns(10);
		textNomeEpisodio.setBounds(143, 268, 264, 26);
		frame.getContentPane().add(textNomeEpisodio);
		
		textDuracao = new JTextField();
		textDuracao.setColumns(10);
		textDuracao.setBounds(142, 383, 264, 26);
		frame.getContentPane().add(textDuracao);
		
		textUrl = new JTextField();
		textUrl.setColumns(10);
		textUrl.setBounds(141, 459, 264, 26);
		frame.getContentPane().add(textUrl);
		
		textNumeroEpisodio = new JTextField();
		textNumeroEpisodio.setColumns(10);
		textNumeroEpisodio.setBounds(278, 312, 128, 26);
		frame.getContentPane().add(textNumeroEpisodio);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnCadastrar.setBounds(62, 550, 176, 37);
		frame.getContentPane().add(btnCadastrar);
		
		JButton btnVerListagem = new JButton("Ver Listagem");
		btnVerListagem.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnVerListagem.setBounds(294, 550, 176, 37);
		frame.getContentPane().add(btnVerListagem);
	}

}
