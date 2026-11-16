# Cadastro de Clientes em Java

Aplicacao de console para gerenciamento de clientes, desenvolvida
com Java 25, Maven e JUnit 5.

## Funcionalidades

- Cadastrar clientes com ID sequencial
- Consultar clientes pelo ID
- Listar clientes
- Atualizar nome, e-mail e telefone
- Excluir clientes
- Impedir e-mails duplicados, sem diferenciar maiusculas e minusculas
- Validar campos obrigatorios e telefone
- Tratar entradas invalidas sem encerrar o programa

## Tecnologias

- Java 25
- Maven
- JUnit 5
- GitHub Actions

## Estrutura

- `Cliente`: entidade e validacoes
- `ClienteService`: regras de negocio e CRUD
- `App`: interface de linha de comando
- `src/test`: testes automatizados

## Executar

Na raiz do projeto:

```powershell
mvn clean verify
java -cp target/classes br.com.portfolio.clientes.App
```

## Menu

1. Cadastrar
2. Listar
3. Consultar
4. Atualizar
5. Excluir
0. Sair

## Regras

- IDs devem ser positivos.
- Nome e telefone sao obrigatorios.
- O e-mail e normalizado para letras minusculas.
- Telefones devem conter 10 ou 11 digitos.
- Nao sao permitidos e-mails duplicados.
- Clientes inexistentes produzem mensagem de erro.

## Testes

```powershell
mvn clean verify
```

O projeto inclui testes da entidade, do servico e do fluxo CLI.

## Limitacoes

Os dados sao armazenados exclusivamente em memoria e nao persistem
apos o encerramento do programa.

A validacao de e-mail e telefone e simplificada e nao substitui
uma biblioteca especializada para validacao internacional.

A aplicacao foi projetada para uso local, sem autenticacao.

## Evolucoes futuras

- Persistencia com banco de dados
- API REST com Spring Boot
- Paginacao e filtros
- Validacao especializada de contatos