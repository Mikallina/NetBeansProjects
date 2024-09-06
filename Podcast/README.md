# Projeto Cenaflix Podcast

## Contexto

A empresa **Cenaflix** deseja expandir suas operações e agora pretende entrar no mercado de streaming de áudio, com foco em podcasts. Para isso, desenvolvemos um módulo de gestão de podcasts que permitirá à equipe do Cenaflix acompanhar e cadastrar as informações da plataforma de podcast.

A tecnologia escolhida para essa aplicação é o **JPA (Java Persistence API)**, um framework robusto e amplamente utilizado para gestão de persistência de dados em Java. O JPA oferece segurança e manutenção simplificada para a aplicação.

## Atividade

O projeto consiste em uma aplicação Java com três telas principais:

1. **Tela de Login**
   - **Campos:** Usuário e Senha.
   - **Funcionalidade:** O usuário deve ser autenticado e, baseado em seu tipo (Administrador, Operador, ou Usuário), diferentes permissões serão concedidas. Após o login, uma mensagem será exibida em um `JOptionPane` informando o usuário sobre seu tipo e permissões.
   - **Tipos de Usuário e Permissões:**
     - **Administrador:** Pode cadastrar, excluir e listar podcasts.
     - **Operador:** Pode cadastrar e listar podcasts.
     - **Usuário:** Pode apenas listar podcasts.

2. **Tela de Cadastro de Podcast**
   - **Campos:** ID, Produtor, Nome do Episódio, Nº do Episódio, Duração e URL do Repositório.
   - **Funcionalidade:** Permite o cadastro de novos podcasts no sistema. 

3. **Tela de Listagem de Podcasts**
   - **Funcionalidade:** Exibe todos os podcasts cadastrados. Inclui um filtro por produtor para facilitar a pesquisa.

## Requisitos Técnicos

- **Java:** Utilização do JPA para persistência.
- **Maven:** Gerenciamento de dependências e construção do projeto.
- **Banco de Dados:** MySQL (ou outro banco de sua escolha).
- **IDE:** NetBeans ou qualquer outra IDE compatível com Java.

## Instruções para Execução

1. **Configuração do Ambiente:**
   - Certifique-se de ter o JDK e o Maven instalados no seu sistema.
   - Configure o banco de dados MySQL e ajuste as credenciais e a URL de conexão no código conforme necessário.

2. **Compilação e Execução:**
   - Navegue até o diretório do projeto e compile o projeto usando Maven:
     ```bash
     mvn clean install
     ```
   - Para executar o projeto, use o comando:
     ```bash
     mvn exec:java -Dexec.mainClass="com.mycompany.podcast.telas.TelaLogin"
     ```

3. **Gerar Documentação JavaDoc:**
   - Utilize o Maven para gerar a documentação JavaDoc:
     ```bash
     mvn javadoc:javadoc
     ```
   - A documentação será gerada na pasta `target/site/javadoc`.

4. **Executar o Projeto:**
   - Após a construção do projeto, o arquivo executável `.jar` será gerado na pasta `target`. Você pode executar o JAR com o comando:
     ```bash
     java -jar target/nome-do-arquivo.jar
     ```

## Entrega

Para a entrega da atividade, compacte o diretório do projeto e inclua os seguintes itens:

1. **Projeto NetBeans:** O diretório completo do projeto.
2. **Script SQL:** O script para a criação e estruturação do banco de dados.
3. **Documentação JavaDoc:** A documentação gerada pelo Maven, encontrada em `target/site/javadoc`.
4. **Arquivo Executável:** O arquivo `.jar` gerado pelo Maven.
