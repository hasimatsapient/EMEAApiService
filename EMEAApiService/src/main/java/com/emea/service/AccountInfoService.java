package com.emea.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.emea.dto.AccountInfoVo;
import com.emea.exception.ApplicationException;

/**
 * Service to fetch account information
 * 
 * @author hmolla
 *
 */
@Service
public interface AccountInfoService {
    /**
     * Method to get account information.
     * 
     * @param accountNumber
     * @param sortCode
     * @return
     */
    public AccountInfoVo getAccountDetails(long accountNumber, long sortCode);

    /**
     * Method to create or update account information.
     * @param accountInfoVo
     * @return
     * @throws ApplicationException 
     */
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public AccountInfoVo createOrUpdateAccountDetails(
            AccountInfoVo accountInfoVo) throws ApplicationException;

}
