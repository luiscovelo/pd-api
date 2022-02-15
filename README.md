# DESAFIO PD HOURS

<br>

## Pré-requisito:

- Java 11+;
- Docker para subir o banco de dados, caso não tiver, veja a instrução na seção abaixo (Banco de dados);

<br>

## Banco de dados:

Caso não tenha o docker instalado, é necessário que tenha o SGBD PostgreSQL instalado:

- O projeto está configurado com as seguintes credencias de acesso ao banco:
    - Host: localhost:5432
    - Usuário: postgres
    - Senha: postgres
    - Database: pdhours
- Crie a database no seu PostsgreSL com o nome de "pdhours" e importe o schema localizado na raiz do projeto, schema.sql.

<br>

## Documentação dos endpoints:

Os endpoins foram documentados usando o programa Insomnia, para ter acesso a coleção de endpoints, procure o arquivo "documentacao-endpoints-api.json" na raiz do projeto e importe no seu aplicativo Insomnia.

<br>

# Como rodar nossa API:
    
É de extrema importância rodar os comandos na sequência correta.

<br>

Abra um terminal e faça o clone do projeto e logo em seguida entre na pasta:

    git clone https://github.com/luiscovelo/pd-api.git
    cd pd-api

<br>

Ainda dentro da pasta pd-api e rode o comando para subir o banco com docker, mantenha esse terminal aberto enquanto estiver usando a aplicação:
    
    sed -i 's/\r$//' ./db/01-init.sh
    docker-compose up
    
Se por algum motivo, o banco não subir com o Docker, por gentileza, crie manualmente conforme na seção "Banco de Dados".

<br>

Agora temos o banco de dados rodando, vamos subir nossa aplicação. Para isso, abra outro terminal dentro da pasta "pd-api" e rode os dois comandos, o primeiro pode demorar um pouco, mas aguarde até finalizar.

    cd pd-api
    ./mvnw test
    ./mvnw spring-boot:run

Caso ocorra tudo bem, a API estará rodando em http://localhost:8080, lembre-se de manter os dois terminais aberto enquanto usa a aplicação.

<br><br>

# Como rodar nosso front-end:


Abra um novo terminal e vamos fazer o clone da aplicação e rodar nossa aplicação

    git clone https://github.com/luiscovelo/pd-front.git
    cd pd-front
    ./mvnw spring-boot:run

Pronto, agora temos nosso front-end rodando em http://localhost:8081.
