package telas;

import telas.administrador.OperacoesAdm;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.ParseException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;
import model.CadastroCliente;
import telas.cliente.OperacoesBancarias;


public class Login {

    private JFrame telaLogin;
    private JButton btnAcessar;
    private JPasswordField passwordField;
    private boolean cpfFieldEmpty = true;
    private JFormattedTextField txtCpf;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login window = new Login();
                    window.telaLogin.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Login() {
        initialize();
    }

    private void initialize() {
        telaLogin = new JFrame();
        telaLogin.setBounds(100, 100, 450, 300);
        telaLogin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        telaLogin.getContentPane().setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Acesse sua Conta");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel.setBounds(145, 10, 151, 22);
        telaLogin.getContentPane().add(lblNewLabel);
        
        txtCpf = new JFormattedTextField(createFormatter("###.###.###-##"));
        txtCpf.setForeground(Color.GRAY);
        txtCpf.setText("");
        txtCpf.setHorizontalAlignment(SwingConstants.CENTER);
        txtCpf.setColumns(10);
        txtCpf.setBounds(100, 84, 227, 19);
        telaLogin.getContentPane().add(txtCpf);
    
        txtCpf.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (cpfFieldEmpty) {
                    txtCpf.setText("");
                    cpfFieldEmpty = false;
                }
            }
            
            public void focusLost(FocusEvent e) {
                if (txtCpf.getText().isEmpty()) {
                    txtCpf.setText("CPF");
                    cpfFieldEmpty = true;
                }
            }
        });
        
        passwordField = new JPasswordField();
        passwordField.setForeground(Color.GRAY);
        passwordField.setText("");
        passwordField.setHorizontalAlignment(SwingConstants.CENTER);
        passwordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (cpfFieldEmpty) {
                    passwordField.setText("");
                    cpfFieldEmpty = false;
                }
            }
            
            @SuppressWarnings("deprecation")
            public void focusLost(FocusEvent e) {
                if (passwordField.getText().isEmpty()) {
                    passwordField.setText("Senha");
                    cpfFieldEmpty = true;
                }
            }
        });
        
        passwordField.setBounds(100, 127, 227, 19);
        telaLogin.getContentPane().add(passwordField);
        
        btnAcessar = new JButton("Acessar");
        btnAcessar.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnAcessar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cpf = txtCpf.getText();
                String senhaTexto = new String(passwordField.getPassword());
                
                if (cpf.equals("123.456.789-00") && senhaTexto.equals("adminpass")) {
                    JOptionPane.showMessageDialog(telaLogin, "Login como Administrador bem-sucedido!");
                    OperacoesAdm telaOperacoesAdm = new OperacoesAdm();
                    telaOperacoesAdm.setVisible(true);
                    telaLogin.dispose();
                    return;
                }
                
                List<CadastroCliente> clientesCadastrados = CadastroCliente.getClientesCadastrados();
                CadastroCliente clienteCadastrado = null;
                for (CadastroCliente cliente : clientesCadastrados) {
                    if (cliente.getCpf().equals(cpf)) {
                        clienteCadastrado = cliente;
                        break;
                    }
                }
                
                if (clienteCadastrado != null && clienteCadastrado.getSenha() == Integer.parseInt(senhaTexto)) {
                    JOptionPane.showMessageDialog(telaLogin, "Login bem-sucedido!");
                    OperacoesBancarias telaOperacoes = new OperacoesBancarias(clienteCadastrado);
                    telaOperacoes.setVisible(true);
                    telaLogin.dispose();
                } else {
                    JOptionPane.showMessageDialog(telaLogin, "CPF ou senha inválidos. Tente novamente.");
                }
            }
        });
        
        btnAcessar.setBounds(100, 164, 227, 21);
        telaLogin.getContentPane().add(btnAcessar);
        
        JLabel lblNewLabel_1 = new JLabel("CPF");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1.setBounds(30, 84, 41, 16);
        telaLogin.getContentPane().add(lblNewLabel_1);
        
        JLabel lblNewLabel_1_1 = new JLabel("Senha");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1_1.setBounds(30, 130, 52, 16);
        telaLogin.getContentPane().add(lblNewLabel_1_1);
        
        JButton btnAcessar_1 = new JButton("Não Tenho Cadastro");
        btnAcessar_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JOptionPane.showMessageDialog(btnAcessar_1, "Solicitar o Castrastro Junto ao Administrador");
        	}
        });
        btnAcessar_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btnAcessar_1.setBounds(100, 209, 227, 21);
        telaLogin.getContentPane().add(btnAcessar_1);
    }

    public void setVisible(boolean b) {
        telaLogin.setVisible(true);
    }

    public void loginAsAdmin() {
        txtCpf.setText("123.456.789-00");
        passwordField.setText("adminpass");
        btnAcessar.doClick();
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
}
