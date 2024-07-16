# Desafio Woke People

Para conseguir rodar o projeto local é necessário as IDE's VSCode e IntelliJ, 
também é necessário ter instalado em seu computador o Node na versão 20.15.1 e também o JDK (Java) 21.

## Passos para executar o projeto

1. Clone os repositórios de backend e frontend.

2. No projeto de frontend:
    - Abra o projeto no VSCode.
    - Instale as dependências com o comando `npm install` ou `npm i`.
    - Após a instalação das dependências, execute o projeto com o comando `npm run dev`.

3. No projeto de backend:
    - Abra o projeto no IntelliJ.
    - Aguarde o build das dependências do Maven.
    - Execute o projeto.

## Acessando a aplicação

Após os projetos estarem no ar, você pode acessar a tela de login através da rota `http://localhost:5173/login` e será necessário realizar um cadastro prévio para fazer login.

## Sobre o projeto

Basicamente, eu criei uma aplicação onde o usuário realizará um cadastro prévio e, após isso, poderá realizar o login. Depois de logado, o usuário verá as informações que ele cadastrou e vagas nas quais ele pode aplicar.

O projeto foi desenvolvido utilizando Kotlin com Spring Boot no backend e, para a interface, foi utilizado React + Vite.

Foi utilizado o banco de dados H2 para a persistência de dados, juntamente com Flyway para a criação de tabelas via migrations. Sempre que a aplicação é desligada, o banco de dados reinicia e será necessário realizar o processo de cadastro novamente para acessar as telas.

Bom divertimento!
