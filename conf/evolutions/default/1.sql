# --- !Ups
  create table mydata (
    id int(10) not null auto_increment, 
    name varchar(100),
    message varchar(100),
    createdAt  varchar(50),
    primary key(id));
# --- !Downs
  drop table mydata;
