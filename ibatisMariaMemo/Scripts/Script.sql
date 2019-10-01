show tables;
select * from memo;

insert into memo (name,password,content,regDate,ip) values ('asdf','1234','asdf',now(),'127.0.0.1');

commit;