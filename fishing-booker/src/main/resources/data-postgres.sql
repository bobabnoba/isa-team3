delete from vacation_home_reservations;
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
--
--Address

insert into address(
id, city, country, street, zip_code)
values (40, 'Novi Sad', 'Serbia', 'Sime Milosevica', 2100);
insert into address(
id, city, country, street, zip_code)
values (41, 'Beograd', 'Serbia', 'Knez Mihajlova', 101801);
insert into address(
id, city, country, street, zip_code)
values (42, 'Bijeljina', 'Bosnia&Herzegovina', 'Sime milosevica', 76300);
insert into address(
id, city, country, street, zip_code)
values (43, 'Ljubljana', 'Slovenia', 'Jana Kustosa', 1000);

--Client
insert into client(id, biography, email, first_name, is_activated, is_blocked, is_deleted, last_name, last_password_reset_date, password, phone, test_rebase_again, address_id, role_id, points, type)
values (10,'Whatever Bio',  'ClientEmail', 'Edgar Alan', 'true' , 'false', 'false', 'Poe',null, /*password : WhateverPassword*/ '$2a$09$pKCALxdgccQZyZp0BwFBq.Fvl82rMQYehkxB5J7BGNwru7UVBpzo.', '043-4234-423', 'false',41 , 2 , 0, 'SILVER');

insert into client(id, biography, email, first_name, is_activated, is_blocked, is_deleted, last_name, last_password_reset_date, password, phone, test_rebase_again, address_id, role_id, points, type)
values (12,'Whatever Bio',  'ClientEmail', 'Edgar Alan', 'true' , 'false', 'false', 'Poe',null, /*password : WhateverPassword*/ '$2a$09$pKCALxdgccQZyZp0BwFBq.Fvl82rMQYehkxB5J7BGNwru7UVBpzo.', '043-4234-423', 'false',41 , 2 , 0, 'SILVER');

----Admin
insert into admin(id, biography, email, first_name, is_activated, is_blocked, is_deleted, last_name, last_password_reset_date, password, phone, test_rebase_again, address_id, role_id)
values (11,'Whatever Bio',  'AdminEmail', 'Edgar Alan', 'true' , 'false', 'false', 'Poe',null, /*password : WhateverPassword*/ '$2a$09$pKCALxdgccQZyZp0BwFBq.Fvl82rMQYehkxB5J7BGNwru7UVBpzo.', '043-4234-423', 'false',41 , 1 );

----Home Owner
insert into home_owner(id, biography, email, first_name, is_activated, is_blocked, is_deleted, last_name, last_password_reset_date, password,
 phone, test_rebase_again, address_id, role_id)
	values (110,'Whatever Bio', 'VacationOwnerEmail', 'VacationOwnerFirstName'
	, 'true' , 'false', 'false', 'VacationOwnerLastName', null , /*password : WhateverPassword*/ '$2a$09$pKCALxdgccQZyZp0BwFBq.Fvl82rMQYehkxB5J7BGNwru7UVBpzo.',
	 '0943242342','false',41, 4);

--Boat Owner
insert into boat_owner(

	id, biography, email, first_name, is_activated, is_blocked, is_deleted, last_name, last_password_reset_date, password,
	 phone, test_rebase_again, address_id, role_id, type)

	values (110,'Whatever Bio', 'BoatOwnerEmail', 'BoatOwnerFirstName'
	, 'true' , 'false', 'false', 'BoatOwnerLastName', null , /*password : WhateverPassword*/ '$2a$09$pKCALxdgccQZyZp0BwFBq.Fvl82rMQYehkxB5J7BGNwru7UVBpzo.',
	 '0943242342','false', 41, 3,'BOAT_OWNER');

-- Instructor
insert into instructor(	id, biography, email, first_name, is_activated, is_blocked, is_deleted, last_name, last_password_reset_date, password, phone, test_rebase_again, address_id, role_id, rating)
	values (110, 'biography', 'InstructorEmail', 'first_name', 'true', 'false', 'false', 'last_name', null, /*password : WhateverPassword*/ '$2a$09$pKCALxdgccQZyZp0BwFBq.Fvl82rMQYehkxB5J7BGNwru7UVBpzo.', '4324234', 'false', 41, 5, 4.3);


--Vacation Home
insert into vacation_home(
	id, deleted, description, guest_limit, name, price_per_day, rating, address_id, home_owner_id)
	values (100, 'false','Located in the heart of Page, walking distance from restaurants, shops, a grocery store, the movie theater, hiking trails, Main Street, and Page City Park, and only a few miles from scenic highlights like Horseshoe Bend, Antelope Canyon, and Lake Powell',
	 5, ' 55 Horseshoe Bend, Antelope Canyon, and Lake Powell', 50,
	 4.5, 40 , 110);

