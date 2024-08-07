/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bancodigital_05.telas.cliente;


import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;


/**
 *
 * @author mi_bo
 */
public class Extrato {
   
	private JFrame telaExtrato;
	private JTable table;
	private DefaultTableModel tableModel;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Extrato window = new Extrato();
					window.telaExtrato.setVisible(true);
					window.telaExtrato.requestFocusInWindow();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public Extrato() {
		initialize();
		
		
	}


	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		telaExtrato = new JFrame();
		telaExtrato.setBounds(100, 100, 566, 440);
		telaExtrato.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		telaExtrato.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("552px"),},
			new RowSpec[] {
				RowSpec.decode("403px"),}));
		
		JScrollPane scrollPane = new JScrollPane();
		telaExtrato.getContentPane().add(scrollPane, "1, 1, fill, fill");
		
		table = new JTable();
        table.setFont(new Font("Tahoma", Font.PLAIN, 10));
        table.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] { "Data", "Débito", "Crédito", "Saldo", "Comentários" }
        ));
        scrollPane.setViewportView(table);
        
        tableModel = (DefaultTableModel) table.getModel();
		
	}
	/**
     * Adiciona uma linha de transação na tabela de extrato.
     * @param data Data da transação
     * @param debito Valor debitado (saque, transferência)
     * @param credito Valor creditado (depósito, transferência recebida)
     * @param saldo Saldo após a transação
     * @param comentarios Comentários sobre a transação
     */
	
	public void adicionarTransacao(String data, double debito, double credito, double saldo, String comentarios) {
        tableModel.addRow(new Object[] { data, debito, credito, saldo, comentarios });
    }

	public void mostrarExtrato() {
        telaExtrato.setVisible(true);
    }
	

}
 
    

