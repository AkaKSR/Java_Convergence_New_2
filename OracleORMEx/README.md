<<<<<<< HEAD
# 191008

### Oracle 설치

1. 기본적으로 Oracle은 8080포트에 설치되므로 Apache Tomcat을 사용중이라면 Oracle의 포트를 변경해야한다.

> Apache Tomcat 서버가 실행중인 상태에서 Oracle을 설치시도하면 포트 변경할수 있는 창이 뜬다.
>
> 현재 사용포트 8081



### 사용 프로그램

1. Oracle 11g Express
2. sqlDeveloper-3.2.20.09.87



### Library

ojdbc6.jar

> mvn install:install-file -Dfile=ojdbc6.jar -DgroupId=com.oracle -DartifactId=ojdbc6 -Dversion=11.2.0.3 -Dpackaging=jar



### 사용된 명령문

1. CREATE SEQUENCE [필드명];
2. SELECT [필드명].nextval FROM dual;
3. SELECT [필드명].currval FROM dual;

> Oracle은 MariaDB와는 다르게 auto_increment를 지원하지 않기때문에 SEQUENCE를 생성해줘야 한다.
>
> usage)
> CREATE SEQUENCE test_seq;  -- test_seq 이름을 가진 SEQUENCE를 생성
> SELECT test_seq.nextval FROM dual;  -- test_seq의 수치를 1증가
> SELECT test_seq.currval FROM dual;  -- 현재의 test_seq의 수치를 보여준다

> MariaDB와는 다르게 인스턴스 테이블의 사용을 위해서는 FROM dual을 입력해주어야 한다.

4. CREATE TABLE [테이블명] (필드명 타입 옵션)

> Oracle에서의 테이블 생성은 다음과 같다.
>
> Usage)
> CREATE TABLE memo(
> 	idx NUMBER PRIMARY KEY,
> 	name varchar2(20) NOT NULL,
> 	password varchar2(20) NOT NULL,
> 	memo varchar2(200) NOT NULL,
> 	regDate timestamp DEFAULT SYSDATE,
> 	ip varchar2(20) NOT NULL
> );
>
> MariaDB에서는 숫자를 표현할때 int를 썻다면 Oracle에서는 NUMBER를 사용한다.
>
> 문자를 표현할때도 varchar()이 아닌 varchar2()를 사용

5. INSERT INTO [테이블명] VALUES (필드명, .....);

> Usage)
> INSERT INTO memo VALUES (memo_idx_seq.nextval, '주인장', '1234', 'ㅁㄴㅇㄹ', SYSDATE, 'localhost');
>
> **중요**
> MariaDB에서는 INSERT INTO [테이블명] (필드명, ....) VALUES (필드명, ....)  으로 작성되었지만
> Oracle에서는 처음의 필드명 부분이 제외된다.

6. SYSDATE

> MariaDB에서의 now()가 Oracle에서는 SYSDATE로 쓰인다.

7. SELECT * FROM tab;

> DB내의 테이블 목록을 보여준다.

8. SELECT username FROM all_users;

> DB내의 사용자 목록을 보여준다.

9. CREATE USER [유저명] IDENTIFIED BY "[비밀번호]";

> 해당 유저명과 비밀번호로 계정을 새로 만든다.

10. GRANT CONNECT,resource TO [유저명];

> 해당 유저명에 접속 권한을 설정한다.

11. sys as sysdba

> 시스템 관리자 계정