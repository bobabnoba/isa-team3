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
(40, 'Novi Sad', 'Srbija', 'Branka Bajica 70', 2100),
(41, 'Novi Sad', 'Srbija', 'Bore Prodanovica 2', 21000),
(42, 'Novi Sad', 'Srbija', 'Danila Kisa 29', 21000),
(43, 'Novi Sad', 'Srbija', 'Kisacka 8', 21000),
(44, 'Novi Sad', 'Srbija', 'Bulevar Cara Lazara 54', 21000),
(45, 'Novi Sad', 'Srbija', 'Brace Ribnikar 5', 21000),
(46, 'Novi Sad', 'Srbija', 'Bulevar Oslobodjenja 105', 21000),
(47, 'Novi Sad', 'Srbija', 'Radnicka 21', 21000);

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
(10,'Fishing lover',  'majablgic505@gmail.com', 'Maja', 'true' , 'false', 'false', 'Blagic',null, /*password : WhateverPassword*/ '$2a$09$pKCALxdgccQZyZp0BwFBq.Fvl82rMQYehkxB5J7BGNwru7UVBpzo.', '043-4234-423', 'false',41 , 2 , 4002, 2, 0),
(12,'Nature lover',  'client2gmail.com', 'Marlena', 'true' , 'false', 'false', 'Voltori',null, /*password : WhateverPassword*/ '$2a$09$pKCALxdgccQZyZp0BwFBq.Fvl82rMQYehkxB5J7BGNwru7UVBpzo.', '043-4234-423', 'false',44 , 2 , 0, 1, 3);

----Admin
insert into admin(id, biography, email, first_name, is_activated, is_blocked, deleted, last_name, last_password_reset_date, password, phone, test_rebase_again, address_id, role_id, points, rank_id, first_login, head_admin)
values (11,'No Biography provided.',  'admin@gmail.com', 'Conan', 'true' , 'false', 'false', 'Grey',null, /*password : WhateverPassword*/ '$2a$09$pKCALxdgccQZyZp0BwFBq.Fvl82rMQYehkxB5J7BGNwru7UVBpzo.', '043-4234-423', 'false',43 , 1, 0, 4, 'false', 'true');

----Home Owner
insert into home_owner(id, biography, email, first_name, is_activated, is_blocked, deleted, last_name, last_password_reset_date, password,
 phone, test_rebase_again, address_id, role_id, points, rank_id)
	values (111,'Lorem Ipsum ', 'homeowner1@gmail.com', 'Selena'
	, 'true' , 'false', 'false', 'Graham', null , /*password : WhateverPassword*/ '$2a$09$pKCALxdgccQZyZp0BwFBq.Fvl82rMQYehkxB5J7BGNwru7UVBpzo.',
	 '0943242342','false',41, 4, 0, 4),
	 (211,'Lorem Ipsum ', 'homeowner2@gmail.com', 'Louis'
     	, 'true' , 'false', 'false', 'Brown', null , /*password : WhateverPassword*/ '$2a$09$pKCALxdgccQZyZp0BwFBq.Fvl82rMQYehkxB5J7BGNwru7UVBpzo.',
     	 '0943242342','false',42, 4, 5000, 5);

--Boat Owner
insert into boat_owner(
	id, biography, email, first_name, is_activated, is_blocked, deleted, last_name, last_password_reset_date, password,
	 phone, test_rebase_again, address_id, role_id, type, points, rank_id)

	values (109,'Lake lover', 'boatowner1@gmail.com', 'Noah'
	, 'true' , 'false', 'false', 'Gravy', null , /*password : WhateverPassword*/ '$2a$09$pKCALxdgccQZyZp0BwFBq.Fvl82rMQYehkxB5J7BGNwru7UVBpzo.',
	 '0943242342','false', 40, 3,'BOAT_OWNER', 8500, 6),
	 (209,'Boat owner 2 biography', 'boatowner2@gmail.com', 'Benny'
     	, 'true' , 'false', 'false', 'Corry', null , /*password : WhateverPassword*/ '$2a$09$pKCALxdgccQZyZp0BwFBq.Fvl82rMQYehkxB5J7BGNwru7UVBpzo.',
     	 '0943242342','false', 46, 3,'BOAT_OWNER', 0, 4);

