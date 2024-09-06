package com.mycompany.podcast.conexao.model;

import jakarta.persistence.Entity;

/**
 * Representa uma entidade de podcast a ser cadastrada no banco de dados.
 * 
 * <p>A classe {@code PodcastCadastro} é uma entidade JPA que corresponde à tabela {@code cadastro_podcast} no banco de dados.
 * Ela contém informações sobre um episódio de podcast, incluindo detalhes como o produtor, nome do episódio, número do episódio,
 * duração e URL do episódio.
 * 
 * @author mi_bo
 */
@Entity(name = "cadastro_podcast")
public class PodcastCadastro {

    private int idPodcast;
    private String produtor;
    private String nomeEpisodio;
    private int numEpisodio;
    private int duracao;
    private String url;
    
    /**
     * Construtor padrão para a classe {@code PodcastCadastro}.
     * 
     * <p>Este construtor é necessário para a criação de instâncias da classe por frameworks de persistência como JPA.
     */
    public PodcastCadastro() {}

    /**
     * Construtor para a classe {@code PodcastCadastro} com todos os atributos.
     * 
     * @param idPodcast o identificador único do podcast.
     * @param produtor o nome do produtor do podcast.
     * @param nomeEpisodio o nome do episódio do podcast.
     * @param numEpisodio o número do episódio do podcast.
     * @param duracao a duração do episódio em minutos.
     * @param url a URL do episódio do podcast.
     */
    public PodcastCadastro(int idPodcast, String produtor, String nomeEpisodio, int numEpisodio, int duracao, String url) {
        this.idPodcast = idPodcast;
        this.produtor = produtor;
        this.nomeEpisodio = nomeEpisodio;
        this.numEpisodio = numEpisodio;
        this.duracao = duracao;
        this.url = url;
    }

    /**
     * Obtém o identificador único do podcast.
     * 
     * @return o identificador do podcast.
     */
    public int getIdPodcast() {
        return idPodcast;
    }

    /**
     * Define o identificador único do podcast.
     * 
     * @param idPodcast o identificador do podcast a ser definido.
     */
    public void setIdPodcast(int idPodcast) {
        this.idPodcast = idPodcast;
    }

    /**
     * Obtém o nome do produtor do podcast.
     * 
     * @return o nome do produtor.
     */
    public String getProdutor() {
        return produtor;
    }

    /**
     * Define o nome do produtor do podcast.
     * 
     * @param produtor o nome do produtor a ser definido.
     */
    public void setProdutor(String produtor) {
        this.produtor = produtor;
    }

    /**
     * Obtém o nome do episódio do podcast.
     * 
     * @return o nome do episódio.
     */
    public String getNomeEpisodio() {
        return nomeEpisodio;
    }

    /**
     * Define o nome do episódio do podcast.
     * 
     * @param nomeEpisodio o nome do episódio a ser definido.
     */
    public void setNomeEpisodio(String nomeEpisodio) {
        this.nomeEpisodio = nomeEpisodio;
    }

    /**
     * Obtém o número do episódio do podcast.
     * 
     * @return o número do episódio.
     */
    public int getNumEpisodio() {
        return numEpisodio;
    }

    /**
     * Define o número do episódio do podcast.
     * 
     * @param numEpisodio o número do episódio a ser definido.
     */
    public void setNumEpisodio(int numEpisodio) {
        this.numEpisodio = numEpisodio;
    }

    /**
     * Obtém a duração do episódio em minutos.
     * 
     * @return a duração do episódio.
     */
    public int getDuracao() {
        return duracao;
    }

    /**
     * Define a duração do episódio em minutos.
     * 
     * @param duracao a duração do episódio a ser definida.
     */
    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    /**
     * Obtém a URL do episódio do podcast.
     * 
     * @return a URL do episódio.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Define a URL do episódio do podcast.
     * 
     * @param url a URL do episódio a ser definida.
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Método não suportado.
     * 
     * <p>Este método foi gerado automaticamente e não é suportado para a classe {@code PodcastCadastro}.
     * 
     * @param aInt um valor inteiro (não utilizado).
     * @throws UnsupportedOperationException se o método for chamado.
     */
    public void setId(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); // Gerado pelo editor de código
    }
}
