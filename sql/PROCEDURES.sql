DROP PROCEDURE "SYS"."ADD_GRADE";
DROP PROCEDURE "SYS"."ADD_GROUP";
DROP PROCEDURE "SYS"."ADD_STUDENT";
DROP PROCEDURE "SYS"."ADD_STUDENT_TO_GROUP";
DROP PROCEDURE "SYS"."REMOVE_GRADE";
DROP PROCEDURE "SYS"."REMOVE_GROUP";
DROP PROCEDURE "SYS"."REMOVE_STUDENT";
DROP PROCEDURE "SYS"."REMOVE_STUDENT_FROM_GROUP";

--------------------------------------------------------
--  DDL for Procedure ADD_GRADE
--------------------------------------------------------

CREATE OR REPLACE NONEDITIONABLE PROCEDURE "ADD_GRADE" (
    student_id_in NUMBER,
    group_id_in NUMBER,
    grade_in DOUBLE PRECISION)
    IS
    grade_var NUMBER;
BEGIN
    SELECT COUNT(*) INTO grade_var FROM "RECORD" rec
    WHERE
            student_id_in = rec.student_id and
            group_id_in = rec.group_id;

    IF grade_var = 1 THEN
        UPDATE "RECORD" rec
        SET GRADE = grade_in,
            GRADE_DATE = CURRENT_DATE
        WHERE
                student_id_in = rec.student_id and
                group_id_in = rec.group_id;
        dbms_output.put_line('Student grade has been added.');
    ELSE
        dbms_output.put_line('Student record does not exist.');
    END IF;
END;

--------------------------------------------------------
--  DDL for Procedure ADD_GROUP
--------------------------------------------------------

CREATE OR REPLACE NONEDITIONABLE PROCEDURE "ADD_GROUP"
(
    GROUP_ID_IN IN NUMBER
, SUBJECT_ID_IN IN NUMBER
, PROFESSOR_ID_IN IN NUMBER
, PARITY_IN IN VARCHAR2
, DAY_IN IN NUMBER
, TIME_STR_IN IN VARCHAR2
, FORM_IN IN VARCHAR2
, STUDENT_LIMIT IN NUMBER
) AS
    TIME_IN TIMESTAMP;
BEGIN
    TIME_IN := TO_TIMESTAMP(TIME_STR_IN, 'HH24:MI');

    insert into "GROUP" ("GROUP_ID", SUBJECT_ID, PROFESSOR_ID, "PARITY", "DAY", "TIME", "FORM", STUDENT_LIMIT)
    values (GROUP_ID_IN, SUBJECT_ID_IN, PROFESSOR_ID_IN, PARITY_IN, DAY_IN, TIME_IN, FORM_IN, STUDENT_LIMIT);
END ADD_GROUP;

--------------------------------------------------------
--  DDL for Procedure ADD_STUDENT
--------------------------------------------------------

CREATE OR REPLACE NONEDITIONABLE PROCEDURE "ADD_STUDENT"
(
    FIRST_NAME_IN IN VARCHAR2
, LAST_NAME_IN IN VARCHAR2
, FACULTY_ID_IN IN VARCHAR2
, SPECIALIZATION_ID_IN IN VARCHAR2
, PESEL_IN IN VARCHAR2
, LOGIN_IN IN VARCHAR2
, PASSWORD_IN IN VARCHAR2
) AS
    TEMP_ID NUMBER;

BEGIN
    TEMP_ID := STUDENT_SEQ.NEXTVAL;

    insert into "USER" (user_id, login, "PASSWORD", "TYPE")
    values (TEMP_ID, LOGIN_IN, PASSWORD_IN, 'STUDENT');

    insert into PERSONAL_DATA (user_id, first_name, last_name, email, faculty_id)
    values (TEMP_ID, FIRST_NAME_IN, LAST_NAME_IN, TEMP_ID || '@edu.meme', FACULTY_ID_IN);

    insert into STUDENT (student_id, pesel, admission_date, year, semester, specialization_id)
    values (TEMP_ID, PESEL_IN, SYSDATE, 1, 1, SPECIALIZATION_ID_IN);
END ADD_STUDENT;

--------------------------------------------------------
--  DDL for Procedure ADD_STUDENT_TO_GROUP
--------------------------------------------------------

CREATE OR REPLACE NONEDITIONABLE PROCEDURE "ADD_STUDENT_TO_GROUP" (
    student_id_in NUMBER,
    group_in NUMBER
)
    IS
    student_count NUMBER;
    student_limit NUMBER;
