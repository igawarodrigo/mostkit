DROP TABLE IF EXISTS contatos CASCADE;
CREATE TABLE contatos (
  id         SERIAL,
  nome       varchar not null,
  telefone_comercial      varchar,
  telefone_residencial    varchar,
  telefone_celular	  varchar,
  email_comercial        varchar,
  email_residencial      varchar,
  data_nascimento 	 date not null,
  favorito              boolean default false,

  PRIMARY KEY (id),

  CHECK (telefone_comercial is not null or telefone_residencial is not null or telefone_celular is not null),
  CHECK (email_comercial is not null or email_residencial is not null)
);

INSERT INTO public.contatos (
  nome, data_nascimento, email_comercial, telefone_comercial)
VALUES ('Rodrigo Augusto Igawa', '1991-07-31', 'igawa.rodrigo@gmail.com', '3323 1077');

INSERT INTO public.contatos (
  nome, data_nascimento, email_comercial, telefone_comercial)
VALUES ('Karina Mayumi Ono', '1993-03-14', 'karina.ono@gmail.com', '9905 9844');