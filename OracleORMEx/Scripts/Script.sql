-- jspuser

-- 오라클은 auto_increment가 없어서 번호를 만들어 주는 객체를 사용한다.
CREATE SEQUENCE test_seq;

SELECT test_seq.nextval FROM dual;
SELECT test_seq.currval FROM dual;

-- 오라클용 메모 테이블 작성
CREATE SEQUENCE memo_idx_seq; -- SEQUENCE 작성
CREATE TABLE memo(
	idx NUMBER PRIMARY KEY,
	name varchar2(20) NOT NULL,
	password varchar2(20) NOT NULL,
	memo varchar2(200) NOT NULL,
	regDate timestamp DEFAULT SYSDATE,
	ip varchar2(20) NOT NULL
);

-- INSERT 예제
INSERT INTO memo VALUES (memo_idx_seq.nextval, '주인장', '1234', 'ㅁㄴㅇㄹ', SYSDATE, 'localhost');

SELECT * FROM memo;
