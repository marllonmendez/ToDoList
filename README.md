# To Do List

<div align="center">

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow)](https://opensource.org/licenses/MIT)
[![Java Version](https://img.shields.io/badge/Java-17%2B-blue)](https://www.java.com/)
[![GitHub repo size](https://img.shields.io/github/repo-size/marllonmendez/stories?color=blue)]()
[![GitHub code size in bytes](https://img.shields.io/github/languages/code-size/marllonmendez/stories?color=blue)]()

[![Sprinb Boot](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot)](https://spring.io/projects/spring-boot)
[![Hinernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)](https://hibernate.org/)
[![Maven](https://img.shields.io/badge/apache_maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)](https://maven.apache.org/)
[![Docker](https://img.shields.io/badge/Docker-2CA5E0?style=for-the-badge&logo=docker&logoColor=white)](https://www.docker.com/)

</div>

## Sobre
A API foi desenvolvida durante aulas de programação orientada a objetos com Java, utilizando o framework Spring Boot. Como banco de dados temporário, foi empregado o [H2 DB](https://www.h2database.com).

## Funções

- Criação de usuário;
- Criação de tarefa;
- Listagem de todas as tarefas;
- Listagem de tarefas de um usuário;
- Edição de tarefas;
- Remoção de tarefas.

## Execução
<h4>1. Instalação de Dependências:</h4>

```bash
mvn install
```
Este comando irá baixar as dependências do projeto e construir o projeto. Ele executa as fases `compile`, `test`, e `package` do ciclo de vida do Maven. O artefato construído geralmente será colocado no diretório `target` do projeto.

<h4>2. Execução do Projeto:</h4>

```bash
mvn spring-boot:run
```

- Se quiser parar de executar aperte as teclas ```ctrl + c```
- Se no terminal perguntar ``Deseja finalizar o arquivo em lotes (S/N)?`` responda ``S``

<h4>3. Limpeza do Projeto:</h4>

- Este comando remove os arquivos gerados durante a compilação e construção do projeto. Isso é útil se você deseja limpar o projeto antes de construir novamente.

```bash
mvn clean
```

## Licença

Este projeto está licenciado sob a [Licença MIT](LICENSE).