insert into vacation_home(
	id, deleted, description, guest_limit, name, price_per_day,  rating, address_id, home_owner_id)
	values (101, 'false','Located in the heart of Page, walking distance from restaurants, shops, a grocery store, the movie theater, hiking trails, Main Street, and Page City Park, and only a few miles from scenic highlights like Horseshoe Bend, Antelope Canyon, and Lake Powell',
	 3, ' Whatever you say',44,
	 3, 41 , 110);

insert into vacation_home(
	id, deleted, description, guest_limit, name, price_per_day,  rating, address_id, home_owner_id)
	values (102, 'false','Located in the heart of Page, walking distance from restaurants, shops, a grocery store, the movie theater, hiking trails, Main Street, and Page City Park, and only a few miles from scenic highlights like Horseshoe Bend, Antelope Canyon, and Lake Powell',
	 5, ' Yo soy tu madre', 60,
	 1.5, 42 , 110);

insert into vacation_home(
	id, deleted, description, guest_limit, name, price_per_day,  rating, address_id, home_owner_id)
	values (103, 'false','Located in the heart of Page, walking distance from restaurants, shops, a grocery store, the movie theater, hiking trails, Main Street, and Page City Park, and only a few miles from scenic highlights like Horseshoe Bend, Antelope Canyon, and Lake Powell',
	 5, ' Home 103 ', 60,
	 1.5, 42 , 110);



--Boat
insert into boat(
	id, capacity, deleted, description, engine_count, engine_power, information, length, max_speed, name, price_per_day,  rating, type, address_id, boat_owner_id)
	values (100, 4, 'false' ,'DESCRIIIIIIIIIIIIPTION', 2, 30, 'iNFORMATION', 20, 40, 'Titanic',35, 3.2, 'SMOL' , 43, 110);


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
	id, discount, end_date, is_cancelled, max_guests, price, start_date, type, client_id)
	values (100, 0.0,  '2020-08-17', 'false', 5, 200, '2020-08-11', 'VACATION_HOME', 10); -- perfect
insert into reservation(
	id, discount, end_date, is_cancelled, max_guests, price, start_date, type, client_id)
	values (101, 0.0,  '2020-08-17', 'false', 3, 200, '2020-08-11', 'VACATION_HOME', 10); -- quest limit
insert into reservation(
	id, discount, end_date, is_cancelled, max_guests, price, start_date, type, client_id)
	values (102, 0.0,  '2020-08-07', 'false', 5, 200, '2020-08-01', 'VACATION_HOME', 12); --overlapping reservation

-- Vacation Home Availability
insert into vacation_home_availability(
	id, end_date, start_date, home_id)
	values (100,  '2020-08-30', '2020-08-01', 100); --  available
insert into vacation_home_availability(
	id, end_date, start_date, home_id)
	values (101,  '2020-08-30', '2020-08-01', 101); --  available
insert into vacation_home_availability(
	id, end_date, start_date, home_id)
	values (102,  '2020-08-30', '2020-08-01', 102); --  available
insert into vacation_home_availability(
	id, end_date, start_date, home_id)
	values (103, '2020-08-21', '2020-08-11', 103); -- not available

-- Vacation Home Reservations
insert into vacation_home_reservations(
	vacation_home_id, reservations_id)
	values (100, 100);
--
insert into vacation_home_reservations(
	vacation_home_id, reservations_id)
	values (101, 101);

insert into vacation_home_reservations(
	vacation_home_id, reservations_id)
	values (102, 102);


-- Adventure
INSERT INTO adventure(
	id, canceling_percentage, description, max_number_of_participants, price_per_day, rating, title, is_deleted, address_id, instructor_id)
	VALUES (111, 20, 'Description', 3, 45, 4, 'title', false, 40, 110);

-- equipment
INSERT INTO fishing_equipment(id, name)
	VALUES (99, 'Fishing Reels'),
	       (100, 'Lures'),
	       (101, 'Fishing Rods'),
	       (102, 'Rod Holders'),
	       (103, 'Rod Racks'),
	       (104, 'Fishing Nets'),
	       (105, 'Tackle Boxes'),
	       (106, 'Rain Gear');

-- utilities
INSERT INTO utility(name, price)
	VALUES ('You keep catch', 40),
			('Child friendly', 0),
			('Catch cleaning & filleting', 20),
			('Snorkeling Equipment', 5),
			('Fighting Chair', 10),
			('Drinks', 10);

-- code of conduct
INSERT INTO rule(id, name)
    VALUES (99, 'No smoking'),
            (100, 'No pets'),
            (101, 'No alcohol'),
            (102, 'No littering'),
            (103, '18+'),
            (104, 'WiFi included'),
            (105, 'No kids');

           insert into adventure_code_of_conduct(adventure_id, code_of_conduct_id)
            values (111, 100);