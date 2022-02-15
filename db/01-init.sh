#!/bin/bash
sed -i -e 's/\r$//' 01-init.sh
set -e
export PGPASSWORD=postgres;
psql -v ON_ERROR_STOP=1 --username "postgres" --dbname "pdhours" <<-EOSQL
  CREATE DATABASE pdhours;
  \connect pdhours;
  BEGIN;
  create table squad(
		id serial primary key,
		name character varying(255) not null
	);
	create table employee(
		id serial primary key,
		name character varying(255) not null,
		estimated_hours integer not null,
		squad_id integer not null,
		FOREIGN KEY (squad_id) REFERENCES squad(id)
	);
	create table report(
		id serial primary key,
		created_at date not null,
		description text not null,
		spent_hours integer not null,
		employee_id integer not null,
		FOREIGN KEY (employee_id) REFERENCES employee(id)
	);
  COMMIT;
EOSQL