/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exercicio_2_22024;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Imc {

    private JFrame telaImc;
    private JTextField textAltura;
    private JTextField textPeso;
    private JLabel lblResultado;
    private JLabel lblInterpretacao;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Imc window = new Imc();
                    window.telaImc.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Imc() {
        initialize();
        
    }
    


    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        telaImc = new JFrame();
        telaImc.addWindowListener(new WindowAdapter() {
        	@Override
        	public void windowClosing(WindowEvent e) {
        		NutriSoft.frmNutrisoft.setVisible(true);
        	}
        });
        
        telaImc.setTitle("telaImc");
        telaImc.setBounds(100, 100, 450, 507);
        telaImc.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        telaImc.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Cálculo de IMC");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel.setBounds(20, 27, 140, 42);
        telaImc.getContentPane().add(lblNewLabel);

        JPanel panel = new JPanel();
        panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        panel.setBounds(20, 66, 373, 214);
        telaImc.getContentPane().add(panel);
        panel.setLayout(null);

        textAltura = new JTextField();
        textAltura.setBounds(169, 46, 172, 32);
        panel.add(textAltura);
        textAltura.setColumns(10);

        textPeso = new JTextField();
        textPeso.setColumns(10);
        textPeso.setBounds(169, 106, 172, 32);
        panel.add(textPeso);

        JLabel lblAltura = new JLabel("Altura (m)");
        lblAltura.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblAltura.setBounds(28, 49, 98, 23);
        panel.add(lblAltura);

        JLabel lblPeso = new JLabel("Peso (kg)");
        lblPeso.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblPeso.setBounds(29, 112, 98, 23);
        panel.add(lblPeso);

        JButton btnCalcularImc = new JButton("Calcular");
        btnCalcularImc.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnCalcularImc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calcularIMC();
            }
        });
        btnCalcularImc.setBounds(188, 165, 123, 32);
        panel.add(btnCalcularImc);

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        panel_1.setBounds(20, 290, 373, 159);
        telaImc.getContentPane().add(panel_1);
        panel_1.setLayout(null);

        lblResultado = new JLabel("Resultado: ");
        lblResultado.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblResultado.setBounds(24, 30, 275, 23);
        panel_1.add(lblResultado);

        lblInterpretacao = new JLabel("Interpretação: ");
        lblInterpretacao.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblInterpretacao.setBounds(24, 82, 275, 48);
        panel_1.add(lblInterpretacao);
        
      
    }

    private void calcularIMC() {
        try {
            double altura = Double.parseDouble(textAltura.getText());
            double peso = Double.parseDouble(textPeso.getText());

            double imc = peso / (altura * altura);

            lblResultado.setText(String.format("Resultado: %.2f", imc));

            if (imc < 18.5) {
                lblInterpretacao.setText("Interpretação: Magreza");
            } else if (imc >= 18.5 && imc <= 24.9) {
                lblInterpretacao.setText("Interpretação: Normal");
            } else if (imc >= 25 && imc <= 29.9) {
                lblInterpretacao.setText("Interpretação: Sobrepeso");
            } else if (imc >= 30 && imc <= 39.9) {
                lblInterpretacao.setText("Interpretação: Obesidade");
            } else {
                lblInterpretacao.setText("Interpretação: Obesidade grave");
            }
        } catch (NumberFormatException ex) {
            lblResultado.setText("Resultado: Por favor, preencha todos os campos corretamente.");
            lblInterpretacao.setText("Interpretação: ");
        }
    }

    public void setVisible(boolean b) {
        telaImc.setVisible(true);
    }
}