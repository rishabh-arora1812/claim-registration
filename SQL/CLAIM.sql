CREATE TABLE CLAIM(CLAIM_NUMBER NUMBER(10) CONSTRAINT Pk_CLAIM_NUMBER  PRIMARY KEY, 
CLAIM_REASON VARCHAR2(30), 
ACCIDENT_LOCATION_STREET VARCHAR2(40), 
ACCIDENT_CITY VARCHAR2(15), 
ACCIDENT_STATE VARCHAR2(15), 
ACCIDENT_ZIP NUMBER(6), 
CLAIM_TYPE VARCHAR(30), 
POLICY_NUMBER NUMBER(10) CONSTRAINT FK_INSURED_USERNAME_inClaim REFERENCES POLICY(POLICY_NUMBER) ON DELETE CASCADE );


DROP SEQUENCE CLAIM_SEQ;

CREATE SEQUENCE CLAIM_SEQ START WITH 1000000;
