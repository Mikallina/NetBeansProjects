package com.mycompany.cenaflixpodcast.telas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class TelaListagem {

	private JFrame frame;
	private JTable table;
    private JLabel lblNewLabel;
    private JLabel lblCenaflix;
    private JLabel lblNewLabel_1;
    private JTextField textField;
    private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaListagem window = new TelaListagem();
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
	public TelaListagem() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	 private void initialize() {
	        frame = new JFrame();
	        frame.getContentPane().setBackground(new Color(255, 255, 255));
	        frame.setBounds(100, 100, 782, 624);
	        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        frame.getContentPane().setLayout(null);
	        
	        lblNewLabel = new JLabel("LISTAGEM");
	        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
	        lblNewLabel.setBounds(309, 81, 126, 29);
	        frame.getContentPane().add(lblNewLabel);

	        JScrollPane scrollPane = new JScrollPane();
	        scrollPane.setBounds(10, 205, 748, 253);
	        frame.getContentPane().add(scrollPane);

	        table = new JTable();
	        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
	        table.setModel(
	                new DefaultTableModel(
	        	new Object[][] {
	        	},
	        	new String[] {
	        		"ID", "Produtor", "Nome do Episodio", "Nº Episódio", "Duração", "URL do Repo"
	        	}
	        ));
	        scrollPane.setViewportView(table); // Adiciona a tabela ao JScrollPane
	        
	        lblCenaflix = new JLabel("CENAFLIX");
	        lblCenaflix.setFont(new Font("Tahoma", Font.BOLD, 36));
	        lblCenaflix.setBounds(279, 27, 190, 44);
	        frame.getContentPane().add(lblCenaflix);
	        
	        lblNewLabel_1 = new JLabel("Pesquisa podcast por produtor:");
	        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
	        lblNewLabel_1.setBounds(10, 166, 253, 20);
	        frame.getContentPane().add(lblNewLabel_1);
	        
	        textField = new JTextField();
	        textField.setBounds(279, 169, 249, 19);
	        frame.getContentPane().add(textField);
	        textField.setColumns(10);
	        
	        btnNewButton = new JButton("CADASTRAR");
	        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
	        btnNewButton.setBounds(598, 483, 160, 44);
	        frame.getContentPane().add(btnNewButton);
	 }
}
