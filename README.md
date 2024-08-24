# Sistema de votação simples

Esta aplicação se trata de um sistema de votação simples, onde é possível registrar 
candidatos, eleitores, cargos, criar uma seção de votação, registrar voto para um candidato
em uma seção, fechar seção e fazer um boletim de seção. Para registrar um candidato, eleitor ou
cargo, basta apenas utilizar o seu nome. Candidatos, eleitores e cargos sao gerenciados por CRUDs,
enquanto a seção e o voto seguem regras específicas.

A documentação pode ser acessada ao executar o projeto e acessar o endpoint:
```
/swagger-ui/index.html#
```

## Tecnologias utilizadas

- Java 17: Implementação da aplicação
- Spring Boot 3.3.3: Implementação da aplicação
- Docker: Conteineres para execução da aplicação
- Redis: Armazenamento de cache para boletim
- MySQL: Banco de dados da aplicação

## Requisitos

Para rodar a aplicação, é necessário ter instalado no sistema:

- Docker
- Docker-Compose
- Java 17+

## Instalação

### Baixando e configurando as ferramentas necessárias

1. Com o docker propriamente instalado, baixe o arquivo [compose.yml](https://github.com/wendersonp/poll/releases)
   e salve em uma pasta de sua preferência
2. Rode o seguinte comando através do terminal no diretório com o arquivo salvo
```
docker-compose -f voting-compose.yaml up -d
```
3. Com isto, as ferramentas já devem estar rodando normalmente

### Executando a aplicação

1. Baixe o [binário da release](https://github.com/wendersonp/voting/releases)
   Pelo link ou através da página de releases no repositório
2. Após ter executado o docker-compose, criado os conteineres com as ferramentas necessárias e 
3. ter instalado a Runtime do Java, execute a aplicação com o seguinte comando:

```
java -jar voting-<versao>.jar
```

### Testando se tá tudo certo

Acesse o endereço a seguir no navegador para conferir se a aplicação está executando corretamente:

```
http://localhost:8080/swagger-ui/index.html
```
Caso a execução teve êxito, a página referida acima já ser acessada e utilizada
para gerenciar a aplicação.

A pagina em questão foi produzida com ajuda do Swagger, caso queira acessar os
endpoints da aplicação  através de outro aplicativo, obtenha a especificação
OpenAPI 3.0 da aplicação através de:

```
http://localhost:8080/v3/api-docs
```


## Versão

A versão do projeto é controlada por versionamento semântico, com a criação de releases no github.

## Autores

- Wenderson, *engenheiro de software e desenvolvedor*, [wendersonp](https://github.com/wendersonp)


