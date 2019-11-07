# MOSKIT TESTE DE NIVELAMENTO

### Requisitos:

 - Docker
 - Maven
 - IntelliJ
 
### Para executar o projeto
    docker create -v /var/lib/postgresql/data --name PostgresData alpine

    docker run -p 5432:5432 --name postgres -e POSTGRES_PASSWORD=admin -d --volumes-from PostgresData postgres
    
Na sequencia, executar o arquivo sql na raíz do projeto. Irá gerar a tabela de contatos já usando a constraint para pelo
menos um telefone/email

### Por fim: 
    docker-compose build
    docker-compose run
    
### Observações

 - Por ser algo rápido, acabei por não criar um servico, chamei o repository direto do controller
 - Não fiz testes para o repository porque ele está sem implementação própria, não costumo testar o framework
 - Com constraint check no banco, decidi por não validar na aplicação 
 - Claro, qualquer uma dessas coisas são adaptáveis