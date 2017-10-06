package com.emea.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.emea.dto.AccountInfoVo;
import com.emea.exception.ApplicationException;
import com.emea.exception.ErrorResponse;
import com.emea.service.AccountInfoService;
import com.emea.util.CommonUtil;

/**
 * Class to handle api service
 * @author hmolla
 *
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/emeaapiservice")
public class AccountInfoController {
    @Autowired
    AccountInfoService accountInfoService;
    private static final Logger LOG = Logger
            .getLogger(AccountInfoController.class);
    private static final String ACCOUNT_NUMBER = "accountNumber";
    private static final String SORT_CODE = "sortCode";
    
    @Value("${github.users.url}")
    String gitHubUrl;
    
    /**
     * Method to get account details
     * @param accountInfoVo
     * @return
     * @throws ApplicationException
     */
    @RequestMapping(value = "/accountdetails", method = RequestMethod.POST, headers = "Accept=application/json")
    public @ResponseBody Map getAccountDetails(@RequestBody Map accountInfoVo)
            throws ApplicationException {
        LOG.info("Started executing getAccountDetails");
        long accountNumber;
        long sortCode;
        Map result = new HashMap();
        String accountNumberStr = accountInfoVo.get(ACCOUNT_NUMBER) != null
                ? accountInfoVo.get(ACCOUNT_NUMBER).toString()
                : null;
        String sortCodeStr = accountInfoVo.get(SORT_CODE) != null
                ? accountInfoVo.get(SORT_CODE).toString()
                : null;

        if (CommonUtil.isNumericRegex(accountNumberStr)) {
            accountNumber = Long.valueOf(accountNumberStr);
        } else {
            throw new ApplicationException(
                    " Account number  is mandatory and numeric");
        }

        if (CommonUtil.isNumericRegex(sortCodeStr) && sortCodeStr.length() == 6) {
            sortCode = Long.valueOf(sortCodeStr);
        } else {
            throw new ApplicationException(
                    " Sort Code  is mandatory and is six digit numeric value");
        }

        AccountInfoVo accountInfo = accountInfoService.getAccountDetails(
                accountNumber, sortCode);
        if (accountInfo == null) {
            throw new ApplicationException(
                    "No account found with account number " + accountNumber
                            + " and sort code " + sortCode);
        }

        result.put("Transactions", accountInfo.getTransactions());
        
        result.put("ThirdpartyOutput", CommonUtil.getThirdPartyResponse(gitHubUrl));
        
        

        LOG.info("Finished executing getAccountDetails");

        return result;
    }

    @ExceptionHandler(ApplicationException.class)
    public @ResponseBody ErrorResponse exceptionHandler(Exception ex) {

        ErrorResponse error = new ErrorResponse();

        error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());

        error.setMessage(ex.getMessage());

        LOG.error(" Error occurred ", ex);

        return error;

    }

}
