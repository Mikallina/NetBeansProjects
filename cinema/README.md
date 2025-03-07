# Sistema Cinema - Gerenciamento de Filmes

## Contexto

O Sistema Cinema foi desenvolvido para gerenciar filmes em um site. Ele permite a exibição de uma lista de filmes, além de fornecer funcionalidades de interação com o usuário, como a troca de temas entre claro e escuro (dark mode). A aplicação foi criada utilizando o framework Spring Boot, com integração ao Thymeleaf para renderização das páginas, e persistência de dados via JPA com banco de dados MySQL.
O sistema também foi desenvolvido com foco em uma boa experiência de usuário, oferecendo uma interface limpa e responsiva, com uma funcionalidade adicional que permite aos usuários escolher entre o modo claro e o modo escuro, adaptando-se às preferências visuais do usuário.
Tecnologias

## Status do Projeto

> :construction: Projeto em construção :construction:

- Projeto concluído parcialmente, com funcionalidades e CRUD's completos.

---
## Tecnologias

O sistema utiliza as seguintes tecnologias:

- Spring Boot: Framework principal para o desenvolvimento de aplicações Java.
- Spring MVC: Arquitetura para controle de rotas e interações HTTP.
- Thymeleaf: Motor de templates utilizado para renderizar as páginas HTML.
- JPA (Java Persistence API): Para gerenciamento da persistência de dados.
- MySQL: Banco de dados relacional utilizado para armazenar informações sobre filmes.
- H2 Database (para testes): Banco de dados em memória usado para testes locais e desenvolvimento.
- Java 17: Versão do JDK utilizada para desenvolvimento.
- Maven: Gerenciador de dependências e build do projeto.
- Bootstrap 4/5: Framework CSS para desenvolvimento responsivo e estilização da interface.
- JavaScript: Para funcionalidades dinâmicas, como troca de tema.
- Cookies: Para armazenar a preferência do usuário sobre o tema (claro/escuro).

---

## Objetivos

O objetivo principal do sistema é fornecer uma aplicação web simples para gerenciamento de filmes, permitindo que o usuário:

- Visualize uma lista de filmes.
- Alterne entre temas claro e escuro.
- Faça alterações no banco de dados de filmes (em um ambiente administrativo).
- Tenha uma experiência de navegação fluída e intuitiva.

---
## Funcionalidades

### 1. Exibição de Filmes
    - O sistema exibe uma lista de filmes com informações como título, ano de lançamento e sinopse.
    - Cada filme na lista possui um link para visualizar mais detalhes.

### 2. Troca de Tema
    - Os usuários podem alternar entre o modo claro e escuro, melhorando a experiência visual.
    - A troca de tema é persistida no navegador por meio de cookies, de modo que o tema selecionado seja mantido mesmo após o fechamento do navegador.
### 3. Cadastro e Gerenciamento de Filmes (Administração)
    - O sistema permite que administradores cadastrem novos filmes, editem e excluam filmes do banco de dados.
    - O gerenciamento de filmes é realizado por meio de formulários simples de preenchimento.
### 4. Autenticação e Autorização
    - A aplicação utiliza autenticação baseada em sessão para garantir que somente usuários autorizados (administradores) possam realizar ações de CRUD (Create, Read, Update, Delete) nos filmes.
### 5. Responsividade
    - O layout do sistema é completamente responsivo, adaptando-se bem a dispositivos móveis e desktops, utilizando o framework Bootstrap.
### 6. Persistência de Dados
    - Utiliza JPA com MySQL para persistir os dados dos filmes.
    - Para o desenvolvimento e testes locais, o sistema também oferece suporte ao H2 Database em memória.

---
## Como executar

### 1. Clone do Repositório:
    - Clone este repositório em seu diretório local
    - git clone https://github.com/mikallina/cinema.git

### 2. Configuração do Banco de Dados:
    - O sistema usa o MySQL. Você precisará de um banco de dados configurado com as credenciais corretas.
    - No arquivo application.properties, configure as credenciais do seu banco de dados:
        - spring.datasource.url=jdbc:mysql://localhost:3306/cinema
spring.datasource.username=root
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update

### 3. Instalar as depedências
    - Certifique-se de que o Maven está instalado em seu sistema.
    - No terminal, execute o comando abaixo para baixar as dependências do Maven: mvn clean install
### 4. Executar o projeto:
    - Para rodar o projeto em modo de desenvolvimento, execute o comando: mvn spring-boot:run

### 5. Acessar o Sistema
    - Abra o navegador e acesse: http://localhost/8080/filmes








