create table tb_client(
id bigint not null auto_increment,
name varchar(60) not null,
email varchar(150) not null,
phone varchar(20) not null,
primary key (id)
);