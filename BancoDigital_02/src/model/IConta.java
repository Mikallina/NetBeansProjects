/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model;

/**
 *
 * @author mi_bo
 */
public interface IConta {
    
    	 
	void sacar(double valor);
	void depositar(double valor);
	void transferir(double valor, ContaCliente contaDestino);
	void imprimirExtrato();
    
}
