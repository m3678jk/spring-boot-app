CREATE TABLE t_user(
id bigint NOT NULL AUTO_INCREMENT,
first_name varchar(300) NOT NULL,
last_name varchar(300) NOT NULL,
email varchar(300) NOT NULL,
password varchar(300),
primary key (id)
);


CREATE TABLE role(
id bigint NOT NULL AUTO_INCREMENT,
name varchar(50) NOT NULL,
primary key (id)
);

INSERT INTO role (name) values
("ADMIN"),
("USER")
;

CREATE TABLE role_users(
    roles_id bigint NOT NULL,
    users_id bigint NOT NULL,
    PRIMARY KEY (roles_id, users_id),
    FOREIGN KEY (roles_id) REFERENCES role(id) ON DELETE CASCADE,
    FOREIGN KEY(users_id) REFERENCES t_user(id) ON DELETE CASCADE
);

INSERT INTO t_user(first_name,last_name,email,password) values
("name", "password:111", "test@mail", "$2a$12$xLpK3hAhnHWtvRUHGxIJjuRxMbJ0N1cJHYcmzZPblhrEe.dqMVuFy")
;

INSERT INTO role_users values
(1,1)
;

CREATE TABLE producer(
id bigint NOT NULL AUTO_INCREMENT,
name varchar(300) NOT NULL,
primary key (id)
);
INSERT INTO producer (name) values
("forex"),
("codex")
;

CREATE TABLE product(
    id bigint NOT NULL AUTO_INCREMENT,
    name varchar(300) NOT NULL,
    price DECIMAL(4,2) NOT NULL,
    producer_id bigint NOT NULL ,
    PRIMARY KEY (id),
    FOREIGN KEY (producer_id) REFERENCES producer(id) ON DELETE CASCADE

);


INSERT INTO product (name, price, producer_id) values
('Aba', 13.12, 1),
('Bbb', 14.22, 1)
;



