# Presente Backend (Java)

## Linguagens e Frameworks

- Java 11
- Hibernate
- Spring
- MySQL
- Amazon Simple Storage Service

## Exemplos

Exemplo de implementação com Angular (Frontend) em https://chbandeira.github.io/presente/ no Heroku (delay de 30 segundos por estar em servidor free).

<img src="https://github.com/chbandeira/presente-backend/blob/master/PresentSample1.jpg">
<img src="https://github.com/chbandeira/presente-backend/blob/master/PresentSample2.jpg">


## Heroku

system.properties está a propriedade para rodar na versão 11 do Java

Procfile é o arquivo que especifica o comando a ser executado na inicialização do app. Veja mais em https://devcenter.heroku.com/articles/procfile

Comando <b>git push heroku master</b> publica na conta Heroku logada

Acesso a base do Heroku, não utilizar acesso SSL (exemplo: spring.datasource.url=jdbc:mysql://localhost:3306/database?<b>useSSL=false</b>) 

## Variáveis de ambiente

Necessário conta AWS com Bucket S3, um email válido para envio de registro do aluno pela aplicação.

Pode ser informadas nas variáveis do Heroku ou no catalina.properties do Tomcat. 

<pre>
AWS_ACCESS_KEY_ID=
AWS_SECRET_ACCESS_KEY=
DATASOURCE_URL=
DATASOURCE_USER=
DATASOURCE_PASS=
JWT_SECRET=algum aleatório a sua escolha
MAIL_PASSWORD=
MAIL_USERNAME=
S3_BUCKET=
S3_REGION=
</pre>
