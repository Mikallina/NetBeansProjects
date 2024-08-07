package bancodigital_01.model;


import bancodigital_01.model.CadastroCliente;
import bancodigital_01.model.ContaCorrente;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author mi_bo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CadastroCliente cliente = new CadastroCliente("João Silva", "12345678901", "Rua A, 123", "(11) 98765-4321", 1234);
        ContaCorrente contaCorrente = new ContaCorrente(cliente);
        
        System.out.println("Conta criada com sucesso!");
        contaCorrente.imprimirExtrato();
    }
    
}
