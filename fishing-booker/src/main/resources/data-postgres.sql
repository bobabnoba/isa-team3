delete from vacation_home_reservations;
delete from reservation;
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
id, city, country, street, zip_code) values
(40, 'Novi Sad', 'Serbia', 'Sime Milosevica', 2100),
(41, 'Beograd', 'Serbia', 'Knez Mihajlova', 101801),
(42, 'Bijeljina', 'Bosnia&Herzegovina', 'Sime milosevica', 76300),
(43, 'Novi Sad', 'Srbija', 'Kisacka 1', 1000);

-- rank
insert into user_rank(id, name, min_points, reservation_percentage, percentage)
    values (1, 'REGULAR_CLIENT', 0, 80, 0),
            (2, 'SILVER_CLIENT', 4000, 80, 14),
            (3, 'GOLD_CLIENT', 8000, 80, 20),
            (4, 'REGULAR_ADVERTISER', 0, 90, 92),
            (5, 'SILVER_ADVERTISER', 4500, 90, 94),
            (6, 'GOLD_ADVERTISER', 8000, 90, 96);

--Client
insert into client(id, biography, email, first_name, is_activated, is_blocked, deleted, last_name, last_password_reset_date, password, phone, test_rebase_again, address_id, role_id, points, rank_id,  no_of_penalties) values
(10,'Whatever Bio',  'majablgic505@gmail.com', 'Edgar Alan', 'true' , 'false', 'false', 'Poe',null, /*password : WhateverPassword*/ '$2a$09$pKCALxdgccQZyZp0BwFBq.Fvl82rMQYehkxB5J7BGNwru7UVBpzo.', '043-4234-423', 'false',41 , 2 , 4002, 2, 0),
(12,'Whatever Bio',  'ClientEmail2', 'Marlena', 'true' , 'false', 'false', 'Voltori',null, /*password : WhateverPassword*/ '$2a$09$pKCALxdgccQZyZp0BwFBq.Fvl82rMQYehkxB5J7BGNwru7UVBpzo.', '043-4234-423', 'false',41 , 2 , 0, 1, 0);

----Admin
insert into admin(id, biography, email, first_name, is_activated, is_blocked, deleted, last_name, last_password_reset_date, password, phone, test_rebase_again, address_id, role_id, points, rank_id, first_login, head_admin)
values (11,'No bio',  'AdminEmail', 'Adminko', 'true' , 'false', 'false', 'kovic',null, /*password : WhateverPassword*/ '$2a$09$pKCALxdgccQZyZp0BwFBq.Fvl82rMQYehkxB5J7BGNwru7UVBpzo.', '043-4234-423', 'false',41 , 1, 0, 4, 'false', 'true');

----Home Owner
insert into home_owner(id, biography, email, first_name, is_activated, is_blocked, deleted, last_name, last_password_reset_date, password,
 phone, test_rebase_again, address_id, role_id, points, rank_id)
	values (111,'Whatever Bio', 'VacationOwnerEmail', 'VacationOwnerFirstName'
	, 'true' , 'false', 'false', 'VacationOwnerLastName', null , /*password : WhateverPassword*/ '$2a$09$pKCALxdgccQZyZp0BwFBq.Fvl82rMQYehkxB5J7BGNwru7UVBpzo.',
	 '0943242342','false',41, 4, 0, 4);

--Boat Owner
insert into boat_owner(
	id, biography, email, first_name, is_activated, is_blocked, deleted, last_name, last_password_reset_date, password,
	 phone, test_rebase_again, address_id, role_id, type, points, rank_id)

	values (109,'Whatever Bio', 'BoatOwnerEmail', 'BoatOwnerFirstName'
	, 'true' , 'false', 'false', 'BoatOwnerLastName', null , /*password : WhateverPassword*/ '$2a$09$pKCALxdgccQZyZp0BwFBq.Fvl82rMQYehkxB5J7BGNwru7UVBpzo.',
	 '0943242342','false', 42, 3,'BOAT_OWNER', 0, 4);

