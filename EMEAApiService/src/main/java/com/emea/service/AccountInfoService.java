package com.emea.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emea.dao.AccountInfoDao;
import com.emea.dto.AccountInfoVo;
import com.emea.model.AccountInfoBo;
import com.emea.util.CommonUtil;

/**
 * Service to fetch account information
 * @author hmolla
 *
 */
@Service
public class AccountInfoService {
    private static final Logger LOG = Logger
            .getLogger(AccountInfoService.class);
    @Autowired
    AccountInfoDao accountInfoDao;

    /**
     * Method to get account information.
     * @param accountNumber
     * @param sortCode
     * @return
     */
    public AccountInfoVo getAccountDetails(long accountNumber, long sortCode){
        LOG.info("Started executing getAccountDetails");
        AccountInfoBo accountInfoBo =accountInfoDao.getAccountInfoByAccountNumberAndSortCode(accountNumber, sortCode);
        
        AccountInfoVo accountInfoVo = CommonUtil. convertAccountInfoBoToVo(accountInfoBo);
        LOG.info("Finished executing getAccountDetails");
        return accountInfoVo;
    }
    
   
}
