--------------------------------------------------------
--  DDL for Function CHECK_LOGIN
--------------------------------------------------------

CREATE OR REPLACE NONEDITIONABLE FUNCTION "CHECK_LOGIN" (
    LOGIN_INP IN VARCHAR2,
    PASS_INP IN VARCHAR2
)
    RETURN VARCHAR2
    IS
    user_type VARCHAR2(255);
BEGIN
    SELECT usr."TYPE" INTO user_type FROM "USER" usr
    WHERE usr.login = LOGIN_INP and usr.password = PASS_INP;

    RETURN user_type;
END;

--------------------------------------------------------
--  DDL for Function GET_ID
--------------------------------------------------------

CREATE OR REPLACE NONEDITIONABLE FUNCTION "GET_ID" (
    LOGIN_INP IN VARCHAR2,
    PASS_INP IN VARCHAR2
)
    RETURN NUMBER
    IS
    r_user_id NUMBER(6,0);
BEGIN
    SELECT usr.user_id INTO r_user_id FROM "USER" usr
    WHERE usr.login = LOGIN_INP and usr.password = PASS_INP;

    RETURN r_user_id;
END;
