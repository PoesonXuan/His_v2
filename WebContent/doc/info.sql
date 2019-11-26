create database tmp_db_his;

create table DB_DEPARTMENT(
	ID bigint primary key auto_increment COMMENT   '主键',
	NAME varchar(255) not null  COMMENT  '名称',
	EN_NAME varchar(255) not null COMMENT  '英文名'
);


create table DB_DICTIONARY(
	ID bigint primary key auto_increment COMMENT '主键',	 
	TABLE_NAME VARCHAR(255) COMMENT '表名称',
	FIELD_NAME VARCHAR(255) COMMENT '字段名',
	VAL VARCHAR(255)	 COMMENT '字段值',
	DES VARCHAR(255)	 COMMENT '描述'
);


create table DB_USER(
	ID bigint primary key auto_increment COMMENT '主键',
	LOGINNAME VARCHAR(255) COMMENT '登录名称',
	PWD VARCHAR(255) COMMENT '密码',
	NAME VARCHAR(255) COMMENT '名称',
	AGE INT COMMENT '年龄',
	SEX INT COMMENT '性别',
	LEV INT COMMENT '医务人员类别（管理员，维护人员，一般医务人员）',
	TELE VARCHAR(255) COMMENT '电话号码',
	DEPARTMENT_ID BIGINT COMMENT '所属科室'
);


create table DB_TICKET(
	ID bigint primary key auto_increment COMMENT '主键',
	NAME VARCHAR(255) COMMENT '名称',
	CODE VARCHAR(255) COMMENT '编码',
	STATUS INT COMMENT '工单状态',
	DESCRIPTION TEXT COMMENT '描述',
	STARTOR_ID bigint COMMENT '发起人',
	CHECKOR_ID bigint COMMENT '审批人',
	CHECKOR_DESC TEXT COMMENT '审批意见',
	DEALER_ID bigint COMMENT '维护人',
	DEALER_DESC TEXT COMMENT '维护人意见',
	DEPARTMENT_ID bigint COMMENT '所属部门'
);


create table DB_DUTY(
	ID bigint primary key auto_increment COMMENT '主键',
	NAME VARCHAR(255) COMMENT '排班表名称',
	DUTY_DESC VARCHAR(255) COMMENT '排班表描述详情',
	CREATE_ID BIGINT COMMENT '发起人',
    CREATE_DATE DATETIME COMMENT '发起日期'
);

create table DB_DUTY_INFO(
	ID bigint primary key auto_increment COMMENT '主键',
	DUTY_ID bigint COMMENT 'DB_DUTY（排版表）',
	DUTOR bigint COMMENT '值班人',
	DUTY_DATE DATETIME COMMENT '值班日期',
	STATUS int COMMENT '值班状态',
	INFO_DESC TEXT COMMENT '值班详情',
	START_DATE_TIME DATETIME COMMENT '开始时间',
	ND_DATE_TIME DATETIME COMMENT '结束时间',
	INFO TEXT COMMENT '值班审核信息'
);
