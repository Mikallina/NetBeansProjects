/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bancodigital_05.model;

/**
 *
 * @author mi_bo
 */
public class ContaCliente {
    private static final int AGENCIA_PADRAO = 0001;
	private static int SEQUENCIAL = 1;
	
	
	protected int agencia;
	protected int numero;
	protected double saldo;
	private CadastroCliente dadosCliente;
	
	public void ContaCliente() {
		
	}

	public ContaCliente(CadastroCliente dadosCliente) {
		this.agencia = ContaCliente.AGENCIA_PADRAO;
		this.numero = SEQUENCIAL++;
		this.dadosCliente = dadosCliente;
	}
	
	public int getAgencia() {
		return agencia;
	}
	
	public int getNumero() {
		return numero;
	}
	
	public double getSaldo() {
		return saldo;
	}

	public void sacar(double valor) {
		
		saldo -=valor;
	}

	public void depositar(double valor) {
		
		saldo +=valor;
	}

	public void transferir(double valor, ContaCliente contaDestino) {
		
		this.sacar(valor);
		contaDestino.depositar(valor);
	}

	protected void imprimirInfosConta() {
		System.out.println(String.format("Titular: %s", this.dadosCliente.getNome()));
		System.out.println(String.format("Agencia: %d", this.agencia));
		System.out.println(String.format("Conta Corrente: %d", this.numero));
		System.out.println(String.format("Saldo: %.2f", this.saldo));
	}
    
    
}
