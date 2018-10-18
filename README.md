# presente
[PT-BR] Projeto de registro de entrada e saída de alunos em Java e JSF

# passos
1 - clonar pasta com os 4 projetos.
2 - "buildar" com clean package install os projetos na ordem: utilitario, presente-core, presente-comum-jsf e presente (esse último que será gerado o presente.war).
3 - presente.war que será usado para colocar no servidor.
# criar pasta principal necessária com os arquivos presente.png e logo.jpg:
C:\presente\

# estouro de memória ao gerar relatórios:
Com o servidor parado, no arquivo standalone.conf.bat, Provavelmente em (C:\presente\jboss\bin), substitua a linha de código de:
set "JAVA_OPTS=-Xms64M -Xmx512M -XX:MaxPermSize=256M"
Para:
set "JAVA_OPTS=-Xms128M -Xmx2048M -XX:MaxPermSize=1024M"

# necessário
- JDK7
- MAVEN2
- TENHA INSTALADO O JBOSS 7.1
- TENHA INSTALADO O POSTGRESQL 9.4
- EXECUTAR script_dados_iniciais.sql PELO pgAdmin NO SQL.
- CASO NÃO TENHA COLOCADO A IMAGEM logo.jpg NA PASTA presente, COLOQUE SE NÃO OCORRERÁ ERRO.
- O SERVIDOR SÓ PODERÁ SER INICIADO APÓS TODOS OS SCRIPTS DO BANCO SEREM EXECUTADOS E TODOS OS OUTROS PROCEDIMENTOS PARA IMPLANTAÇÃO FOREM FINALIZADOS.
- O UPDATE É PARA MUDAR O CÓDIGO DA ESCOLA APENAS UMA VEZ, NORMALMENTE NO INICIO APÓS CRIAR TODAS AS TABELAS NO BANCO DE DADOS.

# configuração do banco de dados
- hibernate.cfg.xml
- applicationContext.xml
username: presente
password: presente4123

# tabela parametro_geral
Informações devem ser alteradas para algum email criado.