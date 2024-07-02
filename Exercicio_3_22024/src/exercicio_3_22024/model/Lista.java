/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exercicio_3_22024.model;

import java.util.List;
import java.util.ArrayList;

public class Lista {
	
	private static final List<Cadastro> listaCadastros = new ArrayList<>();
	
	public static List<Cadastro> Listar() {
		return listaCadastros;
	}
	
	public static void Adicionar(Cadastro novoCadastro) {	
		listaCadastros.add(novoCadastro);
	    
	}
	
}
