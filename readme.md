# Star Wars.

O projeto consiste em desenvolver um crud, consumir uma api(swapi) e guardar os dados no MongoDB.

## Tecnologias utilizadas.

- Java 8.
- Spring Boot, Data, MVC, Cache e Actuator.
- Docker
- Junit 4.
- Mockito.
- Base de dados MongoDB embedded.

## Para rodar o projeto, execute os passos que estão descritos abaixo.

- Importar o projeto na sua IDE como um projeto maven.
- Baixar todas as dependências que estão configuradas no pom.
- Executar o main da classe br.com.starwars.StarWarsApplication

## Rodando com docker

Para rodar o projeto com docker é necessário estar na pasta raiz do projeto e seguir o passo abaixo.

- ```docker-compose up``` para subir a aplicação.

## Endpoints disponíveis.

### Método POST: http://localhost:8080/planet

**JSON:**

```
{
    "name": "Tatooine", 
    "climate": "Temperate",
    "terrain": "Rocks"
}
```

**Observação:** Só podem ser inseridos planetas que existam na SWAPI pois precisam ser exibidos a quantidade de aparições.

**Status:** 201 -> Criado com sucesso, 409 -> Conflito, esse planeta já existe na base., 400 -> name ou climate ou terrain vazio. e 404 -> Planeta não encontrado na Swapi.

**Response data:** 

```
{
    "id": "5b69ccc2d4b8750897f81442",
    "name": "Tatooine",
    "climate": "Temperate",
    "terrain": "Rocks",
    "apparitionsCount": 5
    
}
```

### Método GET: http://localhost:8080/planet

**Status:** 200 -> Retorna um array com ou sem planetas.

**Response data:** 

```
[
    {
        "id": "5b69ccc2d4b8750897f81442",
        "name": "Tatooine",
        "climate": "Temperate",
        "terrain": "Rocks",
        "apparitionsCount": 5
    },
    {
        "id": "5b69d471d4b8755aaf0a5bc0",
        "name": "Yavin IV",
        "climate": "Temperate",
        "terrain": "Mountains",
        "apparitionsCount": 1
    }
]
```

### Método GET: http://localhost:8080/planet?name={name}

**Status:** 200 -> Encontrado com sucesso. e 404 -> Planeta não encontrado na base de dados.

**Response data:** 

```
{
    "id": "5b69ccc2d4b8750897f81442",
    "name": "Tatooine",
    "climate": "Temperate",
    "terrain": "Rocks",
    "apparitionsCount": 5
    
}
```

### Método GET: http://localhost:8080/planet/{id}

**Status:** 200 -> Encontrado com sucesso. e 404 -> Planeta não encontrado na base de dados.

**Response data:** 

```
{
    "id": "5b69ccc2d4b8750897f81442",
    "name": "Tatooine",
    "climate": "Temperate",
    "terrain": "Rocks",
    "apparitionsCount": 5
    
}
```

### Método DELETE: http://localhost:8080/planet?name={name}

**Status:** 204 -> Deletado com sucesso. e 404 -> Planeta não encontrado na base de dados.

### Método DELETE: http://localhost:8080/planet/{id}

**Status:** 204 -> Deletado com sucesso. e 404 -> Planeta não encontrado na base de dados.

## Build do projeto e execução dos testes

Para contruir o projeto e executar todos os testes(unitários + integração).

	mvn clean package

## Monitoramento

Como o projeto utiliza o Actuator, é possível consultar a saúde do serviço por exemplo pelo endereço:

- http://localhost:8080/actuator/health