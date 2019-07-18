SELECT * FROM NOT_EXISTS_IN_PRD_DB;

DELETE TEAM_CHAT; 
DELETE TEAM_CHAT_ROOM; 
DELETE TEAM_CALENDAR; 
DELETE TEAM_CALENDAR_CATEGORY; 
DELETE TABLE_LOCK; 
DELETE INVITE; 
DELETE CREW; 
DELETE ACCOUNT; 
DELETE QUIZ_ANSWER; 
DELETE QUIZ_EXAMPLE; 
DELETE QUIZ; 
DELETE FILTERING; 
DELETE USER_TEMPLATE; 
DELETE COMMON_TEMPLATE; 
DELETE USER_DOMAIN; 
DELETE COMMON_DOMAIN; 
DELETE BLACKLIST; 
DELETE REPLY; 
DELETE POST; 
DELETE BOARD; 
DELETE USERS; 

INSERT INTO USERS VALUES('admin', 'A', 'N', 'admin123', '관리자', 'admin@admin.com', sysdate, null);

insert into users 
(
    SELECT  'TEST_ID' || LEVEL,
        'C',
        'N',
        'TEST_PW' || LEVEL,
        'TEST_NAME' || LEVEL,
        'TEST_MAIL' || LEVEL || '@TEST.COM',
        SYSDATE,
        NULL
    FROM    DUAL
    CONNECT BY LEVEL <= 20
);

insert into board values(BOARD_SEQ.NEXTVAL, 'admin', '테스트게시판1', 'Y', SYSDATE);
insert into board values(BOARD_SEQ.NEXTVAL, 'admin', '테스트게시판2', 'N', SYSDATE);
insert into board values(BOARD_SEQ.NEXTVAL, 'admin', '테스트게시판3', 'Y', SYSDATE);

insert into post values(POST_SEQ.NEXTVAL, null, 'TEST_ID1', BOARD_SEQ.CURRVAL, '게시글테스트제목1', '게시글테스트내용1', sysdate, 'Y', POST_SEQ.CURRVAL);
insert into post values(POST_SEQ.NEXTVAL, null, 'TEST_ID1', BOARD_SEQ.CURRVAL, '게시글테스트제목2', '게시글테스트내용2', sysdate, 'Y', POST_SEQ.CURRVAL);
insert into post values(POST_SEQ.NEXTVAL, null, 'TEST_ID1', BOARD_SEQ.CURRVAL, '게시글테스트제목3', '게시글테스트내용3', sysdate, 'Y', POST_SEQ.CURRVAL);
insert into post values(POST_SEQ.NEXTVAL, null, 'TEST_ID1', BOARD_SEQ.CURRVAL, '게시글테스트제목4', '게시글테스트내용4', sysdate, 'Y', POST_SEQ.CURRVAL);
insert into post values(POST_SEQ.NEXTVAL, null, 'TEST_ID1', BOARD_SEQ.CURRVAL, '게시글테스트제목5', '게시글테스트내용5', sysdate, 'Y', POST_SEQ.CURRVAL);

insert into reply values(REPLY_SEQ.NEXTVAL, 'TEST_ID2', POST_SEQ.CURRVAL, '댓글테스트1', 'Y', sysdate);
insert into reply values(REPLY_SEQ.NEXTVAL, 'TEST_ID2', POST_SEQ.CURRVAL, '댓글테스트2', 'Y', sysdate);
insert into reply values(REPLY_SEQ.NEXTVAL, 'TEST_ID2', POST_SEQ.CURRVAL, '댓글테스트3', 'Y', sysdate);
insert into reply values(REPLY_SEQ.NEXTVAL, 'TEST_ID2', POST_SEQ.CURRVAL, '댓글테스트4', 'Y', sysdate);
insert into reply values(REPLY_SEQ.NEXTVAL, 'TEST_ID2', POST_SEQ.CURRVAL, '댓글테스트5', 'Y', sysdate);

insert into blacklist values(BLACKLIST_SEQ.NEXTVAL, 'TEST_ID19', '못생김', sysdate, 'admin', null, null);
insert into blacklist values(BLACKLIST_SEQ.NEXTVAL, 'TEST_ID20', '너무못생김', sysdate, 'admin', null, null);

insert into common_domain values(COMMON_DOMAIN_SEQ.NEXTVAL, '주민등록번호', 'VARCHAR2(13)');
insert into common_domain values(COMMON_DOMAIN_SEQ.NEXTVAL, '주소', 'VARCHAR2(255)');
insert into common_domain values(COMMON_DOMAIN_SEQ.NEXTVAL, '웹주소', 'VARCHAR2(255)');
insert into common_domain values(COMMON_DOMAIN_SEQ.NEXTVAL, '전화번호', 'VARCHAR2(30)');
insert into common_domain values(COMMON_DOMAIN_SEQ.NEXTVAL, '이메일', 'VARCHAR2(40)');
insert into common_domain values(COMMON_DOMAIN_SEQ.NEXTVAL, '이름', 'VARCHAR2(50)');
insert into common_domain values(COMMON_DOMAIN_SEQ.NEXTVAL, '숫자', 'NUMBER');
insert into common_domain values(COMMON_DOMAIN_SEQ.NEXTVAL, '일시', 'DATE');

