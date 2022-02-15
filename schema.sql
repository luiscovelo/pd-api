create table squad(
	id int primary key auto_increment,
	name varchar(255) not null
);

create table employee(
	id int primary key auto_increment,
	name varchar(255) not null,
	estimated_hours int not null,
	squad_id int not null,
	FOREIGN KEY (squad_id) REFERENCES squad(id)
);

create table report(
	id int primary key auto_increment,
	created_at date not null,
	description text not null,
	spent_hours int not null,
	employee_id int not null,
	FOREIGN KEY (employee_id) REFERENCES employee(id)
);