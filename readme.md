# Onboarding

Projeto desenvolvido com **Spring Boot 3.5.6** e **Java 21**.

## Branch

- Branch principal utilizada: `main`

## Testes

- Cobertura mínima de 90% nos casos de uso (`useCase`).

## Logs

- Foi utilizado **MDC** para rastreamento de IDs nos logs, facilitando o monitoramento e debugging.

## APIs

- Foram criadas 5 APIs para o processo de Onboarding.

## Validações

- As validações das requisições (`requests`) utilizam os padrões do Spring Validation para ganho de tempo.
- Há possibilidade de criar validações personalizadas, por exemplo, para o campo documento.

## Execução

- Antes de rodar a aplicação, é necessário iniciar os serviços dependentes via Docker Compose:

  ```bash
  docker-compose up -d
- A aplicação estará disponível na porta 8080.

## Documentação

- Swagger UI disponível em: http://localhost:8080/mybank/swagger-ui/index.html#/

## Repositório GitHub

- Código fonte disponível em: https://github.com/LucasWilliam11/onboarding