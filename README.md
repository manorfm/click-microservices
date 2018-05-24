# Registro de clicks em microservicos

Caso necessite rodar local, é necessário baixar o lombok.jar e rodar > java -jar lombok.jar, selecionar os eclipses, se não aparecer, 
procurar onde ele está instalado para configurá-lo.

<br>
O projeto foi elaborado com java8, springboot, spring-data, spring cloud, orika e lombok 
com um gateway em zuul, disvorvery service com eureka, tracking distribuído com zipkin e monitoramento com spring cloud sleuth, 
ELK (Elasticsearch, logstash e kibana), todos os serviços dockerizados e configurados também com docker-compose.yml.

<br>

## Débito técnico:
  Adicionar o Kafka e um serviço como consumer apenas para tratar os $post da rota /clicks

<br>
Pra rodar, é necessário baixar o projeto, executar o mvn package, pra baixar as dependências e construir os serviços e em seguida 
fazer o docker-compose up -d --build

<br>
Após executar o docker-compose up -d --build vai estar disponível para acesso os seguintes serviços:

# Monitoramento
<ul>
  <li> link para acessar ao Zipkin: http://localhost:8701/zipkin </li>
  <li> link para acessar ao Eureka: http://localhost:8700 </li>
  <li> link para acessar ao Kibana: http://localhost:5601 </li>
</ul>

e expôe as seguintes apis com protocolo rest (json):

#Apis com protocolo rest (json):

## APIs - POST
> links de cadastro de usuário (http://localhost/api/users/users) <br>
  com body:
  ```javascript
      {
        "name": "Manoel Medeiros",
        "password": "12345"
      }
 ```
 <br>
 
> links de cadastro de clicks (http://localhost/api/clicks/clicks) <br>
  com body:
```javascript
    {
      "pis": 132551,
      "dateTime": "2018-04-18T08:15:50.485"
    }
```
<br>
 
 ## APIs - GET
> links para listar usuários (http://localhost/api/users/users) <br>
> links para acesso ao usuário pelo id (http://localhost/api/users/users/${id}) <br>
> links para listar clicks (http://localhost/api/clicks/clicks) <br>
> links para acesso ao clicks pelo id (http://localhost/api/clicks/clicks/${id}) <br>
<br>

## APIs fechadas - PUT
> links para update do usuário pelo id (http://localhost/api/users/users/${id})
<br>
 
## APIs fechadas - DELETE
> links para deletar o usuário pelo id (http://localhost/api/users/users/${id})
