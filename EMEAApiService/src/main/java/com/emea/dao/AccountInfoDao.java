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

@Component
public interface AccountInfoDao {
    public AccountInfoBo getAccountInfoByAccountNumberAndSortCode(long accountNumber, long sortCode);
    }
