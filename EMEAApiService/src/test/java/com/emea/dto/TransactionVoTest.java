package com.emea.dto;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TransactionVoTest {
    @Test
    public void testTransactionVo() {
        TransactionVo transactionVo = new TransactionVo();
        transactionVo.setBankTransactionCode("test");
        transactionVo.setBankTransactionSubCode("test");
        transactionVo.setBookedDateTime("10-10-2017");
        transactionVo.setCreditDebitIndicator("c");
        transactionVo.setInterimBookedBalanceAmount("10");
        transactionVo.setInterimBookedBalanceCurrencyCode("INR");
        transactionVo.setInterimBookedCreditDebitIndicator("c");
        transactionVo.setMerchantCategoryCode("test");
        transactionVo.setMerchantName("test");
        transactionVo.setPermanentAccountNumber("21");
        transactionVo.setPostedDateTime("10-10-2017");
        transactionVo.setProprietaryTransactionCode("test");
        transactionVo.setTransactionAmount("100");
        transactionVo.setTransactionCurrencyCode("INR");
        transactionVo.setTransactionDescription("test");
        transactionVo.setTransactionId("1");
        transactionVo.setType("test");
        transactionVo.setTransactionStatus("success");
        transactionVo.setProprietaryTransactionIssuer("test");

        assertEquals("test", transactionVo.getType());
        assertEquals("success", transactionVo.getTransactionStatus());
        assertEquals("test", transactionVo.getProprietaryTransactionIssuer());
        assertEquals("1", transactionVo.getTransactionId());
        assertEquals("test", transactionVo.getTransactionDescription());
        assertEquals("INR", transactionVo.getTransactionCurrencyCode());
        assertEquals("100", transactionVo.getTransactionAmount());
        assertEquals("test", transactionVo.getProprietaryTransactionCode());
        assertEquals("10-10-2017", transactionVo.getPostedDateTime());
        assertEquals("21", transactionVo.getPermanentAccountNumber());
        assertEquals("test", transactionVo.getMerchantName());
        assertEquals("test", transactionVo.getMerchantCategoryCode());
        assertEquals("c", transactionVo.getInterimBookedCreditDebitIndicator());
        assertEquals("INR", transactionVo.getInterimBookedBalanceCurrencyCode());
        assertEquals("10", transactionVo.getInterimBookedBalanceAmount());
        assertEquals("c", transactionVo.getCreditDebitIndicator());
        assertEquals("10-10-2017", transactionVo.getBookedDateTime());
        assertEquals("test", transactionVo.getBankTransactionSubCode());
        assertEquals("test", transactionVo.getBankTransactionCode());
    }
}
