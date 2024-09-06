package com.mycompany.podcast.conexao.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Representa um usuário no sistema de podcast.
 * 
 * <p>A classe {@code Usuario} é uma entidade JPA que corresponde à tabela {@code usuario} no banco de dados.
 * Ela armazena informações relacionadas a um usuário, incluindo seu identificador, nome, tipo, login e senha.
 * 
 * @author mi_bo
 */
@Entity(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String tipo;
    private String login;
    private String senha;

    /**
     * Construtor com todos os atributos do usuário.
     * 
     * @param id o identificador único do usuário.
     * @param nome o nome completo do usuário.
     * @param tipo o tipo do usuário (por exemplo, administrador, ouvinte, etc.).
     * @param login o nome de usuário para login.
     * @param senha a senha do usuário.
     */
    public Usuario(int id, String nome, String tipo, String login, String senha) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.login = login;
        this.senha = senha;
    }

    /**
     * Construtor padrão para a classe {@code Usuario}.
     * 
     * <p>Este construtor é necessário para frameworks de persistência como JPA.
     */
    public Usuario() {
    }
    
    /**
     * Construtor para a classe {@code Usuario} com login e senha.
     * 
     * @param login o nome de usuário para login.
     * @param senha a senha do usuário.
     */
    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    /**
     * Obtém o identificador único do usuário.
     * 
     * @return o identificador do usuário.
     */
    public int getId() {
        return id;
    }

    /**
     * Define o identificador único do usuário.
     * 
     * @param id o identificador do usuário a ser definido.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtém o nome completo do usuário.
     * 
     * @return o nome do usuário.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome completo do usuário.
     * 
     * @param nome o nome do usuário a ser definido.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o tipo do usuário.
     * 
     * @return o tipo do usuário (por exemplo, administrador, ouvinte, etc.).
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Define o tipo do usuário.
     * 
     * @param tipo o tipo do usuário a ser definido.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Obtém o nome de usuário para login.
     * 
     * @return o login do usuário.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Define o nome de usuário para login.
     * 
     * @param login o login do usuário a ser definido.
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Obtém a senha do usuário.
     * 
     * @return a senha do usuário.
     */
    public String getSenha() {
        return senha;
    }

    /**
     * Define a senha do usuário.
     * 
     * @param senha a senha do usuário a ser definida.
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }
}
