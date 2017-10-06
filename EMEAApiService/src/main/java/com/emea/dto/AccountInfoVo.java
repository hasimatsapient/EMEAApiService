package com.emea.dto;

import java.util.List;

//@JsonIgnoreProperties(value = { "accountNumber","sortCode" })
public class AccountInfoVo {
    
   transient long accountNumber;
   transient long sortCode;
    List<TransactionVo> transactions;
    
    public long getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }
    
    public long getSortCode() {
        return sortCode;
    }
    public void setSortCode(long sortCode) {
        this.sortCode = sortCode;
    }
    public List<TransactionVo> getTransactions() {
        return transactions;
    }
    public void setTransactions(List<TransactionVo> transactions) {
        this.transactions = transactions;
    }
    

}
