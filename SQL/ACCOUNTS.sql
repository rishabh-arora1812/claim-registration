CREATE TABLE ACCOUNTS(ACCOUNT_NUMBER NUMBER(10) CONSTRAINT Pk_accountNumber PRIMARY KEY, 
INSURED_NAME VARCHAR2(30), 
INSURED_STREET VARCHAR2(40), 
INSURED_CITY VARCHAR2(15), 
INSURED_STATE VARCHAR2(15), 
INSURED_ZIP NUMBER(6), 
BUSINESS_SEGMENT VARCHAR2(30), 
INSURED_USER_NAME VARCHAR2(20) CONSTRAINT FK_INSURED_USERNAME REFERENCES USER_ROLE(USER_NAME) ON DELETE CASCADE, 
AGENT_USER_NAME VARCHAR2(20) CONSTRAINT FK_AGENT_USERNAME REFERENCES USER_ROLE(USER_NAME) ON DELETE CASCADE) ;


CREATE SEQUENCE ACCOUNT_SEQ START WITH 1000000000 INCREMENT BY 10;

INSERT INTO Accounts VALUES(ACCOUNT_SEQ.NEXTVAL,'Abhyudai Mishra','Pali Hill','Juhu','Goa',105223,'Apartment','Abhyudai','harekrushna');
INSERT INTO Accounts VALUES(ACCOUNT_SEQ.NEXTVAL,'Aman Kumar Meena','Chor Bazaar','Delhi','Center',115334,'Vehicle','Aman','harekrushna');
INSERT INTO Accounts VALUES(ACCOUNT_SEQ.NEXTVAL,'Amiratna Bhoyar','Jhumri','Nirvana','Goa',105646,'Apartment','Amiratna','harekrushna');
INSERT INTO Accounts VALUES(ACCOUNT_SEQ.NEXTVAL,'Aneesh K Srivastava','Talliya','Bikaner','Rajasthan',105757,'Vehicle','Aneesh','harekrushna');
INSERT INTO Accounts VALUES(ACCOUNT_SEQ.NEXTVAL,'Anirudh Sen','ASTARANG','PURI','ODISHA',105322,'Apartment','Anirudh','harekrushna');


INSERT INTO Accounts VALUES(ACCOUNT_SEQ.NEXTVAL,'Ankita Bhalavi','Naka','Darbhanga','Bihar',105976,'Vehicle','Ankita','akhil');
INSERT INTO Accounts VALUES(ACCOUNT_SEQ.NEXTVAL,'Arkala Anuvrath','Chulla','Bhopal','Madhya Pradesh',105017,'Apartment','Arkala','akhil');
INSERT INTO Accounts VALUES(ACCOUNT_SEQ.NEXTVAL,'Avakash Yadav','Wadiyah','Lalganj','Kashmir',105658,'Vehicle','Avakash','akhil');
INSERT INTO Accounts VALUES(ACCOUNT_SEQ.NEXTVAL,'Azif Pulliyil Hidayath','Naka','Darbhanga','Bihar',105976,'Vehicle','Azif','akhil');
INSERT INTO Accounts VALUES(ACCOUNT_SEQ.NEXTVAL,'Gera Abhiram','Chulla','Bhopal','Madhya Pradesh',105017,'Abhiram','Abhiram','akhil');


INSERT INTO Accounts VALUES(ACCOUNT_SEQ.NEXTVAL,'Dheeraj Rana','Wadiyah','Lalganj','Kashmir',105658,'Vehicle','Dheeraj','mayank');
INSERT INTO Accounts VALUES(ACCOUNT_SEQ.NEXTVAL,'Dhiraj Mishra','Naka','Darbhanga','Bihar',105976,'Vehicle','Dhiraj','mayank');
INSERT INTO Accounts VALUES(ACCOUNT_SEQ.NEXTVAL,'Gokul T R','Chulla','Bhopal','Madhya Pradesh',105017,'Apartment','Gokul','mayank');
INSERT INTO Accounts VALUES(ACCOUNT_SEQ.NEXTVAL,'Ishant Singh Bhadauriya','Wadiyah','Lalganj','Kashmir',105658,'Vehicle','Ishant','mayank');
INSERT INTO Accounts VALUES(ACCOUNT_SEQ.NEXTVAL,'Kalpam Niharik','Naka','Darbhanga','Bihar',105976,'Vehicle','Niharik','mayank');

