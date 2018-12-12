-- INSERÇÃO INICIAL

INSERT INTO parametro_geral(str_chave, str_valor) VALUES ('ANO_LETIVO','2018');
INSERT INTO parametro_geral(str_chave, str_valor) VALUES ('VERSAO_BASE_DADOS', '3.0');

-- USUARIO PADRAO

INSERT INTO usuario(str_login, str_nome, str_senha) VALUES ('admin', 'Administrador', '41e5653fc7aeb894026d6bb7b2db7f65902b454945fa8fd65a6327047b5277fb'); -- admin12345

-- PERFIS

INSERT INTO perfil(str_perfil, str_nome) VALUES ('admin', 'Administrador');
INSERT INTO perfil(str_perfil, str_nome) VALUES ('diret', 'Diretor');
INSERT INTO perfil(str_perfil, str_nome) VALUES ('coord', 'Coordenador');
INSERT INTO perfil(str_perfil, str_nome) VALUES ('comum', 'Usuário Comum');
INSERT INTO perfil(str_perfil, str_nome) VALUES ('respo', 'Responsável');	

-- RELACIONAMENTOS PERFIS

INSERT INTO usuario_perfil(str_login, str_perfil) VALUES ('admin', 'admin');

-- NOVOS PARAMETROS

INSERT INTO parametro_geral(str_chave, str_valor) VALUES ('MAIL_SMTP_SERVER','smtp.provider.com'); -- smpt.gmail.com
INSERT INTO parametro_geral(str_chave, str_valor) VALUES ('MAIL_SENDER','sender@email.com');
INSERT INTO parametro_geral(str_chave, str_valor) VALUES ('MAIL_PASSWORD','');
INSERT INTO parametro_geral(str_chave, str_valor) VALUES ('MAIL_NAME_SENDER','Sender');
INSERT INTO parametro_geral(str_chave, str_valor) VALUES ('EMAIL_CONTATO_ADM','company@email.com');
INSERT INTO parametro_geral(str_chave, str_valor) VALUES ('NOME_EMPRESA','Company Name');
INSERT INTO parametro_geral(str_chave, str_valor) VALUES ('JOB_SMS_LIGADO','true');
INSERT INTO parametro_geral(str_chave, str_valor) VALUES ('JOB_EMAIL_LIGADO','true'); -- see antivirus permission

-- RELATORIOS

INSERT INTO relatorio(id_relatorio, str_nome) VALUES (1, 'BOLETIM DE FREQUÊNCIA');
INSERT INTO relatorio(id_relatorio, str_nome) VALUES (2, 'RELATÓRIO DE FALTAS');
INSERT INTO relatorio(id_relatorio, str_nome) VALUES (3, 'RELATÓRIO FREQUÊNCIA POR TURMA');
INSERT INTO relatorio(id_relatorio, str_nome) VALUES (4, 'RELATÓRIO DE PRESENÇA');
INSERT INTO relatorio(id_relatorio, str_nome) VALUES (5, 'RELATÓRIO DE OCORRÊNCIAS');
INSERT INTO relatorio(id_relatorio, str_nome) VALUES (6, 'FICHA DISCIPLINAR');
INSERT INTO relatorio(id_relatorio, str_nome) VALUES (7, 'RELATÓRIO DE OCORRÊNCIAS DUPLICADO');
