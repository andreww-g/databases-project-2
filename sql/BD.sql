PURGE RECYCLEBIN;
DROP TABLE FACULTY CASCADE CONSTRAINTS;
DROP TABLE "GROUP" CASCADE CONSTRAINTS;
DROP TABLE PERSONAL_DATA CASCADE CONSTRAINTS;
DROP TABLE PROFESSOR CASCADE CONSTRAINTS;
DROP TABLE "RECORD" CASCADE CONSTRAINTS;
DROP TABLE SPECIALIZATION CASCADE CONSTRAINTS;
DROP TABLE STUDENT CASCADE CONSTRAINTS;
DROP TABLE SUBJECT CASCADE CONSTRAINTS;
DROP TABLE "USER" CASCADE CONSTRAINTS;
DROP TABLE "DEANS_WORKER" CASCADE CONSTRAINTS;
--------------------------------------------------------
--  DDL for Table DEANS_WORKER
--------------------------------------------------------

  CREATE TABLE "DEANS_WORKER" 
   (	"DEANS_WORKER_ID" NUMBER(6,0)
   ) ;
--------------------------------------------------------
--  DDL for Table FACULTY
--------------------------------------------------------

  CREATE TABLE "FACULTY" 
   (	"FACULTY_ID" VARCHAR2(6), 
	"NAME" VARCHAR2(20), 
	"DEAN_ID" NUMBER(6,0)
   ) ;
--------------------------------------------------------
--  DDL for Table GROUP
--------------------------------------------------------

  CREATE TABLE "GROUP" 
   (	"GROUP_ID" NUMBER(6,0) DEFAULT 0, 
	"SUBJECT_ID" NUMBER(6,0), 
	"PROFESSOR_ID" NUMBER(6,0), 
	"PARITY" VARCHAR2(1), 
	"DAY" NUMBER(1,0), 
	"TIME" TIMESTAMP (4), 
	"FORM" VARCHAR2(20), 
	"STUDENT_LIMIT" NUMBER(3,0)
   ) ;
--------------------------------------------------------
--  DDL for Table PERSONAL_DATA
--------------------------------------------------------

  CREATE TABLE "PERSONAL_DATA" 
   (	"USER_ID" NUMBER(6,0), 
	"FIRST_NAME" VARCHAR2(30), 
	"LAST_NAME" VARCHAR2(30), 
	"EMAIL" VARCHAR2(50), 
	"FACULTY_ID" VARCHAR2(6)
   ) ;
--------------------------------------------------------
--  DDL for Table PROFESSOR
--------------------------------------------------------

  CREATE TABLE "PROFESSOR" 
   (	"PROFESSOR_ID" NUMBER(6,0), 
	"DEGREE" VARCHAR2(30)
   ) ;
--------------------------------------------------------
--  DDL for Table RECORD
--------------------------------------------------------

  CREATE TABLE "RECORD" 
   (	"GROUP_ID" NUMBER(6,0), 
	"STUDENT_ID" NUMBER(6,0), 
	"RECORD_DATE" DATE, 
	"GRADE" FLOAT(126), 
	"GRADE_DATE" DATE
   ) ;
--------------------------------------------------------
--  DDL for Table SPECIALIZATION
--------------------------------------------------------

  CREATE TABLE "SPECIALIZATION" 
   (	"SPECIALIZATION_ID" NUMBER(6,0) DEFAULT NULL, 
	"NAME" VARCHAR2(20), 
	"FACULTY_ID" VARCHAR2(6)
   ) ;
--------------------------------------------------------
--  DDL for Table STUDENT
--------------------------------------------------------

  CREATE TABLE "STUDENT" 
   (	"STUDENT_ID" NUMBER(6,0), 
	"PESEL" NUMBER(11,0), 
	"ADMISSION_DATE" DATE, 
	"YEAR" NUMBER(4,0), 
	"SEMESTER" NUMBER(2,0), 
	"SPECIALIZATION_ID" NUMBER(6,0)
   ) ;
--------------------------------------------------------
--  DDL for Table SUBJECT
--------------------------------------------------------

  CREATE TABLE "SUBJECT" 
   (	"SUBJECT_ID" NUMBER(6,0), 
	"SUBJECT_NAME" VARCHAR2(40), 
	"FACULTY_ID" VARCHAR2(6)
   ) ;
--------------------------------------------------------
--  DDL for Table USER
--------------------------------------------------------

  CREATE TABLE "USER" 
   (	"USER_ID" NUMBER(6,0), 
	"LOGIN" VARCHAR2(255), 
	"PASSWORD" VARCHAR2(255), 
	"TYPE" VARCHAR2(20)
   ) ;
