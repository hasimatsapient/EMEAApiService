package com.emea.dto;

public class TransactionVo {

    String transactionId;
    String permanentAccountNumber;
    String transactionAmount;
    String transactionCurrencyCode;
    String creditDebitIndicator;
    String transactionStatus;
    String postedDateTime;
    String bookedDateTime;
    String transactionDescription;
    String type;
    String interimBookedBalanceAmount;
    String interimBookedBalanceCurrencyCode;
    String bankTransactionCode;
    String bankTransactionSubCode;
    String proprietaryTransactionCode;
    String proprietaryTransactionIssuer;
    String merchantName;
    String merchantCategoryCode;
    String interimBookedCreditDebitIndicator;
    /*
     * 
     * 
     "TransactionId": "string",
"PermanentAccountNumber": "string",
"TransactionAmount": "string",
"TransactionCurrencyCode": "string",
"CreditDebitIndicator": "C",
"TransactionStatus": "P",
"PostedDateTime": "string",
"BookedDateTime": "string",
"TransactionDescription": "string",
"Type": "EarMark",
"InterimBookedBalanceAmount": "string",
"InterimBookedBalanceCurrencyCode": "string",
"BankTransactionCode": "string",
"BankTransactionSubCode": "string",
"ProprietaryTransactionCode": "string",
"ProprietaryTransactionIssuer": "string",
"MerchantName": "string",
"MerchantCategoryCode": "string",
"InterimBookedCreditDebitIndicator": "string"
     * 
     * 
     */
    public String getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
    public String getPermanentAccountNumber() {
        return permanentAccountNumber;
    }
    public void setPermanentAccountNumber(String permanentAccountNumber) {
        this.permanentAccountNumber = permanentAccountNumber;
    }
    public String getTransactionAmount() {
        return transactionAmount;
    }
    public void setTransactionAmount(String transactionAmount) {
        this.transactionAmount = transactionAmount;
    }
    public String getTransactionCurrencyCode() {
        return transactionCurrencyCode;
    }
    public void setTransactionCurrencyCode(String transactionCurrencyCode) {
        this.transactionCurrencyCode = transactionCurrencyCode;
    }
    public String getCreditDebitIndicator() {
        return creditDebitIndicator;
    }
    public void setCreditDebitIndicator(String creditDebitIndicator) {
        this.creditDebitIndicator = creditDebitIndicator;
    }
    public String getTransactionStatus() {
        return transactionStatus;
    }
    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }
    public String getPostedDateTime() {
        return postedDateTime;
    }
    public void setPostedDateTime(String postedDateTime) {
        this.postedDateTime = postedDateTime;
    }
    public String getBookedDateTime() {
        return bookedDateTime;
    }
    public void setBookedDateTime(String bookedDateTime) {
        this.bookedDateTime = bookedDateTime;
    }
    public String getTransactionDescription() {
        return transactionDescription;
    }
    public void setTransactionDescription(String transactionDescription) {
        this.transactionDescription = transactionDescription;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getInterimBookedBalanceAmount() {
        return interimBookedBalanceAmount;
    }
    public void setInterimBookedBalanceAmount(String interimBookedBalanceAmount) {
        this.interimBookedBalanceAmount = interimBookedBalanceAmount;
    }
    public String getInterimBookedBalanceCurrencyCode() {
        return interimBookedBalanceCurrencyCode;
    }
    public void setInterimBookedBalanceCurrencyCode(
            String interimBookedBalanceCurrencyCode) {
        this.interimBookedBalanceCurrencyCode = interimBookedBalanceCurrencyCode;
    }
    public String getBankTransactionCode() {
        return bankTransactionCode;
    }
    public void setBankTransactionCode(String bankTransactionCode) {
        this.bankTransactionCode = bankTransactionCode;
    }
    public String getBankTransactionSubCode() {
        return bankTransactionSubCode;
    }
    public void setBankTransactionSubCode(String bankTransactionSubCode) {
        this.bankTransactionSubCode = bankTransactionSubCode;
    }
    public String getProprietaryTransactionCode() {
        return proprietaryTransactionCode;
    }
    public void setProprietaryTransactionCode(String proprietaryTransactionCode) {
        this.proprietaryTransactionCode = proprietaryTransactionCode;
    }
    public String getProprietaryTransactionIssuer() {
        return proprietaryTransactionIssuer;
    }
    public void setProprietaryTransactionIssuer(String proprietaryTransactionIssuer) {
        this.proprietaryTransactionIssuer = proprietaryTransactionIssuer;
    }
    public String getMerchantName() {
        return merchantName;
    }
    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }
    public String getMerchantCategoryCode() {
        return merchantCategoryCode;
    }
    public void setMerchantCategoryCode(String merchantCategoryCode) {
        this.merchantCategoryCode = merchantCategoryCode;
    }
    public String getInterimBookedCreditDebitIndicator() {
        return interimBookedCreditDebitIndicator;
    }
    public void setInterimBookedCreditDebitIndicator(
            String interimBookedCreditDebitIndicator) {
        this.interimBookedCreditDebitIndicator = interimBookedCreditDebitIndicator;
    }
}
