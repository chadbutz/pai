create table conversation (
    id int NOT NULL AUTO_INCREMENT,
    name VARCHAR(50),
    email VARCHAR(50),
    PRIMARY KEY (id)
);

INSERT INTO conversation (name, email) VALUES ('Hello', 'chad@chad.com');
