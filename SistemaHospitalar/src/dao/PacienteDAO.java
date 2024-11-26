/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.regex.Pattern;
import modelo.Paciente;
import persistencia.ConexaoBanco;


/*A classe PacienteDAO é responsável pela comunicação entre a aplicação e o banco de dados, ou seja,
ela é responsável por realizar as operações de cadastro e busca de pacientes no banco de dados.
 */
public class PacienteDAO {

    private ConexaoBanco conexao;
    private Connection con;

    /*No construtor da classe, a instância de ConexaoBanco é criada e 
    armazenada no atributo conexao. 
    Essa instância será usada posteriormente para obter a conexão com o banco de dados.
     */
    public PacienteDAO() {
        this.conexao = new ConexaoBanco();
    }
    
   
    // método cadastrarPaciente
    public void cadastrarPaciente(Paciente pac) throws SQLException {

        try  {
            con = conexao.getConexao();
           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
           sdf.setLenient(false);

            // Validação do Nome (RN01)
            if (pac.getNome() == null || pac.getNome().length() > 55) {
                throw new SQLException("Nome é obrigatório e não pode exceder 55 caracteres.");
            }

            // Validação do CPF (RN02)
            if (pac.getCpf() == null || pac.getCpf().length() != 11 || !isCpfUnico(pac.getCpf())) {
                throw new SQLException("CPF é obrigatório, deve ter 11 caracteres e ser único.");
            }

            // Validação da Data de Nascimento (RN03)
            if (pac.getDataNascimento() == null) {
                throw new SQLException("Data de nascimento é obrigatória.");
            }
            // Verifica se a data de nascimento é válida (DD/MM/YYYY)
            try {
           // Primeiro validamos se a data de nascimento está correta
             sdf.setLenient(false); // Desabilita a leniência na validação
             sdf.parse(sdf.format(pac.getDataNascimento())); // Tenta analisar a data no formato dd/MM/yyyy
            } catch (ParseException e) {
                throw new SQLException("Data de nascimento inválida. O formato correto é DD/MM/YYYY.");
            }
            String dataFormatada = sdf.format(pac.getDataNascimento());

            // Validação do Endereço (RN04)
            if (pac.getEndereco() == null || pac.getEndereco().length() > 200) {
                throw new SQLException("Endereço é obrigatório e não pode exceder 200 caracteres.");
            }

           if (pac.getTelefone() == null || pac.getTelefone().length() != 11 || !isTelefoneValido(pac.getTelefone())) {
                 throw new SQLException("Telefone é obrigatório e deve conter apenas números e ter 11 caracteres.");
            }

            // Aqui, formatamos o telefone antes de armazená-lo no banco
            String telefoneFormatado = formatarTelefone(pac.getTelefone());

            // Validação do E-mail (RN06)
            if (pac.getEmail() != null && !isEmailValido(pac.getEmail())) {
                throw new SQLException("E-mail fornecido é inválido.");
            }

            // String que receberá instrução SQL
            String sql = "INSERT INTO PACIENTE(NOME, ENDERECO, DATA_NASC, TELEFONE, CPF, RG, ID_CONVENIO_FK, EMAIL) VALUES(?,?,?,?,?,?,?,?)";
            
            // Usando try-with-resources para garantir o fechamento do PreparedStatement
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                // Atribuindo valores aos parâmetros
                pst.setString(1, pac.getNome());
                pst.setString(2, pac.getEndereco());
                pst.setString(3,dataFormatada);
                pst.setString(4, telefoneFormatado);
                pst.setString(5, pac.getCpf());
                pst.setString(6, pac.getRg());
                pst.setInt(7, pac.getIdConvenio());
                pst.setString(8, pac.getEmail());

                // Executando o PreparedStatement
                pst.executeUpdate(); // Usando executeUpdate para inserção de dados
            }
        } catch (SQLException se) {
            throw new SQLException("Erro ao inserir dados no Banco de Dados: " + se.getMessage());
        }
    }

    // Método para verificar se o CPF é único no sistema (RN02)
    public boolean isCpfUnico(String cpf) throws SQLException {
        String sql = "SELECT COUNT(*) FROM PACIENTE WHERE CPF = ?";
        try (PreparedStatement pst = this.con.prepareStatement(sql)) {
            pst.setString(1, cpf);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    return false; // CPF já existe
                }
            }
        }
        return true;
    }

    // Método para validar o formato do telefone (RN05)
    private boolean isTelefoneValido(String telefone) {
    // Verifica se o telefone contém apenas números e tem 11 dígitos
        return telefone.matches("\\d{11}");
    }
    private String formatarTelefone(String telefone) {
    if (telefone != null && telefone.length() == 11) {
        // Adiciona a formatação (xx)XXXX-XXXX
        return String.format("(%s)%s-%s", telefone.substring(0, 2), telefone.substring(2, 6), telefone.substring(6, 10));
    }
   
    return telefone; // Retorna o telefone não formatado caso não tenha 11 dígitos
}



    // Método para validar o formato do e-mail (RN06)
    private boolean isEmailValido(String email) {
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return Pattern.matches(regex, email);
    }



    // método buscarPaciente com condição
    public ArrayList<Paciente> buscarPacienteFiltro(String query) throws SQLException {

        /*
         * Criando obj. capaz de executar instruções
         * SQL no banco de dados
         */
        ResultSet rs;

        try {
            // Criando variável sql vazia
            String sql;

            /* Montando o sql com a consulta desejada pelo usuário.
            A consulta foi enviada para o método em uma String chamada query */
            sql = "SELECT * FROM paciente " + query;

            this.con = this.conexao.getConexao();
            PreparedStatement pst = con.prepareStatement(sql);

            rs = pst.executeQuery();


            /* Criando ArrayList para armazenar objetos do tipo Paciente */
            ArrayList<Paciente> pacientes = new ArrayList<>();

            /* Enquanto houver uma próxima linha no
           banco de dados o while roda */
            while (rs.next()) {

                // Criando um novo obj. Paciente
                Paciente pac = new Paciente();

                /* Mapeando a tabela do banco para objeto chamado pac */
                pac.setIdPaciente(rs.getInt("ID_PACIENTE"));
                pac.setNome(rs.getString("NOME"));
                pac.setEndereco(rs.getString("ENDERECO"));
                pac.setDataNascimento(rs.getDate("DATA_NASC"));
                pac.setTelefone(rs.getString("TELEFONE"));
                pac.setCpf(rs.getString("CPF"));
                pac.setRg(rs.getString("RG"));
                pac.setEmail(rs.getString("EMAIL"));
                pac.setIdConvenio(rs.getInt("ID_CONVENIO_FK"));


                /* Inserindo o objeto Paciente no ArrayList */
                pacientes.add(pac);
            }

            // Retornando o ArrayList com todos objetos
            return pacientes;

        } catch (SQLException se) {
            throw new SQLException("Erro ao buscar dados do Banco! " + se.getMessage());
        } finally {
            con.close();
        }
    }

    public ArrayList<Paciente> buscarPaciente() throws SQLException {


        /*
         * Criando obj. capaz de executar instruções
         * SQL no banco de dados
         */
        ResultSet rs;

        try {

            // String que receberá instrução SQL
            String sql = "SELECT * FROM PACIENTE";

            this.con = this.conexao.getConexao();
            PreparedStatement pst = con.prepareStatement(sql);

            rs = pst.executeQuery();


            /* Criando ArrayList para armazenar objetos do tipo Paciente */
            ArrayList<Paciente> pacientes = new ArrayList<>();

            /* Enquanto houver uma próxima linha no banco de dados o while roda */
            while (rs.next()) {

                // Criando um novo objeto Paciente
                Paciente pac = new Paciente();

                /* Mapeando a tabela do banco para objeto chamado pac */
                pac.setIdPaciente(rs.getInt("ID_PACIENTE"));
                pac.setNome(rs.getString("NOME"));
                pac.setEndereco(rs.getString("ENDERECO"));
                pac.setDataNascimento(rs.getDate("DATA_NASC"));
                pac.setTelefone(rs.getString("TELEFONE"));
                pac.setCpf(rs.getString("CPF"));
                pac.setRg(rs.getString("RG"));
                pac.setEmail(rs.getString("EMAIL"));
                pac.setIdConvenio(rs.getInt("ID_CONVENIO_FK"));

                /* Inserindo o objeto pac no ArrayList */
                pacientes.add(pac);
            }

            // Retornando o ArrayList com todos objetos
            return pacientes;
        } catch (SQLException se) {

            throw new SQLException("Erro ao buscar dados do Banco! " + se.getMessage());
        } finally {
            con.close();
        }
    }


}