-- Instructor
insert into instructor(	id, biography, email, first_name, is_activated, is_blocked, deleted, last_name, last_password_reset_date, password, phone, test_rebase_again, address_id, role_id, rating, points, rank_id)
	values (110, 'biography', 'bobaruljic@gmail.com', 'Madison', 'true', 'false', 'false', 'Cole', null,
	/*password : WhateverPassword*/ '$2a$09$pKCALxdgccQZyZp0BwFBq.Fvl82rMQYehkxB5J7BGNwru7UVBpzo.', '4324234', 'false', 41, 5, 4.3, 4499, 4),
	(210, 'biography', 'instructor@gmail.com', 'Sofia', 'true', 'false', 'false', 'Barret', null,
    	/*password : WhateverPassword*/ '$2a$09$pKCALxdgccQZyZp0BwFBq.Fvl82rMQYehkxB5J7BGNwru7UVBpzo.', '4324234', 'false', 41, 5, 4.3, 2500, 4);



--Vacation Home
insert into vacation_home(id, deleted, description, guest_limit, name, price_per_day, rating, address_id, home_owner_id, canceling_percentage)
values
(100, 'false','Located in the heart of Page, walking distance from restaurants, shops, a grocery store, the movie theater, hiking trails, Main Street, and Page City Park, and only a few miles from scenic highlights like Horseshoe Bend, Antelope Canyon, and Lake Powell', 5, 'Antelope Homes', 50, 4.5, 40 , 111, 0),
(101, 'false','Located in the heart of Page, walking distance from restaurants, shops, a grocery store, the movie theater, hiking trails, Main Street, and Page City Park, and only a few miles from scenic highlights like Horseshoe Bend, Antelope Canyon, and Lake Powell', 3, 'Island Suites',44, 3, 41 , 111, 20),
(102, 'false','Located in the heart of Page, walking distance from restaurants, shops, a grocery store, the movie theater, hiking trails, Main Street, and Page City Park, and only a few miles from scenic highlights like Horseshoe Bend, Antelope Canyon, and Lake Powell', 5, 'Spring Hill', 60, 1.5, 42 , 211, 10),
(103, 'false','Located in the heart of Page, walking distance from restaurants, shops, a grocery store, the movie theater, hiking trails, Main Street, and Page City Park, and only a few miles from scenic highlights like Horseshoe Bend, Antelope Canyon, and Lake Powell', 5, 'Le Ritz Homes', 60, 1.5, 43 , 211, 10);


--Boat
insert into boat(
	id, guest_limit, deleted, description, engine_count, engine_power, information, length, max_speed, name, price_per_day,  rating, type, address_id, boat_owner_id, canceling_percentage)
	values (100, 4, 'false' ,'Some boat description', 2, 30, 'Boat info', 20, 40, 'Argo',35, 3, 'MEDIUM' , 43, 109, 5),
	(101, 4, 'false' ,'Boat 2 description', 2, 30, 'Boat info 2', 20, 40, 'Nisi',35, 3, 'MEDIUM' , 46, 109, 20);


-- Adventure
insert into adventure(
	id, duration_in_hours, canceling_percentage, description, max_number_of_participants, price_per_day, rating, title, deleted, address_id, instructor_id)
	values
	(111, 2.00, 20, 'Snorkeling with eels', 3, 45, 4, 'Reel paradise', 'false', 45, 110),
	(112, 5.00, 20, 'Sportfishing adventure', 3, 45, 4, 'Fish On', 'false', 44, 110);


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
	(101, 100),
	(102, 100),
	(103, 100),
    (100, 101),
	(101, 101),
	(102, 101),
	(103, 101),
	(102, 102),
	(103, 102),
	(100, 103);

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
	values (111, 104),
	(111, 106),
	(111, 107),
	(111, 108),
	(112, 104),
    (112, 106),
    (112, 107),
    (112, 108);


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

insert into adventure_fishing_equipment(
	adventure_id, fishing_equipment_id)
	values (111, 99),
	(111, 100),
	(111, 102),
	(111, 103),
	(111, 104),
	(112, 105),
    (112, 106),
    (112, 101),
    (112, 99);