show tables;

create table memo(
	idx int primary key auto_increment,
	name varchar(50) not null,
	password varchar(50) not null,
	content text not null,
	regDate timestamp not null,
	ip varchar(20) not null
);

drop table memo;

desc memo;

select * from memo;

insert into memo (name,password,content,regDate,ip)
values ('1234','1234','1234',now(),'localhost');

select * from memo where idx=2;