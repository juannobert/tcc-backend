CREATE SEQUENCE employer_seq;
CREATE TABLE IF NOT EXISTS employer
(
    id bigint NOT NULL DEFAULT NEXTVAL ('employer_seq'),
    email character varying(255) NOT NULL,
    nome character varying(80)  NOT NULL,
    senha character varying(80) NOT NULL,
    codigo character varying(6) NOT NULL,
    cpf_cnpj character varying(14) NOT NULL,
    endereco character varying(255) NOT NULL,
    CONSTRAINT employer_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE employee_seq;
CREATE TABLE IF NOT EXISTS employee
(
    id bigint NOT NULL DEFAULT NEXTVAL ('employee_seq'),
    email character varying(255)  NOT NULL,
    nome character varying(80) NOT NULL,
    senha character varying(80)  NOT NULL,
    carga_horaria_mensal integer,
    cpf character varying(14)  NOT NULL,
    data_nascimento timestamp(6) without time zone NOT NULL,
    telefone character varying(15) NOT NULL,
    empregador_id bigint NOT NULL,
    CONSTRAINT employee_pkey PRIMARY KEY (id),
    CONSTRAINT fkfli9j6hvm6lt6gqmbbjlpsn85 FOREIGN KEY (empregador_id)
        REFERENCES public.employer (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE SEQUENCE record_seq;
CREATE TABLE IF NOT EXISTS record
(
    id bigint NOT NULL DEFAULT NEXTVAL ('record_seq'),
    hora_entrada timestamp(6) without time zone,
    hora_saida timestamp(6) without time zone,
    localizacao character varying(255),
    employee_id bigint,
    CONSTRAINT record_pkey PRIMARY KEY (id),
    CONSTRAINT fkq9n0beip5me2rv1qk3kck76oh FOREIGN KEY (employee_id)
        REFERENCES public.employee (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