INSERT INTO USER_DOMAIN VALUES(USER_DOMAIN_SEQ.NEXTVAL, 'TEST_ID20', '나이', 'NUMBER');

insert into common_template values(COMMON_TEMPLATE_SEQ.NEXTVAL, 'CT', 'CREATE TABLE 테이블명(컬렁명 타입)');
insert into common_template values(COMMON_TEMPLATE_SEQ.NEXTVAL, 'ATA', 'ALTER TABLE 테이블명 ADD(컬럼명 데이터타입(사이즈))');
insert into common_template values(COMMON_TEMPLATE_SEQ.NEXTVAL, 'ATM', 'ALTER TABLE 테이블명 MODIFY(컬럼명 데이터타입(사이즈))');
insert into common_template values(COMMON_TEMPLATE_SEQ.NEXTVAL, 'ATD', 'ALTER TABLE 테이블명 DROP COLUMN(컬럼명)');
insert into common_template values(COMMON_TEMPLATE_SEQ.NEXTVAL, 'ATR', 'ALTER TABLE 테이블명 RENAME COLUMN(기존컬럼명 TO 변경컬럼명)');
insert into common_template values(COMMON_TEMPLATE_SEQ.NEXTVAL, 'DT', 'DROP TABLE 테이블명');
insert into common_template values(COMMON_TEMPLATE_SEQ.NEXTVAL, 'SFW', 'SELETE 컬럼명 FROM 테이블명 WHERE 조건');
insert into common_template values(COMMON_TEMPLATE_SEQ.NEXTVAL, 'IIU', 'INSERT INTO 테이블명(컬럼명) VALUES(데이터)');
insert into common_template values(COMMON_TEMPLATE_SEQ.NEXTVAL, 'USW', 'UPDATE 테이블명 SET 변경할내용 WHERE 조건');
insert into common_template values(COMMON_TEMPLATE_SEQ.NEXTVAL, 'DFW', 'DELETE FROM 테이블명 WHERE 조건');

insert into USER_template values(USER_TEMPLATE_SEQ.NEXTVAL, 'TEST_ID20','ASD', '테스트 원문', 'yes');

INSERT INTO filtering VALUES(FILTERING_SEQ.NEXTVAL, 'admin', sysdate, '시볼');

INSERT INTO QUIZ VALUES(QUIZ_SEQ.NEXTVAL, 'admin', '01', 'TESTQUIZ', SYSDATE);
INSERT INTO QUIZ_EXAMPLE VALUES(QUIZ_EXAMPLE_SEQ.NEXTVAL, QUIZ_SEQ.CURRVAL, 1, '선택지 내용 테스트');
INSERT INTO QUIZ_ANSWER VALUES(QUIZ_ANSWER_SEQ.NEXTVAL, QUIZ_SEQ.CURRVAL, '답 테스트', '해설 테스트');

INSERT INTO ACCOUNT VALUES('testDB', 'TEST_ID20', 'TEST_PW');

INSERT INTO CREW VALUES('testDB', 'TEST_ID20');

INSERT INTO INVITE VALUES(INVITE_SEQ.NEXTVAL, 'testDB', 'TEST_ID19');

INSERT INTO TABLE_LOCK VALUES('test_TABLE', 'testDB', 'TEST_ID20');

INSERT INTO TEAM_CALENDAR_CATEGORY VALUES(TEAM_CALENDAR_CATEGORY_SEQ.NEXTVAL, '테스트 일정명');

INSERT INTO TEAM_CALENDAR VALUES(TEAM_CALENDAR_SEQ.NEXTVAL, TEAM_CALENDAR_CATEGORY_SEQ.CURRVAL, 'testDB', 'TEST_ID20', SYSDATE, SYSDATE, SYSDATE, '테스트 캘린더 제목', '테스트 캘린더 내용', '테스트 캘런더 배경색', '테스트 캘린더 구분');

INSERT INTO TEAM_CHAT_ROOM VALUES(TEAM_CHAT_ROOM_SEQ.NEXTVAL, 'testDB', 'TEST_ID20', '테스트 채팅방');

INSERT INTO TEAM_CHAT VALUES(TEAM_CHAT_SEQ.NEXTVAL, TEAM_CHAT_ROOM_SEQ.CURRVAL, 'testDB', 'TEST_ID20', '테스트 채팅 내용', SYSDATE);

commit;
