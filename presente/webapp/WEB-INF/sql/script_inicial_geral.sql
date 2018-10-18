-- ##################################
-- ##### INICIO DA VERSÃO 0.2.0 #####
-- ##################################
--
-- TOC entry 161 (class 1259 OID 34672)
-- Dependencies: 1928 5
-- Name: aluno; Type: TABLE; Schema: public; Owner: presente; Tablespace: 
--

CREATE TABLE aluno (
    id_aluno bigint NOT NULL,
    bol_ativo boolean DEFAULT true,
    dat_nascimento date NOT NULL,
    str_nome character varying(255) NOT NULL
);


ALTER TABLE public.aluno OWNER TO presente;

--
-- TOC entry 162 (class 1259 OID 34678)
-- Dependencies: 1929 5
-- Name: matricula; Type: TABLE; Schema: public; Owner: presente; Tablespace: 
--

CREATE TABLE matricula (
    id_matricula bigint NOT NULL,
    int_ano_letivo integer,
    bol_ativo boolean DEFAULT true,
    bol_bolsista boolean,
    dat_data_matricula timestamp without time zone NOT NULL,
    str_matricula character varying(255),
    str_turno character varying(255),
    id_aluno bigint NOT NULL,
    id_sala bigint,
    id_serie bigint,
    id_turma bigint
);


ALTER TABLE public.matricula OWNER TO presente;

--
-- TOC entry 163 (class 1259 OID 34687)
-- Dependencies: 5
-- Name: parametro_geral; Type: TABLE; Schema: public; Owner: presente; Tablespace: 
--

CREATE TABLE parametro_geral (
    str_chave character varying(255) NOT NULL,
    str_valor character varying(255)
);


ALTER TABLE public.parametro_geral OWNER TO presente;

--
-- TOC entry 164 (class 1259 OID 34695)
-- Dependencies: 5
-- Name: registro; Type: TABLE; Schema: public; Owner: presente; Tablespace: 
--

CREATE TABLE registro (
    id_registro bigint NOT NULL,
    dat_data timestamp without time zone NOT NULL,
    str_tipo_registro character varying(255),
    id_matricula bigint
);


ALTER TABLE public.registro OWNER TO presente;

--
-- TOC entry 165 (class 1259 OID 34700)
-- Dependencies: 1930 5
-- Name: sala; Type: TABLE; Schema: public; Owner: presente; Tablespace: 
--

CREATE TABLE sala (
    id_sala bigint NOT NULL,
    bol_ativo boolean DEFAULT true,
    str_descricao character varying(255) NOT NULL
);


ALTER TABLE public.sala OWNER TO presente;

--
-- TOC entry 168 (class 1259 OID 34743)
-- Dependencies: 5
-- Name: seq_aluno; Type: SEQUENCE; Schema: public; Owner: presente
--

CREATE SEQUENCE seq_aluno
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_aluno OWNER TO presente;

--
-- TOC entry 169 (class 1259 OID 34745)
-- Dependencies: 5
-- Name: seq_matricula; Type: SEQUENCE; Schema: public; Owner: presente
--

CREATE SEQUENCE seq_matricula
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_matricula OWNER TO presente;

--
-- TOC entry 170 (class 1259 OID 34747)
-- Dependencies: 5
-- Name: seq_registro; Type: SEQUENCE; Schema: public; Owner: presente
--

CREATE SEQUENCE seq_registro
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_registro OWNER TO presente;

--
-- TOC entry 171 (class 1259 OID 34749)
-- Dependencies: 5
-- Name: seq_sala; Type: SEQUENCE; Schema: public; Owner: presente
--

CREATE SEQUENCE seq_sala
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_sala OWNER TO presente;

--
-- TOC entry 172 (class 1259 OID 34751)
-- Dependencies: 5
-- Name: seq_serie; Type: SEQUENCE; Schema: public; Owner: presente
--

CREATE SEQUENCE seq_serie
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_serie OWNER TO presente;

--
-- TOC entry 173 (class 1259 OID 34753)
-- Dependencies: 5
-- Name: seq_turma; Type: SEQUENCE; Schema: public; Owner: presente
--

CREATE SEQUENCE seq_turma
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.seq_turma OWNER TO presente;

