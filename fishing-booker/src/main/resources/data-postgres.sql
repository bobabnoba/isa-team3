delete from vacation_home_available_reservations;
delete from reservation;
delete from vacation_home_code_of_conduct;
delete from boat;
delete from room;
delete from vacation_home;
delete from boat_owner;
delete from home_owner;
delete from client;
delete from address;
delete from role;

--Role
insert into role (id, name) values (1, 'ROLE_ADMIN');
insert into role (id, name) values (2, 'ROLE_CLIENT');
insert into role (id, name) values (3, 'ROLE_BOAT_OWNER');
insert into role (id, name) values (4, 'ROLE_HOME_OWNER');
insert into role (id, name) values (5, 'ROLE_INSTRUCTOR');

--Address
insert into address(
id, city, country, street, zip_code)
values (40, 'Novi Sad', 'Serbia', 'Sime milosevica', 2100);


--Client
insert into client(id, address, biography, city, country, email, first_name, is_activated, is_blocked, is_deleted, last_name, last_password_reset_date, password, phone, test_rebase_again, role_id, points, type)
values (10, 'WhateverAddress','Whatever Bio', 'WhateverCity', 'WhateverCountry', 'WhateverEmail', 'Whatever', 'true' , 'false', 'false', 'Whatever',null, /*password : WhateverPassword*/ '$2a$09$pKCALxdgccQZyZp0BwFBq.Fvl82rMQYehkxB5J7BGNwru7UVBpzo.', '043-4234-423', 'false' , 1 , 0, 'SILVER');

----Admin
--insert into admin(id, address, city, country, email, first_name, is_activated, is_blocked, last_name, password, phone, test_rebase_again, role_id)
--
----Home Owner
insert into home_owner(
	id, address, biography, city, country, email, first_name, is_activated, is_blocked, is_deleted, last_name, last_password_reset_date, password, phone, test_rebase_again, role_id)
	values (110, 'VacationOwnerAddress','Whatever Bio', 'VacationOwnerCity', 'VacationOwnerCountry', 'VacationOwnerEmail', 'VacationOwnerFirstName'
	, 'true' , 'false', 'false', 'VacationOwnerLastName', null , /*password : WhateverPassword*/ '$2a$09$pKCALxdgccQZyZp0BwFBq.Fvl82rMQYehkxB5J7BGNwru7UVBpzo.',
	 '0943242342','false', 4);


--Boat Owner
insert into boat_owner(
	id, address, biography, city, country, email, first_name, is_activated, is_blocked, is_deleted, last_name, last_password_reset_date, password, phone, test_rebase_again, role_id, type)
	values (110, 'BoatOwnerAddress','Whatever Bio', 'BoatOwnerCity', 'BoatOwnerCountry', 'BoatOwnerEmail', 'BoatOwnerFirstName'
	, 'true' , 'false', 'false', 'BoatOwnerLastName', null , /*password : WhateverPassword*/ '$2a$09$pKCALxdgccQZyZp0BwFBq.Fvl82rMQYehkxB5J7BGNwru7UVBpzo.',
	 '0943242342','false', 3,'BOAT_OWNER');

--instructor
insert into instructor(
	id, address, biography, city, country, email, first_name, is_activated, is_blocked, is_deleted, last_name, last_password_reset_date, password, phone, test_rebase_again, role_id)
	values (110, 'InstructorAddress','Whatever Bio', 'InstructorCity', 'InstructorCountry', 'InstructorEmail', 'InstructorFirstName'
	, 'true' , 'false', 'false', 'InstructorLastName', null , /*password : WhateverPassword*/ '$2a$09$pKCALxdgccQZyZp0BwFBq.Fvl82rMQYehkxB5J7BGNwru7UVBpzo.',
	 '0943242342','false', 5);

--Vacation Home
insert into vacation_home(
	id, deleted, description, guest_limit, name, rating, address_id, home_owner_id)
	values (100, 'false','Located in the heart of Page, walking distance from restaurants, shops, a grocery store, the movie theater, hiking trails, Main Street, and Page City Park, and only a few miles from scenic highlights like Horseshoe Bend, Antelope Canyon, and Lake Powell',
	 5, ' 55 Horseshoe Bend, Antelope Canyon, and Lake Powell',
	 4.5, 40 , 110);



--Boat
insert into boat(
	id, capacity, deleted, description, engine_count, engine_power, information, length, max_speed, name, rating, type, address_id, boat_owner_id)
	values (100, 4, 'false' ,'DESCRIIIIIIIIIIIIPTION', 2, 30, 'iNFORMATION', 20, 40, 'Titanic',3.5, 'SMOL' , null, 110);


--Rooms
insert into room(
	id, bed_number, home_id)
	values (100, 4, 100);


--Code of Conduct
insert into vacation_home_code_of_conduct(
	vacation_home_id, code_of_conduct)
	values (100, 'No Smoking'),
	 (100, 'No Pets'),
	 (100, 'No Party');


---- Reservations
insert into reservation(
	id, days, destination, discount, duration, end_date, is_cancelled, is_reserved, max_guests, price, start_date, type, client_id)
	values (99, 6, 'destination', 0.0, null, '2020-01-01', 'false', 'false', 6, 200, '2020-01-21', 'VACATION_HOME', 10);


---- Vacation Home Reservations
insert into vacation_home_available_reservations(
	vacation_home_id, available_reservations_id)
	values (100, 99);