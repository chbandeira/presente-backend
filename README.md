# Presente Backend (Java)

Java 11, Spring 5, MySQL 8 e AWS S3

Exemplo de implementação com Angular (Frontend) em https://chbandeira.github.io/presente/

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