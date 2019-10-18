# OBS
Para acessar a plicacao precisa da baixar o Postman e colocar antes de cada endpoint o endereco "52.14.19.166:8090" esse é o ip do servico no AWS, todas as rotas foram feitas com e sem autenticacao de usuario, a atividade bonus tambem foi feita "Requisito extra (bonus stage)".       



# ESTÓRIAS DE USUÁRIO
1 - Cadastrar um novo usuario com seus devidos carros - configurações 
Endpoint: /api/users  RequestMethod: POST
Json: 
{ 
   "firstName":"Hello",
   "lastName":"World",
   "email":"hello@world3.com",
   "birthday":"1990-05-01",
   "login":"hello.world",
   "password":"h3ll0",
   "phone":"988888888",
   "cars":[ 
      { 
         "year":2018,
         "licensePlate":"PDV-0265",
         "model":"Audi",
         "color":"White"
      },
       { 
         "year":2019,
         "licensePlate":"PDV-0267",
         "model":"Audi",
         "color":"White"
      }
   ]
}

2 - Listar todos os usuários: Listar os usuario que foi cadastrado
Endpoint: /api/users  RequestMethod: GET

3- Buscar um usuário pelo id: Atraves do id buscar o usuario solicitado.
Endpoint: /api/users/{id}  RequestMethod: GET

4- Atualizar um usuário pelo id: fazer atualizaçães no usuario pelo id
Endpoint: /api/users/{id}  RequestMethod: PUT
json:
{ 
   "firstName":"Hello",
   "lastName":"World",
   "email":"hello@world3.com",
   "birthday":"1990-05-01",
   "login":"hello.world",
   "password":"h3ll0",
   "phone":"988888888",
   "cars":[ 
      { 
         "year":2018,
         "licensePlate":"PDV-0099900",
         "model":"Audi",
         "color":"White"
      },
       { 
         "year":2019,
         "licensePlate":"PDV-0330",
         "model":"Audi",
         "color":"White"
      },
       { 
         "year":2019,
         "licensePlate":"PDV-0269",
         "model":"Audi",
         "color":"White"
      }
   ]
}

5 - Remover um usuário pelo id : remover o usuario pelo id
Endpoint: /api/users/{id}  RequestMethod: DELETE

6 - Logar no sistema : entrar no sistema atraves de login e password
Endpoint: /api/signin  RequestMethod: GET
obs: informar os dados pelo headers pos é mais seguro para informar login e senha.
Exemplo:
KEY: login  VALUE: hello.world3
KEY: password  VALUE: h3ll0

7 - Retornar as informações do usuário logado : retorna informacoes do usuario atraves do token 
Endpoint: /api/me  RequestMethod: GET
obs: informar do token é passada pelo headers. Quando for colocar o token sempre add "Token "+ Token gerado
Exemplo:
KEY: Authorization  VALUE: Token eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJoZWxsby53b3JsZCIsInVzZXJJZCI6IjYiLCJyb2xlIjoiYWRtaW4iLCJsYXN0TG9naW4iOiIyMDE5LTEwLTE3In0.09AqctIknITVFsKb67E82TKBBw2iL0ejPg6pE7oK1cNBhr7izl05TXgk0Mz17y7D7aDU-rJWe1gdemGfaT0_Cw

8 - Listar todos os carros do usuário logado : Lista todos os carros do usuario logado
Endpoint: api/cars  RequestMethod: GET
obs: informar do token é passada pelo headers. Quando for colocar o token sempre add "Token "+ Token gerado
Exemplo:
KEY: Authorization  VALUE: Token eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJoZWxsby53b3JsZCIsInVzZXJJZCI6IjYiLCJyb2xlIjoiYWRtaW4iLCJsYXN0TG9naW4iOiIyMDE5LTEwLTE3In0.09AqctIknITVFsKb67E82TKBBw2iL0ejPg6pE7oK1cNBhr7izl05TXgk0Mz17y7D7aDU-rJWe1gdemGfaT0_Cw

9 - Cadastrar e alterar Carro : foi feito as duas API juntas
obs: informar do token é passada pelo headers. Quando for colocar o token sempre add "Token "+ Token gerado
Exemplo:
KEY: Authorization  VALUE: Token eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJoZWxsby53b3JsZCIsInVzZXJJZCI6IjYiLCJyb2xlIjoiYWRtaW4iLCJsYXN0TG9naW4iOiIyMDE5LTEwLTE3In0.09AqctIknITVFsKb67E82TKBBw2iL0ejPg6pE7oK1cNBhr7izl05TXgk0Mz17y7D7aDU-rJWe1gdemGfaT0_Cw

cadastro
Endpoint: api/cars  RequestMethod: POST
json:
{
"year": 2018,
"licensePlate": "PDV-06737",
"model": "Audi",
"color": "White"
}

alteracao:
Endpoint: /api/cars/{id}  RequestMethod: PUT
json: 
{
"year": 2020,
"licensePlate": "PDV-073335",
"model": "Aaudio",
"color": "red"
}

10 - Desafio do Requisito extra concluido : desafio concluido.


