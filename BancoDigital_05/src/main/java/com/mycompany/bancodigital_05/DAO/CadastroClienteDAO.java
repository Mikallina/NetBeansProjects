/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bancodigital_05.DAO;

/**
 *
 * @author mi_bo
 */
import com.mycompany.bancodigital_05.model.CadastroCliente;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CadastroClienteDAO {

    private static final String URL = "jdbc:mysql://localhost/banco_digital";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void adicionarCliente(CadastroCliente cliente) {
        String sql = "INSERT INTO cadastro_cliente (nome, cpf, endereco, telefone, senha) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf().replaceAll("\\D", ""));
            stmt.setString(3, cliente.getEndereco());
            stmt.setString(4, cliente.getTelefone());
            stmt.setInt(5, cliente.getSenha());
            stmt.executeUpdate();
            System.out.println("Cliente adicionado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao adicionar cliente: " + e.getMessage());
        }
    }

    public List<CadastroCliente> buscarTodosClientes() {
        List<CadastroCliente> clientes = new ArrayList<>();
        String sql = "SELECT cpf, nome, endereco, telefone FROM cadastro_cliente";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                CadastroCliente cliente = new CadastroCliente();
                cliente.setCpf(rs.getString("cpf").replaceAll("\\D", ""));
                cliente.setNome(rs.getString("nome"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setTelefone(rs.getString("telefone"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

     public CadastroCliente buscarClientePorCpf(String cpf) {
        CadastroCliente cliente = null;
        String sql = "SELECT cpf, nome, endereco, telefone, senha FROM cadastro_cliente WHERE cpf = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cpf);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    cliente = new CadastroCliente();
                    cliente.setCpf(rs.getString("cpf")); // Assumindo que o CPF já está no formato correto
                    cliente.setNome(rs.getString("nome"));
                    cliente.setEndereco(rs.getString("endereco"));
                    cliente.setTelefone(rs.getString("telefone"));
                    cliente.setSenha(rs.getInt("senha")); // Adicione isso para verificar a senha
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }


    public void excluirCliente(String cpf) {
        String sql = "DELETE FROM cadastro_cliente WHERE cpf = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cpf);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarCliente(CadastroCliente cliente) {
        String sql = "UPDATE cadastro_cliente SET nome = ?, endereco = ?, telefone = ?, senha = ? WHERE cpf = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEndereco());
            stmt.setString(3, cliente.getTelefone());
            stmt.setInt(4, cliente.getSenha());
            stmt.setString(5, cliente.getCpf().replaceAll("\\D", ""));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}