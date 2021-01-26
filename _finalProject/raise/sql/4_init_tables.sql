INSERT INTO role(id,
                 name)
values (1, 'ADMIN'),
       (2, 'USER');

INSERT INTO role_permissions(id,
                             role_id,
                             permission)
values (1, 1, '*'),
       (2, 2, 'user:*'),
       (3, 2, 'test:*');

INSERT INTO test_category(id, category, parent_id) VALUES (1, 'Programming', NULL),
                                                          (2, 'Math', NULL),
                                                          (3, 'Nature', NULL),
                                                          (4, 'Java', 1),
                                                          (5, 'C++', 1),
                                                          (6, 'Algebra', 2),
                                                          (7, 'Geometry', 2),
                                                          (8, 'Wildlife', 3);
