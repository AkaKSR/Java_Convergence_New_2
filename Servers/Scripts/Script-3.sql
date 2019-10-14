-- 2장 함수
-- 1. 문자열 함수
-- instr(문자열, 찾는문자) : 위치 반환
SELECT INSTR('qwerty','r') FROM dual; -- 4
SELECT INSTR('qwerty','a') FROM dual; -- 없으면 0
SELECT INSTR('qwerty우리나라12345좋은나라','나') FROM dual; -- 9 한글도 1글자로 인식
-- instrb(문자열, 찾는문자) : 첫번째 위치 반환(한글 3byte)
SELECT INSTRB('qwerty','r') FROM dual; -- 4
SELECT INSTRB('qwerty','a') FROM dual; -- 없으면 0
SELECT INSTRB('qwerty우리나라12345좋은나라','나') FROM dual; -- 13 한글 3byte
-- (문자열, 찾는문자, 시작위치, 몇번째)
SELECT INSTR('qwerty우리나라12345좋은나라','나', 1, 2) FROM dual;
SELECT INSTRB('qwerty우리나라12345좋은나라','나', 1, 2) FROM dual;

-- substr(문자열, 시작위치, 길이)
SELECT SUBSTR('qwerty우리나라12345좋은나라', 1, 2) FROM dual;
SELECT SUBSTR('qwerty우리나라12345좋은나라', 1, 5) FROM dual;
SELECT SUBSTR('qwerty우리나라12345좋은나라', 10, 5) FROM dual;
SELECT SUBSTRB('qwerty우리나라12345좋은나라', 1, 2) FROM dual;
SELECT SUBSTRB('qwerty우리나라12345좋은나라', 1, 5) FROM dual;
SELECT SUBSTRB('qwerty우리나라12345좋은나라', 10, 5) FROM dual;
-- 뒤에서 4글자만
SELECT SUBSTR('qwerty우리나라12345좋은나라', -4, 4) FROM dual;
SELECT SUBSTRB('qwerty우리나라12345좋은나라', -12, 12) FROM dual;

-- length(문자열) : 길이
-- temp테이블의 이름에서 성을 제외한 이름만!!!
SELECT * FROM temp;
SELECT
	SUBSTR(EMP_NAME,2,2), LENGTH(EMP_NAME), LENGTHB(EMP_NAME)
FROM
	temp;
-- LPAD(문자열,길이,채울문자) : 왼쪽에 채워준다.
SELECT
	LPAD(EMP_NAME,10,'*')
FROM
	temp;
-- RPAD(문자열,길이,채울문자) : 오른쪽에 채워준다.
SELECT
	RPAD(EMP_NAME,10,'*')
FROM
	temp;

SELECT
	LPAD(RPAD(EMP_NAME,8,'*'),10,'*')
FROM
	temp;
	
-- tdept 테이블의 부서이름을 10자리에 맞추어 읽어오는데 왼쪽의 공란에 순서대로 번호를 붙여서 가져와라
SELECT
	LPAD(DEPT_NAME,10,'1234567890')
FROM
	TDEPT;
	
-- tdept의 부서이름을 10자리에 맞추어 읽어오는데 뒤에 남은 자리에 자신의 자릿수에 해당하는 숫자로 채워라
-- 경영지원90
-- 재무567890
SELECT
	RPAD(DEPT_NAME,10,SUBSTR('1234567890',LENGTH(DEPT_NAME)+1))
FROM
	TDEPT;
	
-- replace(문자열, 찾는문자, 바꿀문자) : 치환
SELECT
	REPLACE('qwerty우리나라12345좋은나라','나라','국가')
FROM
	dual;
-- 123,567,890.123을 ,와 .을 제외하고 출력하시오
SELECT
	REPLACE(REPLACE('123,567,890.123',',',''),'.','')
FROM
	dual;

-- CHR과 ASCII : 아스키코드값, 코드값에 대한 문자
SELECT
	ASCII('A'), ASCII('a'), CHR(65), CHR(97)
FROM
	dual;

