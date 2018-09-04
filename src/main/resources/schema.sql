CREATE TABLE JOURNAL (
    ID int NOT NULL AUTO_INCREMENT primary key,
    TITLE varchar(255) NOT NULL,
    SUMMARY varchar(255),
    CREATED timestamp
);