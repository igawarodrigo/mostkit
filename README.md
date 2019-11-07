# MOSKIT TESTE DE NIVELAMENTO

### Requisitos:

 - Docker
 - Maven
 - IntelliJ
 
### Para executar o projeto
    docker run -p 5432:5432 --name postgres -e POSTGRES_PASSWORD=admin -d --volumes-from PostgresData postgres
    
Na sequencia, executar o arquivo sql na raíz do projeto. Irá gerar a tabela de contatos já usando a constraint para pelo
menos um telefone/email

Por fim, basta dar Run com a intellij na classe principal.

Mas, Rodrigo, tem dockerfile e docker-compose. Pse, tentei, ainda não rolou
    