-- 다음과 같이 출력하는 쿼리를 만드시오
-- 사번
-- 이름
SELECT
	EMP_ID, EMP_NAME
FROM
	TEMP;
SELECT
	EMP_ID || chr(13) || EMP_NAME
FROM
	TEMP;

-- 부서이름에 한글이 몇글자가 있을까요?
SELECT
	DEPT_NAME, (LENGTHB(DEPT_NAME)-LENGTH(DEPT_NAME))/2
FROM
	TDEPT;

-- tdept의 부서이름을 10자리에 맞추어 읽어 오는데 뒤에 남은 자리에 자신의 자릿수에 해당하는 숫자로 채워라
-- 경영지원90
-- 재무567890
SELECT
	RPAD(DEPT_NAME,10,SUBSTR('1234567890',LENGTH(DEPT_NAME)+1)) Before,
	RPAD(DEPT_NAME,10,SUBSTR('1234567890',LENGTH(DEPT_NAME)+1+((LENGTHB(DEPT_NAME)-LENGTH(DEPT_NAME))/2))) After
FROM
	TDEPT;

-- ================================================================== --
-- 숫자 함수
-- ================================================================== --
-- 1. round : 반올림, 양수는 소수이하 음수는 양의 자리
--    round(숫자,자릿수)
SELECT
	ROUND(4567.8976,1),ROUND(4567.8976,2),ROUND(4567.8976,3),
	ROUND(4567.8976,-1),ROUND(4567.8976,-2),ROUND(4567.8976,-3)
FROM
	dual;

-- 2. trunc : 버림
SELECT
	TRUNC(4567.8976,1),TRUNC(4567.8976,2),TRUNC(4567.8976,3),
	TRUNC(4567.8976,-1),TRUNC(4567.8976,-2),TRUNC(4567.8976,-3)
FROM
	dual;

-- 3. sign : 부호를 알려준다. -1, 0, 1
SELECT
	SIGN(4567.8976),SIGN(0),SIGN(-4567)
FROM
	dual;

-- 4. ceil : 넘는 최소 정수
SELECT
	CEIL(2),CEIL(2.2),CEIL(-2),CEIL(-2.2)
FROM
	dual;

-- 5. floor : 넘지 않는 최대 정수
SELECT
	FLOOR(2),FLOOR(2.2),FLOOR(-2),FLOOR(-2.2)
FROM
	dual;

-- 6. mod : 나머지
SELECT
	MOD(1,3),MOD(2,3),MOD(-3,3),MOD(-1,3),MOD(-2,3),MOD(-3,3)
FROM
	dual;
	
-- rownum이란? 오라클에는 숨겨진 필드가 존재한다.
-- ROWid란? 검색을 빠르게하기 위해서 붙여준 ID
SELECT
	rownum, ROWid, EMP_NAME
FROM
	temp;

SELECT
	rownum, ROWid, EMP_NAME
FROM
	temp
ORDER BY
	EMP_NAME DESC;

-- select를 이용한 테이블 생성
-- CREATE TABLE 테이블명 AS SELECT명령
CREATE TABLE mod_test AS SELECT rownum NO FROM temp;
SELECT * FROM mod_test;

CREATE TABLE SALARY_test AS SELECT EMP_ID, EMP_NAME, SALARY FROM temp;
SELECT * FROM SALARY_test;

-- 7. power함수 : 제곱을 구해준다.
SELECT NO, MOD(NO,3), mod(NO-1,3)+1, POWER(2,NO) FROM mod_test;

-- 월급의 1000원 미만을 기부하기로 했다.
-- 기부액을 구하라
-- 월급은 년봉/13으로 한다.

SELECT
	ROUND(SALARY/13) 월급, SALARY 연봉, ROUND(SALARY/13)-TRUNC(SALARY/13,-3) 기부액
FROM
	TEMP;

SELECT
	SUM(ROUND(SALARY/13)-TRUNC(SALARY/13,-3)) 월간기부총액, (SUM(ROUND(SALARY/13)-TRUNC(SALARY/13,-3)))*12 연간기부총액
FROM
	TEMP;