--
-- TOC entry 166 (class 1259 OID 34706)
-- Dependencies: 1931 5
-- Name: serie; Type: TABLE; Schema: public; Owner: presente; Tablespace: 
--

CREATE TABLE serie (
    id_serie bigint NOT NULL,
    bol_ativo boolean DEFAULT true,
    str_descricao character varying(255) NOT NULL
);


ALTER TABLE public.serie OWNER TO presente;

--
-- TOC entry 167 (class 1259 OID 34712)
-- Dependencies: 1932 5
-- Name: turma; Type: TABLE; Schema: public; Owner: presente; Tablespace: 
--

CREATE TABLE turma (
    id_turma bigint NOT NULL,
    bol_ativo boolean DEFAULT true,
    descricao character varying(255) NOT NULL
);


ALTER TABLE public.turma OWNER TO presente;

--
-- TOC entry 1934 (class 2606 OID 34677)
-- Dependencies: 161 161 1966
-- Name: aluno_pkey; Type: CONSTRAINT; Schema: public; Owner: presente; Tablespace: 
--

ALTER TABLE ONLY aluno
    ADD CONSTRAINT aluno_pkey PRIMARY KEY (id_aluno);


--
-- TOC entry 1936 (class 2606 OID 34686)
-- Dependencies: 162 162 1966
-- Name: matricula_pkey; Type: CONSTRAINT; Schema: public; Owner: presente; Tablespace: 
--

ALTER TABLE ONLY matricula
    ADD CONSTRAINT matricula_pkey PRIMARY KEY (id_matricula);


--
-- TOC entry 1938 (class 2606 OID 34694)
-- Dependencies: 163 163 1966
-- Name: parametro_geral_pkey; Type: CONSTRAINT; Schema: public; Owner: presente; Tablespace: 
--

ALTER TABLE ONLY parametro_geral
    ADD CONSTRAINT parametro_geral_pkey PRIMARY KEY (str_chave);


--
-- TOC entry 1940 (class 2606 OID 34699)
-- Dependencies: 164 164 1966
-- Name: registro_pkey; Type: CONSTRAINT; Schema: public; Owner: presente; Tablespace: 
--

ALTER TABLE ONLY registro
    ADD CONSTRAINT registro_pkey PRIMARY KEY (id_registro);


--
-- TOC entry 1942 (class 2606 OID 34705)
-- Dependencies: 165 165 1966
-- Name: sala_pkey; Type: CONSTRAINT; Schema: public; Owner: presente; Tablespace: 
--

ALTER TABLE ONLY sala
    ADD CONSTRAINT sala_pkey PRIMARY KEY (id_sala);


--
-- TOC entry 1944 (class 2606 OID 34711)
-- Dependencies: 166 166 1966
-- Name: serie_pkey; Type: CONSTRAINT; Schema: public; Owner: presente; Tablespace: 
--

ALTER TABLE ONLY serie
    ADD CONSTRAINT serie_pkey PRIMARY KEY (id_serie);


--
-- TOC entry 1946 (class 2606 OID 34717)
-- Dependencies: 167 167 1966
-- Name: turma_pkey; Type: CONSTRAINT; Schema: public; Owner: presente; Tablespace: 
--

ALTER TABLE ONLY turma
    ADD CONSTRAINT turma_pkey PRIMARY KEY (id_turma);


--
-- TOC entry 1950 (class 2606 OID 34733)
-- Dependencies: 162 1941 165 1966
-- Name: fk3e46fd7e478cbfe0; Type: FK CONSTRAINT; Schema: public; Owner: presente
--

ALTER TABLE ONLY matricula
    ADD CONSTRAINT fk3e46fd7e478cbfe0 FOREIGN KEY (id_sala) REFERENCES sala(id_sala);


--
-- TOC entry 1948 (class 2606 OID 34723)
-- Dependencies: 162 161 1933 1966
-- Name: fk3e46fd7ea81a35bc; Type: FK CONSTRAINT; Schema: public; Owner: presente
--

ALTER TABLE ONLY matricula
    ADD CONSTRAINT fk3e46fd7ea81a35bc FOREIGN KEY (id_aluno) REFERENCES aluno(id_aluno);