-- Instructor
insert into instructor(	id, biography, email, first_name, is_activated, is_blocked, deleted, last_name, last_password_reset_date, password, phone, test_rebase_again, address_id, role_id, rating, points, rank_id)
	values (110, 'biography', 'bobaruljic@gmail.com', 'first_name', 'true', 'false', 'false', 'last_name', null, /*password : WhateverPassword*/ '$2a$09$pKCALxdgccQZyZp0BwFBq.Fvl82rMQYehkxB5J7BGNwru7UVBpzo.', '4324234', 'false', 41, 5, 4.3, 2500, 4);


--Vacation Home
insert into vacation_home(id, deleted, description, guest_limit, name, price_per_day, rating, address_id, home_owner_id, canceling_percentage)
values
(100, 'false','Located in the heart of Page, walking distance from restaurants, shops, a grocery store, the movie theater, hiking trails, Main Street, and Page City Park, and only a few miles from scenic highlights like Horseshoe Bend, Antelope Canyon, and Lake Powell', 5, ' 55 Horseshoe Bend, Antelope Canyon, and Lake Powell', 50, 4.5, 40 , 111, 0),
(101, 'false','Located in the heart of Page, walking distance from restaurants, shops, a grocery store, the movie theater, hiking trails, Main Street, and Page City Park, and only a few miles from scenic highlights like Horseshoe Bend, Antelope Canyon, and Lake Powell', 3, ' Whatever you say',44, 3, 41 , 111, 0),
(102, 'false','Located in the heart of Page, walking distance from restaurants, shops, a grocery store, the movie theater, hiking trails, Main Street, and Page City Park, and only a few miles from scenic highlights like Horseshoe Bend, Antelope Canyon, and Lake Powell', 5, ' Yo soy tu madre', 60, 1.5, 42 , 111, 0),
(103, 'false','Located in the heart of Page, walking distance from restaurants, shops, a grocery store, the movie theater, hiking trails, Main Street, and Page City Park, and only a few miles from scenic highlights like Horseshoe Bend, Antelope Canyon, and Lake Powell', 5, ' Home 103 ', 60, 1.5, 42 , 111, 0);


--Boat
insert into boat(
	id, guest_limit, deleted, description, engine_count, engine_power, information, length, max_speed, name, price_per_day,  rating, type, address_id, boat_owner_id, canceling_percentage)
	values (100, 4, 'false' ,'DESCRIIIIIIIIIIIIPTION', 2, 30, 'iNFORMATION', 20, 40, 'Titanic',35, 3, 'SMOL' , 43, 109, 5),
	(101, 4, 'false' ,'noviiii descriptiooonnn', 2, 30, 'iNFORMATION', 20, 40, 'bela ladja',35, 3, 'SMOL' , 43, 109, 5);


-- Adventure
insert into adventure(
	id, duration_in_hours, canceling_percentage, description, max_number_of_participants, price_per_day, rating, title, deleted, address_id, instructor_id)
	values
	(111, 2.00, 20, 'Snorkeling with eels', 3, 45, 4, 'Eeel pradise', 'false', 40, 110),
	(112, 5.00, 20, 'We fish Sharks', 3, 45, 4, 'Shark soup', 'false', 40, 110);

--Rooms
--insert into room(
--	id, bed_number, home_id)
--	values (100, 4, 100);


--Utilities
insert into utility(
	id, name, price)
	values (100, 'WIFI', 10),
	(101, 'A/C', 20),
	(102, 'PETS', 23),
	(103, 'CLEANING', 40),
	(104, 'You keep catch', 40),
    (106, 'Catch cleaning & filleting', 20),
	(107, 'Snorkeling Equipment', 5),
	(108, 'Fighting Chair', 10),
	(109, 'Bla', 10),
	(110, 'Raw Fish', 20),
	(111, 'Treasure Hunting', 5),
	(112, 'Gospodje Pufna Fish', 10);
insert into vacation_home_utilities(
	vacation_home_id, utilities_id)
	values (100, 100),
	(100, 101),
	(100, 102),
	(100, 103),
	(100, 104),
	(100, 106),
	(100, 107),
	(100, 108),
	(100, 109),
	(102, 102),
	(103, 103),
	(100, 110),
	(100, 111),
	(100, 112);

insert into boat_utilities(
	boat_id, utilities_id)
	values (100, 100),
	(100, 101),
	(100, 102),
	(100, 103),
	(100, 104),
    (101, 102),
    (101, 103),
	(101, 101);

