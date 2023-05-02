

CREATE TABLE IF NOT EXISTS UserInfo (
	Student_Id	Int	PRIMARY KEY,
	Student_Name	CHAR	NULL,
	PW	CHAR	NULL,
	Phone_Number	Int	NULL,
	Email	CHAR	NULL,
	Major	CHAR	NULL,
	Grade	CHAR	NULL,
	Doing	Text	NULL,
	Admin_Aut	Int	NULL,
	Condition	CHAR	NULL,
	Wanted_Act	Text	NULL,
	Introduction	Text	NULL,
	Join_Date	DATE	NULL,
	Like_Language	CHAR	NULL,
	PW2	CHAR	NULL
);

CREATE TABLE Calender (
	Calender_Id	Int	PRIMARY KEY,
	Student_Id	Int	NOT NULL,
	Calender_Register	DATE	NULL,
	Calender_Text	Text	NULL,
	Calender_Deadline	DATE	NULL,
	Calender_Entry	CHAR	NOT NULL,
	Calender_Category	CHAR	NULL,
	Calender_Replay	CHAR	NULL
);

CREATE TABLE Notice (
	Notice_Id	Int	PRIMARY KEY,
	Notice_Register	DATE	NULL,
	Student_Id 	Int	NOT NULL,
	Notice_Title	Text	NULL,
	Notice_Number	CHAR	NULL,
	Notice_Text	Text	NULL,
	Noitce_File	Int	NULL
);

CREATE TABLE NoticeComment (
	Com_Id	Int	PRIMARY KEY,
	Notice_Id	Int	NOT NULL,
	Student_Id	Int	NOT NULL,
	Notice_Register	DATE	NULL,
	Com_Text	Text	NULL,
	Com_Register	DATE	NULL
);

CREATE TABLE Important (
	Impor_Id	Int	PRIMARY KEY,
	Impor_Register	DATE	NULL,
	Student_Id	Int	NOT NULL,
	Impor_Title	Text	NULL,
	Important	Int	NULL,
	Impor_Text	Text	NULL,
	Impor_Deadline	DATE	NULL,
	Impor_File	Int	NULL
);

CREATE TABLE Reference (
	Impor_Id	Int	NOT NULL,
	Student_Id	Int	NOT NULL,
	Notice_Id	Int	NOT NULL,
	Impor_Register	DATE	NULL,
	Notice_Register	DATE	NULL,
	File_Id	Int	NULL,
	Origin_FileName	CHAR	NULL,
	File_Name	CHAR	NULL,
	File_Ext	CHAR	NULL,
	File_Path	CHAR	NULL,
	Delete_Yn	Int	NULL
);

CREATE TABLE LikeLanguage(
	Student_Id	Int	NOT NULL,
	Langu Char 
);

CREATE TABLE Schedule(
	Calender_Id Int NOT NULL,
	Student_Id INT NOT NULL
);

 ALTER TABLE Calender ADD FOREIGN KEY (Student_Id) REFERENCES UserInfo(Student_Id);
 ALTER TABLE Notice ADD FOREIGN KEY (Student_Id) REFERENCES UserInfo(Student_Id);
 ALTER TABLE Important ADD FOREIGN KEY (Student_Id) REFERENCES UserInfo(Student_Id);
 ALTER TABLE NoticeComment ADD FOREIGN KEY (Student_Id) REFERENCES UserInfo(Student_Id);
 ALTER TABLE NoticeComment ADD FOREIGN KEY (Notice_Id) REFERENCES Notice(Notice_Id);
 ALTER TABLE Reference ADD FOREIGN KEY (Impor_Id) REFERENCES Important(Impor_Id);
 ALTER TABLE Reference ADD FOREIGN KEY (Notice_Id) REFERENCES Notice(Notice_Id);
 ALTER TABLE LikeLanguage ADD FOREIGN KEY (Student_Id) REFERENCES UserInfo(Student_Id);
 ALTER TABLE Schedule ADD FOREIGN KEY (Calender_Id) REFERENCES Calender(Calender_Id);
 ALTER TABLE Schedule ADD FOREIGN KEY (Student_Id) REFERENCES UserInfo(Student_Id);