--------------------------------------------------------
--  DDL for Index PERSONAL_DATA_EMAIL_INDEX
--------------------------------------------------------

  CREATE UNIQUE INDEX "PERSONAL_DATA_EMAIL_INDEX" ON "PERSONAL_DATA" ("EMAIL") 
  ;
--------------------------------------------------------
--  DDL for Index PERSONAL_NAME_INDEX
--------------------------------------------------------

  CREATE INDEX "PERSONAL_NAME_INDEX" ON "PERSONAL_DATA" ("FIRST_NAME", "LAST_NAME") 
  ;
--------------------------------------------------------
--  DDL for Index RECORD_RECORD_DATE_INDEX
--------------------------------------------------------

  CREATE INDEX "RECORD_RECORD_DATE_INDEX" ON "RECORD" ("RECORD_DATE" DESC) 
  ;
--------------------------------------------------------
--  DDL for Index STUDENT_PESEL_INDEX
--------------------------------------------------------

  CREATE UNIQUE INDEX "STUDENT_PESEL_INDEX" ON "STUDENT" ("PESEL") 
  ;
--------------------------------------------------------
--  DDL for Index USER_LOGIN_INDEX
--------------------------------------------------------

  CREATE UNIQUE INDEX "USER_LOGIN_INDEX" ON "USER" ("LOGIN") 
  ;
