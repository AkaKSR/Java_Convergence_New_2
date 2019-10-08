-- testuser

 DROP TABLE TEMP;

CREATE TABLE TEMP (
 EMP_ID      NUMBER NOT NULL PRIMARY KEY,
 EMP_NAME    VARCHAR2(10) NOT NULL,
 BIRTH_DATE  DATE,
 DEPT_CODE   VARCHAR2(06) NOT NULL,
 EMP_TYPE    VARCHAR2(06),
 USE_YN      VARCHAR2(01) NOT NULL,
 TEL         VARCHAR2(15),
 HOBBY       VARCHAR2(30),
 SALARY      NUMBER,
 LEV         VARCHAR2(06)
);

CREATE TABLE TDEPT (
 DEPT_CODE   VARCHAR2(06) NOT NULL PRIMARY KEY,
 DEPT_NAME   VARCHAR2(20) NOT NULL,
 PARENT_DEPT VARCHAR2(06) NOT NULL,
 USE_YN      VARCHAR2(01) NOT NULL,
 AREA        VARCHAR2(10),
 BOSS_ID     NUMBER
);

INSERT INTO TEMP VALUES (19970101,'김길동',TO_DATE('19740125','YYYYMMDD'),'AA0001','정규','Y','','등산',100000000,'부장');
INSERT INTO TEMP VALUES (19960101,'홍길동',TO_DATE('19730322','YYYYMMDD'),'AB0001','정규','Y','','낚시',72000000,'과장');
INSERT INTO TEMP VALUES (19970201,'박문수',TO_DATE('19750415','YYYYMMDD'),'AC0001','정규','Y','','바둑',50000000,'과장');
INSERT INTO TEMP VALUES (19930331,'정도령',TO_DATE('19760525','YYYYMMDD'),'BA0001','정규','Y','','노래',70000000,'차장');
INSERT INTO TEMP VALUES (19950303,'이순신',TO_DATE('19730615','YYYYMMDD'),'BB0001','정규','Y','','',56000000,'대리');
INSERT INTO TEMP VALUES (19966102,'지문덕',TO_DATE('19720705','YYYYMMDD'),'BC0001','정규','Y','','',45000000,'과장');
INSERT INTO TEMP VALUES (19930402,'강감찬',TO_DATE('19720815','YYYYMMDD'),'CA0001','정규','Y','','',64000000,'차장');
INSERT INTO TEMP VALUES (19960303,'설까치',TO_DATE('19710925','YYYYMMDD'),'CB0001','정규','Y','','',35000000,'사원');
INSERT INTO TEMP VALUES (19970112,'연흥부',TO_DATE('19761105','YYYYMMDD'),'CC0001','정규','Y','','',45000000,'대리');
INSERT INTO TEMP VALUES (19960212,'배뱅이',TO_DATE('19721215','YYYYMMDD'),'CD0001','정규','Y','','',39000000,'과장');
--
INSERT INTO TDEPT VALUES ('AA0001','경영지원','AA0001','Y','서울',19940101);
INSERT INTO TDEPT VALUES ('AB0001','재무','AA0001','Y','서울',19960101);
INSERT INTO TDEPT VALUES ('AC0001','총무','AA0001','Y','서울',19970201);
INSERT INTO TDEPT VALUES ('BA0001','기술지원','BA0001','Y','인천',19930301);
INSERT INTO TDEPT VALUES ('BB0001','H/W지원','BA0001','Y','인천',19950303);
INSERT INTO TDEPT VALUES ('BC0001','S/W지원','BA0001','Y','인천',19966102);
INSERT INTO TDEPT VALUES ('CA0001','영업','CA0001','Y','본사',19930402);
INSERT INTO TDEPT VALUES ('CB0001','영업기획','CA0001','Y','본사',19950103);
INSERT INTO TDEPT VALUES ('CC0001','영업1','CA0001','Y','본사',19970112);
INSERT INTO TDEPT VALUES ('CD0001','영업2','CA0001','Y','본사',19960212);
--
COMMIT;

-- 테이블 확인
SELECT * FROM tab;

-- 테이블 내용 확인
SELECT * FROM temp;
SELECT * FROM tdept;

-- 특정 필드만 보기
SELECT
	T.EMP_ID, T.EMP_NAME
FROM
	temp T;
	
