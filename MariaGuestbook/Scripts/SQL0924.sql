-- guestbook 테이블 생성
create table guestbook(
	idx int primary key auto_increment,
	name varchar(50) not null,
	password varchar(50) not null,
	content text not null,
	regDate timestamp default now(),
	ip varchar(20) not null
);

-- guestbook 속성 확인
desc guestbook;

-- guestbook 테이블 조회
select * from guestbook;

-- guestbook 테이블에 데이터 삽입
insert into guestbook (name,password,content,regDate,ip)
values ('나그네','1234','블라블라',now(),'localhost');