--------------------------------------------------------
--  Constraints for Table DEANS_WORKER
--------------------------------------------------------

  ALTER TABLE "DEANS_WORKER" ADD CONSTRAINT "DEANS_WORKER_ID_PK" PRIMARY KEY ("DEANS_WORKER_ID")
  USING INDEX (CREATE UNIQUE INDEX "DEANS_WORKER_INDEX" ON "DEANS_WORKER" ("DEANS_WORKER_ID") 
  )  ENABLE;
  ALTER TABLE "DEANS_WORKER" MODIFY ("DEANS_WORKER_ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table FACULTY
--------------------------------------------------------

  ALTER TABLE "FACULTY" MODIFY ("FACULTY_ID" NOT NULL ENABLE);
  ALTER TABLE "FACULTY" MODIFY ("NAME" NOT NULL ENABLE);
  ALTER TABLE "FACULTY" MODIFY ("DEAN_ID" NOT NULL ENABLE);
  ALTER TABLE "FACULTY" ADD CONSTRAINT "FACULTY_ID_PK" PRIMARY KEY ("FACULTY_ID")
  USING INDEX (CREATE UNIQUE INDEX "FACULTY_INDEX" ON "FACULTY" ("FACULTY_ID") 
  )  ENABLE;
--------------------------------------------------------
--  Constraints for Table GROUP
--------------------------------------------------------

  ALTER TABLE "GROUP" MODIFY ("GROUP_ID" NOT NULL ENABLE);
  ALTER TABLE "GROUP" MODIFY ("SUBJECT_ID" NOT NULL ENABLE);
  ALTER TABLE "GROUP" MODIFY ("PROFESSOR_ID" NOT NULL ENABLE);
  ALTER TABLE "GROUP" MODIFY ("DAY" NOT NULL ENABLE);
  ALTER TABLE "GROUP" MODIFY ("TIME" NOT NULL ENABLE);
  ALTER TABLE "GROUP" MODIFY ("FORM" NOT NULL ENABLE);
  ALTER TABLE "GROUP" ADD CONSTRAINT "GROUP_ID_PK" PRIMARY KEY ("GROUP_ID")
  USING INDEX (CREATE UNIQUE INDEX "GROUP_INDEX" ON "GROUP" ("GROUP_ID") 
  )  ENABLE;
--------------------------------------------------------
--  Constraints for Table PERSONAL_DATA
--------------------------------------------------------

  ALTER TABLE "PERSONAL_DATA" MODIFY ("FIRST_NAME" NOT NULL ENABLE);
  ALTER TABLE "PERSONAL_DATA" MODIFY ("LAST_NAME" NOT NULL ENABLE);
  ALTER TABLE "PERSONAL_DATA" MODIFY ("EMAIL" NOT NULL ENABLE);
  ALTER TABLE "PERSONAL_DATA" MODIFY ("FACULTY_ID" NOT NULL ENABLE);
  ALTER TABLE "PERSONAL_DATA" ADD CONSTRAINT "PERSONAL_DATA_USER_ID_PK" PRIMARY KEY ("USER_ID")
  USING INDEX (CREATE UNIQUE INDEX "PERSONAL_DATA_INDEX" ON "PERSONAL_DATA" ("USER_ID") 
  )  ENABLE;
--------------------------------------------------------
--  Constraints for Table PROFESSOR
--------------------------------------------------------

  ALTER TABLE "PROFESSOR" MODIFY ("PROFESSOR_ID" NOT NULL ENABLE);
  ALTER TABLE "PROFESSOR" MODIFY ("DEGREE" NOT NULL ENABLE);
  ALTER TABLE "PROFESSOR" ADD CONSTRAINT "PROFESSOR_ID_PK" PRIMARY KEY ("PROFESSOR_ID")
  USING INDEX (CREATE UNIQUE INDEX "PROFESSOR_INDEX" ON "PROFESSOR" ("PROFESSOR_ID") 
  )  ENABLE;
--------------------------------------------------------
--  Constraints for Table RECORD
--------------------------------------------------------

  ALTER TABLE "RECORD" MODIFY ("GROUP_ID" NOT NULL ENABLE);
  ALTER TABLE "RECORD" MODIFY ("STUDENT_ID" NOT NULL ENABLE);
  ALTER TABLE "RECORD" MODIFY ("RECORD_DATE" NOT NULL ENABLE);
  ALTER TABLE "RECORD" ADD CONSTRAINT "RECORD_ID_PK" PRIMARY KEY ("GROUP_ID", "STUDENT_ID")
  USING INDEX (CREATE UNIQUE INDEX "RECORD_INDEX" ON "RECORD" ("GROUP_ID", "STUDENT_ID") 
  )  ENABLE;
--------------------------------------------------------
--  Constraints for Table SPECIALIZATION
--------------------------------------------------------

  ALTER TABLE "SPECIALIZATION" MODIFY ("SPECIALIZATION_ID" NOT NULL ENABLE);
  ALTER TABLE "SPECIALIZATION" MODIFY ("NAME" NOT NULL ENABLE);
  ALTER TABLE "SPECIALIZATION" MODIFY ("FACULTY_ID" NOT NULL ENABLE);
  ALTER TABLE "SPECIALIZATION" ADD CONSTRAINT "SPECIALIZATION_ID_PK" PRIMARY KEY ("SPECIALIZATION_ID")
  USING INDEX (CREATE UNIQUE INDEX "SPECIALIZATION_INDEX" ON "SPECIALIZATION" ("SPECIALIZATION_ID") 
  )  ENABLE;
--------------------------------------------------------
--  Constraints for Table STUDENT
--------------------------------------------------------

  ALTER TABLE "STUDENT" MODIFY ("STUDENT_ID" NOT NULL ENABLE);
  ALTER TABLE "STUDENT" MODIFY ("PESEL" NOT NULL ENABLE);
  ALTER TABLE "STUDENT" MODIFY ("YEAR" NOT NULL ENABLE);
  ALTER TABLE "STUDENT" MODIFY ("SEMESTER" NOT NULL ENABLE);
  ALTER TABLE "STUDENT" MODIFY ("SPECIALIZATION_ID" NOT NULL ENABLE);
  ALTER TABLE "STUDENT" ADD CONSTRAINT "STUDENT_ID_PK" PRIMARY KEY ("STUDENT_ID")
  USING INDEX (CREATE UNIQUE INDEX "STUDENT_INDEX" ON "STUDENT" ("STUDENT_ID") 
  )  ENABLE;
  ALTER TABLE "STUDENT" ADD CONSTRAINT "STUDENT_ADMISSION_DATE_UK" UNIQUE ("ADMISSION_DATE")
  USING INDEX (CREATE UNIQUE INDEX "STUDENT_ADMISSION_DATE_INDEX" ON "STUDENT" ("ADMISSION_DATE") 
  )  ENABLE;
--------------------------------------------------------
--  Constraints for Table SUBJECT
--------------------------------------------------------

  ALTER TABLE "SUBJECT" MODIFY ("SUBJECT_NAME" NOT NULL ENABLE);
  ALTER TABLE "SUBJECT" MODIFY ("FACULTY_ID" NOT NULL ENABLE);
  ALTER TABLE "SUBJECT" ADD CONSTRAINT "SUBJECT_ID_PK" PRIMARY KEY ("SUBJECT_ID")
  USING INDEX (CREATE UNIQUE INDEX "SUBJECT_INDEX" ON "SUBJECT" ("SUBJECT_ID") 
  )  ENABLE;
--------------------------------------------------------
--  Constraints for Table USER
--------------------------------------------------------

  ALTER TABLE "USER" MODIFY ("USER_ID" NOT NULL ENABLE);
  ALTER TABLE "USER" MODIFY ("LOGIN" NOT NULL ENABLE);
  ALTER TABLE "USER" MODIFY ("PASSWORD" NOT NULL ENABLE);
  ALTER TABLE "USER" MODIFY ("TYPE" NOT NULL ENABLE);
  ALTER TABLE "USER" ADD CONSTRAINT "USER_ID_PK" PRIMARY KEY ("USER_ID")
  USING INDEX (CREATE UNIQUE INDEX "USER_INDEX" ON "USER" ("USER_ID") 
  )  ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table DEANS_WORKER
--------------------------------------------------------

  ALTER TABLE "DEANS_WORKER" ADD CONSTRAINT "DEANS_WORKER_PERSONAL_DATA_FK" FOREIGN KEY ("DEANS_WORKER_ID")
	  REFERENCES "PERSONAL_DATA" ("USER_ID") ENABLE;
  ALTER TABLE "DEANS_WORKER" ADD CONSTRAINT "DEANS_WORKER_USER_ID_FK" FOREIGN KEY ("DEANS_WORKER_ID")
	  REFERENCES "USER" ("USER_ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table FACULTY
--------------------------------------------------------

  ALTER TABLE "FACULTY" ADD CONSTRAINT "DEAN_ID_FK" FOREIGN KEY ("DEAN_ID")
	  REFERENCES "PROFESSOR" ("PROFESSOR_ID") DISABLE;
--------------------------------------------------------
--  Ref Constraints for Table GROUP
--------------------------------------------------------

  ALTER TABLE "GROUP" ADD CONSTRAINT "GROUP_SUBJECT_ID_FK" FOREIGN KEY ("SUBJECT_ID")
	  REFERENCES "SUBJECT" ("SUBJECT_ID") ENABLE;
  ALTER TABLE "GROUP" ADD CONSTRAINT "GROUP_PROFESSOR_ID_FK" FOREIGN KEY ("PROFESSOR_ID")
	  REFERENCES "PROFESSOR" ("PROFESSOR_ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table PERSONAL_DATA
--------------------------------------------------------

  ALTER TABLE "PERSONAL_DATA" ADD CONSTRAINT "PERSONAL_DATA_FACULTY_ID_FK" FOREIGN KEY ("FACULTY_ID")
	  REFERENCES "FACULTY" ("FACULTY_ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table PROFESSOR
--------------------------------------------------------

  ALTER TABLE "PROFESSOR" ADD CONSTRAINT "PROFESSOR_USER_ID_FK" FOREIGN KEY ("PROFESSOR_ID")
	  REFERENCES "USER" ("USER_ID") ENABLE;
  ALTER TABLE "PROFESSOR" ADD CONSTRAINT "PROFESSOR_PERSONAL_DATA_FK" FOREIGN KEY ("PROFESSOR_ID")
	  REFERENCES "PERSONAL_DATA" ("USER_ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table RECORD
--------------------------------------------------------

  ALTER TABLE "RECORD" ADD CONSTRAINT "STUDENT_ID_FK" FOREIGN KEY ("STUDENT_ID")
	  REFERENCES "STUDENT" ("STUDENT_ID") ENABLE;
  ALTER TABLE "RECORD" ADD CONSTRAINT "GROUP_ID_FK" FOREIGN KEY ("GROUP_ID")
	  REFERENCES "GROUP" ("GROUP_ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table SPECIALIZATION
--------------------------------------------------------

  ALTER TABLE "SPECIALIZATION" ADD CONSTRAINT "SPECIALIZATION_FACULTY_ID_FK" FOREIGN KEY ("FACULTY_ID")
	  REFERENCES "FACULTY" ("FACULTY_ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table STUDENT
--------------------------------------------------------

  ALTER TABLE "STUDENT" ADD CONSTRAINT "STUDENT_USER_ID_FK" FOREIGN KEY ("STUDENT_ID")
	  REFERENCES "USER" ("USER_ID") ENABLE;
  ALTER TABLE "STUDENT" ADD CONSTRAINT "STUDENT_SPECIALIZATION_FK" FOREIGN KEY ("SPECIALIZATION_ID")
	  REFERENCES "SPECIALIZATION" ("SPECIALIZATION_ID") ENABLE;
  ALTER TABLE "STUDENT" ADD CONSTRAINT "STUDENT_PERSONAL_DATA_FK" FOREIGN KEY ("STUDENT_ID")
	  REFERENCES "PERSONAL_DATA" ("USER_ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table SUBJECT
--------------------------------------------------------

  ALTER TABLE "SUBJECT" ADD CONSTRAINT "SUBJECT_FACULTY_FK" FOREIGN KEY ("FACULTY_ID")
	  REFERENCES "FACULTY" ("FACULTY_ID") ENABLE;
