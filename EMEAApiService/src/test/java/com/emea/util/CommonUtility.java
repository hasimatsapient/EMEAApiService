package com.emea.util;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class CommonUtility {
    public static void prepareData(EntityManager entityManager){
        Query q =  entityManager.createNativeQuery(" drop table ACCOUNT  if exists ");
        q.executeUpdate();
        q =  entityManager.createNativeQuery(" CREATE TABLE ACCOUNT  ( ID NUMBER(19,0) ,SORT_CODE NUMBER(19,0),  PRIMARY KEY (ID) ) ");
        q.executeUpdate();
        
        q =  entityManager.createNativeQuery(" CREATE SEQUENCE account_info_seq START WITH 1 CACHE 1000 ");
        q.executeUpdate();
         q =  entityManager.createNativeQuery(" drop table TRANSACTION  if exists ");
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
        .append("    PRIMARY KEY (ID) ")
        .append(" )  ");
        
        
        
        q =  entityManager.createNativeQuery(createTransacSql.toString());
        q.executeUpdate();
        
        q =  entityManager.createNativeQuery("CREATE SEQUENCE transaction_seq START WITH 1 CACHE 1000");
        q.executeUpdate();
        
        q =  entityManager.createNativeQuery("select account_info_seq.nextVal from dual");
        
        
        q.getResultList();
        
        q =  entityManager.createNativeQuery("insert into account values (account_info_seq.currVal,122312)");
        q.executeUpdate();
        q =  entityManager.createNativeQuery("select transaction_seq.nextVal from dual");
        q.getResultList();
        q =  entityManager.createNativeQuery("insert into transaction values (transaction_seq.currVal,'as','as',{ts '2012-09-17 18:47:52.69'},'c',123.20,'we','we','we','ew','we',{ts '2012-09-17 18:47:52.69'},'ew','er',234.29,'ewr','wed','we','EarMark',account_info_seq.currVal)");
        q.executeUpdate();
    }
    
    public static Object prepareDataWithSql(EntityManager entityManager, String sql, boolean isSelect){
        Query q =  entityManager.createNativeQuery(sql);
        if(isSelect){
           return q.getResultList();
        } else{
            q.executeUpdate();
            return null;
        }
       
       
    }
    
}
