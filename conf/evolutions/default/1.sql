# --- !Ups
  create table mydata (
    id int(10) not null auto_increment, 
    name varchar(100),
    mail varchar(100),
    tel  varchar(50),
    primary key(id));
# --- !Downs
  drop table mydata;
