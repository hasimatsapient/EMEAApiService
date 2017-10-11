package com.emea.dao;

import org.springframework.stereotype.Component;

import com.emea.model.AccountInfoBo;

/**
 * Interface to fetch data of account.
 * 
 * @author hmolla
 *
 */
@Component
public interface AccountInfoDao {
    /**
     * Method to get account information based on account number and sortcode
     * 
     * @param accountNumber
     * @param sortCode
     * @return
     */
    public AccountInfoBo getAccountInfoByAccountNumberAndSortCode(
            long accountNumber, long sortCode);
}
