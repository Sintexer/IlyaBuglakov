insert into characteristics(
	id,
	memory,
	reaction,
	logic,
	calculations
) values (
	1,0,0,0,0
);

insert into users (
	id,
	email,
	name,
	surname,
	password,
	role,
	status,
	registration_date,
	characteristic_id
) values (
	1,
	'admin@gmail.com',
	'admin',
	'admin',
	'$2y$12$cOWQZuEvcWi5d3.LasbUluPxCq4FwuAWQM6SYyhNfwIB4kQfD.OCy',
	'admin',
	'active',
	'2020-02-02',
	1
);