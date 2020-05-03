INSERT INTO house (id, created, description, name, `type`)
VALUES (1, '2020-05-02', 'The main kitchen fridge', 'Kitchen Fridge', 1),
       (2, '2020-05-02', 'The utility room freezer', 'Inside Freezer', 2),
       (3, '2020-05-02', 'The outside fridge', 'Outside Fridge', 1)
;

INSERT INTO product (id, created, expiry, name, description, house_id)
VALUES (1, '2020-05-02', '2020-05-06', 'Milk', 'Low fat Tesco milk', 1),
       (2, '2020-05-02', '2020-06-01', 'Chicken Wings', 'in a buffalo sauce', 1)
;

INSERT INTO user (id, joined, name, `password`, status)
VALUES (1, '2020-05-02', 'Mary Reilly', 'ilovemyfridge', 0),
       (2, '2020-04-23', 'Bill Dwyre', 'crunchcrunch', 1)
;

INSERT INTO user_houses (users_id, houses_id)
VALUES (1, 2),
       (1, 3),
       (2, 2),
       (2, 3)
;