CREATE TABLE t_user(
id binary (16) NOT NULL,
first_name varchar(300) NOT NULL,
last_name varchar(300) NOT NULL,
email varchar(300) NOT NULL UNIQUE,
password varchar(300),
primary key (id)
);


CREATE TABLE role(
id binary(16) NOT NULL ,
name varchar(50) NOT NULL,
primary key (id)
);

INSERT INTO role (id,name) values
(UUID_TO_BIN(UUID()),"ADMIN"),
(UUID_TO_BIN(UUID()), "USER")
;

CREATE TABLE role_users(
    roles_id binary(16) NOT NULL,
    users_id binary(16) NOT NULL,
    PRIMARY KEY (roles_id, users_id),
    FOREIGN KEY (roles_id) REFERENCES role(id) ON DELETE CASCADE,
    FOREIGN KEY(users_id) REFERENCES t_user(id) ON DELETE CASCADE
);

INSERT INTO t_user(id, first_name,last_name,email,password) values
(UUID_TO_BIN(UUID()), "admin", "adminLastName", "admin@mail", "$2a$12$xLpK3hAhnHWtvRUHGxIJjuRxMbJ0N1cJHYcmzZPblhrEe.dqMVuFy"),
(UUID_TO_BIN(UUID()), "user", "userLastName", "user@mail", "$2a$12$hR5ICtBWiPaHz7ZZ1uj9.OCgUZfwPmeW8MhBWWNUuayIhMsU/yrqS")
;


CREATE TABLE producer(
id binary(16) NOT NULL,
name varchar(300) NOT NULL,
primary key (id)
);
INSERT INTO producer (id, name) values
(UUID_TO_BIN(UUID()),"Producer A"),
(UUID_TO_BIN(UUID()),"Producer B")
;

CREATE TABLE product(
    id binary(16) NOT NULL,
    name varchar(300) NOT NULL,
    price DECIMAL(8,2) NOT NULL,
    producer_id binary(16) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (producer_id) REFERENCES producer(id) ON DELETE CASCADE
);
