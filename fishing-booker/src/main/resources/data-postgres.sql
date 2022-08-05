delete from client;
delete from admin;
delete from vacation_home_code_of_conduct;
delete from reservation;
delete from vacation_home_owner;
delete from room;
delete from role;

--Role
insert into role (id, name) values (1, 'ROLE_ADMIN');
insert into role (id, name) values (2, 'ROLE_CLIENT');
insert into role (id, name) values (3, 'ROLE_BOAT_OWNER');
insert into role (id, name) values (4, 'ROLE_HOME_OWNER');
insert into role (id, name) values (5, 'ROLE_INSTRUCTOR');

----Client
--insert into client(id, address, city, country, email, first_name, is_activated, is_blocked, last_name, password, phone, test_rebase_again, role_id, points, type)
--values (101, 'WhateverAddress', 'WhateverCity', 'WhateverCountry', 'WhateverEmail', 'Whatever', 'true' , 'false', 'Whatever', /*password : WhateverPassword*/ '$2a$09$pKCALxdgccQZyZp0BwFBq.Fvl82rMQYehkxB5J7BGNwru7UVBpzo.', '043-4234-423', false , 1 , 0, 0);
--
----Admin
--insert into admin(id, address, city, country, email, first_name, is_activated, is_blocked, last_name, password, phone, test_rebase_again, role_id)
--
----Home Owner
--values (100, 'WhateverAddress', 'WhateverCity', 'WhateverCountry', 'WhateverEmailAdmin', 'Whatever', 'true' , 'false', 'Whatever', /*password : WhateverPassword*/ '$2a$09$pKCALxdgccQZyZp0BwFBq.Fvl82rMQYehkxB5J7BGNwru7UVBpzo.', '043-4234-423', false , 1 );
--insert into vacation_home_owner(
--	id, address, city, country, email, first_name, is_activated, is_blocked, last_name, last_password_reset_date, password, phone, test_rebase_again, role_id)
--	values (110, 'VacationOwnerAddress', 'VacationOwnerCity', 'VacationOwnerCountry', 'VacationOwnerEmail', 'VacationOwnerFirstName'
--	, true , false, 'VacationOwnerLastName', null , /*password : WhateverPassword*/ '$2a$09$pKCALxdgccQZyZp0BwFBq.Fvl82rMQYehkxB5J7BGNwru7UVBpzo.',
--	 '0943242342',false, 4);
--
----Boat Owner
--insert into public.boat_owner(
--	id, address, city, country, email, first_name, is_activated, is_blocked, last_name, last_password_reset_date, password, phone, test_rebase_again, role_id, type)
--	values (110, 'BoatOwnerAddress', 'BoatOwnerCity', 'BoatOwnerCountry', 'BoatOwnerEmail', 'BoatOwnerFirstName'
--	, true , false, 'BoatOwnerLastName', null , /*password : WhateverPassword*/ '$2a$09$pKCALxdgccQZyZp0BwFBq.Fvl82rMQYehkxB5J7BGNwru7UVBpzo.',
--	 '0943242342',false, 3,0);
---- Vacation Home
--insert into public.vacation_home(
--	id, address, deleted, description, guest_limit, information, name, rating, home_owner_id)
--	values (100, 'Novosadskog Sajma, 43', false, 'Located in the heart of Page, walking distance from restaurants, shops, a grocery store, the movie theater, hiking trails, Main Street, and Page City Park, and only a few miles from scenic highlights like Horseshoe Bend, Antelope Canyon, and Lake Powell',
--	 5, 'Bla bla whatevers', 'PRIVATE ROOMS CLOSE TO HORSESHOE BEND', 3.0, 110);
--
----Boat
--insert into public.boat(
--	id, address, capacity, deleted, description, engine_count, engine_power, information, length, max_speed, name, rating, reservation_canceling, type, boat_owner_id)
--	values (100, 'Novosadskog Sajma, 43', 4,false ,'DESCRIIIIIIIIIIIIPTION', 2, 30, 'iNFORMATION', 20, 40, 'Titanic',3.5, 0, 0, 110);
----Rooms
--insert into room(
--	id, number_of_beds, home_id)
--	values (101, 5, 100);
----Code of Conduct
--insert into vacation_home_code_of_conduct(
--	vacation_home_id, code_of_conduct)
--	values (100, 'No Smoking'),
--	 (100, 'No Pets'),
--	 (100, 'No Party');
---- Reservations
--INSERT INTO public.reservation(
--	dtype, id, end_date, is_canceled, max_guests, price, start_date, adventure_destination, client_id, adventure_id, boat_id, home_id)
--	VALUES (0, 100, '2022-01-01', false, 5, 100, '2022-01-01', 'Salt Lake City', 101, null, null, 100);
