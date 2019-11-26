create database tmp_db_his;

create table DB_DEPARTMENT(
	ID bigint primary key auto_increment COMMENT   '����',
	NAME varchar(255) not null  COMMENT  '����',
	EN_NAME varchar(255) not null COMMENT  'Ӣ����'
);


create table DB_DICTIONARY(
	ID bigint primary key auto_increment COMMENT '����',	 
	TABLE_NAME VARCHAR(255) COMMENT '������',
	FIELD_NAME VARCHAR(255) COMMENT '�ֶ���',
	VAL VARCHAR(255)	 COMMENT '�ֶ�ֵ',
	DES VARCHAR(255)	 COMMENT '����'
);


create table DB_USER(
	ID bigint primary key auto_increment COMMENT '����',
	LOGINNAME VARCHAR(255) COMMENT '��¼����',
	PWD VARCHAR(255) COMMENT '����',
	NAME VARCHAR(255) COMMENT '����',
	AGE INT COMMENT '����',
	SEX INT COMMENT '�Ա�',
	LEV INT COMMENT 'ҽ����Ա��𣨹���Ա��ά����Ա��һ��ҽ����Ա��',
	TELE VARCHAR(255) COMMENT '�绰����',
	DEPARTMENT_ID BIGINT COMMENT '��������'
);


create table DB_TICKET(
	ID bigint primary key auto_increment COMMENT '����',
	NAME VARCHAR(255) COMMENT '����',
	CODE VARCHAR(255) COMMENT '����',
	STATUS INT COMMENT '����״̬',
	DESCRIPTION TEXT COMMENT '����',
	STARTOR_ID bigint COMMENT '������',
	CHECKOR_ID bigint COMMENT '������',
	CHECKOR_DESC TEXT COMMENT '�������',
	DEALER_ID bigint COMMENT 'ά����',
	DEALER_DESC TEXT COMMENT 'ά�������',
	DEPARTMENT_ID bigint COMMENT '��������'
);


create table DB_DUTY(
	ID bigint primary key auto_increment COMMENT '����',
	NAME VARCHAR(255) COMMENT '�Ű������',
	DUTY_DESC VARCHAR(255) COMMENT '�Ű����������',
	CREATE_ID BIGINT COMMENT '������',
    CREATE_DATE DATETIME COMMENT '��������'
);

create table DB_DUTY_INFO(
	ID bigint primary key auto_increment COMMENT '����',
	DUTY_ID bigint COMMENT 'DB_DUTY���Ű��',
	DUTOR bigint COMMENT 'ֵ����',
	DUTY_DATE DATETIME COMMENT 'ֵ������',
	STATUS int COMMENT 'ֵ��״̬',
	INFO_DESC TEXT COMMENT 'ֵ������',
	START_DATE_TIME DATETIME COMMENT '��ʼʱ��',
	ND_DATE_TIME DATETIME COMMENT '����ʱ��',
	INFO TEXT COMMENT 'ֵ�������Ϣ'
);
