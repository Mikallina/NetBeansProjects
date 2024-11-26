/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

import dao.PacienteDAO;
import java.sql.SQLException;
import modelo.Paciente;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mi_bo
 */
public class TestCadastrarPaciente {
    
    private PacienteDAO pacienteService; 

    @Before
    public void setUp() {
        // Cria o objeto que está sendo testado
        pacienteService = new PacienteDAO();
    }

   
    @Test
    public void testCadastrarPaciente_NomeInvalido() {
        // Nome inválido (null)
        Paciente pac = new Paciente(null, "Rua X",new java.util.Date(),"12345678901","555555552","11987654321", "joao@example.com", 1);

        // Espera que uma SQLException seja lançada
        SQLException exception = assertThrows(SQLException.class, () -> {
            pacienteService.cadastrarPaciente(pac);
        });

        // Verifica a mensagem da exceção
        assertEquals("Nome é obrigatório e não pode exceder 55 caracteres.", exception.getMessage());
    }

    @Test
    public void testCadastrarPaciente_CpfInvalido() {
        // CPF inválido (menos de 11 caracteres)
        Paciente pac = new Paciente("João", "Rua X",new java.util.Date(),"11981820502","3253253251","307975555", "joao@example.com", 1);

        // Espera que uma SQLException seja lançada
        SQLException exception = assertThrows(SQLException.class, () -> {
            pacienteService.cadastrarPaciente(pac);
        });

        // Verifica a mensagem da exceção
        assertEquals("CPF é obrigatório, deve ter 11 caracteres e ser único.", exception.getMessage());
    }

    @Test
    public void testCadastrarPaciente_DataNascimentoInvalida() {
        // Data de nascimento inválida (null)
        Paciente pac = new Paciente("João", "Rua X",null,"11981820502","32532532511","307975555", "joao@example.com", 1);

        // Espera que uma SQLException seja lançada
        SQLException exception = assertThrows(SQLException.class, () -> {
            pacienteService.cadastrarPaciente(pac);
        });

        // Verifica a mensagem da exceção
        assertEquals("Data de nascimento é obrigatória.", exception.getMessage());
    }

    @Test
    public void testCadastrarPaciente_TelefoneInvalido() {
        // Telefone inválido (menos de 11 caracteres)
        Paciente pac = new Paciente("João", "Rua X",new java.util.Date(),"981820502","32532532511","307975555", "joao@example.com", 1);

        // Espera que uma SQLException seja lançada
        SQLException exception = assertThrows(SQLException.class, () -> {
            pacienteService.cadastrarPaciente(pac);
        });

        // Verifica a mensagem da exceção
        assertEquals("Telefone é obrigatório e deve conter apenas números e ter 11 caracteres.", exception.getMessage());
    }

    @Test
    public void testCadastrarPaciente_EmailInvalido() {
        // E-mail inválido (sem @)
        Paciente pac = new Paciente("João", "Rua X",new java.util.Date(),"11981820502","32532532511","307975555", "joaoexample.com", 1);

        // Espera que uma SQLException seja lançada
        SQLException exception = assertThrows(SQLException.class, () -> {
            pacienteService.cadastrarPaciente(pac);
        });

        // Verifica a mensagem da exceção
        assertEquals("E-mail fornecido é inválido.", exception.getMessage());
    }

        
     @Test
    public void testCadastrarPaciente_Sucesso() throws SQLException {
        // Dados válidos para o paciente
        Paciente pac = new Paciente("João", "Rua X",new java.util.Date(),"12345678901","32532532585","11987654321", "joao@example.com", 1);

        pacienteService.cadastrarPaciente(pac);

        // Aqui, podemos apenas verificar se o método não lança exceções
        // Se o método for executado sem problemas, podemos assumir que o teste passou
        // A verificação de chamada a banco seria manual (ou seja, é preciso garantir que o código faça a operação de banco conforme esperado)
    }



    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}