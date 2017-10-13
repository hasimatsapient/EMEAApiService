package com.emea.dto;

import java.util.List;

public class AccountInfoVo {

    Long accountNumber;
    Long sortCode;
    List<TransactionVo> transactions;

    public Long getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Long getSortCode() {
        return sortCode;
    }
    public void setSortCode(Long sortCode) {
        this.sortCode = sortCode;
    }
    public List<TransactionVo> getTransactions() {
        return transactions;
    }
    public void setTransactions(List<TransactionVo> transactions) {
        this.transactions = transactions;
    }

}
