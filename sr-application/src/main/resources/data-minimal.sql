
-- ==================================================
--  系统管理数据
-- ==================================================

INSERT INTO sys_user (id, created_time, last_modified_time, active, deleted, email, gender, mobile, nickname, password, picture_url, realname, username, created_by_id, last_modified_by_id)
VALUES (1, '2021-01-08 10:00:00.0', '2021-01-19 23:52:01.086', 1, 0, 'superadmin@163.com', 1, NULL, '系统管理员', '$2a$10$Hw5P4u0Aen7aiIPc6nSWsuFnmvZ7icYMtwacvBdq4yCzQwIN87lZ.', NULL, '系统管理员', 'superadmin', 1, 1)
     , (2, '2021-01-08 10:00:00.0', '2021-01-19 23:52:44.254', 1, 0, 'admin@qq.com', 1, NULL, '风流盲侠', '$2a$10$IaynXehgA6NHOurIcsiof.I69a.0I/OaSxxF.k6xBJRDM4UQbUATG', NULL, '花满楼', 'admin', 1, 1)
     , (3, '2021-01-08 10:00:00.0', '2021-01-19 22:01:01.761', 1, 0, 'user@qq.com', 1, NULL, '冷如冰寒如雪', '$2a$10$gScvF8GErqQTqvQqa8ZjIOh8Tsp0lnhVPPdMyOMKPb7NCdgXU3FgC', NULL, '西门吹雪', 'user', 1, 1)
     , (4, '2021-01-19 18:07:00.14', '2021-01-19 20:38:44.745', 1, 0, 'yegucheng@163.com', 1, NULL, '白云城主', '$2a$10$2gnuRabSeHaOJYbQKDDoyu2e8V7D.GUndwYKuAztSwKPUPoikRsXy', NULL, '叶孤城', 'yegc', 1, 1)
     , (5, '2021-01-19 21:06:39.878', '2021-01-19 21:46:58.295', 1, 0, 'huamanlou@qq.com', 3, NULL, '小李探花', '$2a$10$6Tc9m1N5NUVqj4irBW4fquRAxV4ymGWUi0X1kHYVtexrkVcJZ21Jm', NULL, '李寻欢', 'lixf', 1, 1)
     , (6, '2021-01-19 21:41:38.833', '2021-01-19 21:44:27.805', 1, 0, 'luxiaofeng@qq.com', 3, NULL, '四条眉毛', '$2a$10$FBH2zPKGiGyjsCwKdoyN.eHNk.rUpp2KcRcVQQXM7aWPFRbdy/ZIS', NULL, '陆小凤', 'luxf', 1, 1)
     , (7, '2021-01-19 21:57:39.748', '2021-01-19 22:00:21.081', 1, 0, 'linglingfa@qq.com', 3, NULL, '大内密探', '$2a$10$G8FpI6cMJyh0aVp4A4eyheOa1QfnmnlsijKRfkOr.eb2lbnvXzjeO', NULL, '零零发', 'linglf', 1, 1)
;

INSERT INTO sys_role (id, created_time, last_modified_time, description, name, value, created_by_id, last_modified_by_id)
VALUES (1, '2021-01-08 10:00:00.0', '2021-01-19 20:11:29.103', '管理员', '管理员', 'Admin', 1, 1)
     , (2, '2021-01-08 10:00:00.0', '2021-01-19 20:47:02.177', '操作员，负责图书信息录入', '操作员', 'Operator', 1, 1)
     , (3, '2021-01-19 19:32:04.057', '2021-01-19 20:05:34.57', '只读访问，不能新增、修改或删除', '游客', 'Visitor', 1, 1)
;

INSERT INTO sys_user_role (user_id, role_id)
VALUES (2, 1)
     , (3, 2)
     , (4, 3)
     , (5, 3)
     , (6, 3)
     , (7, 3)
;



-- ==================================================
--  馆藏管理数据
-- ==================================================
INSERT INTO lib_author (id, created_time, last_modified_time, country, gender, name, created_by_id, last_modified_by_id)
VALUES (1, '2021-01-15 16:11:28.194', '2021-01-15 16:38:51.557', 840, 1, 'W. Richard Stevens', 1, 1)
     , (2, '2021-01-15 23:04:26.353', '2021-01-16 01:04:09.587', 840, 1, 'David R. Shaffer', 1, 1)
     , (3, '2021-01-15 23:04:38.986', '2021-01-16 01:04:12.131', 840, 2, 'Katherine Kipp', 1, 1)
     , (4, '2021-01-19 22:02:59.362', '2021-01-19 22:02:59.362', 156, 1, '刘未鹏', 1, 1)
     , (5, '2021-01-19 22:04:08.592', '2021-01-19 22:04:08.592', 156, 1, '张荫麟', 1, 1)
;

INSERT INTO lib_publisher (id, created_time, last_modified_time, address, country, name, zip_code, created_by_id, last_modified_by_id)
VALUES (1, '2021-01-15 23:32:09.847', '2021-01-15 23:32:09.847', '北京东长安街6号', 156, '中国轻工业出版社', '100740', 1, 1)
     , (2, '2021-01-15 23:37:32.97', '2021-01-15 23:37:32.97', '北京市崇文区夕照寺街14号', 156, '人民邮电出版社', '100061', 1, 1)
     , (3, '2021-01-19 22:06:11.194', '2021-01-19 22:06:11.194', '长沙市雨花区东二环一段508号', 156, '湖南文艺出版社', '410014', 1, 1)
     , (4, '2021-01-19 22:10:22.36', '2021-01-19 22:10:22.36', '北京市海淀区万寿路173信箱', 156, '电子工业出版社', '100036', 1, 1)
;

INSERT INTO lib_book (id, created_time, last_modified_time, isbn, title, unit_price, word_count, translaters, created_by_id, last_modified_by_id, publisher_id)
VALUES (1, '2021-01-16 02:42:01.424', '2021-01-19 23:32:44.878', '978-7-115-22259-6', 'TCP/IP详解 卷1：协议（英文版）', 7900, 715, NULL, 1, 1, 2)
     , (2, '2021-01-16 11:19:09.592', '2021-01-19 14:33:32.005', '978-7-5184-0643-2', '发展心理学（第九版）', 8800, 700, '邹泓 等', 1, 2, 1)
     , (3, '2021-01-19 22:12:06.961', '2021-01-19 22:12:06.961', '978-7-121-14006-8', '暗时间', 3500, 25, NULL, 1, 1, 4)
     , (4, '2021-01-19 22:13:25.546', '2021-01-19 23:32:37.223', '978-7-5404-4811-0', '中国史纲', 2600, 180, NULL, 1, 1, 3)
;

INSERT INTO lib_book_author (book_id, author_id)
VALUES (1, 1)
     , (2, 2)
     , (2, 3)
     , (3, 4)
     , (4, 5)
;

