package com.emea.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.emea.model.AccountInfoBo;

/**
 * Class to fetch account information.
 * 
 * @author hmolla
 *
 */
@Component
public class AccountInfoDaoImpl implements AccountInfoDao {
    private static final Logger LOG = Logger.getLogger(AccountInfoDaoImpl.class);
    private static final String ID = "id";
    private static final String SORT_CODE = "sortCode";
    @PersistenceContext
    private EntityManager entityManager;

    /*
     * Method to get account information based on account number and sortcode
     * (non-Javadoc)
     * 
     * @see
     * com.emea.dao.AccountInfoDao#getAccountInfoByAccountNumberAndSortCode(
     * long, long)
     */
    public AccountInfoBo getAccountInfoByAccountNumberAndSortCode(
            long accountNumber, long sortCode) {
        AccountInfoBo result = null;
        LOG.info("Started executing getAccountInfoByAccountNumberAndSortCode");
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<AccountInfoBo> cq = cb.createQuery(AccountInfoBo.class);
        Root<AccountInfoBo> accountInfo = cq.from(AccountInfoBo.class);
        cq.select(accountInfo);
        cq.where(cb.and(cb.equal(accountInfo.get(ID), accountNumber),
                cb.equal(accountInfo.get(SORT_CODE), sortCode)));
        TypedQuery<AccountInfoBo> q = entityManager.createQuery(cq);
        List<AccountInfoBo> allUsers = q.getResultList();

        if (allUsers != null && allUsers.size() > 0) {
            result = allUsers.get(0);
        }
        LOG.info("Finished executing getAccountInfoByAccountNumberAndSortCode");
        return result;

    }

    @Override
    public AccountInfoBo createAccountInfo(AccountInfoBo accountInfoBo) {
        LOG.info("Started executing createAccountInfo");
        entityManager.persist(accountInfoBo);
        LOG.info("Finished executing createAccountInfo");
        
        return accountInfoBo;
    }

    @Override
    public AccountInfoBo updateAccountInfo(AccountInfoBo accountInfoBo) {
        LOG.info("Started executing updateAccountInfo");
        accountInfoBo =  entityManager.merge(accountInfoBo);
        LOG.info("Finished executing updateAccountInfo");
        return accountInfoBo;
    }
}
