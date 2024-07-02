/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package exercicio_1_22024;
import javax.swing.JOptionPane;

/**
 *
 * @author mi_bo
 */
public class Exercicio_1_22024 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

		Double valor;
		Double desconto;
		Double total;

		// Janelas iniciais
		JOptionPane.showMessageDialog(null, "Olá\nVamos Calcular o Produto !!");
		valor = Double.parseDouble(JOptionPane.showInputDialog("Informe o valor do Produto"));
		try {
			if (valor <= 0) {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Valor inválido! Por favor, insira um número válido maior que zero.");
			System.exit(0);
		}

		// Cálculo

		if (valor > 500) {
			desconto = Double.parseDouble(JOptionPane.showInputDialog("Informe o valor do Desconto: "));
			try {
				if (desconto < 0 || desconto >= 100) {
					throw new NumberFormatException();
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Desconto inválido! Por favor, insira um valor entre 0 e 99.");
				System.exit(0);
			}
			desconto = (valor * desconto) / 100;
			total = valor - desconto;
			JOptionPane.showMessageDialog(null, "Valor do produto: " + valor + "\nValor do Desconto: " + desconto
					+ "\nValor total do Produto: " + total);

		} else {
			JOptionPane.showMessageDialog(null, "Valor total do Produto: " + valor);
		}

	}
}