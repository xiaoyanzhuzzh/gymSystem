use user_management;
drop table users;
drop table admin;
create table users (
  id int(11) NOT NULL auto_increment,
  name varchar(30) NOT NULL,
  gender varchar(10) NOT NULL,
  age int(6) NOT NULL,
  email varchar(40) default NULL,
  password varchar(60) NOT NULL ,
  PRIMARY KEY  (id)
);


insert into users values(null,'张志慧','female',22,'zhzhang1993@outlook.com', '123456');

create table admin (
  id int(11) NOT NULL auto_increment,
  name varchar(30) NOT NULL,
  password varchar(50) NOT NULL,
  PRIMARY KEY  (id)
);

insert into admin values(null,'张志慧','123456');

