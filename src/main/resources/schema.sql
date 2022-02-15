create table if not exists squad(
	id serial primary key,
	name character varying(255) not null
);

create table if not exists employee(
	id serial primary key,
	name character varying(255) not null,
	estimated_hours integer not null,
	squad_id integer not null,
	FOREIGN KEY (squad_id) REFERENCES squad(id)
);

create table if not exists report(
	id serial primary key,
	created_at date not null,
	description text not null,
	spent_hours integer not null,
	employee_id integer not null,
	FOREIGN KEY (employee_id) REFERENCES employee(id)
);