BEGIN
    SELECT count(*) INTO student_count FROM "RECORD" rec
    WHERE
            rec.group_id = group_in;

    SELECT grp.student_limit INTO student_limit FROM "GROUP" grp
    WHERE
            grp.group_id = group_in;

    IF student_count < student_limit THEN
        INSERT INTO "RECORD"(student_id, group_id, record_date)
        VALUES (student_id_in, group_in, CURRENT_DATE);
    END IF;
END;

--------------------------------------------------------
--  DDL for Procedure MODIFY_GROUP
--------------------------------------------------------

CREATE OR REPLACE NONEDITIONABLE PROCEDURE "MODIFY_GROUP"
(GROUP_ID_IN IN NUMBER
, SUBJECT_ID_IN IN NUMBER
, PROFESSOR_ID_IN IN NUMBER
, PARITY_IN IN VARCHAR2
, DAY_IN IN NUMBER
, TIME_STR_IN IN VARCHAR2
, FORM_IN IN VARCHAR2
, STUDENT_LIMIT_IN IN NUMBER
) AS
    TIME_IN TIMESTAMP;
BEGIN
    TIME_IN := TO_TIMESTAMP(TIME_STR_IN, 'HH24:MI');

    UPDATE "GROUP" SET "SUBJECT_ID" = subject_id_in,
                       "PROFESSOR_ID" = professor_id_in,
                       "PARITY" = parity_in,
                       "DAY" = day_in,
                       "TIME" = time_in,
                       "FORM" = form_in,
                       "STUDENT_LIMIT" = student_limit_in
    WHERE "GROUP_ID" = group_id_in;
END;

--------------------------------------------------------
--  DDL for Procedure REMOVE_GRADE
--------------------------------------------------------

CREATE OR REPLACE NONEDITIONABLE PROCEDURE "REMOVE_GRADE" (
    student_id NUMBER,
    group_id_n NUMBER)
    IS
    grade_var NUMBER;
BEGIN
    SELECT COUNT(*) INTO grade_var FROM "RECORD" rec
    WHERE
            student_id = rec.student_id and
            group_id_n = rec.group_id;

    IF grade_var = 1 THEN
        UPDATE "RECORD" rec
        SET
            GRADE = NULL,
            GRADE_DATE = NULL
        WHERE
                student_id = rec.student_id and
                group_id_n = rec.group_id;
        dbms_output.put_line('Student grade has been removed.');
    ELSE
        dbms_output.put_line('Student record does not exist.');
    END IF;
END;

--------------------------------------------------------
--  DDL for Procedure REMOVE_GROUP
--------------------------------------------------------

CREATE OR REPLACE NONEDITIONABLE PROCEDURE "REMOVE_GROUP" (group_in NUMBER)
    IS
BEGIN
    DELETE FROM "RECORD" rec
    WHERE
            rec.group_id = group_in;

    DELETE FROM "GROUP" grp
    WHERE
            grp.group_id = group_in;
END;

--------------------------------------------------------
--  DDL for Procedure REMOVE_STUDENT
--------------------------------------------------------

CREATE OR REPLACE NONEDITIONABLE PROCEDURE "REMOVE_STUDENT"
(
    STUDENT_ID_IN IN NUMBER
) AS
BEGIN
    DELETE FROM "RECORD" rec
    WHERE
            STUDENT_ID_IN = rec.student_id;

    DELETE FROM STUDENT st
    WHERE
            STUDENT_ID_IN = st.student_id;

    DELETE FROM "USER" usr
    WHERE
            STUDENT_ID_IN = usr.user_id;

    DELETE FROM PERSONAL_DATA pd
    WHERE
            STUDENT_ID_IN = pd.user_id;
END REMOVE_STUDENT;

--------------------------------------------------------
--  DDL for Procedure REMOVE_STUDENT_FROM_GROUP
--------------------------------------------------------

CREATE OR REPLACE NONEDITIONABLE PROCEDURE "REMOVE_STUDENT_FROM_GROUP"
(
    STUDENT_IN IN NUMBER
, GROUP_IN IN NUMBER
) AS
BEGIN
    DELETE FROM "RECORD" rec
    WHERE
            group_in = rec.group_id and
            student_in = rec.student_id;

END REMOVE_STUDENT_FROM_GROUP;
END;