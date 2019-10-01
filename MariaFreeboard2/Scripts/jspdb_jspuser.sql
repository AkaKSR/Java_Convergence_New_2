select * from freeboard;

desc freeboard;

insert into freeboard (name,password,subject,content,ip)
values ("asdf",aes_encrypt('1234',sha2('1111',512)),"asdf","asdf","localhost");

select '1234', password('1234'), length(password('1234'));
select '1234', sha('1234'), length(sha('1234'));
select '1234', md5('1234'), length(md5('1234'));
select '1234', AES_ENCRYPT('1234',SHA2('1111',512)); -- 암호화
select '1234', AES_DECRYPT(AES_ENCRYPT('1234',SHA2('1111',512)), SHA2('1111',512)); -- 복호화
select '1234', AES_DECRYPT(AES_ENCRYPT('1234',SHA2('11',512)), SHA2('1111',512)); -- 복호화

-- 댓글 테이블 생성
-- idx
-- ref
-- name
-- password
-- content
-- regDate
create table free_comment(
	idx int primary key auto_increment,
	ref int not null,
	name varchar(50) not null,
	password varchar(50) not null,
	content text not null,
	regDate timestamp default now()
);

desc comment;

show tables;

drop table comment;

-- 원본 글번호를 확인
select * from freeboard order by idx desc;

-- 댓글 여러개를 수동으로 넣어보자
insert into free_comment (ref,name,password,content) values (22,'asdf','1234','jsjsjsjsjsjsjs');
insert into free_comment (ref,name,password,content) values (21,'asdf','1234','jsjsjsjsjsjsjs');
insert into free_comment (ref,name,password,content) values (20,'asdf','1234','jsjsjsjsjsjsjs');

select * from free_comment;