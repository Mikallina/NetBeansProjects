/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package portalaluno.telas.model;

/**
 *
 * @author mi_bo
 */
public class Aluno {
    
    private String nome;
    private String email;
    private String curso;

    public Aluno(String nome, String email, String curso) {
        this.nome = nome;
        this.email = email;
        this.curso = curso;
    }
    
    public Aluno(){
    
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
    
    
    
}
