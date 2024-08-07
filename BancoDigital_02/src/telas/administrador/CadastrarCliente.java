package telas.administrador;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import model.CadastroCliente;
import model.Validador;
import telas.Main;


public class CadastrarCliente {

	private JFrame telaCliente;
	protected JTextField textNome;
	protected JTextField textCpf;
	protected JTextField textEndereco;
	protected JTextField textTelefone;
	protected JPasswordField passwordField;

	private Validador validador;

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
	 * Create the application.
	 */
	public CadastrarCliente() {
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

		JLabel lblNewLabel = new JLabel("Cadastrar Cliente");
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

		textNome = new JTextField();
		textNome.setBounds(114, 91, 343, 19);
		panel.add(textNome);
		textNome.setColumns(10);

		final JFormattedTextField formattedTextField = new JFormattedTextField(createFormatter("###.###.###-##"));
		formattedTextField.setColumns(10);
		formattedTextField.setBounds(114, 123, 343, 19);
		panel.add(formattedTextField);

		textEndereco = new JTextField();
		textEndereco.setColumns(10);
		textEndereco.setBounds(114, 155, 343, 19);
		panel.add(textEndereco);

		final JFormattedTextField formattedTextField_1 = new JFormattedTextField(createFormatter("(##) #####-####"));
		formattedTextField_1.setColumns(10);
		formattedTextField_1.setBounds(115, 186, 166, 19);
		panel.add(formattedTextField_1);

		JButton btnNewButton = new JButton("SALVAR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validador = new Validador();
				CadastroCliente cadastro = new CadastroCliente();
				cadastro.setNome(textNome.getText());
				cadastro.setCpf(formattedTextField.getText());
				cadastro.setEndereco(textEndereco.getText());
				cadastro.setTelefone(formattedTextField_1.getText());
				String senhaTexto = new String(passwordField.getPassword());

				try {
					int senhaInt = Integer.parseInt(senhaTexto);
					cadastro.setSenha(senhaInt);
				} catch (NumberFormatException ex) {
					System.err.println("Erro ao converter senha para int: " + ex.getMessage());
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

				// Cadastro efetivado
				JOptionPane.showMessageDialog(null, "Cadastro Efetivado");
				CadastroCliente.adicionarCliente(cadastro);

				/* // Após cadastrar, fechar esta tela e abrir a tela inicial ou outra tela desejada
				TelaInicial telaInicial = new TelaInicial();
				telaInicial.setVisible(true);
				telaCliente.dispose();*/
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(200, 299, 166, 32);
		panel.add(btnNewButton);

		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Senha:");
		lblNewLabel_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1_1_1_1.setBounds(28, 215, 68, 22);
		panel.add(lblNewLabel_1_1_1_1_1);

		passwordField = new JPasswordField();
		passwordField.setBounds(114, 215, 106, 19);
		panel.add(passwordField);
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
		telaCliente.setVisible(true);
	}
}