--
-- TOC entry 1947 (class 2606 OID 34718)
-- Dependencies: 1943 162 166 1966
-- Name: fk3e46fd7eaa0f0ede; Type: FK CONSTRAINT; Schema: public; Owner: presente
--

ALTER TABLE ONLY matricula
    ADD CONSTRAINT fk3e46fd7eaa0f0ede FOREIGN KEY (id_serie) REFERENCES serie(id_serie);


--
-- TOC entry 1949 (class 2606 OID 34728)
-- Dependencies: 1945 167 162 1966
-- Name: fk3e46fd7eaa39cab0; Type: FK CONSTRAINT; Schema: public; Owner: presente
--

ALTER TABLE ONLY matricula
    ADD CONSTRAINT fk3e46fd7eaa39cab0 FOREIGN KEY (id_turma) REFERENCES turma(id_turma);


--
-- TOC entry 1951 (class 2606 OID 34738)
-- Dependencies: 1935 162 164 1966
-- Name: fkd6dc30339cf043e2; Type: FK CONSTRAINT; Schema: public; Owner: presente
--

ALTER TABLE ONLY registro
    ADD CONSTRAINT fkd6dc30339cf043e2 FOREIGN KEY (id_matricula) REFERENCES matricula(id_matricula);

--INSERÇÃO INICIAL

INSERT INTO parametro_geral(str_chave, str_valor) VALUES ('ANO_LETIVO','2013');

-- ########################
-- ##### VERSÃO 0.3.0 #####
-- ########################
ALTER TABLE aluno ALTER dat_nascimento DROP NOT NULL;
ALTER TABLE matricula ALTER bol_bolsista SET DEFAULT false;

-- ########################
-- ##### VERSÃO 0.3.1 #####
-- ########################
CREATE SEQUENCE seq_ocorrencia_importacao
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_ocorrencia_importacao
  OWNER TO presente;

CREATE TABLE ocorrencia_importacao
(
  id_ocorrencia_importacao bigint NOT NULL,
  str_matricula character varying(255),
  str_nome character varying(255),
  str_serie character varying(255),
  str_turma character varying(255),
  str_turno character varying(255),
  CONSTRAINT ocorrencia_importacao_pkey PRIMARY KEY (id_ocorrencia_importacao)
);
ALTER TABLE ocorrencia_importacao OWNER TO presente;

-- ########################
-- ##### VERSÃO 0.4.0 #####
-- ########################
CREATE TABLE usuario
(
  str_login character varying(255) NOT NULL,
  bol_ativo boolean NOT NULL DEFAULT true,
  str_nome character varying(255) NOT NULL,
  str_senha character varying(255) NOT NULL,
  CONSTRAINT usuario_pkey PRIMARY KEY (str_login )
);
ALTER TABLE usuario
  OWNER TO presente;

CREATE TABLE perfil
(
  str_perfil character varying(255) NOT NULL,
  str_nome character varying(255) NOT NULL,
  CONSTRAINT perfil_pkey PRIMARY KEY (str_perfil )
);
ALTER TABLE perfil
  OWNER TO presente;

