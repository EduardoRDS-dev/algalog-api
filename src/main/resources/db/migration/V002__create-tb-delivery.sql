create table tb_delivery(
    id bigint not null auto_increment,
    client_id bigint not null,
    tax decimal(10,2) not null,
    status varchar(20) not null,
    request_date datetime not null,
    delivery_date datetime,

    remittee_name varchar(30) not null,
    remittee_street varchar(255) not null,
    remittee_number varchar(30) not null,
    remittee_complement varchar(60),
    remittee_district varchar(30) not null,

    primary key(id)
);

alter table tb_delivery add constraint fk_delivery_client_id foreign key (client_id) references tb_client (id);