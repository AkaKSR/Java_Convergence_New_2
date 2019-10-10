-- 사용자 객체를 확인해 보겠다.
SELECT
	OBJECT_name, OBJECT_type
FROM
	USER_OBJECTS;
-- 테이블을 만들면 키 필드를 기준으로 인덱스가 자동 생성 된다.

-- 자신이 가지고 있는 모든 객체를 삭제하는 명령을 만들어주는 쿼리를 작성해라.
SELECT 'drop ' || OBJECT_type || ' ' || OBJECT_name || ';' FROM USER_OBJECTS;

-- 사번의 앞자리 4자리만 잘라서 그 값이 1998인 사원의 사번과 이름을 출력하는 쿼리
-- SUBSTR함수 : SUBSTR(문자열, 시작위치, 길이)
SELECT
	EMP_ID, EMP_NAME, SUBSTR(EMP_ID,0,4)
FROM
	TEMP
WHERE
	SUBSTR(EMP_ID,0,4)='1997';

SELECT
	EMP_ID, EMP_NAME, SUBSTR(EMP_ID,5,2)
FROM
	TEMP
WHERE
	SUBSTR(EMP_ID,5,2)='02';

-- 성명과 생년월일을 문자로 만들어 작은 따옴표안에 표시하는 쿼리를 작성하시오.
SELECT
	'''' || EMP_NAME || BIRTH_DATE || '''', BIRTH_DATE 
FROM
	TEMP;

-- 생년월일을 0000년 00월 00일로 출력하는 쿼리를 작성하시오
SELECT
	EMP_NAME,
	(TO_CHAR(BIRTH_DATE, 'yyyy') || '년 ' || TO_CHAR(BIRTH_DATE, 'mm') || '월 ' || TO_CHAR(BIRTH_DATE, 'dd') || '일') 생년월일
FROM
	TEMP;


SELECT TO_CHAR(SYSDATE, 'yyyy') FROM dual;
SELECT TO_CHAR(SYSDATE, 'mm') FROM dual;
SELECT TO_CHAR(SYSDATE, 'dd') FROM dual;
SELECT TO_CHAR(SYSDATE, 'yyyy') FROM dual;

-- 정렬
SELECT
	LEV, EMP_ID, EMP_NAME
FROM
	TEMP
ORDER BY 1 DESC, 2 ASC; -- 정렬방식은 ASC(기본값)||DESC를 지정하고 필드마다 써야 한다.,

SELECT
	LEV||'('||EMP_NAME||')', EMP_ID
FROM
	TEMP
ORDER BY 1 DESC; -- 필드명에 가공을 했을 경우에는 순서를 기술 가능하다.

-- 이름이 'ㄱ'으로 시작하는 사원의 사번과 이름을 출력하는 쿼리를 작성하시오(BETWEEN 사용)
SELECT
	EMP_ID, EMP_NAME
FROM
	TEMP
WHERE
	EMP_NAME BETWEEN '김' AND '홍';

SELECT EMP_ID, EMP_NAME FROM TEMP;

-- IN 연산자 : OR 대치
-- 김길동 또는 홍길동 사원의 사번과 이름을 출력하는 쿼리를 작성하시오
SELECT
	EMP_ID, EMP_NAME
FROM
	TEMP
WHERE
	EMP_NAME='홍길동' OR EMP_NAME='김길동';

SELECT
	EMP_ID, EMP_NAME
FROM
	TEMP
WHERE
	EMP_NAME NOT IN ('홍길동', '김길동');

-- LEV 별로 인원, 최고연봉, 최소 연봉, 연봉합계, 연봉 평균을 출력하시오.
SELECT
	LEV, COUNT(LEV), MAX(SALARY) , MIN(SALARY), SUM(SALARY), AVG(SALARY)
FROM
	TEMP
GROUP BY
	LEV;

-- 모든 직급을 보고 싶다.
SELECT
	LEV
FROM
	TEMP; -- 중복 포함

SELECT
	LEV
FROM
	TEMP;
GROUP BY
	LEV; -- 중복제거(GROUP BY)

SELECT DISTINCT
	LEV
FROM
	TEMP; -- 중복제거(DISTINCT)

-- tdept 자료를 이용하여 부서장 중 사번이 가장 빠른 사람이 근무하는 부서를 알고싶다.
SELECT * FROM TDEPT;
-- BOSS_ID의 최소값을 구해서 그 값으로 조건을 걸어야 한다.
SELECT
	DEPT_NAME
FROM
	TDEPT
WHERE
	BOSS_ID = (SELECT MIN(BOSS_ID) FROM TDEPT);
-- 서브쿼리를 사용할 수 없다면 어떻게 해야하는가?
SELECT
	MIN(BOSS_ID||DEPT_NAME), SUBSTR(MIN(BOSS_ID||DEPT_NAME),9)
FROM
	TDEPT;

-- tdept 자료를 이용하여 AREA별로 최소 BOSS_ID를 구해 내림차순으로 정렬
SELECT
	MIN(BOSS_ID)
FROM
	TDEPT
GROUP BY
	AREA;

-- 직급별로 연봉 평균을 구한 상태에서 평균 연봉이 5천만원 이상인 경우의 직급과 연봉을 조회
SELECT
	LEV, AVG(SALARY)
FROM
	TEMP
GROUP BY
	LEV;

SELECT
	LEV, AVG(SALARY)
FROM
	TEMP
GROUP BY
	LEV
WHERE
	avg(SALARY)>=50000000; -- 에러
	
SELECT
	LEV, AVG(SALARY)
FROM
	TEMP
GROUP BY
	LEV
HAVING
	avg(SALARY)>=50000000;

SELECT
	LEV, AVG(SALARY)
FROM
	TEMP
WHERE
	avg(SALARY)>=50000000; -- 에러

-- 직급별 사번이 제일 늦은 사람의 사번이 1997로 시작하는 사원 출력
SELECT
	LEV, MIN(DEPT_CODE)
FROM
	TEMP;
GROUP BY
	
HAVING
	