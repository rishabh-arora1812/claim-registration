CREATE TABLE CLAIM_QUESTIONS(CLAIM_TYPE VARCHAR2(30), 
QUESTION_ID NUMBER(10) CONSTRAINT Pk_QUESTION_ID PRIMARY KEY,
QUESTION  VARCHAR2(80), 
ANSWER_1 VARCHAR2(80), 
ANSWER_2 VARCHAR(80), 
ANSWER_3 VARCHAR2(80));


INSERT INTO CLAIM_QUESTIONS VALUES('Motor Insurance', 100, 'Select Vehicle Type from below menu:', 'Light-Duty[2-Wheeler, Auto-Richshaw, Sports-Car]', 'Medium-Duty[SUV, Mini-Bus, Step-Van]', 'Heavy-duty[Trailer-Truck, Passenger-Bus, Lorry]');

INSERT INTO CLAIM_QUESTIONS VALUES('Motor Insurance', 101, 'Select Vehicle Class:', 'Private', 'Commercial','');

INSERT INTO CLAIM_QUESTIONS VALUES('Motor Insurance', 102, 'Enter Chasis-Number of Vehicle:', '', '','');

INSERT INTO CLAIM_QUESTIONS VALUES('Motor Insurance', 103, 'Select type of Service Center:', 'Authorised', 'Unauthorised','');

INSERT INTO CLAIM_QUESTIONS VALUES('Motor Insurance', 104, 'Enter Model Year:', '', '','');

INSERT INTO CLAIM_QUESTIONS VALUES('Motor Insurance', 105, 'Enter Distance travelled(in kms):', '', '','');

INSERT INTO CLAIM_QUESTIONS VALUES('Home Insurance', 200, 'Select Type of Loss:', 'Natural Calamity[Earthquake, Lightning Strike, Flood]','Burglary/Theft','Appliance Failure[Gas-Leak, Faulty Power-Grid]');

INSERT INTO CLAIM_QUESTIONS VALUES('Home Insurance', 201, 'Enter Loss of Property(Sum Value Of Loss):', '','','');

INSERT INTO CLAIM_QUESTIONS VALUES('Home Insurance', 202, 'Any Official Report Status[Police or Fire Brigade] ?', 'Yes','No','Not Applicable');

INSERT INTO CLAIM_QUESTIONS VALUES('Home Insurance', 203, 'Enter Salvage Value:', '','','');

INSERT INTO CLAIM_QUESTIONS VALUES('Home Insurance', 204, 'Enter Year of Property ?', '','','');

INSERT INTO CLAIM_QUESTIONS VALUES('Home Insurance', 205, 'Select of Degree Of Ownership:', 'Sole Owner','Shared Ownership','');

INSERT INTO CLAIM_QUESTIONS VALUES('Health Insurance', 300, 'Enter Hospitalisation Charges:', '','','');

INSERT INTO CLAIM_QUESTIONS VALUES('Health Insurance', 301, 'Opted For Surgery ?', 'Yes', 'No', 'In Progress');

INSERT INTO CLAIM_QUESTIONS VALUES('Health Insurance', 302, 'Enter Date Of Hospitalisation:', '', '', '');

INSERT INTO CLAIM_QUESTIONS VALUES('Health Insurance', 303, 'Is there any Amputation/Need of Transplant ?', 'Yes', 'No', '');

INSERT INTO CLAIM_QUESTIONS VALUES('Health Insurance', 304, 'Select Degree Of Paralysis:', 'Above 75%', '20%-75%', 'Not Applicable');

INSERT INTO CLAIM_QUESTIONS VALUES('Health Insurance', 305, 'Any Need Of Transfer ?', 'Yes', 'No', 'Not Sure');

COMMIT;