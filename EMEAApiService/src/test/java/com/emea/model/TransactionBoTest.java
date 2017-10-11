package com.emea.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TransactionBoTest {
    @Test
    public void testTransactionBo() {
        TransactionBo transactionBo = new TransactionBo();
        transactionBo.setAccountInfo(null);
        transactionBo.setBankTransactionCode("test");
        transactionBo.setBankTransactionSubCode("test");
        transactionBo.setBookedDateTime(new Date());
        transactionBo.setCreditDebitInd("C");
        transactionBo.setId(1L);
        transactionBo.setInterimBookedBalanceAmount(10.0);
        transactionBo.setInterimBookedBalanceCurrencyCode("INR");
        transactionBo.setMerchantName("Test");
        transactionBo.setMerchantCategoryCode("test");
        transactionBo.setInterimBookedCreditDebitIndicator("C");
        transactionBo.setPermanentAccountNumber("test");
        transactionBo.setPostedDateTime(new Date());
        transactionBo.setProprietaryTransactionCode("test");
        transactionBo.setProprietaryTransactionIssuer("test");
        transactionBo.setTransactionAmount(100.0);
        transactionBo.setTransactionCurrencyCode("INR");
        transactionBo.setTransactionDescription("test");
        transactionBo.setTransactionStatus("Success");
        transactionBo.setType("test");
        assertNotNull(transactionBo);
        assertEquals(null, transactionBo.getAccountInfo());
    }
}
