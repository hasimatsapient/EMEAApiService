package com.emea.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Component;

import com.emea.model.AccountInfoBo;

/**
 * Interface to fetch data of account.
 * @author hmolla
 *
 */
@Component
public interface AccountInfoDao {
    /**
     * Method to get account information based on account number and sortcode
     * @param accountNumber
     * @param sortCode
     * @return
     */
    public AccountInfoBo getAccountInfoByAccountNumberAndSortCode(long accountNumber, long sortCode);
    }
