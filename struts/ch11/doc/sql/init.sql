prompt PL/SQL Developer import file
prompt Created on 2011��3��11�� by nan.chen
set feedback off
set define off
prompt Disabling triggers for DISTRICT...
alter table DISTRICT disable all triggers;
prompt Disabling triggers for STREET...
alter table STREET disable all triggers;
prompt Disabling triggers for TYPE...
alter table TYPE disable all triggers;
prompt Disabling triggers for USERS...
alter table USERS disable all triggers;
prompt Disabling foreign key constraints for STREET...
alter table STREET disable constraint STREET_FK;
prompt Deleting USERS...
delete from USERS;
commit;
prompt Deleting TYPE...
delete from TYPE;
commit;
prompt Deleting STREET...
delete from STREET;
commit;
prompt Deleting DISTRICT...
delete from DISTRICT;
commit;
prompt Loading DISTRICT...
insert into DISTRICT (ID, NAME)
values (1001, '����');
insert into DISTRICT (ID, NAME)
values (1002, '����');
insert into DISTRICT (ID, NAME)
values (1003, 'ʯ��ɽ');
insert into DISTRICT (ID, NAME)
values (1006, '����');
insert into DISTRICT (ID, NAME)
values (1000, '��̨');
insert into DISTRICT (ID, NAME)
values (1004, '����');
commit;
prompt 6 records loaded
prompt Loading STREET...
insert into STREET (ID, NAME, DISTRICT_ID)
values (1000, '֪��·', 1004);
insert into STREET (ID, NAME, DISTRICT_ID)
values (1001, '�йش���', 1004);
insert into STREET (ID, NAME, DISTRICT_ID)
values (1002, 'ѧԺ·', 1004);
insert into STREET (ID, NAME, DISTRICT_ID)
values (1003, '����·', 1006);
commit;
prompt 4 records loaded
prompt Loading TYPE...
insert into TYPE (ID, NAME)
values (1000, 'һ��һ��');
insert into TYPE (ID, NAME)
values (1001, 'һ������');
insert into TYPE (ID, NAME)
values (1002, '����һ��');
insert into TYPE (ID, NAME)
values (1003, '��������');
insert into TYPE (ID, NAME)
values (1004, '����һ��');
insert into TYPE (ID, NAME)
values (1005, '��������');
insert into TYPE (ID, NAME)
values (1006, '����һ��');
insert into TYPE (ID, NAME)
values (1007, '��������');
insert into TYPE (ID, NAME)
values (1008, '��ʮ����');
commit;
prompt 9 records loaded
prompt Loading USERS...
insert into USERS (ID, NAME)
values (1001, 'accp');
insert into USERS (ID, NAME)
values (1002, 'jbit');
insert into USERS (ID, NAME)
values (1000, 'admin');
insert into USERS (ID, NAME)
values (60, 'ACCP6');
insert into USERS (ID, NAME)
values (77, 'accp5');
commit;
prompt 5 records loaded
prompt Enabling foreign key constraints for STREET...
alter table STREET enable constraint STREET_FK;
prompt Enabling triggers for DISTRICT...
alter table DISTRICT enable all triggers;
prompt Enabling triggers for STREET...
alter table STREET enable all triggers;
prompt Enabling triggers for TYPE...
alter table TYPE enable all triggers;
prompt Enabling triggers for USERS...
alter table USERS enable all triggers;
set feedback on
set define on
prompt Done.
