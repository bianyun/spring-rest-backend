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

INSERT INTO sys_api_perm (id, name, value, parent_id)
VALUES (1, '系统管理', 'api:sys', NULL)
     , (2, '用户管理', 'api:sys:user', 1)
     , (3, '查看用户列表', 'api:sys:user:view-list', 2)
     , (4, '查看用户详情', 'api:sys:user:view-detail', 2)
     , (5, '添加用户', 'api:sys:user:add', 2)
     , (6, '更新用户', 'api:sys:user:update', 2)
     , (7, '删除用户', 'api:sys:user:delete', 2)
     , (8, '批量删除用户', 'api:sys:user:batch-delete', 2)
     , (9, '启用用户', 'api:sys:user:activate', 2)
     , (10, '停用用户', 'api:sys:user:deactivate', 2)
     , (11, '角色管理', 'api:sys:role', 1)
     , (12, '查看角色列表', 'api:sys:role:view-list', 11)
     , (13, '查看角色详情', 'api:sys:role:view-detail', 11)
     , (14, '添加角色', 'api:sys:role:add', 11)
     , (15, '更新角色', 'api:sys:role:update', 11)
     , (16, '删除角色', 'api:sys:role:delete', 11)
     , (17, '批量删除角色', 'api:sys:role:batch-delete', 11)
     , (18, '获取角色分配的权限', 'api:sys:role:fetch-perms', 11)
     , (19, '保存角色分配的权限', 'api:sys:role:save-perms', 11)
     , (20, '权限管理', 'api:sys:perm', 1)
     , (21, '获取菜单权限元数据', 'api:sys:perm:fetch-menu-meta', 20)
     , (22, '获取接口权限元数据', 'api:sys:perm:fetch-api-meta', 20)
     , (23, '同步接口权限数据', 'api:sys:perm:sync-api', 20)
     , (24, '同步菜单权限数据', 'api:sys:perm:sync-menu', 20)
     , (25, '添加按钮权限', 'api:sys:perm:button:add', 20)
     , (26, '更新按钮权限', 'api:sys:perm:button:update', 20)
     , (27, '删除按钮权限', 'api:sys:perm:button:delete', 20)
     , (28, '菜单关联接口权限', 'api:sys:perm:menu:link-api', 20)
     , (29, '馆藏管理', 'api:lib', NULL)
     , (30, '作者管理', 'api:lib:author', 29)
     , (31, '查看作者列表', 'api:lib:author:view-list', 30)
     , (32, '查看作者详情', 'api:lib:author:view-detail', 30)
     , (33, '添加作者', 'api:lib:author:add', 30)
     , (34, '更新作者', 'api:lib:author:update', 30)
     , (35, '删除作者', 'api:lib:author:delete', 30)
     , (36, '批量删除作者', 'api:lib:author:batch-delete', 30)
     , (37, '译者管理', 'api:lib:translater', 29)
     , (38, '查看译者列表', 'api:lib:translater:view-list', 37)
     , (39, '查看译者详情', 'api:lib:translater:view-detail', 37)
     , (40, '添加译者', 'api:lib:translater:add', 37)
     , (41, '更新译者', 'api:lib:translater:update', 37)
     , (42, '删除译者', 'api:lib:translater:delete', 37)
     , (43, '批量删除译者', 'api:lib:translater:batch-delete', 37)
     , (44, '出版社管理', 'api:lib:publisher', 29)
     , (45, '查看出版社列表', 'api:lib:publisher:view-list', 44)
     , (46, '查看出版社详情', 'api:lib:publisher:view-detail', 44)
     , (47, '添加出版社', 'api:lib:publisher:add', 44)
     , (48, '更新出版社', 'api:lib:publisher:update', 44)
     , (49, '删除出版社', 'api:lib:publisher:delete', 44)
     , (50, '批量删除出版社', 'api:lib:publisher:batch-delete', 44)
     , (51, '图书管理', 'api:lib:book', 29)
     , (52, '查看图书列表', 'api:lib:book:view-list', 51)
     , (53, '查看图书详情', 'api:lib:book:view-detail', 51)
     , (54, '添加图书', 'api:lib:book:add', 51)
     , (55, '更新图书', 'api:lib:book:update', 51)
     , (56, '删除图书', 'api:lib:book:delete', 51)
     , (57, '批量删除图书', 'api:lib:book:batch-delete', 51)
;

INSERT INTO sys_menu (id, name, value, parent_id)
VALUES (1, '系统管理', 'menu:sys', NULL)
     , (2, '角色管理', 'menu:sys:role', 1)
     , (3, '权限管理', 'menu:sys:perm', 1)
     , (4, '图标预览', 'menu:icons', NULL)
     , (5, '外部文档', 'menu:docs', NULL)
     , (6, 'Vue', 'menu:docs:vue', 5)
     , (7, 'ElementUI', 'menu:docs:element', 5)
     , (8, 'Lodash', 'menu:docs:lodash', 5)
     , (9, 'Luxon', 'menu:docs:luxon', 5)
     , (10, 'YDNLU', 'menu:docs:ydnlu', 5)
;

INSERT INTO sys_button (id, name, show_order, value, parent_menu_id)
VALUES (1, '添加角色', 1, 'button:sys:role:add', 2)
     , (2, '更新角色', 2, 'button:sys:role:update', 2)
     , (3, '删除角色', 3, 'button:sys:role:delete', 2)
     , (4, '批量删除角色', 4, 'button:sys:role:batch-delete', 2)
     , (5, '角色分配权限', 5, 'button:sys:role:assign-perm', 2)
     , (6, '更新按钮权限', 3, 'button:sys:perm:add-button', 3)
     , (7, '修改按钮权限', 4, 'button:sys:perm:update-button', 3)
     , (8, '删除按钮权限', 5, 'button:sys:perm:batch-delete-btn', 3)
     , (9, '同步接口权限数据', 1, 'button:sys:perm:sync-api', 3)
     , (10, '同步菜单权限数据', 2, 'button:sys:perm:sync-menu', 3)
     , (11, '菜单关联接口权限', 6, 'button:sys:perm:menu:link-api', 3)
;

INSERT INTO sys_button_api_perm (button_id, api_perm_id)
VALUES (1, 14)
     , (2, 15)
     , (3, 16)
     , (4, 17)
     , (5, 18)
     , (5, 19)
     , (9, 23)
     , (10, 24)
     , (6, 25)
     , (7, 26)
     , (8, 27)
     , (11, 28)
;

INSERT INTO sys_menu_api_perm (menu_id, api_perm_id)
VALUES (2, 12)
     , (3, 21)
     , (3, 22)
;

INSERT INTO sys_role_menu (role_id, menu_id)
VALUES (1, 1)
     , (1, 2)
     , (1, 4)
     , (1, 5)
     , (1, 6)
     , (1, 7)
     , (1, 8)
     , (1, 9)
     , (1, 10)
;

INSERT INTO sys_role_button (role_id, button_id)
VALUES (1, 1)
     , (1, 2)
     , (1, 3)
     , (1, 4)
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
