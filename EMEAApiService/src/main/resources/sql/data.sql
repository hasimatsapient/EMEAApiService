INSERT INTO user_details (username,email, password, activated) VALUES ('admin', 'admin@mail.me', '19dd6b59fc02091d6bb881286f8ff0a0958fc9df308812b62065c346cec792c8d70d23035674822d', 'true');
INSERT INTO user_details (username,email, password, activated) VALUES ('user', 'user@mail.me', '19dd6b59fc02091d6bb881286f8ff0a0958fc9df308812b62065c346cec792c8d70d23035674822d', 'true');
INSERT INTO user_details (username,email, password, activated) VALUES ('hasim', 'hasim@abc.com', '19dd6b59fc02091d6bb881286f8ff0a0958fc9df308812b62065c346cec792c8d70d23035674822d', 'true');
INSERT INTO authority (name) VALUES ('ROLE_USER');
INSERT INTO authority (name) VALUES ('ROLE_ADMIN');
INSERT INTO authority (name) VALUES ('ROLE_CLIENT');

INSERT INTO user_authority (username,authority) VALUES ('hasim', 'ROLE_USER');
INSERT INTO user_authority (username,authority) VALUES ('hasim', 'ROLE_ADMIN');
INSERT INTO user_authority (username,authority) VALUES ('user', 'ROLE_USER');
INSERT INTO user_authority (username,authority) VALUES ('admin', 'ROLE_USER');
INSERT INTO user_authority (username,authority) VALUES ('admin', 'ROLE_ADMIN');

select account_info_seq.nextVal from dual;
insert into account values (account_info_seq.currVal,122312);
select transaction_seq.nextVal from dual;
insert into transaction values (transaction_seq.currVal,'as','as',TO_TIMESTAMP('2017-07-02 06:14:00.742000000', 'YYYY-MM-DD HH24:MI:SS.FF'),'c',123.20,'we','we','we','ew','we',TO_TIMESTAMP('2017-03-02 06:14:00.742000000', 'YYYY-MM-DD HH24:MI:SS.FF'),'ew','er',234.29,'ewr','wed','we','EarMark',account_info_seq.currVal);