insert into adventure_utilities(
	adventure_id, utilities_id)
	values (111, 100),
	(112, 101),
	(111, 102),
	(111, 103),
	(112, 104);

---- Reservations
insert into reservation(
	id, end_date, is_cancelled, guests, price, start_date, type, client_id, canceling_percentage, owner_captain)
	values
	(101, '2022-08-24', 'false', 3, 200, '2022-08-20', 'VACATION_HOME', 10, 0, 'false'),
	(104, '2022-08-30', 'false', 3, 200, '2022-08-29', 'ADVENTURE', 10, 0, 'false'),
	(105, '2022-08-24 09:00:00', 'false', 3, 500, '2022-08-24 05:00:00', 'ADVENTURE', 10,0, 'false'),
	(106, '2022-08-24 15:00:00', 'false', 3, 400, '2022-08-24 09:00:00', 'ADVENTURE', 10,0, 'false'),
	(107, '2022-08-24 20:00:00', 'false', 3, 90, '2022-08-24 11:00:00', 'ADVENTURE', 10,0, 'false'),
	(108, '2022-08-24 12:00:00', 'false', 3, 354, '2022-08-24 10:00:00', 'ADVENTURE', 10, 0, 'false');

--	(102, '2022-08-30', 'false', 5, 200, '2022-08-25', 'VACATION_HOME', 12);


-- Vacation Home Availability
insert into vacation_home_availability_periods(id, end_date, start_date)
values
(100,  '2022-08-30', '2022-08-01'), --available
(101,  '2022-08-30', '2022-08-01'), --available
(102,  '2022-08-30', '2022-08-01'), --available
(103,  '2022-09-14', '2022-08-26'); --available

insert into vacation_home_availability(
	vacation_home_id, availability_id)
	values (100, 100),
	(101,101),
	(102,102),
	(103,103);
--Instructor Availability
--insert into instructor_availability_periods(
--	id, end_date, start_date)
--	values (100,  '2022-08-30', '2022-08-01'), --available
--	(101,  '2022-09-30', '2022-09-01'), --available
--	(102,  '2022-10-30', '2022-10-01'); --available
--
--insert into instructor_availability(
--	instructor_id, availability_id)
--	values (110, 100),
--	(110, 101),
--	(110, 102);

----Boats Availability
--insert into boat_availability_periods(id, end_date, start_date)
--values
--(100,  '2022-08-30', '2022-08-01'); --available
--insert into boat_available_time_periods (boat_id, available_time_periods_id)
--values
--(100, 100);

-- Vacation Home Reservations
insert into vacation_home_reservations(vacation_home_id, reservations_id)
values
(101, 101);
--(102, 102);


-- Equipment
insert into fishing_equipment(id, name)
	values (99, 'Fishing Reels'),
	       (100, 'Lures'),
	       (101, 'Fishing Rods'),
	       (102, 'Rod Holders'),
	       (103, 'Rod Racks'),
	       (104, 'Fishing Nets'),
	       (105, 'Tackle Boxes'),
	       (106, 'Rain Gear');


-- Code of Conduct
insert into rule(id, name)
    values (99, 'No smoking'),
            (100, 'No pets'),
            (101, 'No alcohol'),
            (102, 'No littering'),
            (103, '18+'),
            (104, 'WiFi included'),
            (105, 'No kids');

           insert into adventure_code_of_conduct(adventure_id, code_of_conduct_id)
            values (111, 100);

insert into adventure_reservations(adventure_id, reservations_id)
values (112, 105),
    (112, 106),
    (112, 107),
    (111,104),
    (112, 108);


insert into special_offer(id, active_from, active_to, discount, guests, is_used, price, reservation_end_date, reservation_start_date, type, canceling_percentage, is_captain)
	values (100, '2022-08-25 19:50:00', '2022-09-02 19:50:00', 5, 3, 'false', 202, '2022-09-05 00:50:00', '2022-09-04 19:50:00', 'ADVENTURE',0, 'false'),
	 (101, '2022-09-02 19:50:00', '2022-09-03 19:50:00', 5, 3, 'false', 303, '2022-09-06 00:50:00', '2022-09-05 19:50:00', 'ADVENTURE',0, 'false'),
	 (102, '2022-09-03 19:50:00', '2022-09-14 19:50:00', 5, 3, 'false', 404, '2022-09-28 00:50:00', '2022-09-25 19:50:00', 'ADVENTURE',10, 'false');

