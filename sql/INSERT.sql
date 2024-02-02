delete from "RECORD";
delete from "GROUP";
delete from STUDENT;
delete from PROFESSOR;
delete from DEANS_WORKER;
delete from PERSONAL_DATA;
delete from "USER";
delete from SUBJECT;
delete from SPECIALIZATION;
delete from FACULTY;

alter sequence deans_worker_seq restart start with 1 minvalue 1;
alter sequence professor_seq restart start with 100001 minvalue 100001;
alter sequence student_seq restart start with 200001 minvalue 200001;

insert into FACULTY (faculty_id, name, dean_id) values ('W1', 'Wydział 1', 000001);
insert into FACULTY (faculty_id, name, dean_id) values ('W2', 'Wydział 2', 000002);
insert into FACULTY (faculty_id, name, dean_id) values ('W3', 'Wydział 3', 000003);
insert into FACULTY (faculty_id, name, dean_id) values ('W4', 'Wydział 4', 000004);
insert into FACULTY (faculty_id, name, dean_id) values ('W5', 'Wydział 5', 000005);

insert into SPECIALIZATION (specialization_id, name, faculty_id) values (000001, 'S1', 'W1');
insert into SPECIALIZATION (specialization_id, name, faculty_id) values (000002, 'S2', 'W2');
insert into SPECIALIZATION (specialization_id, name, faculty_id) values (000003, 'S3', 'W3');
insert into SPECIALIZATION (specialization_id, name, faculty_id) values (000004, 'S4', 'W4');
insert into SPECIALIZATION (specialization_id, name, faculty_id) values (000005, 'S5', 'W5');

insert into SUBJECT (subject_id, subject_name, faculty_id) values (000001, 'Micromitrium Moss', 'W1');
insert into SUBJECT (subject_id, subject_name, faculty_id) values (000002, 'Gypsum Blazingstar', 'W2');
insert into SUBJECT (subject_id, subject_name, faculty_id) values (000003, 'Black Flatsedge', 'W3');
insert into SUBJECT (subject_id, subject_name, faculty_id) values (000004, 'Coille''s Rush', 'W4');
insert into SUBJECT (subject_id, subject_name, faculty_id) values (000005, 'Ruth''s Sedge', 'W4');

insert into "USER" (user_id, login, password, type) values (STUDENT_SEQ.NEXTVAL, '1', '6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b', 'STUDENT');
insert into "USER" (user_id, login, password, type) values (STUDENT_SEQ.NEXTVAL, 's2', '6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b', 'STUDENT');
insert into "USER" (user_id, login, password, type) values (STUDENT_SEQ.NEXTVAL, 's3', '6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b', 'STUDENT');
insert into "USER" (user_id, login, password, type) values (STUDENT_SEQ.NEXTVAL, 's4', '6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b', 'STUDENT');
insert into "USER" (user_id, login, password, type) values (STUDENT_SEQ.NEXTVAL, 's5', '6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b', 'STUDENT');

insert into "USER" (user_id, login, password, type) values (PROFESSOR_SEQ.NEXTVAL, 'p1', '6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b', 'PROFESSOR');
insert into "USER" (user_id, login, password, type) values (PROFESSOR_SEQ.NEXTVAL, '2', '6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b', 'PROFESSOR');
insert into "USER" (user_id, login, password, type) values (PROFESSOR_SEQ.NEXTVAL, 'p3', '6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b', 'PROFESSOR');
insert into "USER" (user_id, login, password, type) values (PROFESSOR_SEQ.NEXTVAL, 'p4', '6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b', 'PROFESSOR');
insert into "USER" (user_id, login, password, type) values (PROFESSOR_SEQ.NEXTVAL, 'p5', '6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b', 'PROFESSOR');

insert into "USER" (user_id, login, password, type) values (DEANS_WORKER_SEQ.NEXTVAL, 'w1', '6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b', 'DEANS_WORKER');
insert into "USER" (user_id, login, password, type) values (DEANS_WORKER_SEQ.NEXTVAL, 'w2', '6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b', 'DEANS_WORKER');
insert into "USER" (user_id, login, password, type) values (DEANS_WORKER_SEQ.NEXTVAL, '3', '6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b', 'DEANS_WORKER');
insert into "USER" (user_id, login, password, type) values (DEANS_WORKER_SEQ.NEXTVAL, 'w4', '6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b', 'DEANS_WORKER');
insert into "USER" (user_id, login, password, type) values (DEANS_WORKER_SEQ.NEXTVAL, 'w5', '6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b', 'DEANS_WORKER');

insert into PERSONAL_DATA (user_id, first_name, last_name, email, faculty_id) values (200001, 'Alice', 'Leroux', 'aleroux0@cdc.gov', 'W1');
insert into PERSONAL_DATA (user_id, first_name, last_name, email, faculty_id) values (200002, 'Tillie', 'Vigrass', 'tvigrass1@berkeley.edu', 'W2');
insert into PERSONAL_DATA (user_id, first_name, last_name, email, faculty_id) values (200003, 'Minny', 'Tollemache', 'mtollemache2@mysql.com', 'W3');
insert into PERSONAL_DATA (user_id, first_name, last_name, email, faculty_id) values (200004, 'Francesca', 'Pawlata', 'fpawlata3@mayoclinic.com', 'W4');
insert into PERSONAL_DATA (user_id, first_name, last_name, email, faculty_id) values (200005, 'Sandor', 'McCraw', 'smccraw4@princeton.edu', 'W5');

