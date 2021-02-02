# spring-rest-backend

## 简介

本项目是基于 SpringBoot, Spring Data JPA, Shiro, Jooq, MapStruct 等框架搭建的 RESTful 风格的 Web 管理系统后台。主要优化了权限管理模块和复杂查询模块。需要与管理台前端项目 [Spring-Rest Admin](https://github.com/bianyun1981/spring-rest-admin) 搭配使用，[**点此处进入在线演示**](https://wisecoder.work)。

## 主要优化点

### 权限管理

- 根据配置的自定义注解 `@RequiresPerm` 自动生成初始的接口权限元数据，不需要往数据库中手工插入数据；

- 菜单权限元数据由管理台前端项目根据路由配置自动生成，按钮权限数据由管理员在页面上添加，不需要往数据库中手工插入数据；

- 菜单权限、按钮权限、接口权限的元数据均由管理员在管理台前端页面同步到数据库；

- 管理员在管理台前端页面配置好菜单权限和按钮权限与接口权限的关联关系，在给角色分配权限时，只需要给角色分配 菜单权限和 按钮权限即可，不再需要手动给角色分配接口权限；

- 常用操作的接口权限在抽象公共父类 `AbstractBaseController` 里面配置一次即可，无需子类中重复配置。

  ![Screen of Perm Admin](https://cdn.jsdelivr.net/gh/bianyun1981/CDN@latest/img/readme/2021-01/2021-01-31-233302-793.png)

### 复杂查询

- 复杂查询由基于 Jooq 的扁平查询 (FlatQuery) 来实现，支持数据库字段编译期类型安全，可以用 Java代码像写 SQL语句一样去写 SQL。表对应的 Jooq 模型代码直接从 JPA Model 生成，无缝衔接；
- 扁平查询 (FlatQuery) 的返回结果为扁平的 MAP型数据，方便页面结果展示 以及 查询条件表达式的组装；
- 前端页面封装条件过滤组件，自动组装查询条件表达式**（待完成）**；
- 查询条件表达式 到 Jooq查询条件的转换采用的解析器通过 ANTLR 4 实现；
- 支持形如 `sys_user__email contains '163.com' && sys_user__created_time > '2020-10-08 10:00:00'` 的查询条件表达式；
- 查询条件表达式中的关系操作符类型包括：`=`, `!=`, `>`, `>=`, `<`, `<=`, `contains`, `like` ，操作符右侧支持字段类型；
- 支持形如 `sys_user__username ASC,sys_user__realname DESC` 的查询排序表达式。

## 其它优化点

- 通过划分多模块强制分层，避免循环依赖；
- 使用 `MapStruct` 进行 `DO` 到 `DTO` 的映射转换，避免手写映射代码导致的冗余；
- `Entity` 中的枚举类型属性与数据库字段之间的转换逻辑由抽象的泛型公共父类 `BaseEnumConstAttributeConverter` 实现，避免每个枚举类都手写转换代码导致的冗余；
- 控制器层定义抽象的泛型公共父类 `AbstractBaseController`，封装公共接口方法，子类只需要在类层面定义相应的 `RequestMapping` 即可，无需重复定义这些公共接口方法。
- 服务层定义抽象的泛型公共父类 `AbstractBaseService`，封装公共的通用操作，提高代码重用度；
- `DTO` 对象除了进行 `JSR-380` 标准验证外，通过自定义注解 `@Unique` 实现数据库中属性值是否唯一的验证（对应数据库字段的 unique 约束），同时支持将 `DTO` 验证异常自动转换成友好的错误消息；
- 执行删除操作时在服务层公共父类中进行外键完整性约束检查并自动生成友好的异常提示信息，避免在子类中重复进行检查；
- 自动将需要持久化的枚举类型转换成字典数据，以供前端调用，无需再往字典表插数据。

## 开发环境运行步骤

### 数据库安装和配置

安装MySQL 5.7，并做如下配置：

```ini
character-set-server=utf8mb4
collation-server=utf8mb4_unicode_ci
lower-case-table-names=1
sql-mode=STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION
```

### 创建数据库和用户

```mysql
DROP DATABASE IF EXISTS spring_rest;
CREATE DATABASE spring_rest CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

DROP USER IF EXISTS 'sr'@'localhost';
DROP USER IF EXISTS 'sr'@'%';
CREATE USER 'sr'@'localhost' IDENTIFIED BY 'sr123';
CREATE USER 'sr'@'%' IDENTIFIED BY 'sr123';
GRANT ALL PRIVILEGES ON spring_rest.* to 'sr'@'localhost';
GRANT ALL PRIVILEGES ON spring_rest.* to 'sr'@'%';
flush PRIVILEGES;
```

### 检出代码并运行

```bash
$ git clone https://github.com/bianyun1981/spring-rest-backend.git spring-rest
$ cd spring-rest
$ ./gradlew bootRun
```

### 打开API文档调试界面

控制台日志打印出 `Tomcat started on port(s): 8888 (http) with context path '/api/v1'` 后，表明已经启动完成。在浏览器地址栏中输入 http://localhost:8888/api/v1/doc.html ， 然后回车进入 API 文档调试界面。

### 可能需要修改的应用配置

配置文件路径：sr-application/src/main/resources/[application.yml, application-dev.yml, application-staging.yml]，`application.yml` 为所有环境公共配置，`application-dev.yml` 为开发环境独有配置。

```yaml
spring:
  datasource:
    url: jdbc:mysql://127.0.0.1:3306/spring_rest?characterEncoding=UTF-8&[...太长省略]  // 数据库名和端口
    username: sr                         // 用户名
    password: sr123                      // 密码
    initialization-mode: always          // 如果不希望应用重启后数据被重置为 data.sql中的数据，请改为 never
  jpa:
    hibernate:
      ddl-auto: create                   // 每次启动时清空数据库原有的所有表并重新创建数据库表，可改为 update
server:
  port: 8888                             // 此端口若有改动，管理台前端的接口调用地址也要做相应修改
cors:
  allowed-origin: http://localhost:9527  // 此端口为管理台前端默认监听端口，如有变动，请两边同步修改。
```

**注：服务端部署好后，可以继续部署配套的管理台前端项目 [Spring-Rest Admin](https://github.com/bianyun1981/spring-rest-admin)** 