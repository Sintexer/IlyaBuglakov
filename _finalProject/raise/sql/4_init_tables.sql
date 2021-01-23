INSERT INTO role(id,
                 name)
values (1, 'ADMIN'),
       (2, 'USER');

INSERT INTO role_permissions(id,
                             role_id,
                             permission)
values (1, 1, '*'),
       (2, 2, 'user:*'),
       (3, 2, 'test:*')
--        (4, 2, 'auth:**')

