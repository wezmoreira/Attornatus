# API REST com Spring Boot

![Badge](http://img.shields.io/static/v1?label=STATUS&message=DEVELOPMENT&color=yellow&style=for-the-badge)

<p align="center">API teste tecnico Attornatus<p>

## Coleção Postman
- * [Postman](https://github.com/wezmoreira/Attornatus/blob/main/PostmanCollection/Attornatus.postman_collection.json)

## Teste tecnico Attornatus.

- Qualidade de código
- Java, Spring boot
- API REST
- Testes
- Clean Code


### Exemplo de Post Person, o primeiro endereço sera automaticamente colocado como principal

```
{
    "name": "Wesley",
    "birthDate": "20/06/1997",
    "address": [
        {
            "street": "Av. Principal",
            "cep": "11223344",
            "number": "10",
            "city": "Sarandi"
        }
    ]
}
```