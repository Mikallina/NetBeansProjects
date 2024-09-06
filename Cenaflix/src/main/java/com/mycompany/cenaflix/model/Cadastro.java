package com.mycompany.cenaflix.model;

import jakarta.persistence.Entity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Representa um filme cadastrado no sistema.
 *
 * <p>
 * A classe {@code Cadastro} contém informações sobre um filme, incluindo seu
 * nome, data e categoria. Ela também mantém uma lista estática de filmes
 * cadastrados que pode ser usada para manipulação global dos filmes.</p>
 *
 * <p>
 * Esta classe está anotada com {@link jakarta.persistence.Entity} para ser
 * usada com JPA (Java Persistence API) para persistência em banco de dados.</p>
 *
 * @author Michelle Borges
 * @version 1.0
 */
@Entity(name = "cadastro")
public class Cadastro {

    private int id;
    private String nomeFilme;
    private Date data;
    private String categoria;

    private static List<Cadastro> filmesCadastrados = new ArrayList<Cadastro>();

    /**
     * Cria uma instância de {@code Cadastro} com os dados fornecidos.
     *
     * @param nomeFilme o nome do filme
     * @param data a data de lançamento do filme
     * @param categoria a categoria do filme
     */
    public Cadastro(String nomeFilme, Date data, String categoria) {
        this.nomeFilme = nomeFilme;
        this.data = data;
        this.categoria = categoria;
    }

    /**
     * Cria uma instância de {@code Cadastro} sem dados iniciais.
     *
     * <p>
     * Este é o construtor padrão necessário para a JPA.</p>
     */
    public Cadastro() {
        // Construtor sem argumentos
    }

      /**
     * Retorna o ID do filme.
     * 
     * @return o ID do filme
     */
    public int getId() {
        return id;
    }

    /**
     * Define o ID do filme.
     * 
     * @param id o ID do filme
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retorna o nome do filme.
     * 
     * @return o nome do filme
     */
    public String getNomeFilme() {
        return nomeFilme;
    }

    /**
     * Define o nome do filme.
     * 
     * @param nomeFilme o nome do filme
     */
    public void setNomeFilme(String nomeFilme) {
        this.nomeFilme = nomeFilme;
    }

    /**
     * Retorna a data de lançamento do filme.
     * 
     * @return a data de lançamento do filme
     */
    public Date getData() {
        return data;
    }

    /**
     * Define a data de lançamento do filme.
     * 
     * @param data a data de lançamento do filme
     */
    public void setData(Date data) {
        this.data = data;
    }

    /**
     * Retorna a categoria do filme.
     * 
     * @return a categoria do filme
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Define a categoria do filme.
     * 
     * @param categoria a categoria do filme
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * Retorna a lista de filmes cadastrados.
     * 
     * <p>Esta lista é estática e é usada para armazenar todos os filmes cadastrados.</p>
     * 
     * @return a lista de filmes cadastrados
     */
    public static List<Cadastro> getFilmesCadastrados() {
        return filmesCadastrados;
    }

    /**
     * Define a lista de filmes cadastrados.
     * 
     * @param filmesCadastrados a lista de filmes a ser definida
     */
    public static void setFilmesCadastrados(List<Cadastro> filmesCadastrados) {
        Cadastro.filmesCadastrados = filmesCadastrados;
    }

    /**
     * Adiciona um filme à lista de filmes cadastrados.
     * 
     * @param cadastro o filme a ser adicionado
     */
    public static void adicionarFilmes(Cadastro cadastro) {
        filmesCadastrados.add(cadastro);
    }
}   