/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testeoak;

public class Produto {
    private String nome;
    private String descricao;
    private double valor;
    private boolean disponivelParaVenda;

    // Construtor
    public Produto(String nome, String descricao, double valor, boolean disponivelParaVenda) {
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.disponivelParaVenda = disponivelParaVenda;
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValor() {
        return valor;
    }

    public boolean isDisponivelParaVenda() {
        return disponivelParaVenda;
    }
}
