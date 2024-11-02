-- insert into roles(insert_date_time, insert_user_id, is_deleted, last_update_date_time, last_update_user_id, description)
-- VALUES ('2021-01-05 00:00:00', 1, false, '2021-01-05 00:00:00', 1, 'Admin'),
--        ('2021-01-05 00:00:00', 1, false, '2021-01-05 00:00:00', 1, 'Manager'),
--        ('2021-01-05 00:00:00', 1, false, '2021-01-05 00:00:00', 1, 'Employee');
-- insert into users(insert_date_time, insert_user_id, is_deleted, last_update_date_time, last_update_user_id, enabled,
--                   first_name, gender, last_name, user_name, role_id, pass_word)
--
-- values ('2021-01-05 00:00:00', 1, false, '2021-01-05 00:00:00', 1, true, 'admin', 'MALE', 'admin', 'admin@admin.com',
--         1, '$2a$10$Q7ilQ6Hv11qpU0T7xfMzMeqxoPXkvhTVXxFqg0UL2xvLnhNqB7vba');


insert into roles(insert_date_time, insert_user_id, is_deleted, last_update_date_time,
                  last_update_user_id, description)
values ('2022-09-09 00:00:00', 1, false, '2022-09-09 00:00:00', 1, 'Root User'),
       ('2022-09-09 00:00:00', 1, false, '2022-09-09 00:00:00', 1, 'Admin'),
       ('2022-09-09 00:00:00', 1, false, '2022-09-09 00:00:00', 1, 'Manager'),
       ('2022-09-09 00:00:00', 1, false, '2022-09-09 00:00:00', 1, 'Employee');

insert into addresses(insert_date_time, insert_user_id, is_deleted, last_update_date_time, last_update_user_id,
                      address_line1, address_line2, city, state, country, zip_code)
values ('2022-09-15 00:00:00', 1, false, '2022-09-15 00:00:00', 1,
        '7925 Jones Branch Dr, #3300', 'Tysons', 'Virginia', 'VA', 'United States', '22102-1234'),
       ('2022-09-15 00:00:00', 1, false, '2022-09-15 00:00:00', 1,
        'Future Street', 'Times Square', 'Atlanta', 'Alabama', 'United States', '54321-4321'),
       ('2022-09-15 00:00:00', 1, false, '2022-09-15 00:00:00', 1,
        'North Street', 'Circle Square', 'San Francisco', 'California', 'United States', '65245-8546'),
       ('2022-09-15 00:00:00', 1, false, '2022-09-15 00:00:00', 1,
        'West Street', 'Triangle Square', 'Los Angeles', 'California', 'United States', '54782-5214'),
       ('2022-09-15 00:00:00', 1, false, '2022-09-15 00:00:00', 1,
        'East Street', 'Cube Square', 'Los Angeles', 'California', 'United States', '54782-5214'),
       ('2022-09-15 00:00:00', 1, false, '2022-09-15 00:00:00', 1,
        'South Street', 'Times Square', 'Los Angeles', 'California', 'United States', '54782-5214'),
       ('2022-09-15 00:00:00', 1, false, '2022-09-15 00:00:00', 1,
        'SouthWest Street', 'Puzzle Square', 'Los Angeles', 'California', 'United States', '65654-8989'),
       ('2022-09-15 00:00:00', 1, false, '2022-09-15 00:00:00', 1,
        'OwerWest Street', 'Android Square', 'Los Angeles', 'Phoneix', 'United States', '65654-8989');;

insert into companies(insert_date_time, insert_user_id, is_deleted, last_update_date_time, last_update_user_id,
                      title, phone, website, address_id, company_status)
