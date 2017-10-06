package com.emea.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Account")
public class AccountInfoBo {
    static final long serialVersionUID = 1L; 

    @Id
    @SequenceGenerator(name = "account_info_seq_GENERATOR", sequenceName = "account_info_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_info_seq_GENERATOR")
    private Long id;
    
    /*
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    UserBo user;*/
    
    
    @OneToMany(targetEntity=TransactionBo.class, mappedBy = "accountInfo", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    Set<TransactionBo> transactionBos;
    
    
    
    @Column(name="sort_code")
    long sortCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<TransactionBo> getTransactionBos() {
        return transactionBos;
    }

    public void setTransactionBos(Set<TransactionBo> transactionBos) {
        this.transactionBos = transactionBos;
    }

    public long getSortCode() {
        return sortCode;
    }

    public void setSortCode(long sortCode) {
        this.sortCode = sortCode;
    }

   
    
}
