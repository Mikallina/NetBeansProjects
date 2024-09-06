package com.mycompany.podcast.conexao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * A classe {@code Criptografia} fornece utilitários para operações de criptografia.
 * 
 * <p>Esta classe contém um método estático que calcula o hash MD5 de uma string. O hash MD5 é uma função de resumo
 * criptográfico que produz uma saída de 128 bits (16 bytes), geralmente representada como uma string hexadecimal de 32 caracteres.
 * 
 * @author mi_bo
 */
public class Criptografia {

    /**
     * Calcula o hash MD5 de uma string fornecida.
     * 
     * <p>Este método recebe um texto como entrada, gera um hash MD5 para esse texto, e retorna a representação hexadecimal
     * do hash. Se o algoritmo MD5 não estiver disponível, uma exceção {@code RuntimeException} será lançada.
     * 
     * @param texto o texto para o qual o hash MD5 será calculado.
     * @return uma string hexadecimal representando o hash MD5 do texto fornecido.
     * @throws RuntimeException se ocorrer uma exceção ao tentar obter a instância do algoritmo MD5.
     */
    public static String getMD5(String texto) {
        try {
            // O método estático getInstance é chamado com hash MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // O método digest() é chamado para calcular o hash da mensagem
            // E então temos o vetor da mensagem
            byte[] messageDigest = md.digest(texto.getBytes());

            // Convertemos o vetor de bytes em um BigInt
            BigInteger no = new BigInteger(1, messageDigest);

            // A mensagem em BigInt é convertida para hexadecimal
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } // Em caso de erro, é lançada uma exceção
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
