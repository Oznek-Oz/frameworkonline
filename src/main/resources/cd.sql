CREATE DATABASE `convert`;
USE `convert`;
    CREATE TABLE `conversion`(
        ID integer primary key not null AUTO_INCREMENT,
        deviseSource varchar(20),
        deviseCible varchar(20),
        montantSource double,
        montantConverti double,
        taux double,
        dateConversion date
    );
    CREATE TABLE `devise` (
        code varchar(10) primary key not null ,
        nom varchar(50)
    );
