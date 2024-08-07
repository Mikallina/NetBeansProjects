package com.mycompany.bancodigital_05.telas.administrador;

import com.mycompany.bancodigital_05.DAO.CadastroClienteDAO;
import com.mycompany.bancodigital_05.model.CadastroCliente;
import com.mycompany.bancodigital_05.model.Validador;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import javax.swing.text.MaskFormatter;

public class CadastrarCliente {

    private JFrame telaCliente;
    private JTextField textNome;
    private JFormattedTextField textCpf;
    private JTextField textEndereco;
    private JFormattedTextField textTelefone;
    private JPasswordField passwordField;
    private Validador validador;
    private CadastroClienteDAO clienteDAO;
    private CadastroCliente clienteEditado;
    private Runnable callback;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CadastrarCliente window = new CadastrarCliente();
                    window.telaCliente.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application for new client registration.
     */
    public CadastrarCliente() {
        clienteDAO = new CadastroClienteDAO(); // Inicialize o DAO
        initialize();
    }

    /**
     * Create the application for editing an existing client.
     *
     * @param cliente Cliente a ser editado
     */
    public CadastrarCliente(CadastroCliente cliente, Runnable callback) {
        clienteDAO = new CadastroClienteDAO();
        this.clienteEditado = cliente;
        this.callback = callback;
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        telaCliente = new JFrame();
        telaCliente.setBounds(100, 100, 547, 393);
        telaCliente.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        telaCliente.getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 533, 353);
        telaCliente.getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel(clienteEditado == null ? "Cadastrar Cliente" : "Editar Cliente");
        lblNewLabel.setBounds(197, 5, 138, 22);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        panel.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Nome: ");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_1.setBounds(28, 87, 68, 22);
        panel.add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("CPF: ");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_1_1.setBounds(28, 119, 68, 22);
        panel.add(lblNewLabel_1_1);

        JLabel lblNewLabel_1_1_1 = new JLabel("Endereço: ");
        lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_1_1_1.setBounds(28, 151, 100, 22);
        panel.add(lblNewLabel_1_1_1);

        JLabel lblNewLabel_1_1_1_1 = new JLabel("Telefone:");
        lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_1_1_1_1.setBounds(28, 183, 68, 22);
        panel.add(lblNewLabel_1_1_1_1);

        textNome = new JTextField(clienteEditado != null ? clienteEditado.getNome() : "");
        textNome.setBounds(114, 91, 343, 19);
        panel.add(textNome);
        textNome.setColumns(10);

        textCpf = new JFormattedTextField(createFormatter("###.###.###-##"));
        textCpf.setText(clienteEditado != null ? clienteEditado.getCpf() : "");
        textCpf.setBounds(114, 123, 343, 19);
        textCpf.setEditable(clienteEditado == null); // Não permite edição do CPF se for edição
        panel.add(textCpf);

        textEndereco = new JTextField(clienteEditado != null ? clienteEditado.getEndereco() : "");
        textEndereco.setColumns(10);
        textEndereco.setBounds(114, 155, 343, 19);
        panel.add(textEndereco);

        textTelefone = new JFormattedTextField(createFormatter("(##) #####-####"));
        textTelefone.setText(clienteEditado != null ? clienteEditado.getTelefone() : "");
        textTelefone.setColumns(10);
        textTelefone.setBounds(115, 186, 166, 19);
        panel.add(textTelefone);

        passwordField = new JPasswordField(clienteEditado != null ? String.valueOf(clienteEditado.getSenha()) : "");
        passwordField.setBounds(114, 215, 106, 19);
        panel.add(passwordField);

        JButton btnSalvar = new JButton("SALVAR");
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                validador = new Validador();
                CadastroCliente cadastro = clienteEditado != null ? clienteEditado : new CadastroCliente();
                cadastro.setNome(textNome.getText());
                cadastro.setCpf(textCpf.getText());
                cadastro.setEndereco(textEndereco.getText());
                cadastro.setTelefone(textTelefone.getText());
                String senhaTexto = new String(passwordField.getPassword());

                try {
                    if (senhaTexto.isEmpty()) {
                        throw new NumberFormatException("Senha não pode estar vazia.");
                    }
                    int senha = Integer.parseInt(senhaTexto);
                    cadastro.setSenha(senha);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao converter senha para int: " + ex.getMessage());
                    return;
                }

                // Verificar se todos os campos obrigatórios estão preenchidos
                if (cadastro.getNome().isEmpty() || cadastro.getTelefone().isEmpty() || cadastro.getCpf().isEmpty()
                        || textNome.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios.");
                    return;
                }

                // Validar CPF
                if (!validador.validarCPF(cadastro.getCpf())) {
                    JOptionPane.showMessageDialog(null, "CPF inválido. Informe no formato XXX.XXX.XXX-XX.");
                    return;
                }

                // Validar Telefone
                if (!validador.validarTelefone(cadastro.getTelefone())) {
                    JOptionPane.showMessageDialog(null,
                            "Telefone inválido. Informe no formato (XX) XXXX-XXXX ou (XX) XXXXX-XXXX.");
                    return;
                }

                // Cadastro ou atualização efetivada
                try {
                    if (clienteEditado == null) {
                        clienteDAO.adicionarCliente(cadastro);
                        JOptionPane.showMessageDialog(null, "Cadastro Efetivado");
                    } else {
                        clienteDAO.atualizarCliente(cadastro);
                        JOptionPane.showMessageDialog(null, "Dados atualizados com sucesso.");
                    }

                    // Chama o callback
                    if (callback != null) {
                        callback.run();
                    }

                    telaCliente.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao processar cliente: " + ex.getMessage());
                }

            }
        });
        btnSalvar.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnSalvar.setBounds(200, 299, 166, 32);
        panel.add(btnSalvar);

        JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Senha:");
        lblNewLabel_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_1_1_1_1_1.setBounds(28, 215, 68, 22);
        panel.add(lblNewLabel_1_1_1_1_1);
    }

    public MaskFormatter createFormatter(String s) {
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
        telaCliente.setVisible(b);
    }
}
