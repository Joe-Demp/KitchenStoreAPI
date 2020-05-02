INSERT INTO product (id, created, expiry, name, description)
VALUES (1, '2020-05-02', '2020-05-06', 'Milk', 'Low fat Tesco milk'),
       (2, '2020-05-02', '2020-06-01', 'Chicken Wings', 'in a buffalo sauce')
;

INSERT INTO house (id)
VALUES (1),
       (2),
       (3)
;

INSERT INTO user (id, joined, name, `password`)
VALUES (1, '2020-05-02', 'Mary Reilly', 'ilovemyfridge'),
       (2, '2020-04-23', 'Bill Dwyre', 'crunchcrunch')
;

INSERT INTO user_houses (users_id, houses_id)
VALUES (1, 2),
       (1, 3),
       (2, 2),
       (2, 3)
;