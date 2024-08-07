/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bancodigital_05.model;

/**
 *
 * @author mi_bo
 */
public class ContaCorrente extends ContaCliente {
	
	

	public ContaCorrente(CadastroCliente dadosCliente) {
		super(dadosCliente);
		
	}


	public void imprimirExtrato() {
		System.out.println("=== Extrato Conta Corrente ===");
		super.imprimirInfosConta();
		
	}
    
}
