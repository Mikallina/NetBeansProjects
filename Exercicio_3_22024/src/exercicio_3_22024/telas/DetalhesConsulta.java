/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exercicio_3_22024.telas;

import exercicio_3_22024.model.Cadastro;
import exercicio_3_22024.model.Consulta;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class DetalhesConsulta {

    private JFrame detalhesConsulta;
    private Cadastro cadastro;
    private Consulta consulta;
    private JCheckBox chckbxConsultaRealizada;
    private JTextArea textAreaReceita;
    private JButton btnFinalizar;
    private Listagem listagem;

   public DetalhesConsulta(Listagem listagem, Cadastro consulta) {
        this.listagem = listagem;
        this.consulta = (Consulta) consulta;
        initialize();
    }

    DetalhesConsulta() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void initialize() {
        detalhesConsulta = new JFrame();
        detalhesConsulta.setBounds(100, 100, 498, 533);
        detalhesConsulta.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        detalhesConsulta.getContentPane().setLayout(null);

        JLabel lblDetalheConsulta = new JLabel("Detalhes da Consulta");
        lblDetalheConsulta.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblDetalheConsulta.setBounds(21, 48, 210, 30);
        detalhesConsulta.getContentPane().add(lblDetalheConsulta);

        chckbxConsultaRealizada = new JCheckBox("Consulta Realizada");
        chckbxConsultaRealizada.setFont(new Font("Tahoma", Font.PLAIN, 16));
        chckbxConsultaRealizada.setBounds(21, 94, 180, 30);
        chckbxConsultaRealizada.setSelected(consulta.isConsultaRealizada());
        chckbxConsultaRealizada.setEnabled(false); // Desativa a checkbox
        detalhesConsulta.getContentPane().add(chckbxConsultaRealizada);

        JLabel lblNewLabel = new JLabel("Receita e observações:");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel.setBounds(28, 154, 192, 30);
        detalhesConsulta.getContentPane().add(lblNewLabel);

        textAreaReceita = new JTextArea();
        textAreaReceita.setBounds(24, 194, 415, 195);
        detalhesConsulta.getContentPane().add(textAreaReceita);

        btnFinalizar = new JButton("Finalizar");
        btnFinalizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                finalizarConsulta();
            }
        });
        btnFinalizar.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnFinalizar.setBounds(21, 412, 122, 30);
        detalhesConsulta.getContentPane().add(btnFinalizar);

        // Oculta o botão Finalizar se a consulta já foi realizada
        if (consulta.isConsultaRealizada()) {
            btnFinalizar.setVisible(false);
        }
    }

    public void setVisible(boolean b) {
        detalhesConsulta.setVisible(b);
    }

    private void finalizarConsulta() {
        String receita = textAreaReceita.getText().trim();
        if (receita.isEmpty()) {
            JOptionPane.showMessageDialog(detalhesConsulta, "Informe a receita e observações antes de finalizar a consulta.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        consulta.setConsultaRealizada(true);
        consulta.setReceita(receita);
        listagem.atualizarConsulta(consulta); // Atualiza a tabela na Listagem
        JOptionPane.showMessageDialog(detalhesConsulta, "Consulta finalizada com sucesso.");
        detalhesConsulta.dispose();
    }
}