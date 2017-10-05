package com.emea.model;

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
public class TransactionBo {

    @Id
    @SequenceGenerator(name = "transaction_seq_GENERATOR", sequenceName = "transaction_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_seq_GENERATOR")
    private Long id;
    
    @Column(name="credit_debit_indicator")
    String creditDebitInd;
    
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    AccountInfoBo accountInfo;

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
}