-- 오라클은 FROM 절을 생략할 수 없다.
-- 그래서 임시테이블인 dual을 지원한다.
-- SELECT sysdate;
SELECT SYSDATE 날짜, 5*123 계산 FROM dual;

-- 연봉을 이용한 월급여 계산
SELECT
	t.EMP_NAME 이름, t.SALARY/18 "홀수달 월급", t.SALARY/18*2 "짝수달 월급"
FROM
	temp t;

SELECT
	t.EMP_NAME 이름, ROUND(t.SALARY/18) "홀수달 월급", ROUND(t.SALARY/18*2) "짝수달 월급"
FROM
	temp t;

SELECT
	t.EMP_NAME 이름, ROUND(t.SALARY/18,2) "홀수달 월급", ROUND(t.SALARY/18*2,2) "짝수달 월급"
FROM
	temp t;

SELECT
	t.EMP_NAME 이름, ROUND(t.SALARY/18,-2) "홀수달 월급", ROUND(t.SALARY/18*2,-2) "짝수달 월급"
FROM
	temp t;

-- temp 테이블에서  hobby가 null이 아닌 사람
-- null의 비교는 is null 또는 is not null로 해야 한다.
SELECT * FROM temp WHERE HOBBY IS NOT NULL;
SELECT * FROM temp WHERE HOBBY IS NULL;

-- NVL 함수 : NVL(값, 값이 null인 경우 대체될 값)
SELECT EMP_NAME, HOBBY FROM temp;
SELECT EMP_NAME, NVL(HOBBY,'취미없음') FROM temp;
-- NVL2 함수 : NVL2(값, 값이 null이 아닌 경우 대체될 값, 값이 null인 경우 대체될 값)
SELECT EMP_NAME, NVL2(HOBBY, HOBBY, '취미없음') FROM temp;

-- 취미가 없는 사람을 '등산'으로 치환한뒤 취미가 '등산'인 목록 보기
SELECT EMP_NAME, NVL2(HOBBY, HOBBY, '등산') FROM temp WHERE NVL(HOBBY, '등산') = '등산';

-- 별칭 주기
-- 필드명 [AS] 별명, ... : 별명에 공백이 있으면 ""로 감싸줘야 한다.
-- 테이블명 별명
SELECT
	EMP_NAME, DEPT_CODE, DEPT_NAME
FROM
	TEMP,
	TDEPT
WHERE
	TEMP.DEPT_CODE = TDEPT.DEPT_CODE;
-- 위의 명령은 에러이다. DEPT_CODE의 소속이 불명확해서이다.
SELECT
	EMP_NAME, TEMP.DEPT_CODE, DEPT_NAME
FROM
	TEMP,
	TDEPT
WHERE
	TEMP.DEPT_CODE = TDEPT.DEPT_CODE;
-- "테이블명.필드명"으로 명확하게 소속을 밝히면 된다.
SELECT
	EMP_NAME, A.DEPT_CODE, DEPT_NAME
FROM
	TEMP A,
	TDEPT B
WHERE
	A.DEPT_CODE = B.DEPT_CODE;
-- 테이블명에 별칭을 주면 "별칭.필드명"으로 접근 가능하다.
SELECT
	EMP_NAME AS 이름, A.DEPT_CODE "부서 코드", DEPT_NAME "부서 이름"
FROM
	TEMP A,
	TDEPT B
WHERE
	A.DEPT_CODE = B.DEPT_CODE;
-- 필드명에도 별칭이 가능하다. AS는 생략 가능

-- ||(문자열 연결 연산자)
SELECT
	EMP_NAME, SALARY,
	EMP_NAME || '씨의 연봉은 ' || SALARY || '원 입니다.' "메세지"
FROM
	temp;

SELECT
	EMP_NAME, SALARY,
	'"' || EMP_NAME || '"씨의 연봉은 ' || SALARY || '원 입니다.' "메세지"
FROM
	temp;
-- 이름을 ""안에 넣어서 출력하기

SELECT
	EMP_NAME, SALARY,
	'''' || EMP_NAME || '''' || '씨의 연봉은 ' || SALARY || '원 입니다.' "메세지"
FROM
	temp;
-- 이름을 ''안에 넣어서 출력하기
-- 작은 따옴표 자첼르 출력하려면 연달아 4개를 입력한다.
