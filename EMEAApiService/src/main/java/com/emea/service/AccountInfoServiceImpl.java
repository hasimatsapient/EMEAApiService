package com.emea.service;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emea.dao.AccountInfoDao;
import com.emea.dto.AccountInfoVo;
import com.emea.exception.ApplicationException;
import com.emea.model.AccountInfoBo;
import com.emea.util.CommonUtil;

/**
 * Service to fetch account information
 * 
 * @author hmolla
 *
 */
@Service
public class AccountInfoServiceImpl implements AccountInfoService {
    private static final Logger LOG = Logger
            .getLogger(AccountInfoServiceImpl.class);
    @Autowired
    AccountInfoDao accountInfoDao;

    @Autowired
    ValidationService validationService;

    /**
     * Method to get account information.
     * 
     * @param accountNumber
     * @param sortCode
     * @return
     */
    public AccountInfoVo getAccountDetails(long accountNumber, long sortCode) {
        LOG.info("Started executing getAccountDetails");
        AccountInfoBo accountInfoBo = accountInfoDao
                .getAccountInfoByAccountNumberAndSortCode(accountNumber,
                        sortCode);

        AccountInfoVo accountInfoVo = CommonUtil
                .convertAccountInfoBoToVo(accountInfoBo);
        LOG.info("Finished executing getAccountDetails");
        return accountInfoVo;
    }

    /**
     * Method to create or update account information. (non-Javadoc)
     * 
     * @throws ApplicationException
     * @see com.emea.service.AccountInfoService#createOrUpdateAccountDetails(com.emea.dto.AccountInfoVo)
     */
    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public AccountInfoVo createOrUpdateAccountDetails(
            AccountInfoVo accountInfoVo) throws ApplicationException {
        LOG.info("Started executing createOrUpdateAccountDetails");

        AccountInfoBo accountInfoBo = null;
        boolean createFlow = accountInfoVo.getAccountNumber() == null;
        accountInfoBo = CommonUtil.convertAccountInfoVoToBo(accountInfoVo,
                createFlow);

        boolean flag = validationService.validate(accountInfoVo);
        if (!flag) {
            throw new ApplicationException(
                    "Invalid account number or sort code");
        }

        AccountInfoBo accountInfoBoResult;
        if (createFlow) {
             accountInfoDao
                    .createAccountInfo(accountInfoBo);
        } else {
             accountInfoDao
                    .updateAccountInfo(accountInfoBo);
        }
        accountInfoBoResult = accountInfoDao
                .getAccountInfoByAccountNumberAndSortCode(accountInfoBo.getId(), accountInfoBo.getSortCode());

        LOG.info("Finished executing createOrUpdateAccountDetails");
        return CommonUtil.convertAccountInfoBoToVo(accountInfoBoResult);
    }

}