insert into PERSONAL_DATA (user_id, first_name, last_name, email, faculty_id) values (100001, 'Claudie', 'Fulloway', 'cfulloway5@go.com', 'W1');
insert into PERSONAL_DATA (user_id, first_name, last_name, email, faculty_id) values (100002, 'Sharron', 'Siddele', 'ssiddele6@amazon.co.uk', 'W2');
insert into PERSONAL_DATA (user_id, first_name, last_name, email, faculty_id) values (100003, 'Sybil', 'Berntssen', 'sberntssen7@cargocollective.com', 'W3');
insert into PERSONAL_DATA (user_id, first_name, last_name, email, faculty_id) values (100004, 'Putnem', 'St. John', 'pstjohn8@techcrunch.com', 'W4');
insert into PERSONAL_DATA (user_id, first_name, last_name, email, faculty_id) values (100005, 'Morgen', 'Fathers', 'mfathers9@shutterfly.com', 'W5');

insert into PERSONAL_DATA (user_id, first_name, last_name, email, faculty_id) values (000001, 'Starlene', 'Frizell', 'sfrizella@google.de', 'W1');
insert into PERSONAL_DATA (user_id, first_name, last_name, email, faculty_id) values (000002, 'Lonni', 'Thunderman', 'lthundermanb@msn.com', 'W2');
insert into PERSONAL_DATA (user_id, first_name, last_name, email, faculty_id) values (000003, 'Mordy', 'Calloway', 'mcallowayc@cafepress.com', 'W3');
insert into PERSONAL_DATA (user_id, first_name, last_name, email, faculty_id) values (000004, 'Hazlett', 'Yaldren', 'hyaldrend@dailymail.co.uk', 'W4');
insert into PERSONAL_DATA (user_id, first_name, last_name, email, faculty_id) values (000005, 'Austin', 'Reolfi', 'areolfie@odnoklassniki.ru', 'W5');

insert into STUDENT (student_id, pesel, admission_date, year, semester, specialization_id) values (200001, 20000000001, TO_DATE('2020/04/30', 'yyyy/mm/dd'),1999, 1, 1);
insert into STUDENT (student_id, pesel, admission_date, year, semester, specialization_id) values (200002, 20000000002, TO_DATE('1888/04/30', 'yyyy/mm/dd'), 1999, 2, 2);
insert into STUDENT (student_id, pesel, admission_date, year, semester, specialization_id) values (200003, 20000000003, TO_DATE('2677/04/30', 'yyyy/mm/dd'), 1999, 3, 3);
insert into STUDENT (student_id, pesel, admission_date, year, semester, specialization_id) values (200004, 20000000004, TO_DATE('2110/04/12', 'yyyy/mm/dd'), 1999, 4,4);
insert into STUDENT (student_id, pesel, admission_date, year, semester, specialization_id) values (200005, 20000000005, TO_DATE('2110/04/30', 'yyyy/mm/dd'), 1999, 5, 5);

insert into PROFESSOR (professor_id, degree) values (100001, 'INŻ');
insert into PROFESSOR (professor_id, degree) values (100002, 'MGR');
insert into PROFESSOR (professor_id, degree) values (100003, 'MGR');
insert into PROFESSOR (professor_id, degree) values (100004, 'MGR');
insert into PROFESSOR (professor_id, degree) values (100005, 'MGR');

insert into DEANS_WORKER (DEANS_WORKER_ID) values (000001);
insert into DEANS_WORKER (DEANS_WORKER_ID) values (000002);
insert into DEANS_WORKER (DEANS_WORKER_ID) values (000003);
insert into DEANS_WORKER (DEANS_WORKER_ID) values (000004);
insert into DEANS_WORKER (DEANS_WORKER_ID) values (000005);

insert into "GROUP" (group_id, subject_id, professor_id, parity, day, time, form, student_limit) values (1, 1, 100001, 'P', 1, TIMESTAMP '2003-01-01 2:00:00', 'C', 30);
insert into "GROUP" (group_id, subject_id, professor_id, parity, day, time, form, student_limit) values (2, 2, 100002, 'N', 1, TIMESTAMP '2003-01-01 2:00:00', 'C', 30);
insert into "GROUP" (group_id, subject_id, professor_id, parity, day, time, form, student_limit) values (3, 3, 100003, 'P', 2, TIMESTAMP '2003-01-01 2:00:00', 'W', 30);
insert into "GROUP" (group_id, subject_id, professor_id, parity, day, time, form, student_limit) values (4, 4, 100004, 'P', 3, TIMESTAMP '2003-01-01 2:00:00', 'C', 30);
insert into "GROUP" (group_id, subject_id, professor_id, parity, day, time, form, student_limit) values (5, 5, 100005, 'N', 3, TIMESTAMP '2003-01-01 2:00:00', 'L', 30);

insert into "RECORD" (group_id, student_id, record_date, grade, grade_date) values (000001, 200001, TO_DATE('2020/04/30', 'yyyy/mm/dd'), 4, TO_DATE('2020/04/30', 'yyyy/mm/dd'));
insert into "RECORD" (group_id, student_id, record_date, grade, grade_date) values (000002, 200002, TO_DATE('2020/04/30', 'yyyy/mm/dd'), 4, TO_DATE('2020/04/30', 'yyyy/mm/dd'));
insert into "RECORD" (group_id, student_id, record_date, grade, grade_date) values (000003, 200003, TO_DATE('2020/04/30', 'yyyy/mm/dd'), 2, TO_DATE('2020/04/30', 'yyyy/mm/dd'));
insert into "RECORD" (group_id, student_id, record_date, grade, grade_date) values (000004, 200004, TO_DATE('2020/04/30', 'yyyy/mm/dd'), 3, TO_DATE('2020/04/30', 'yyyy/mm/dd'));
insert into "RECORD" (group_id, student_id, record_date, grade, grade_date) values (000005, 200005, TO_DATE('2020/04/30', 'yyyy/mm/dd'), 4, TO_DATE('2020/04/30', 'yyyy/mm/dd'));