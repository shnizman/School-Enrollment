DROP TABLE IF EXISTS SCHOOL;
DROP TABLE IF EXISTS PUPIL;
DROP TABLE IF EXISTS GRADES;
DROP TABLE IF EXISTS FRIENDSHIP;

create table SCHOOL
(
   ID BIGINT not null AUTO_INCREMENT,
   LATITUDE double not null,
   LONGITUDE double not null,
   MINIMUMGPA int not null,
   MAXNUMBEROFPUPILS int not null,
   ENROOLED int not null,
   primary key(ID)
);

create table PUPIL
(
   ID BIGINT not null AUTO_INCREMENT,
   LATITUDE double not null,
   LONGITUDE double not null,
   SCHOOLID BIGINT,
   primary key(ID),
   foreign key (SCHOOLID) references SCHOOL(ID)
);

create table GRADES
(
   PUPILID BIGINT not null,
   COURSENAME varchar(255) not null,
   GRADE int not null,
   primary key(PUPILID,COURSENAME),
   foreign key (PUPILID) references PUPIL(ID)
);

create table FRIENDSHIP
(
   PUPILIDA BIGINT not null,
   PUPILIDB BIGINT not null,
   primary key(PUPILIDA,PUPILIDB),
   foreign key (PUPILIDA) references PUPIL(ID)
);


