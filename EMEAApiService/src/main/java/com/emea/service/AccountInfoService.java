package com.emea.service;

import org.springframework.stereotype.Service;

import com.emea.dto.AccountInfoVo;

/**
 * Service to fetch account information
 * @author hmolla
 *
 */
@Service
public interface AccountInfoService {
    /**
     * Method to get account information.
     * @param accountNumber
     * @param sortCode
     * @return
     */
    public AccountInfoVo getAccountDetails(long accountNumber, long sortCode);
    
   
}
