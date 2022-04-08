INSERT INTO role (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO role (id, name) VALUES (2, 'ROLE_CLIENT');

INSERT INTO client(id, address, city, country, email, first_name, is_activated, is_blocked, last_name, password, phone, test_rebase_again, registration_id, role_id, points, type)

VALUES (1, 'WhateverAddress', 'WhateverCity', 'WhateverCountry', 'WhateverEmail', 'Whatever', 'true' , 'false', 'Whatever', /*password : WhateverPassword*/ '$2a$09$pKCALxdgccQZyZp0BwFBq.Fvl82rMQYehkxB5J7BGNwru7UVBpzo.', '043-4234-423', false , null , 1 , 0, 'GOLD');