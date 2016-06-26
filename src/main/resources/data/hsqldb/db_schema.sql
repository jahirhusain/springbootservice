DROP TABLE UserInfo IF EXISTS;
DROP TABLE AddressInfo IF EXISTS;

CREATE TABLE UserInfo(
   USERID BIGINT GENERATED BY DEFAULT AS IDENTITY (START WITH 4, INCREMENT BY 1) NOT NULL ,
   USERNAME VARCHAR(100) NOT NULL,
   EMAILADDRESS VARCHAR(40) NOT NULL,
   AGE INT NOT NULL,
   MOBILE VARCHAR(20) NOT NULL,
   CREATEDDATE DATE,
   PRIMARY KEY (USERID)
);

CREATE TABLE AddressInfo(
   ADDRESSID BIGINT GENERATED BY DEFAULT AS IDENTITY (START WITH 4, INCREMENT BY 1) NOT NULL ,
   USERID BIGINT NOT NULL,
   STREET VARCHAR(100) NOT NULL,
   CITY VARCHAR(40) NOT NULL,
   PIN INT NOT NULL,
   CREATEDDATE DATE,
   PRIMARY KEY (ADDRESSID),
   CONSTRAINT FKUSERID FOREIGN KEY (USERID) REFERENCES UserInfo(USERID)
);


