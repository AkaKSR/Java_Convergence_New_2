-- ===================================== --
SELECT
	*
FROM
	EMPLOYEES;
	
SELECT
	*
FROM
	DEPARTMENTS;

SELECT
	*
FROM
	LOCATIONS;

-- ===================================== --

-- 1. 사원 테이블에서 각 사원에 부서명을 출력
-- 조인 시 부서가 없는 사원은 출력 안함
SELECT
	dep.DEPARTMENT_NAME DNAME, emp.DEPARTMENT_ID EMPNO, emp.FIRST_NAME
FROM
	EMPLOYEES emp, DEPARTMENTS dep
WHERE
	emp.DEPARTMENT_ID=dep.DEPARTMENT_ID
ORDER BY
	2, 1;
	
-- 2. 사원 테이블에서 각 사원에 부서명을 출력
-- 조인 시 부서가 없는 사원도 출력
-- 부서명이 null이 존재하지 않으므로 스킵
SELECT
	dep.DEPARTMENT_NAME DNAME, emp.DEPARTMENT_ID EMPNO, emp.FIRST_NAME
FROM
	EMPLOYEES emp, DEPARTMENTS dep
WHERE
	(emp.DEPARTMENT_ID=dep.DEPARTMENT_ID);
	
-- 3. 부서 위치가 'London', 'Oxford' 곳에 근무하는 사원 정보 출력
SELECT
	loc.CITY, emp.DEPARTMENT_ID, emp.LAST_NAME
FROM
	EMPLOYEES emp, LOCATIONS loc, DEPARTMENTS dep
WHERE
	emp.DEPARTMENT_ID = dep.DEPARTMENT_ID
	AND
	dep.LOCATION_ID = loc.LOCATION_ID
	AND
	loc.CITY IN ('Locdon', 'Oxford')
ORDER BY
	2 DESC, 1;

-- 4. 부서별 최고 급여 금액 출력
SELECT
	DEPARTMENT_ID DEPTNO, max(SALARY) SAL
FROM
	EMPLOYEES
GROUP BY
	DEPARTMENT_ID
HAVING
	DEPARTMENT_ID IS NOT NULL;
	
-- 5. 부서별 최고 급여 금액을 받는 사원 정보 출력
SELECT
	e.DEPARTMENT_ID, e.SALARY, e.EMPLOYEE_ID, e.LAST_NAME, e.JOB_ID
FROM
	EMPLOYEES e,
	(
	SELECT DEPARTMENT_ID, MAX(SALARY) MAXSAL FROM EMPLOYEES
	GROUP BY DEPARTMENT_ID HAVING DEPARTMENT_ID IS NOT NULL
	ORDER BY DEPARTMENT_ID
	) Q
WHERE
	E.SALARY=Q.MAXSAL AND e.DEPARTMENT_ID=q.DEPARTMENT_ID;