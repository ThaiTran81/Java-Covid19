﻿USE QLC19
  GO

DROP PROC AddNewAccount
go
CREATE PROCEDURE AddNewAccount (@ID VARCHAR(15), @PASSWORD VARCHAR(50), @ROLE INT, @FULLNAME NVARCHAR(100)=NULL, @PHONENUMBER VARCHAR(20)=NULL, @DOB DATE=NULL, @GENDER NVARCHAR(5)=NULL, @PROVINE INT=NULL, @DISTRICT INT=NULL, @VILLAGE INT=NULL, @ID_QUARATINE INT=NULL)
AS 
BEGIN
     INSERT INTO PROFILE(ID, FULLNAME, PHONENUMBER, DOB, GENDER, PROVINE, DISTRICT, VILLAGE, ID_QUARATINE)
     VALUES(@ID, @FULLNAME,@PHONENUMBER,@DOB, @GENDER ,@PROVINE,@DISTRICT,@VILLAGE,@ID_QUARATINE)

     INSERT INTO ACCOUNT(USERNAME, PASSWORD, TYPE)
     VALUES(@ID, @PASSWORD,@ROLE)
     IF @ROLE != 0 INSERT INTO PAYMENT(ID_BANK, BALANCE) VALUES (@ID,20000000)
END;

GO



--INSERT INTO PROFILE(ID, FULLNAME, PHONENUMBER, DOB, GENDER, PROVINE, DISTRICT, VILLAGE, ID_QUARATINE, ID_BANK)
-- 
--EXEC AddNewAccount @ID = '11223344'
--                 ,@PASSWORD = '123456'
--                  ,@ROLE = 2;
--SELECT * 
--FROM PROFILE p
--WHERE p.id='99887766'
--
--SELECT*
--FROM ACCOUNT a
--WHERE a.USERNAME='11223344'
