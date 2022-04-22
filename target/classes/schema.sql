DROP TABLE IF EXISTS Student;

CREATE TABLE Student (
                               id INT PRIMARY KEY,
                               name VARCHAR(250) NOT NULL,
                               age INT(250) NOT NULL,
                               email VARCHAR(250) DEFAULT NULL
);