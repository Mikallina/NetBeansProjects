/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exercicio_3_22024.telas;

import exercicio_3_22024.model.Cadastro;
import exercicio_3_22024.model.Validador;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.text.MaskFormatter;
import javax.xml.crypto.Data;

/**
 *
 * @author mi_bo
 */
public class CadastrarConsulta {

    private JFrame cadastrarConsulta;
    private Listagem listagem;
    private Validador validador;

    public CadastrarConsulta() {
        initialize();
        validador = new Validador();
    }

    public CadastrarConsulta(Listagem listagem) {
        this.listagem = listagem;
        initialize();
        validador = new Validador();
    }

    private void initialize() {
        cadastrarConsulta = new JFrame();
        cadastrarConsulta.setBounds(100, 100, 484, 523);
        cadastrarConsulta.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cadastrarConsulta.getContentPane().setLayout(null);

        JLabel lblCadastrarConsulta = new JLabel("Nome do(a) Paciente:");
        lblCadastrarConsulta.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblCadastrarConsulta.setBounds(20, 86, 168, 30);
        cadastrarConsulta.getContentPane().add(lblCadastrarConsulta);

        JLabel lblCadastrarConsulta_1 = new JLabel("Cadastrar Consulta");
        lblCadastrarConsulta_1.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblCadastrarConsulta_1.setBounds(20, 46, 202, 30);
        cadastrarConsulta.getContentPane().add(lblCadastrarConsulta_1);

        JTextArea textAreaNome = new JTextArea();
        textAreaNome.setBounds(207, 86, 174, 32);
        cadastrarConsulta.getContentPane().add(textAreaNome);

        JFormattedTextField textAreaTelefone = new JFormattedTextField(createFormatter("(##) #####-####"));
        textAreaTelefone.setBounds(207, 139, 174, 32);
        cadastrarConsulta.getContentPane().add(textAreaTelefone);

        JLabel lblTelefone = new JLabel("Telefone:");
        lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblTelefone.setBounds(20, 139, 168, 30);
        cadastrarConsulta.getContentPane().add(lblTelefone);

        JFormattedTextField textAreaCpf = new JFormattedTextField(createFormatter("###.###.###-##"));
        textAreaCpf.setBounds(207, 191, 174, 32);
        cadastrarConsulta.getContentPane().add(textAreaCpf);

        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblCpf.setBounds(20, 191, 168, 30);
        cadastrarConsulta.getContentPane().add(lblCpf);

        JFormattedTextField textAreaDataConsulta = new JFormattedTextField(createFormatter("##/##/####"));
        textAreaDataConsulta.setBounds(207, 248, 174, 32);
        cadastrarConsulta.getContentPane().add(textAreaDataConsulta);

        JLabel lblDataConsulta = new JLabel("Data da Consulta:");
        lblDataConsulta.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblDataConsulta.setBounds(20, 248, 168, 30);
        cadastrarConsulta.getContentPane().add(lblDataConsulta);

        JCheckBox chckbxPaciente = new JCheckBox(" Já é Paciente");
        chckbxPaciente.setFont(new Font("Tahoma", Font.PLAIN, 16));
        chckbxPaciente.setBounds(20, 315, 157, 30);
        cadastrarConsulta.getContentPane().add(chckbxPaciente);

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Cadastro cadastro = new Cadastro();
                cadastro.setNome(textAreaNome.getText());
                cadastro.setTelefone(textAreaTelefone.getText());
                cadastro.setCpf(textAreaCpf.getText());
                cadastro.setCadastro(chckbxPaciente.isSelected());

                if (cadastro.getNome().isEmpty() || 
                    cadastro.getTelefone().isEmpty() || 
                    cadastro.getCpf().isEmpty() || 
                    textAreaDataConsulta.getText().isEmpty()) { 
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios.");
                    return;
                }

                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date dataConsulta = sdf.parse(textAreaDataConsulta.getText());
                    cadastro.setData(dataConsulta); 
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Data inválida. Informe no formato dd/MM/yyyy.");
                    return;
                }

                if (!validador.validarCPF(cadastro.getCpf())) {
                    JOptionPane.showMessageDialog(null, "CPF inválido. Informe no formato XXX.XXX.XXX-XX.");
                    return;
                }

                if (!validador.validarTelefone(cadastro.getTelefone())) {
                    JOptionPane.showMessageDialog(null, "Telefone inválido. Informe no formato (XX) XXXX-XXXX ou (XX) XXXXX-XXXX.");
                    return;
                }

                listagem.atualizarTabela(cadastro);
                JOptionPane.showMessageDialog(null,
                        "Os seguintes dados foram cadastrados com sucesso: \n" + "\nNome: " + cadastro.getNome()
                );
                cadastrarConsulta.dispose();
              
            }
        });
        btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnCadastrar.setBounds(20, 384, 131, 38);
        cadastrarConsulta.getContentPane().add(btnCadastrar);
    }

    private MaskFormatter createFormatter(String s) {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter(s);
            formatter.setPlaceholderCharacter(' ');
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatter;
    }

    public void setVisible(boolean b) {
        cadastrarConsulta.setVisible(true);
    }
}