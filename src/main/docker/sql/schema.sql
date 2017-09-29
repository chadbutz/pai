create database pai;
use pai;

CREATE TABLE conversation
(
id INTEGER AUTO_INCREMENT,
convo TEXT,
PRIMARY KEY (id)
) COMMENT='This represents a conversation with a user';
