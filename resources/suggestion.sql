SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

CREATE TABLE users(
	id uuid NOT NULL,
	email character varying(255) NOT NULL,
	password character varying(255) NOT NULL,
	name character varying(255) NOT NULL
);
ALTER TABLE users OWNER TO postgres;
ALTER TABLE ONLY users
	ADD CONSTRAINT users_pkey PRIMARY KEY (id);
ALTER TABLE ONLY users
    ADD CONSTRAINT users_email_key UNIQUE (email);


CREATE TABLE suggestions(
	id uuid NOT NULL,
	message character varying(500),
	recepient_id uuid NOT NULL,
	is_seen boolean,
	is_starred boolean,
	label character varying(255),
	created_at timestamp with time zone NOT NULL
);
ALTER TABLE suggestions OWNER TO postgres;
ALTER TABLE ONLY suggestions
	ADD CONSTRAINT suggestions_pkey PRIMARY KEY(id);
ALTER TABLE ONLY suggestions
	ADD CONSTRAINT suggestions_recepient_id_fkey FOREIGN KEY (recepient_id) REFERENCES users(id);


CREATE TABLE decryption_keys(
	id uuid NOT NULL,
	user_id uuid NOT NULL,
	key text NOT NULL
);
ALTER TABLE decryption_keys OWNER TO postgres;
ALTER TABLE ONLY decryption_keys
	ADD CONSTRAINT decryption_keys_pkey PRIMARY KEY(id);
ALTER TABLE ONLY decryption_keys
	ADD CONSTRAINT decryption_keys_user_id_fkey FOREIGN KEY (user_id) REFERENCES users(id);


CREATE TABLE encryption_keys(
	id uuid NOT NULL,
	user_id uuid NOT NULL,
	key text NOT NULL
);
ALTER TABLE encryption_keys OWNER TO postgres;
ALTER TABLE ONLY encryption_keys
	ADD CONSTRAINT encryption_keys_pkey PRIMARY KEY(id);
ALTER TABLE ONLY encryption_keys
	ADD CONSTRAINT encryption_keys_user_id_fkey FOREIGN KEY (user_id) REFERENCES users(id);


CREATE TABLE sessions(
	id uuid NOT NULL,
	user_id uuid NOT NULL,
	encrypted_password text NOT NULL,
	expires_at time with time zone NOT NULL
);
ALTER TABLE sessions OWNER TO postgres;
ALTER TABLE ONLY sessions
	ADD CONSTRAINT sessions_pkey PRIMARY KEY(id);
ALTER TABLE ONLY sessions
	ADD CONSTRAINT sessions_user_id_fkey FOREIGN KEY (user_id) REFERENCES users(id);

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;