/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bancodigital_05.model;

/**
 *
 * @author mi_bo
 */
public class Validador {
    
     public boolean validarCPF(String cpf) {
	        cpf = cpf.replaceAll("[^0-9]", ""); // Remove caracteres não numéricos

	        if (cpf.length() != 11) {
	            return false;
	        }

	        // Verifica se todos os dígitos são iguais
	        if (cpf.matches("(\\d)\\1{10}")) {
	            return false;
	        }

	        // Calcula o primeiro dígito verificador
	        int sum = 0;
	        for (int i = 0; i < 9; i++) {
	            sum += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
	        }
	        int remainder = sum % 11;
	        int digit1 = (remainder < 2) ? 0 : (11 - remainder);

	        // Calcula o segundo dígito verificador
	        sum = 0;
	        for (int i = 0; i < 10; i++) {
	            sum += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
	        }
	        remainder = sum % 11;
	        int digit2 = (remainder < 2) ? 0 : (11 - remainder);

	        // Verifica os dígitos verificadores
	        return (digit1 == Character.getNumericValue(cpf.charAt(9)) &&
	                digit2 == Character.getNumericValue(cpf.charAt(10)));
	    }
	   public boolean validarTelefone(String telefone) {
	        return telefone.matches("\\(\\d{2}\\)\\s\\d{4,5}-\\d{4}"); // Verifica se está no formato (XX) XXXX-XXXX ou (XX) XXXXX-XXXX
	    }
    
}