values ('2022-09-15 00:00:00', 1, false, '2022-09-15 00:00:00', 1,
        'CYDEO','+1 (652) 852-8888', 'https://www.cydeo.com', 1, 'ACTIVE'),
       ('2022-09-15 00:00:00', 1, false, '2022-09-15 00:00:00', 1,
        'Green Tech','+1 (652) 852-3246', 'https://www.greentech.com', 2, 'ACTIVE'),
       ('2022-09-15 00:00:00', 1, false, '2022-09-15 00:00:00', 1,
        'Blue Tech','+1 (215) 654-5268', 'https://www.bluetech.com', 3, 'ACTIVE'),
       ('2022-09-15 00:00:00', 1, false, '2022-09-15 00:00:00', 1,
        'Red Tech','+1 (215) 846-2642', 'https://www.redtech.com', 4, 'PASSIVE');

insert into users(insert_date_time, insert_user_id, is_deleted, last_update_date_time, last_update_user_id,
                  username, password, firstname, lastname, phone, role_id, company_id, enabled)
values
-- COMPANY-1 / CYDEO / ROOT USER
('2022-09-09 00:00:00', 1, false, '2022-09-09 00:00:00', 1,
 'root@cydeo.com', '$2a$10$nAB5j9G1c3JHgg7qzhiIXO7cqqr5oJ3LXRNQJKssDUwHXzDGUztNK',
 'Robert', 'Martin', '+1 (852) 564-5874', 1, 1, true),
-- COMPANY-2 / Green Tech / ADMIN-1
('2022-09-09 00:00:00', 1, false, '2022-09-09 00:00:00', 1,
 'admin@greentech.com', '$2a$10$nAB5j9G1c3JHgg7qzhiIXO7cqqr5oJ3LXRNQJKssDUwHXzDGUztNK',
 'Mary', 'Grant', '+1 (234) 345-4362', 2, 2, true),
-- COMPANY-2 / Green Tech / ADMIN-2
('2022-09-09 00:00:00', 1, false, '2022-09-09 00:00:00', 1,
 'admin2@greentech.com', '$2a$10$nAB5j9G1c3JHgg7qzhiIXO7cqqr5oJ3LXRNQJKssDUwHXzDGUztNK',
 'Garrison', 'Short', '+1 (234) 356-7865', 2, 2, true),
-- COMPANY-2 / Green Tech / MANAGER
('2022-09-09 00:00:00', 1, false, '2022-09-09 00:00:00', 1,
 'manager@greentech.com', '$2a$10$nAB5j9G1c3JHgg7qzhiIXO7cqqr5oJ3LXRNQJKssDUwHXzDGUztNK',
 'Robert', 'Noah', '+1 (234) 564-5874', 3, 2, true),
-- COMPANY-2 / Green Tech / EMPLOYEE
('2022-09-09 00:00:00', 1, false, '2022-09-09 00:00:00', 1,
 'employee@greentech.com', '$2a$10$nAB5j9G1c3JHgg7qzhiIXO7cqqr5oJ3LXRNQJKssDUwHXzDGUztNK',
 'Mike', 'Times', '+1 (234) 741-8569', 4, 2, true),
-- COMPANY-3 / Blue Tech / ADMIN
('2022-09-09 00:00:00', 1, false, '2022-09-09 00:00:00', 1,
 'admin@bluetech.com', '$2a$10$nAB5j9G1c3JHgg7qzhiIXO7cqqr5oJ3LXRNQJKssDUwHXzDGUztNK',
 'Chris', 'Brown', '+1 (356) 258-3544', 2, 3, true),
-- COMPANY-3 / Blue Tech / MANAGER
('2022-09-09 00:00:00', 1, false, '2022-09-09 00:00:00', 1,
 'manager@bluetech.com', '$2a$10$nAB5j9G1c3JHgg7qzhiIXO7cqqr5oJ3LXRNQJKssDUwHXzDGUztNK',
 'Tom', 'Hanks', '+1 (356) 258-3544', 3, 3, true),
-- COMPANY-4 / Red Tech / ADMIN
('2022-09-09 00:00:00', 1, false, '2022-09-09 00:00:00', 1,
 'admin@redtech.com', '$2a$10$nAB5j9G1c3JHgg7qzhiIXO7cqqr5oJ3LXRNQJKssDUwHXzDGUztNK',
 'John', 'Doe', '+1 (659) 756-1265', 2, 4, true);
