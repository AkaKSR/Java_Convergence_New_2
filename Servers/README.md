# 191010

### Oracle 사용 코드

1. 사용자 객체를 확인하기

> ````sql
> SELECT
> 	OBJECT_name, OBJECT_type
> FROM
> 	USER_OBJECTS;
> ````
>
> > 테이블을 만들면 키 필드를 기준으로 인덱스가 자동 생성 된다.

2. 자신이 가지고 있는 모든 객체를 삭제하는 명령을 만들어주는 쿼리를 작성하기

> **SELECT 'drop ' || OBJECT_type || ' ' || OBJECT_name || ';' FROM USER_OBJECTS;**

3. 사번의 앞자리 4자리만 잘라서 그 값이 1997인 사원의 사번과 이름을 출력하는 쿼리

> SUBSTR 함수 : SUBSTR(문자열, 시작위치, 길이)
>
> ````sql
> SELECT
> 	EMP_ID, EMP_NAME, SUBSTR(EMP_ID,0,4)
> FROM
> 	TEMP
> WHERE
> 	SUBSTR(EMP_ID,0,4)='1997';
> ````

4. 성명과 생년월일을 문자로 만들어 작은 따옴표안에 표시하는 쿼리를 작성하시오

> `````sql
> SELECT
> 	'''' || EMP_NAME || BIRTH_DATE || '''', BIRTH_DATE 
> FROM
> 	TEMP;
> `````

5. 생년월일을 0000년 00월 00일로 출력하는 쿼리를 작성하시오

> ````sql
> SELECT
> 	EMP_NAME,
> 	(TO_CHAR(BIRTH_DATE, 'yyyy') || '년 ' || TO_CHAR(BIRTH_DATE, 'mm') || '월 ' || TO_CHAR(BIRTH_DATE, 'dd') || '일') 생년월일
> FROM
> 	TEMP;
> ````

6. 정렬

> ````sql
> SELECT
> 	LEV, EMP_ID, EMP_NAME
> FROM
> 	TEMP
> ORDER BY 1 DESC, 2 ASC; -- 정렬방식은 ASC(기본값)||DESC를 지정하고 필드마다 써야 한다.,
> 
> SELECT
> 	LEV||'('||EMP_NAME||')', EMP_ID
> FROM
> 	TEMP
> ORDER BY 1 DESC; -- 필드명에 가공을 했을 경우에는 순서를 기술 가능하다.
> ````

7. 검색

>````sql
>SELECT
>	EMP_ID, EMP_NAME
>FROM
>	TEMP
>WHERE
>	EMP_NAME='홍길동' OR EMP_NAME='김길동';
>
>SELECT
>	EMP_ID, EMP_NAME
>FROM
>	TEMP
>WHERE
>	EMP_NAME NOT IN ('홍길동', '김길동');
>````

8. LEV 별로 인원, 최고연봉, 최소 연봉, 연봉합계, 연봉 평균을 출력하시오.

> ````sql
> SELECT
> 	LEV, COUNT(LEV), MAX(SALARY) , MIN(SALARY), SUM(SALARY), AVG(SALARY)
> FROM
> 	TEMP
> GROUP BY
> 	LEV;
> ````

9. 모든 직급을 조회

> ````sql
> SELECT
> 	LEV
> FROM
> 	TEMP; -- 중복 포함
> 
> SELECT
> 	LEV
> FROM
> 	TEMP;
> GROUP BY
> 	LEV; -- 중복제거(GROUP BY)
> 
> SELECT DISTINCT
> 	LEV
> FROM
> 	TEMP; -- 중복제거(DISTINCT)
> ````

10. TDEPT 자룔를 이용하여 부서장 중 사번이 가장 빠른 사람이 근무하는 부서를 조회

> ````sql
> SELECT * FROM TDEPT;
> -- BOSS_ID의 최소값을 구해서 그 값으로 조건을 걸어야 한다.
> SELECT
> 	DEPT_NAME
> FROM
> 	TDEPT
> WHERE
> 	BOSS_ID = (SELECT MIN(BOSS_ID) FROM TDEPT);
> -- 서브쿼리를 사용할 수 없다면 어떻게 해야하는가?
> SELECT
> 	MIN(BOSS_ID||DEPT_NAME), SUBSTR(MIN(BOSS_ID||DEPT_NAME),9)
> FROM
> 	TDEPT;
> ````

11. 

TDEPT 자료를 이용하여 AREA벼ㅑㄹ로 최소 BOSS_ID를 구해 내림차순으로 정렬

> ````sql
> SELECT
> 	MIN(BOSS_ID)
> FROM
> 	TDEPT
> GROUP BY
> 	AREA;
> ````

12. 직급별로 연봉 평균을 구한 상태에서 평균 연봉이 5천만원 이상인 경우의 직급과 연봉을 조회

> ````sql
> SELECT
> 	LEV, AVG(SALARY)
> FROM
> 	TEMP
> GROUP BY
> 	LEV
> HAVING
> 	avg(SALARY)>=50000000;
> ````