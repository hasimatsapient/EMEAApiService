package com.emea.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Transaction")
public class TransactionBo  {
    @Id
    @SequenceGenerator(name = "transaction_seq_GENERATOR", sequenceName = "transaction_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_seq_GENERATOR")
    private Long id;
    
    @Column(name="credit_debit_indicator")
    String creditDebitInd;
    
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    AccountInfoBo accountInfo;
    
    @Column(name="Permanent_Account_Number")
    String permanentAccountNumber;
    @Column(name="Transaction_Amount")
    Double transactionAmount;
    @Column(name="Transaction_Curr_Code")
    String transactionCurrencyCode;
    @Column(name="Transaction_Status")
    String transactionStatus;
    @Column(name="Posted_Date_Time")
    Date postedDateTime;
    @Column(name="Booked_Date_Time")
    Date bookedDateTime;
    @Column(name="Tran_Description")
    String transactionDescription;
    @Column(name="Type")
    String type;
    @Column(name="Inter_Booked_Bal_Amt")
    Double interimBookedBalanceAmount;
    @Column(name="Inter_Booked_Bal_Curr_Code")
    String interimBookedBalanceCurrencyCode;
    @Column(name="Bank_Tran_Code")
    String bankTransactionCode;
    @Column(name="Bank_Tran_Sub_Code")
    String bankTransactionSubCode;
    @Column(name="Proprietary_Tran_Code")
    String proprietaryTransactionCode;
    @Column(name="Proprietary_Tran_Issuer")
    String proprietaryTransactionIssuer;
    @Column(name="Merchant_Name")
    String merchantName;
    @Column(name="Merchant_Category_Code")
    String merchantCategoryCode;
    @Column(name="Inter_Booked_Cr_Db_Ind")
    String interimBookedCreditDebitIndicator;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreditDebitInd() {
        return creditDebitInd;
    }

    public void setCreditDebitInd(String creditDebitInd) {
        this.creditDebitInd = creditDebitInd;
    }

    public AccountInfoBo getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(AccountInfoBo accountInfo) {
        this.accountInfo = accountInfo;
    }

    public String getPermanentAccountNumber() {
        return permanentAccountNumber;
    }

    public void setPermanentAccountNumber(String permanentAccountNumber) {
        this.permanentAccountNumber = permanentAccountNumber;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionCurrencyCode() {
        return transactionCurrencyCode;
    }

    public void setTransactionCurrencyCode(String transactionCurrencyCode) {
        this.transactionCurrencyCode = transactionCurrencyCode;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public Date getPostedDateTime() {
        return postedDateTime;
    }

    public void setPostedDateTime(Date postedDateTime) {
        this.postedDateTime = postedDateTime;
    }

    public Date getBookedDateTime() {
        return bookedDateTime;
    }

    public void setBookedDateTime(Date bookedDateTime) {
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

    public Double getInterimBookedBalanceAmount() {
        return interimBookedBalanceAmount;
    }

    public void setInterimBookedBalanceAmount(Double interimBookedBalanceAmount) {
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
