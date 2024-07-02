# Monitor de Pressão Arterial - Projeto Java

## Contexto

O projeto foi desenvolvido para atender a uma demanda de cardiologistas interessados em disponibilizar um software para que pacientes possam registrar suas medições de pressão arterial. O objetivo é que os pacientes possam levar um histórico das medições para suas consultas médicas.

## Funcionalidades Implementadas

- **Tela Principal:**
  - Uma única tela onde o usuário pode registrar:
    - Data da medição
    - Hora da medição
    - Pressão Sistólica (número inteiro)
    - Pressão Diastólica (número inteiro)
    - Opção para indicar se está em situação de estresse durante a medição

- **Componentes de Usabilidade e Acessibilidade:**
  - Descrições acessíveis e tooltips nos componentes da tela
  - Associação de rótulos (JLabel) aos componentes correspondentes
  - Ordem sequencial de foco nos componentes da tela para facilitar a navegação por teclado
  - Atalhos por teclado para acesso rápido aos botões e campos principais

- **Persistência de Dados:**
  - Os dados de cada medição são salvos em um arquivo de texto ou CSV.
  - Ao iniciar o programa, os dados são carregados e exibidos em uma tabela na mesma tela.

- **Feedback ao Usuário:**
  - Mensagens amigáveis são exibidas em caso de falhas, como entrada de valores inadequados nos campos.

## Tecnologias Utilizadas

- Linguagem: Java
- Interface Gráfica: Swing
- Ambiente de Desenvolvimento: NetBeans

## Como Executar o Projeto

1. **Clonar o Repositório:**
   - Clone o repositório do projeto para sua máquina local.

2. **Abrir o Projeto no NetBeans:**
   - Abra o NetBeans e importe o projeto.

3. **Executar o Projeto:**
   - Compile e execute o projeto no NetBeans.
   - A tela principal do Monitor de Pressão Arterial será exibida.

## Capturas de Tela

- *Incluir capturas de tela da tela principal do programa em funcionamento.*

## Contribuições

- Contribuições são bem-vindas! Sinta-se à vontade para fazer um fork do projeto e enviar pull requests com melhorias.

## Autor

- Michelle Borges
- Contato: mi_borges@msn.com

---

Este arquivo README.md fornece uma visão geral do projeto, suas funcionalidades principais, como executá-lo e informações adicionais para os usuários e desenvolvedores interessados no Monitor de Pressão Arterial.