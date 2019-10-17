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
