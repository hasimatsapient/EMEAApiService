package com.emea.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emea.dao.AccountInfoDao;
import com.emea.dao.SortCodeDao;
import com.emea.dto.AccountInfoVo;

@Service
public class ValidationServiceImpl implements ValidationService {
    private final Logger LOG = LoggerFactory
            .getLogger(ValidationServiceImpl.class);

    @Autowired
    AccountInfoDao accountInfoDao;

    @Autowired
    SortCodeDao sortCodeDao;
    @Override
    public boolean validate(AccountInfoVo accountInfoVo) {
        boolean flag = true;
        LOG.info("Started executing validate");
        if (accountInfoVo.getAccountNumber() != null
                && accountInfoVo.getSortCode() != null) {
            flag = accountInfoDao.getAccountInfoByAccountNumberAndSortCode(
                    accountInfoVo.getAccountNumber(),
                    accountInfoVo.getSortCode()) != null;
        } else if (accountInfoVo.getSortCode() != null) {
            flag = sortCodeDao.getSortCode(accountInfoVo.getSortCode()) != null;
        } else {
            flag = false;
        }

        LOG.info("Finished executing validate");
        return flag;
    }

}
