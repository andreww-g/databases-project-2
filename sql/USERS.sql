DROP USER "LOGIN_ONLY" CASCADE;
DROP USER "STUDENT" CASCADE;
DROP USER "PROFESSOR" CASCADE;
DROP USER "DEANS_WORKER" CASCADE;

alter session set "_ORACLE_SCRIPT"=true;
CREATE USER LOGIN_ONLY IDENTIFIED BY pass;
GRANT CREATE SESSION TO LOGIN_ONLY;
GRANT EXECUTE ON SYS.CHECK_LOGIN TO LOGIN_ONLY;
GRANT EXECUTE ON SYS.GET_ID TO LOGIN_ONLY;

alter session set "_ORACLE_SCRIPT"=true; 
CREATE USER STUDENT IDENTIFIED BY pass;
GRANT CREATE SESSION TO STUDENT;
GRANT SELECT ON SYS.STUDENT_VIEW TO STUDENT;
GRANT SELECT ON SYS.STUDENT_GRADES_VIEW TO STUDENT;
GRANT SELECT ON SYS.RECORD_VIEW TO STUDENT;

alter session set "_ORACLE_SCRIPT"=true; 
CREATE USER PROFESSOR IDENTIFIED BY pass;
GRANT CREATE SESSION TO PROFESSOR;
GRANT SELECT ON SYS.PROFESSOR_VIEW TO PROFESSOR;
GRANT SELECT ON SYS.GROUP_VIEW TO PROFESSOR;
GRANT SELECT ON SYS.GROUP_STUDENTS_VIEW TO PROFESSOR;
GRANT EXECUTE ON SYS.ADD_GRADE TO PROFESSOR;
GRANT EXECUTE ON SYS.REMOVE_GRADE TO PROFESSOR;

alter session set "_ORACLE_SCRIPT"=true; 
CREATE USER DEANS_WORKER IDENTIFIED BY pass;
GRANT CREATE SESSION TO DEANS_WORKER;
GRANT SELECT ON SYS.DEANS_WORKER_VIEW TO DEANS_WORKER;
GRANT SELECT ON SYS.STUDENT_VIEW TO DEANS_WORKER;
GRANT SELECT ON SYS.PROFESSOR_VIEW TO DEANS_WORKER;
GRANT SELECT ON SYS.GROUP_VIEW TO DEANS_WORKER;
GRANT SELECT ON SYS.GROUP_STUDENTS_VIEW TO DEANS_WORKER;
GRANT SELECT ON SYS.SUBJECT TO DEANS_WORKER;
GRANT EXECUTE ON SYS.ADD_GROUP TO DEANS_WORKER;
GRANT EXECUTE ON SYS.ADD_STUDENT TO DEANS_WORKER;
GRANT EXECUTE ON SYS.ADD_STUDENT_TO_GROUP TO DEANS_WORKER;
GRANT EXECUTE ON SYS.REMOVE_GROUP TO DEANS_WORKER;
GRANT EXECUTE ON SYS.REMOVE_STUDENT TO DEANS_WORKER;
GRANT EXECUTE ON SYS.REMOVE_STUDENT_FROM_GROUP TO DEANS_WORKER;
GRANT EXECUTE ON SYS.MODIFY_GROUP TO DEANS_WORKER;