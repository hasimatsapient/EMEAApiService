package com.emea.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.emea.dto.AccountInfoVo;
import com.emea.model.AccountInfoBo;
import com.emea.util.CommonUtility;

@RunWith(MockitoJUnitRunner.class)
public class AccountInfoDaoTest  {

   
    private AccountInfoDao accountInfoDao;
    @Mock
    private EntityManager entityManager;
    @Mock
    CriteriaBuilder cb;
    @Mock
    CriteriaQuery<AccountInfoBo> cq;
    @Mock
    Root<AccountInfoBo> accountInfo;
    @Mock
    TypedQuery<AccountInfoBo> q;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        accountInfoDao = new AccountInfoDaoImpl();
        CommonUtility.setPrivateField(((AccountInfoDaoImpl) accountInfoDao).getClass(), accountInfoDao, "entityManager", entityManager);
        
    }
    
    /*
     * 
     CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<AccountInfoBo> cq = cb.createQuery(AccountInfoBo.class);
        Root<AccountInfoBo> accountInfo = cq.from(AccountInfoBo.class);
        cq.select(accountInfo);
        cq.where(cb.and(cb.equal(accountInfo.get(ID), accountNumber),
                cb.equal(accountInfo.get(SORT_CODE), sortCode)));
        TypedQuery<AccountInfoBo> q = entityManager.createQuery(cq);
        List<AccountInfoBo> allUsers = q.getResultList();

     
     
     * 
     * 
     */
    

    @Test
    public void testGetAccountInfoByAccountNumberAndSortCodeWithData() {
        
        Mockito.when(entityManager.getCriteriaBuilder()).thenReturn(cb);
        Mockito.when(cb.createQuery(AccountInfoBo.class)).thenReturn(cq);
        Mockito.when(cq.from(AccountInfoBo.class)).thenReturn(accountInfo);
        Mockito.when(entityManager.createQuery(cq)).thenReturn(q);
        List<AccountInfoBo> allUsers = new ArrayList();
        AccountInfoBo accountInfoBo = new AccountInfoBo();
        accountInfoBo.setId(1L);
        accountInfoBo.setSortCode(122312L);
        allUsers.add(accountInfoBo);
        Mockito.when(q.getResultList()).thenReturn(allUsers);
        
        AccountInfoBo accountInfoBoOutput = accountInfoDao
                .getAccountInfoByAccountNumberAndSortCode(1L, 122312);

        assertNotNull(accountInfoBoOutput);
    }
    
    
    @Test
    public void testGetAccountInfoByAccountNumberAndSortCodeWithNoData() {
        Mockito.when(entityManager.getCriteriaBuilder()).thenReturn(cb);
        Mockito.when(cb.createQuery(AccountInfoBo.class)).thenReturn(cq);
        Mockito.when(cq.from(AccountInfoBo.class)).thenReturn(accountInfo);
        Mockito.when(entityManager.createQuery(cq)).thenReturn(q);
        List<AccountInfoBo> allUsers = new ArrayList();
        AccountInfoBo accountInfoBo = new AccountInfoBo();
        accountInfoBo.setId(1L);
        accountInfoBo.setSortCode(122312L);
        allUsers.add(accountInfoBo);
        Mockito.when(q.getResultList()).thenReturn(null);
        
        AccountInfoBo accountInfoBoOutput = accountInfoDao
                .getAccountInfoByAccountNumberAndSortCode(1L, 122312);

        assertNull(accountInfoBoOutput);
    }
    
    @Test
    public void testCreateAccountInfo() {
       AccountInfoBo accountInfoBo = new AccountInfoBo();
        accountInfoBo.setId(1L);
        accountInfoBo.setSortCode(122312L);
        AccountInfoBo accountInfoBoOutput = accountInfoDao
                .createAccountInfo(accountInfoBo);
        assertNotNull(accountInfoBoOutput);
    }
    
    @Test
    public void testUpdateAccountInfo() {
       AccountInfoBo accountInfoBo = new AccountInfoBo();
        accountInfoBo.setId(1L);
        accountInfoBo.setSortCode(122312L);
        
        Mockito.when(entityManager.merge(Matchers.any(AccountInfoBo.class))).thenReturn(accountInfoBo);
        
        
        AccountInfoBo accountInfoBoOutput = accountInfoDao
                .updateAccountInfo(accountInfoBo);
        assertNotNull(accountInfoBoOutput);
    }
    

}