CREATE TABLE usuario_perfil
(
  str_login character varying(255) NOT NULL,
  str_perfil character varying(255) NOT NULL,
  CONSTRAINT fk57cd23fd57d4ae9c FOREIGN KEY (str_perfil)
      REFERENCES perfil (str_perfil) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk57cd23fda844bc93 FOREIGN KEY (str_login)
      REFERENCES usuario (str_login) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
ALTER TABLE usuario_perfil
  OWNER TO presente;

--USUARIOS PADRAO

INSERT INTO usuario(str_login, str_nome, str_senha)
    VALUES ('admin', 'Administrador', 'efb82089ef1e5cb475cc8ba9e97a5d9a72f621873677ca7686d239a9364c9918');

INSERT INTO usuario(str_login, str_nome, str_senha)
    VALUES ('comum', 'Usuário Comum', 'bb2819463f481b9f05114eb630da5ad5cdb696e4ad7d7f5b28d13ab047ae03a5'); --comum123

--PERFIS

INSERT INTO perfil(str_perfil, str_nome)
    VALUES ('admin', 'Administrador');

INSERT INTO perfil(str_perfil, str_nome)
    VALUES ('diret', 'Diretor');

INSERT INTO perfil(str_perfil, str_nome)
    VALUES ('coord', 'Coordenador');

INSERT INTO perfil(str_perfil, str_nome)
    VALUES ('comum', 'Usuário Comum');

--RELACIONAMENTOS PERFIS

INSERT INTO usuario_perfil(str_login, str_perfil)
    VALUES ('admin', 'admin');

INSERT INTO usuario_perfil(str_login, str_perfil)
    VALUES ('comum', 'comum');

--ADICIONANDO VERSÃO DA BASE DA DADOS
INSERT INTO parametro_geral(str_chave, str_valor)
    VALUES ('VERSAO_BASE_DADOS', '0.4.0');

-- ########################
-- ##### VERSÃO 0.4.1 #####
-- ########################
--ATUALIZAR VERSÃO DA BASE DA DADOS
UPDATE parametro_geral SET str_valor = '0.4.1' WHERE str_chave = 'VERSAO_BASE_DADOS';

-- ########################
-- ##### VERSÃO 0.5.0 #####
-- ########################
--ATUALIZAR VERSÃO DA BASE DA DADOS
UPDATE parametro_geral SET str_valor = '0.5.0' WHERE str_chave = 'VERSAO_BASE_DADOS';

--CRIANDO TABELA RESPONSAVEL
CREATE SEQUENCE seq_responsavel
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_responsavel
  OWNER TO presente;

CREATE TABLE responsavel
(
  id_responsavel bigint NOT NULL,
  str_email character varying(255),
  str_nome character varying(255),
  CONSTRAINT responsavel_pkey PRIMARY KEY (id_responsavel )
);
ALTER TABLE responsavel
  OWNER TO presente;

--RELACIONANDO MATRICULA COM RESPONSAVEL
ALTER TABLE matricula ADD COLUMN bol_enviar_email_registro boolean;
ALTER TABLE matricula ADD id_responsavel bigint;
ALTER TABLE matricula ADD CONSTRAINT fk_responsavel FOREIGN KEY (id_responsavel)
      REFERENCES responsavel (id_responsavel) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;
UPDATE matricula SET bol_enviar_email_registro = FALSE WHERE bol_enviar_email_registro IS NULL;
UPDATE matricula SET bol_bolsista = FALSE WHERE bol_bolsista IS NULL;
UPDATE matricula SET bol_ativo = TRUE WHERE bol_ativo IS NULL;
ALTER TABLE matricula ALTER COLUMN bol_enviar_email_registro SET DEFAULT true;
ALTER TABLE matricula ALTER COLUMN bol_enviar_email_registro SET NOT NULL;
ALTER TABLE matricula ALTER COLUMN bol_bolsista SET NOT NULL;
ALTER TABLE matricula ALTER COLUMN bol_ativo SET NOT NULL;

--NOVOS PARAMETROS
INSERT INTO parametro_geral(str_chave, str_valor) VALUES ('MAIL_SMTP_SERVER','smtp.gmail.com');
INSERT INTO parametro_geral(str_chave, str_valor) VALUES ('MAIL_SENDER','email.oryontec@gmail.com');
INSERT INTO parametro_geral(str_chave, str_valor) VALUES ('MAIL_PASSWORD','Ory0N#2015');
INSERT INTO parametro_geral(str_chave, str_valor) VALUES ('MAIL_NAME_SENDER','Oryon Tecnologia - Presente');

--RETIRAR USUARIO COMUM
DELETE FROM usuario_perfil pf WHERE pf.str_login = 'comum';
DELETE FROM usuario u WHERE u.str_login = 'comum';

-- ########################
-- ##### VERSÃO 0.5.1 #####
-- ########################
--ATUALIZAR VERSÃO DA BASE DA DADOS
UPDATE parametro_geral SET str_valor = '0.5.1' WHERE str_chave = 'VERSAO_BASE_DADOS';

-- ########################
-- ##### VERSÃO 0.6.0 #####
-- ########################
--ATUALIZAR VERSÃO DA BASE DA DADOS
UPDATE parametro_geral SET str_valor = '0.6.0' WHERE str_chave = 'VERSAO_BASE_DADOS';

--NOVAS COLUNAS PARA O RESPONSÁVEL
ALTER TABLE responsavel ADD COLUMN str_telefone_celular character varying(255);
ALTER TABLE responsavel ADD COLUMN str_telefone_fixo character varying(255);

--NOVAS TABELAS
CREATE SEQUENCE seq_calendario_escolar
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_calendario_escolar
  OWNER TO presente;

CREATE SEQUENCE seq_configuracao_escola
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_configuracao_escola
  OWNER TO presente;

CREATE TABLE calendario_escolar
(
  id_calendario_escolar bigint NOT NULL,
  dat_data date NOT NULL,
  str_descricao character varying(255) NOT NULL,
  num_status bigint NOT NULL, -- Status '1' para incluir e '2' para excluir a data do calendario escolar
  bol_recorrente boolean NOT NULL DEFAULT false,
  bol_automatico boolean NOT NULL DEFAULT false, -- Se foi salvo automatico ou manual
  CONSTRAINT calendario_escolar_pkey PRIMARY KEY (id_calendario_escolar )
);
ALTER TABLE calendario_escolar
  OWNER TO presente;

CREATE TABLE configuracao_escola
(
  id_configuracao_escola bigint NOT NULL,
  str_codigo character varying(255),
  dat_fim_ano_letivo date NOT NULL,
  dat_inicio_ano_letivo date NOT NULL,
  str_nome character varying(255) NOT NULL,
  str_ua character varying(255),
  CONSTRAINT configuracao_escola_pkey PRIMARY KEY (id_configuracao_escola )
);
ALTER TABLE configuracao_escola
  OWNER TO presente;

-- ########################
-- ##### VERSÃO 0.7.0 #####
-- ########################
--ATUALIZAR VERSÃO DA BASE DA DADOS
UPDATE parametro_geral SET str_valor = '0.7.0' WHERE str_chave = 'VERSAO_BASE_DADOS';

CREATE TABLE relatorio
(
  id_relatorio bigint NOT NULL,
  str_nome character varying(255) NOT NULL,
  CONSTRAINT relatorio_pkey PRIMARY KEY (id_relatorio )
);
ALTER TABLE relatorio
  OWNER TO presente;

INSERT INTO relatorio(id_relatorio, str_nome) VALUES (1, 'BOLETIM DE FREQUÊNCIA');
INSERT INTO relatorio(id_relatorio, str_nome) VALUES (2, 'RELATÓRIO DE FALTAS');
INSERT INTO relatorio(id_relatorio, str_nome) VALUES (3, 'RELATÓRIO FREQUÊNCIA POR TURMA');

INSERT INTO parametro_geral(str_chave, str_valor) VALUES ('EMAIL_CONTATO_ADM', 'oryontec@gmail.com');
INSERT INTO parametro_geral(str_chave, str_valor) VALUES ('NOME_EMPRESA', 'Oryon Tecnologia');

-- ########################
-- ##### VERSÃO 0.7.1 #####
-- ########################
--ATUALIZAR VERSÃO DA BASE DA DADOS
UPDATE parametro_geral SET str_valor = '0.7.1' WHERE str_chave = 'VERSAO_BASE_DADOS';

-- ########################
-- ##### VERSÃO 0.7.2 #####
-- ########################
--ATUALIZAR VERSÃO DA BASE DA DADOS
UPDATE parametro_geral SET str_valor = '0.7.2' WHERE str_chave = 'VERSAO_BASE_DADOS';

-- ########################
-- ##### VERSÃO 0.8.0 #####
-- ########################
--ATUALIZAR VERSÃO DA BASE DA DADOS
UPDATE parametro_geral SET str_valor = '0.8.0' WHERE str_chave = 'VERSAO_BASE_DADOS';

CREATE TABLE historico_alteracao
(
  id_historico_alteracao bigint NOT NULL,
  bol_aluno_ativo boolean,
  dat_aluno_nascimento date,
  str_aluno_nome character varying(255),
  id_aluno bigint,
  id_matricula bigint,
  id_responsavel bigint,
  id_sala bigint,
  id_serie bigint,
  id_turma bigint,
  str_matricula character varying(255),
  int_matricula_ano_letivo integer,
  bol_matricula_ativo boolean,
  bol_matricula_bolsista boolean,
  dat_matricula_data_matricula timestamp without time zone,
  bol_matricula_enviar_email_registro boolean,
  str_matricula_turno character varying(255),
  str_responsavel_email character varying(255),
  str_responsavel_nome character varying(255),
  str_responsavel_telefone_celular character varying(255),
  str_responsavel_telefone_fixo character varying(255),
  bol_sala_ativo boolean,
  str_sala_descricao character varying(255),
  bol_serie_ativo boolean,
  str_serie_descricao character varying(255),
  str_tipo_alteracao character varying(255),
  bol_turma_ativo boolean,
  descricao character varying(255),
  dat_ultima_alteracao timestamp without time zone,
  CONSTRAINT historico_alteracao_pkey PRIMARY KEY (id_historico_alteracao)
);
ALTER TABLE historico_alteracao
  OWNER TO presente;

CREATE SEQUENCE seq_historico_alteracao
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_historico_alteracao
  OWNER TO presente;
  
--ALTERANDO TABELA DE REGISTRO
ALTER TABLE registro ADD COLUMN id_aluno bigint;
ALTER TABLE registro ADD COLUMN bol_aluno_ativo boolean;  
ALTER TABLE registro ADD COLUMN str_aluno_nome character varying(255);
ALTER TABLE registro ADD COLUMN id_sala bigint;
ALTER TABLE registro ADD COLUMN str_sala_descricao character varying(255);
ALTER TABLE registro ADD COLUMN bol_sala_ativo boolean;
ALTER TABLE registro ADD COLUMN id_serie bigint;
ALTER TABLE registro ADD COLUMN str_serie_descricao character varying(255);
ALTER TABLE registro ADD COLUMN bol_serie_ativo boolean;
ALTER TABLE registro ADD COLUMN id_turma bigint;
ALTER TABLE registro ADD COLUMN str_turma_descricao character varying(255);
ALTER TABLE registro ADD COLUMN bol_turma_ativo boolean;
ALTER TABLE registro ADD COLUMN str_matricula character varying(255);
ALTER TABLE registro ADD COLUMN int_matricula_ano_letivo integer;
ALTER TABLE registro ADD COLUMN bol_matricula_ativo boolean;
ALTER TABLE registro ADD COLUMN dat_matricula_data_matricula timestamp without time zone;
ALTER TABLE registro ADD COLUMN bol_matricula_enviar_email_registro boolean;
ALTER TABLE registro ADD COLUMN str_matricula_turno character varying(255);

--CORRIGINDO COLUNA DA TABELA TURMA
ALTER TABLE turma ADD COLUMN str_descricao character varying(255);
ALTER TABLE turma DROP COLUMN descricao;

--ALTERANDO TABELA DE OCORRENCIAS
ALTER TABLE ocorrencia_importacao ADD COLUMN str_responsavel character varying(255);
ALTER TABLE ocorrencia_importacao ADD COLUMN str_responsavel_email character varying(255);
ALTER TABLE ocorrencia_importacao ADD COLUMN str_responsavel_celular character varying(255);
ALTER TABLE ocorrencia_importacao ADD COLUMN str_responsavel_telefone character varying(255);
ALTER TABLE ocorrencia_importacao ADD COLUMN str_sala character varying(255);
ALTER TABLE ocorrencia_importacao ADD COLUMN dat_nascimento timestamp without time zone;
ALTER TABLE ocorrencia_importacao ADD COLUMN bol_bolsista boolean;
ALTER TABLE ocorrencia_importacao ADD COLUMN int_ano_letivo bigint;

-- ########################
-- ##### VERSÃO 0.8.1 #####
-- ########################
--ATUALIZAR VERSÃO DA BASE DA DADOS
UPDATE parametro_geral SET str_valor = '0.8.1' WHERE str_chave = 'VERSAO_BASE_DADOS';

-- ########################
-- ##### VERSÃO 0.9.0 #####
-- ########################
--ATUALIZAR VERSÃO DA BASE DA DADOS
UPDATE parametro_geral SET str_valor = '0.9.0' WHERE str_chave = 'VERSAO_BASE_DADOS';

ALTER TABLE historico_alteracao ADD COLUMN str_entidade_origem character varying(255);

-- ########################
-- ##### VERSÃO 0.9.1 #####
-- ########################
--ATUALIZAR VERSÃO DA BASE DA DADOS
UPDATE parametro_geral SET str_valor = '0.9.1' WHERE str_chave = 'VERSAO_BASE_DADOS';

-- ########################
-- ##### VERSÃO 0.9.2 #####
-- ########################
--ATUALIZAR VERSÃO DA BASE DA DADOS
UPDATE parametro_geral SET str_valor = '0.9.2' WHERE str_chave = 'VERSAO_BASE_DADOS';

-- ########################
-- ##### VERSÃO 1.0.0 #####
-- ########################
--ATUALIZAR VERSÃO DA BASE DA DADOS
UPDATE parametro_geral SET str_valor = '1.0.0' WHERE str_chave = 'VERSAO_BASE_DADOS';

-- ########################
-- ##### VERSÃO 1.0.1 #####
-- ########################
--ATUALIZAR VERSÃO DA BASE DA DADOS
UPDATE parametro_geral SET str_valor = '1.0.1' WHERE str_chave = 'VERSAO_BASE_DADOS';

-- ########################
-- ##### VERSÃO 1.0.2 #####
-- ########################
--ATUALIZAR VERSÃO DA BASE DA DADOS
UPDATE parametro_geral SET str_valor = '1.0.2' WHERE str_chave = 'VERSAO_BASE_DADOS';

ALTER TABLE registro ADD COLUMN str_email character varying(255);
ALTER TABLE registro ADD COLUMN str_telefone_celular character varying(255);

ALTER TABLE registro ADD COLUMN bol_matricula_enviar_sms_registro boolean;
UPDATE registro SET bol_matricula_enviar_sms_registro=FALSE;

ALTER TABLE registro ADD COLUMN bol_email_registro_enviado boolean;
UPDATE registro SET bol_email_registro_enviado=FALSE;

ALTER TABLE registro ADD COLUMN bol_sms_registro_enviado boolean;
UPDATE registro SET bol_sms_registro_enviado=FALSE;

ALTER TABLE matricula ADD COLUMN bol_enviar_sms_registro boolean;
UPDATE matricula SET bol_enviar_sms_registro=FALSE;
ALTER TABLE matricula ALTER COLUMN bol_enviar_sms_registro SET NOT NULL;
ALTER TABLE matricula ALTER COLUMN bol_enviar_sms_registro SET DEFAULT true;

-- ########################
-- ##### VERSÃO 1.0.3 #####
-- ########################
--ATUALIZAR VERSÃO DA BASE DA DADOS
UPDATE parametro_geral SET str_valor = '1.0.3' WHERE str_chave = 'VERSAO_BASE_DADOS';

-- ########################
-- ##### VERSÃO 1.0.4 #####
-- ########################
--ATUALIZAR VERSÃO DA BASE DA DADOS
UPDATE parametro_geral SET str_valor = '1.0.4' WHERE str_chave = 'VERSAO_BASE_DADOS';
UPDATE parametro_geral SET str_valor = 'Oryon Tecnologia - Presente!' WHERE str_chave = 'MAIL_NAME_SENDER';

-- ########################
-- ##### VERSÃO 1.0.5 #####
-- ########################
--ATUALIZAR VERSÃO DA BASE DA DADOS
UPDATE parametro_geral SET str_valor = '1.0.5' WHERE str_chave = 'VERSAO_BASE_DADOS';

ALTER TABLE aluno ADD COLUMN identificador bigint NOT NULL DEFAULT 1234;
ALTER TABLE calendario_escolar ADD COLUMN identificador bigint NOT NULL DEFAULT 1234;
ALTER TABLE configuracao_escola ADD COLUMN identificador bigint NOT NULL DEFAULT 1234;
ALTER TABLE historico_alteracao ADD COLUMN identificador bigint NOT NULL DEFAULT 1234;
ALTER TABLE matricula ADD COLUMN identificador bigint NOT NULL DEFAULT 1234;
ALTER TABLE ocorrencia_importacao ADD COLUMN identificador bigint NOT NULL DEFAULT 1234;
ALTER TABLE parametro_geral ADD COLUMN identificador bigint NOT NULL DEFAULT 1234;
ALTER TABLE perfil ADD COLUMN identificador bigint NOT NULL DEFAULT 1234;
ALTER TABLE registro ADD COLUMN identificador bigint NOT NULL DEFAULT 1234;
ALTER TABLE relatorio ADD COLUMN identificador bigint NOT NULL DEFAULT 1234;
ALTER TABLE responsavel ADD COLUMN identificador bigint NOT NULL DEFAULT 1234;
ALTER TABLE sala ADD COLUMN identificador bigint NOT NULL DEFAULT 1234;
ALTER TABLE serie ADD COLUMN identificador bigint NOT NULL DEFAULT 1234;
ALTER TABLE turma ADD COLUMN identificador bigint NOT NULL DEFAULT 1234;
ALTER TABLE usuario ADD COLUMN identificador bigint NOT NULL DEFAULT 1234;
ALTER TABLE usuario_perfil ADD COLUMN identificador bigint NOT NULL DEFAULT 1234;

-- ########################
-- ##### VERSÃO 1.0.6 #####
-- ########################
--ATUALIZAR VERSÃO DA BASE DA DADOS
UPDATE parametro_geral SET str_valor = '1.0.6' WHERE str_chave = 'VERSAO_BASE_DADOS';

-- ########################
-- ##### VERSÃO 1.0.7 #####
-- ########################
--ATUALIZAR VERSÃO DA BASE DA DADOS
UPDATE parametro_geral SET str_valor = '1.0.7' WHERE str_chave = 'VERSAO_BASE_DADOS';

-- ########################
-- ##### VERSÃO 1.1 #####
-- ########################
--ATUALIZAR VERSÃO DA BASE DA DADOS
UPDATE parametro_geral SET str_valor = '1.1' WHERE str_chave = 'VERSAO_BASE_DADOS';
ALTER TABLE aluno ADD COLUMN foto bytea;

-- ########################
-- ##### VERSÃO 1.2 #####
-- ########################
--ATUALIZAR VERSÃO DA BASE DA DADOS
--criacao de tabelas
CREATE TABLE tipo_ocorrencia
(
  id_tipo_ocorrencia bigint NOT NULL,
  identificador bigint NOT NULL,
  bol_ativo boolean DEFAULT true,
  str_descricao character varying(255),
  CONSTRAINT tipo_ocorrencia_pkey PRIMARY KEY (id_tipo_ocorrencia)
);

ALTER TABLE tipo_ocorrencia OWNER TO presente;

CREATE TABLE ocorrencia
(
  id_ocorrencia bigint NOT NULL,
  identificador bigint NOT NULL,
  bol_ativo boolean DEFAULT true,
  tmp_data timestamp without time zone,
  tmp_geracao timestamp without time zone,
  str_descricao character varying(255),
  num_dias integer,
  id_matricula bigint NOT NULL,
  id_tipo_ocorrencia bigint NOT NULL,
  str_login character varying(255) NOT NULL,
  CONSTRAINT ocorrencia_pkey PRIMARY KEY (id_ocorrencia),
  CONSTRAINT fka3c30fb742b5d87e FOREIGN KEY (id_matricula)
      REFERENCES matricula (id_matricula) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fka3c30fb75e98eb3d FOREIGN KEY (id_tipo_ocorrencia)
      REFERENCES tipo_ocorrencia (id_tipo_ocorrencia) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fka3c30fb7a82b6d5 FOREIGN KEY (str_login)
      REFERENCES usuario (str_login) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

ALTER TABLE ocorrencia OWNER TO presente;

--atualizar tabelas
ALTER TABLE configuracao_escola ADD COLUMN dat_inicio_segundo_semestre date;
--Data servirá para atualizar para não enviar SMS para todos os responsáveis enquanto não pagar a segunda parcela
UPDATE configuracao_escola SET dat_inicio_segundo_semestre = '2015-08-01';
ALTER TABLE configuracao_escola ALTER COLUMN dat_inicio_segundo_semestre SET DEFAULT '2015-08-01';
ALTER TABLE configuracao_escola ALTER COLUMN dat_inicio_segundo_semestre SET NOT NULL;

UPDATE parametro_geral SET str_valor = '1.2' WHERE str_chave = 'VERSAO_BASE_DADOS';

-- ########################
-- ##### VERSÃO 1.2.1 #####
-- ########################
--ATUALIZAR VERSÃO DA BASE DA DADOS
UPDATE parametro_geral SET str_valor = '1.2.1' WHERE str_chave = 'VERSAO_BASE_DADOS';