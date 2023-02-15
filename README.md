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


### Exemplo de Post Person, é necessário pelo menos 1 endereço onde o primeiro endereço sera automaticamente colocado como principal

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


1. Durante a implementação de uma nova funcionalidade de software solicitada, quais critérios você avalia e implementa para garantia de qualidade de software?

Levando em conta que foi feito uma boa coleta de requisitos então podemos realizar algumas ações para o que foi solicitado seja entregue com qualidade, a primeira ação é realizar os testes tanto de Integração para garantir que a funcionalidade esteja funcionando perfeitamente com o sistema quanto de Unidade para garantir o funcionamento correto de cada parte do código. É essencial também a revisão de código, pois permite que os outros membros da equipe analisem o código e ajuda a identificar erros e também de melhorar o código, isso também permite verificar se a nova funcionalidade não tem vulnerabilidades.


2. Em qual etapa da implementação você considera a qualidade de software?

A qualidade de software deve ser considerada durante todas as etapas do processo de implementação, mas eu considero essencial na análise de requisitos, fazer um bom levantamento de requisitos garante um produto mais fiel às expectativas do cliente, além de evitar grandes mudanças poupando dinheiro e tempo para a empresa.