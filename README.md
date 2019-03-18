# Pan App

Repositorio com um simples exemplo de projeto Java utilizando Spring Boot e Banco de dados em memória.

> Para executar o projeto localmente:

**Requerimentos**
Java 1.8+
Maven
Git

**Obtendo o projeto**
```
git clone git@github.com:fabriciomaciel/pan-app.git
```

**Executando o projeto**
```
cd pan-app
mvn spring:boot run
```

> O que está incluso no projeto:

Como usamos um banco de dados em memória, assim que a aplicação é executada inserimos alguns dados para
podermos testar nossa API Rest.

O arquivo: *br.com.maciel.panapp.MockDevData.java* possui os dados que são inseridos no banco de dados.

### API Rest (URLs demonstradas possuem o endereço para testes locais)

# Pessoa

### Consultar uma pessoa utilizando seu CPF como parâmetro.

**[GET]** http://localhost:8080/panapp/pessoa/45081816604

### Consultar o endereço pessoa utilizando seu CPF e CEP como parâmetro.

**[GET]** http://localhost:8080/panapp/pessoa/45081816604/endereco/02721200

### Alterar o endereço de uma pessoa

**[PUT]** http://localhost:8080/panapp/pessoa/45081816604/endereco/3

> exemplo de curl para realizar a alteração:
```
curl -X PUT \
  http://localhost:8080/panapp/pessoa/45081816604/endereco/4 \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: eb1fd97d-7c1a-4eee-9a3d-ea826d85d86d' \
  -H 'cache-control: no-cache' \
  -d '    {
        "apelidoEndereco": "Escritorio",
        "logradouro": "Nome da rua comercial",
        "numeroLogradouro": 100,
        "complemento": "2º andar sala 215",
        "bairro": "Freguesia do ó",
        "cep": "02621003",
        "cidade": "São Paulo",
        "estado": "SP"
    }'
```

# Endereço

### Consulta de estados brasileiros

**[GET]**  http://localhost:8080/panapp/endereco/estados

### Consulta de municipios de um determinado estados brasileiro

**[GET]**  http://localhost:8080/panapp/endereco/estados/35/municipios

### Consulta de um endereço pelo CEP

**[GET]**  http://localhost:8080/panapp/endereco/cep/02721200