INSERT INTO Accounts VALUES(ACCOUNT_SEQ.NEXTVAL,'Katyayan Aanand','Wadiyah','Lalganj','Kashmir',105658,'Vehicle','Katyayan','sravani');
INSERT INTO Accounts VALUES(ACCOUNT_SEQ.NEXTVAL,'Kondeti Satyanarayana','Naka','Darbhanga','Bihar',105976,'Vehicle','Satyanarayana','sravani');
INSERT INTO Accounts VALUES(ACCOUNT_SEQ.NEXTVAL,'Mahendra Choudhary','Chulla','Bhopal','Madhya Pradesh',105017,'Apartment','Mahendra','sravani');
INSERT INTO Accounts VALUES(ACCOUNT_SEQ.NEXTVAL,'Medidi Geethika','Wadiyah','Lalganj','Kashmir',105658,'Vehicle','Geethika','sravani');
INSERT INTO Accounts VALUES(ACCOUNT_SEQ.NEXTVAL,'Mohit Mundra','Naka','Darbhanga','Bihar',105976,'Vehicle','Mohit','sravani');

INSERT INTO Accounts VALUES(ACCOUNT_SEQ.NEXTVAL,'Naga Sai Praneeth Gunturu','Wadiyah','Lalganj','Kashmir',105658,'Vehicle','Praneeth','jayraj');
INSERT INTO Accounts VALUES(ACCOUNT_SEQ.NEXTVAL,'Namuduri Sree Srujana','Naka','Darbhanga','Bihar',105976,'Vehicle','Srujana','jayraj');
INSERT INTO Accounts VALUES(ACCOUNT_SEQ.NEXTVAL,'Nihaal Ahmed Shibly','Chulla','Bhopal','Madhya Pradesh',105017,'Apartment','Nihaal','jayraj');
INSERT INTO Accounts VALUES(ACCOUNT_SEQ.NEXTVAL,'Nutan Kumari','Wadiyah','Lalganj','Kashmir',105658,'Vehicle','Nutan','jayraj');
INSERT INTO Accounts VALUES(ACCOUNT_SEQ.NEXTVAL,'Pulkit Agrawal','Naka','Darbhanga','Bihar',105976,'Vehicle','Pulkit','jayraj');

INSERT INTO Accounts VALUES(ACCOUNT_SEQ.NEXTVAL,'Puneet Mohanpuria','Wadiyah','Lalganj','Kashmir',105658,'Vehicle','Puneet','shivam');
INSERT INTO Accounts VALUES(ACCOUNT_SEQ.NEXTVAL,'Rishabh Arora','Naka','Darbhanga','Bihar',105976,'Vehicle','Rishabh','shivam');
INSERT INTO Accounts VALUES(ACCOUNT_SEQ.NEXTVAL,'Shashanka Mallikarjun','Chulla','Bhopal','Madhya Pradesh',105017,'Apartment','Shashanka','shivam');
INSERT INTO Accounts VALUES(ACCOUNT_SEQ.NEXTVAL,'Shubham Gupta','Wadiyah','Lalganj','Kashmir',105658,'Vehicle','Shubham','shivam');


INSERT INTO Accounts VALUES(ACCOUNT_SEQ.NEXTVAL,'Tushita Rathore','Wadiyah','Lalganj','Kashmir',105658,'Vehicle','Tushita','siddhant');
INSERT INTO Accounts VALUES(ACCOUNT_SEQ.NEXTVAL,'Vinay Bukke','Naka','Darbhanga','Bihar',105976,'Vehicle','Vinay','siddhant');
INSERT INTO Accounts VALUES(ACCOUNT_SEQ.NEXTVAL,'Vishal Gupta','Chulla','Bhopal','Madhya Pradesh',105017,'Apartment','Vishal','siddhant');
INSERT INTO Accounts VALUES(ACCOUNT_SEQ.NEXTVAL,'Vishesh Kumar Sharma','Wadiyah','Lalganj','Kashmir',105658,'Vehicle','Vishesh','siddhant');
COMMIT;

SET LINESIZE 1000;
COLUMN ACCOUNT_NUMBER FORMAT 9999999999;
COLUMN INSURED_NAME FORMAT A20;
COLUMN INSURED_CITY FORMAT A20;
COLUMN INSURED_STATE FORMAT A20;
COLUMN BUSINESS_SEGMENT FORMAT A20;
COLUMN INSURED_USER_NAMR FORMAT A20;
COLUMN AGENT_USER_NAMR FORMAT A20;
COLUMN INSURED_ZIP FORMAT 999999;
COLUMN INSURED_STREET FORMAT A20;

SELECT * FROM ACCOUNTS;



