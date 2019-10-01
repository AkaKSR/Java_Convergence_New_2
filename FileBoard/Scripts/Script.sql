-- 게시판 테이블 작성
create table fileboard(
	idx int primary key auto_increment,
	name varchar(50) not null,
	password varchar(50) not null,
	subject varchar(200) not null,
	content text not null,
	regDate timestamp default now(),
	ip varchar(20) not null
);
-- 첨부파일 테이블
create table fileboard_file(
	idx int primary key auto_increment,
	ref int not null,
	ofile varchar(50) not null,
	sfile varchar(50) not null
);

select password(1234), length(password('1234'));

show tables;

select * from fileboard;
select * from fileboard_file;

-- 마지막으로 증가한 auto_increment 값 얻기
select max(idx) from fileboard;
select last_insert_id();

-- 암호 일치 여부 확인
-- select count(*) from 테이블명 where password=password('1234') and idx=?a -- 일치하면 1 아닐경우 0
