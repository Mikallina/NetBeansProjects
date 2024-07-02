/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exercicio_3_22024.model;

import java.util.Date;

/**
 *
 * @author mi_bo
 */
public class Consulta extends Cadastro{

    private String paciente;
    private String observacoes;
    private boolean consultaRealizada;
    private Cadastro cadastro;
    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    public boolean ConsultaRealizada() {
        return consultaRealizada;
    }

    public void setConsultaRealizada(boolean consultaRealizada) {
        this.consultaRealizada = consultaRealizada;
    }
       public boolean isConsultaRealizada() {
        return consultaRealizada;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
 

}
