-- sys as sysdba

CREATE USER testuser IDENTIFIED BY "123456";

GRANT CONNECT,resource TO testuser;