insert into adventure_special_offers(
	adventure_id, special_offers_id)
	values (112, 100),
	 (112, 101),
	 (112, 102);


--Boat owner availability
insert into boat_owner_availability_periods(
	id, end_date, start_date)
	values (200,  '2022-08-30', '2022-08-01'),
	 (201,  '2022-09-04', '2022-09-01'),
	 (202,  '2022-09-08', '2022-09-07'),
	 (203,  '2022-09-13', '2022-09-10'),
	 (204,  '2022-09-23', '2022-09-18'); --available


insert into boat_owner_availability(
	boat_owner_id, availability_id)
	values (109, 200), (109,201), (109, 202), (109, 203), (109, 204);

------ Reservations
--insert into reservation(
--	id, end_date, is_cancelled, guests, price, start_date, type, client_id, canceling_percentage, owner_captain)
--	values
--	(201, '2022-08-21', 'false', 3, 200, '2022-08-20', 'BOAT', 10, 5, 'false'),
--	(202, '2022-08-13', 'false', 3, 200, '2022-08-11', 'BOAT', 10, 5, 'false'),
--	(203, '2022-08-31', 'false', 3, 200, '2022-08-28', 'BOAT', 10, 5, 'false'),
--	(204, '2022-09-13', 'false', 3, 200, '2022-09-11', 'BOAT', 10, 5, 'false'),
--	(205, '2022-09-17', 'false', 3, 200, '2022-09-15', 'BOAT', 10, 5, 'false'),
--		(206, '2022-08-31', 'false', 3, 200, '2022-08-28', 'BOAT', 10, 5, 'false'),
--    	(207, '2022-09-13', 'false', 3, 200, '2022-09-11', 'BOAT', 10, 5, 'false'),
--    	(208, '2022-09-17', 'false', 3, 200, '2022-09-15', 'BOAT', 10, 5, 'false'),
--    	(209, '2022-08-21', 'false', 3, 200, '2022-08-20', 'BOAT', 10, 5, 'false'),
--        	(210, '2022-08-13', 'false', 3, 200, '2022-08-11', 'BOAT', 10, 5, 'false');
--
----Boat reservations
--
--insert into boat_reservations(boat_id, reservations_id) values
--    (100, 201),(100, 202),(100, 203), (100, 204), (100, 205), (101, 206), (101, 207), (101, 208), (101, 209), (101, 210);
--
--insert into reservation(
--	id, end_date, is_cancelled, guests, price, start_date, type, client_id, canceling_percentage, owner_captain)
--	values
--	(211, '2022-08-21', 'false', 3, 300, '2022-08-20', 'VACATION_HOME', 12, 5, 'false'),
--	(212, '2022-08-13', 'false', 3, 300, '2022-08-11', 'VACATION_HOME', 12, 5, 'false'),
--	(213, '2022-09-03', 'false', 3, 300, '2022-08-28', 'VACATION_HOME', 12, 5, 'false'),
--	(214, '2022-09-13', 'false', 3, 300, '2022-09-11', 'VACATION_HOME', 12, 5, 'false'),
--	(215, '2022-09-17', 'false', 3, 300, '2022-09-15', 'VACATION_HOME', 12, 5, 'false'),
--		(216, '2022-09-03', 'false', 3, 300, '2022-08-28', 'VACATION_HOME', 12, 5, 'false'),
--    	(217, '2022-09-13', 'false', 3, 300, '2022-09-11', 'VACATION_HOME', 12, 5, 'false'),
--    	(218, '2022-09-17', 'false', 3, 300, '2022-09-15', 'VACATION_HOME', 12, 5, 'false'),
--    	(219, '2022-08-21', 'false', 3, 300, '2022-08-20', 'VACATION_HOME', 12, 5, 'false'),
--        	(220, '2022-08-13', 'false', 3, 300, '2022-08-11', 'VACATION_HOME', 12, 5, 'false');
--
----Boat reservations
--
--insert into vacation_home_reservations(vacation_home_id, reservations_id) values
--    (102, 211),(102, 212),(102, 213), (102, 214), (102, 215), (103, 216), (103, 217), (103, 218), (103, 219), (103, 220);