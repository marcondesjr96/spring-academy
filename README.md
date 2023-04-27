# Spring Boot - Estudos de JPA, Banco de Dados, API Restful, Docker, Swagger, JWT e Mais

Este repositório contém uma série de estudos sobre o desenvolvimento de aplicativos com Spring Boot, focando em conceitos importantes como JPA, banco de dados, API Restful, Docker, Swagger e JWT. O objetivo deste repositório é fornecer uma referência útil para desenvolvedores que estão começando a trabalhar com essas tecnologias ou desejam aprimorar suas habilidades.

## Tecnologias utilizadas

- Spring Boot
- JPA/Hibernate
- Banco de dados (MySQL)
- API Restful
- Docker
- Swagger
- JWT
- Mais

## Como utilizar

1. Clone este repositório em sua máquina local:

```bash 
git clone https://github.com/seu-usuario/repositorio-spring-boot.git
```

2. Importe o projeto em sua IDE de preferência (Eclipse, IntelliJ, etc.)

3. Certifique-se de que o banco de dados MySQL está configurado corretamente em sua máquina. Caso contrário, altere as configurações de conexão do banco de dados no arquivo application.properties para as suas configurações de banco de dados.

4. Execute o projeto Spring Boot a partir da sua IDE ou utilize o comando mvn spring-boot:run no terminal para iniciar o servidor.

5. Você pode acessar a documentação Swagger da API em *http://localhost:8080/swagger-ui.html*. A partir daqui, você pode explorar a API e testar as diversas rotas.

6. Você também pode acessar o banco de dados através do console do MySQL ou utilizando alguma ferramenta gráfica como o MySQL Workbench.

7. Para executar o projeto em um contêiner Docker, certifique-se de ter o Docker instalado em sua máquina e execute os seguintes comandos:

```arduino
docker build -t nome-do-projeto .
docker run -p 8080:8080 nome-do-projeto
```

## Contribuição

Este repositório é aberto a contribuições! Se você deseja sugerir alguma melhoria, corrigir algum bug ou adicionar novos recursos, sinta-se à vontade para enviar um pull request.
