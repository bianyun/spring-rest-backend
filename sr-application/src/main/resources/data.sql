SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE sys_user;
TRUNCATE TABLE lib_author;
TRUNCATE TABLE lib_translater;
TRUNCATE TABLE lib_publisher;
TRUNCATE TABLE lib_book;
TRUNCATE TABLE lib_book_author;
TRUNCATE TABLE lib_book_translater;


INSERT INTO sys_user (id, created_time, last_modified_time, active, deleted, email, gender, mobile, nickname, password, picture_url, realname, username, created_by_id, last_modified_by_id)
VALUES (1, '2021-01-08 10:00:00.0', '2021-01-08 10:00:00.0', 1, 0, 'superadmin@163.com', 1, '13866668888', '系统管理员', '$2a$11$YcFyE0u00fbRGn4AWoaJzOh4eaDu/MmT6jjMhV1KQYmSTq94POPAG', '', '系统管理员', 'superadmin', 1, 1)
     , (2, '2021-01-08 10:00:00.0', '2021-01-08 10:00:00.0', 1, 0, 'admin@qq.com', 1, '13811112222', '李寻欢', '$2a$11$o9wjnWtPfTq4fVBybPLg4e23szkYq4EkKVU/170tEe.RZl167mrde', '', '李寻欢', 'admin', 1, 1)
     , (3, '2021-01-08 10:00:00.0', '2021-01-08 10:00:00.0', 1, 0, 'user@qq.com', 1, '13833334444', '西门吹雪', '$2a$11$o9wjnWtPfTq4fVBybPLg4e23szkYq4EkKVU/170tEe.RZl167mrde', '', '西门吹雪', 'user', 1, 1)
;

INSERT INTO sys_role (id, created_time, last_modified_time, description, name, value, created_by_id, last_modified_by_id)
VALUES (1, '2021-01-08 10:00:00.0', '2021-01-08 10:00:00.0', '后台管理员', '管理员', 'Admin', 1, 1)
     , (2, '2021-01-08 10:00:00.0', '2021-01-08 10:00:00.0', '操作员，负责图书信息录入', '操作员', 'Operator', 1, 1)
;

INSERT INTO sys_user_role (user_id, role_id)
VALUES (2, 1)
     , (3, 2)
;

INSERT INTO lib_author (id, created_time, last_modified_time, country_code, gender, name, created_by_id, last_modified_by_id)
VALUES (1, NULL, NULL, 48, 2, 'testAuthor1', NULL, NULL)
     , (3, NULL, NULL, 233, 2, 'testAuthor2', NULL, NULL)
     , (4, NULL, NULL, 233, 2, 'testAuthor4', NULL, NULL)
     , (5, NULL, NULL, 233, 2, 'testAuthor5', NULL, NULL)
;

INSERT INTO lib_translater (id, created_time, last_modified_time, country_code, name, created_by_id, last_modified_by_id)
VALUES (1, NULL, NULL, 48, 'testTranslater', NULL, NULL)
     , (2, NULL, NULL, 48, 'testTranslater2', NULL, NULL)
     , (3, NULL, NULL, 48, 'testTranslater3', NULL, NULL)
     , (4, NULL, NULL, 48, 'testTranslater4', NULL, NULL)
     , (5, NULL, NULL, 48, 'testTranslater5', NULL, NULL)
;

INSERT INTO lib_publisher (id, created_time, last_modified_time, address, country_code, name, zip_code, created_by_id, last_modified_by_id)
VALUES (1, NULL, NULL, 'address1', 48, 'testPulisher1', '350001', NULL, NULL)
     , (2, NULL, NULL, 'address 2', 48, 'testPublisher2', '350001', NULL, NULL)
     , (3, NULL, NULL, 'address3', 48, 'testPublisher3', '350001', NULL, NULL)
     , (4, NULL, NULL, 'address3', 48, 'testPublisher4', '350001', NULL, NULL)
     , (5, NULL, NULL, 'address4', 48, 'testPublisher4', '350001', NULL, NULL)
     , (6, NULL, NULL, 'address4', 48, 'testPublisher5', '350001', NULL, NULL)
     , (7, NULL, NULL, 'address5', 48, 'testPublisher5', '350001', NULL, NULL)
;

INSERT INTO lib_book (id, created_time, last_modified_time, isbn, title, unit_price, word_count, created_by_id, last_modified_by_id, publisher_id)
VALUES (5, NULL, NULL, 'isbn1111', 'bookTitle1', 19850, 100000, NULL, NULL, 1)
     , (6, NULL, NULL, 'isbn2222', 'bookTitle2', 29850, 100000, NULL, NULL, 1)
     , (7, NULL, NULL, 'isbn3333', 'bookTitle3', 39850, 300000, NULL, NULL, 2)
;

INSERT INTO lib_book_author (book_id, author_id)
VALUES (5, 1)
     , (5, 3)
     , (6, 1)
     , (6, 4)
     , (7, 3)
     , (7, 4)
;

INSERT INTO lib_book_translater (book_id, translater_id)
VALUES (5, 1)
     , (5, 2)
     , (6, 1)
     , (6, 2)
     , (6, 3)
     , (7, 4)
;

SET FOREIGN_KEY_CHECKS = 1;
