package com.emea.service;

import org.springframework.stereotype.Service;

import com.emea.dto.AccountInfoVo;

/**
 * Service to fetch validate account and transaction information
 * 
 * @author hmolla
 *
 */
@Service
public interface ValidationService {
    /**
     * Method to validate account information.
     * 
     * @param accountNumber
     * @param sortCode
     * @return
     */
    public boolean validate(AccountInfoVo accountInfoVo);

}
