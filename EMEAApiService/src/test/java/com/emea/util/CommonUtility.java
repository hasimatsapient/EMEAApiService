package com.emea.util;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class CommonUtility {

    public static void setUpUser(EntityManager entityManager) {
        StringBuilder createTransacSql = new StringBuilder();
        createTransacSql.append("   CREATE TABLE user_details ( ")
                .append("  username VARCHAR(50) NOT NULL PRIMARY KEY, ")
                .append("  email VARCHAR(50), ")
                .append("  password VARCHAR(500), ")
                .append("  activated VARCHAR(50), ")
                .append("  activationkey VARCHAR(50), ")
                .append("  resetpasswordkey VARCHAR(50) ").append(") ");
        Query q = entityManager.createNativeQuery(createTransacSql.toString());
        q.executeUpdate();

        createTransacSql = new StringBuilder();
        createTransacSql.append("CREATE TABLE authority (")
                .append(" name VARCHAR(50) NOT NULL PRIMARY KEY ")
                .append(" ) ");
        q = entityManager.createNativeQuery(createTransacSql.toString());
        q.executeUpdate();

        createTransacSql = new StringBuilder();
        createTransacSql.append(" CREATE TABLE user_authority ")
                .append("  (    username VARCHAR(50) NOT NULL, ")
                .append(" authority VARCHAR(50) NOT NULL ) ");
        q = entityManager.createNativeQuery(createTransacSql.toString());
        q.executeUpdate();

        q = entityManager
                .createNativeQuery(" alter table user_authority add CONSTRAINT FK_user FOREIGN KEY (username) REFERENCES user_details (username) ");
        q.executeUpdate();

        q = entityManager
                .createNativeQuery(" alter table user_authority add CONSTRAINT FK_authority FOREIGN KEY (authority) REFERENCES authority (name) ");
        q.executeUpdate();

        q = entityManager
                .createNativeQuery(" alter table user_authority add CONSTRAINT Uniq_authority Unique(username,authority) ");
        q.executeUpdate();

        createTransacSql = new StringBuilder();
        createTransacSql.append(" CREATE TABLE oauth_access_token ( ")
                .append("  token_id VARCHAR(256) DEFAULT NULL, ")
                .append("   token BLOB, ")

                .append(" authentication_id VARCHAR(256) DEFAULT NULL, ")
                .append(" user_name VARCHAR(256) DEFAULT NULL, ")
                .append(" client_id VARCHAR(256) DEFAULT NULL, ")
                .append(" authentication BLOB, ")
                .append(" refresh_token VARCHAR(256) DEFAULT NULL ")
                .append(" ) ");
        q = entityManager.createNativeQuery(createTransacSql.toString());
        q.executeUpdate();

        createTransacSql = new StringBuilder();
        createTransacSql.append(" CREATE TABLE oauth_refresh_token ( ")
                .append("  token_id VARCHAR(256) DEFAULT NULL, ")
                .append("   token BLOB, ")

                .append(" authentication BLOB ").append(" ) ");
        q = entityManager
                .createNativeQuery("INSERT INTO user_details (username,email, password, activated) VALUES ('hasim', 'hasim@abc.com', 'test', 'true')");
        q.executeUpdate();

        q = entityManager
                .createNativeQuery("INSERT INTO authority (name) VALUES ('ROLE_USER')");
        q.executeUpdate();

        q = entityManager
                .createNativeQuery("INSERT INTO authority (name) VALUES ('ROLE_ADMIN')");
        q.executeUpdate();

        q = entityManager
                .createNativeQuery("INSERT INTO user_authority (username,authority) VALUES ('hasim', 'ROLE_USER')");
        q.executeUpdate();
        q = entityManager
                .createNativeQuery("INSERT INTO user_authority (username,authority) VALUES ('hasim', 'ROLE_ADMIN')");
        q.executeUpdate();

    }
    public static void prepareData(EntityManager entityManager) {
        Query q = entityManager
                .createNativeQuery(" drop table ACCOUNT  if exists ");
        q.executeUpdate();
        q = entityManager
                .createNativeQuery(" CREATE TABLE ACCOUNT  ( ID NUMBER(19,0) ,SORT_CODE NUMBER(19,0),  PRIMARY KEY (ID) ) ");
        q.executeUpdate();
        
        q = entityManager
                .createNativeQuery(" CREATE TABLE SORT_CODE  (SORT_CODE NUMBER(19,0)) ");
        q.executeUpdate();
        
        q = entityManager
                .createNativeQuery("insert into SORT_CODE values (200013)");
        q.executeUpdate();

        q = entityManager
                .createNativeQuery(" CREATE SEQUENCE account_info_seq START WITH 1 CACHE 1000 ");
        q.executeUpdate();
        q = entityManager
                .createNativeQuery(" drop table TRANSACTION  if exists ");
        q.executeUpdate();
        StringBuilder createTransacSql = new StringBuilder();
        createTransacSql.append("  CREATE TABLE TRANSACTION  ( ")
                .append("   ID NUMBER(19,0) NOT NULL ,  ")
                .append("   BANK_TRAN_CODE VARCHAR2(255 CHAR),  ")
                .append("   BANK_TRAN_SUB_CODE VARCHAR2(255 CHAR),  ")
                .append("   BOOKED_DATE_TIME TIMESTAMP (6),  ")
                .append("   CREDIT_DEBIT_INDICATOR VARCHAR2(255 CHAR),  ")
                .append("   INTER_BOOKED_BAL_AMT FLOAT(126),  ")
                .append("   INTER_BOOKED_BAL_CURR_CODE VARCHAR2(255 CHAR), ")
                .append("   INTER_BOOKED_CR_DB_IND VARCHAR2(255 CHAR),  ")
                .append("   MERCHANT_CATEGORY_CODE VARCHAR2(255 CHAR),  ")
                .append("   MERCHANT_NAME VARCHAR2(255 CHAR),  ")
                .append("   PERMANENT_ACCOUNT_NUMBER VARCHAR2(255 CHAR),  ")
                .append("   POSTED_DATE_TIME TIMESTAMP (6),  ")
                .append("   PROPRIETARY_TRAN_CODE VARCHAR2(255 CHAR),  ")
                .append("   PROPRIETARY_TRAN_ISSUER VARCHAR2(255 CHAR),  ")
                .append("   TRANSACTION_AMOUNT FLOAT(126),  ")
                .append("   TRANSACTION_CURR_CODE VARCHAR2(255 CHAR),  ")
                .append("   TRAN_DESCRIPTION VARCHAR2(255 CHAR),  ")
                .append("   TRANSACTION_STATUS VARCHAR2(255 CHAR),  ")
                .append("   TYPE VARCHAR2(255 CHAR),  ")
                .append("   ACCOUNT_ID NUMBER(19,0),  ")
                .append("    PRIMARY KEY (ID) ").append(" )  ");

        q = entityManager.createNativeQuery(createTransacSql.toString());
        q.executeUpdate();

        q = entityManager
                .createNativeQuery("CREATE SEQUENCE transaction_seq START WITH 1 CACHE 1000");
        q.executeUpdate();

        q = entityManager
                .createNativeQuery("select account_info_seq.nextVal from dual");

        q.getResultList();

        q = entityManager
                .createNativeQuery("insert into account values (account_info_seq.currVal,122312)");
        q.executeUpdate();
        q = entityManager
                .createNativeQuery("select transaction_seq.nextVal from dual");
        q.getResultList();
        q = entityManager
                .createNativeQuery("insert into transaction values (transaction_seq.currVal,'as','as',{ts '2012-09-17 18:47:52.69'},'c',123.20,'we','we','we','ew','we',{ts '2012-09-17 18:47:52.69'},'ew','er',234.29,'ewr','wed','we','EarMark',account_info_seq.currVal)");
        q.executeUpdate();
        
               
       /* q = entityManager
                .createNativeQuery(" CREATE TABLE SORTCODE  ( ID NUMBER(19,0) ,SORTCODE NUMBER(19,0),  PRIMARY KEY (ID) ) ");
        q.executeUpdate();
        
        q = entityManager
                .createNativeQuery("insert into SORTCODE values (1,122312)");
        q.executeUpdate();*/
        
        /*q = entityManager
                .createNativeQuery("CREATE TABLE SORT_CODE_A ( SORT_CODE_b NUMBER(19,0), PRIMARY KEY (SORT_CODE_b))");
        q.executeUpdate();
        q = entityManager
                .createNativeQuery("insert into SORT_CODE_A values (200013)");
        q.executeUpdate();*/
        
        
    }

    public static Object prepareDataWithSql(EntityManager entityManager,
            String sql, boolean isSelect) {
        Query q = entityManager.createNativeQuery(sql);
        if (isSelect) {
            return q.getResultList();
        } else {
            q.executeUpdate();
            return null;
        }

    }

}
