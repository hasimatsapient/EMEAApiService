package com.emea.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.emea.dto.AccountInfoVo;
import com.emea.dto.TransactionVo;
import com.emea.exception.ApplicationException;
import com.emea.exception.ErrorResponse;
import com.emea.service.AccountInfoService;
import com.emea.util.CommonUtil;

/**
 * Class to handle api service
 * 
 * @author hmolla
 *
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/emeaapiservice")
@Api(value="AccountInfo", description="Endpoint for Account Information")
public class AccountInfoController {
    @Autowired
    AccountInfoService accountInfoService;
    private static final Logger LOG = Logger
            .getLogger(AccountInfoController.class);
    private static final String ACCOUNT_NUMBER = "accountNumber";
    private static final String SORT_CODE = "sortCode";

    @Value("${github.users.url:}")
    String gitHubUrl;

    @Value("${github.users.url.timeout:1000}")
    String connectionTimeOut;

    /**
     * Method to create account details
     * 
     * @param accountInfoVo
     * @return
     * @throws ApplicationException
     */
    @RequestMapping(value = "/accountdetails/{sortCode}", method = RequestMethod.POST, headers = "Accept=application/json")
    @ApiOperation(value="This api creates account information and validate whether valid sort code is specified")
    public @ResponseBody AccountInfoVo createAccountDetails(
            @RequestBody List<TransactionVo> transactionVos,  
            @PathVariable("sortCode") String sortCodeStr ) throws ApplicationException{
        AccountInfoVo accountInfoVo = new AccountInfoVo();
        LOG.info("Started executing createAccountDetails");
        Long sortCode=null;
        if (CommonUtil.isNumericRegex(sortCodeStr) && sortCodeStr.length() == 6) {
            accountInfoVo.setSortCode(Long.valueOf(sortCodeStr));
        } else {
            throw new ApplicationException(
                    " Sort Code  is mandatory and is six digit numeric value");
        }
        
        accountInfoVo.setAccountNumber(null);
        accountInfoVo.setTransactions(transactionVos);
        AccountInfoVo result = accountInfoService.createOrUpdateAccountDetails(accountInfoVo);
        
        
        LOG.info("Finished executing createAccountDetails");
        
        return result;
    }
    
    
    /**
     * Method to create account details
     * 
     * @param accountInfoVo
     * @return
     * @throws ApplicationException
     */
    @RequestMapping(value = "/accountdetails/{sortCode}/{accountNumber}", method = RequestMethod.PUT, headers = "Accept=application/json")
    @ApiOperation(value="This api update account information based on valid data is specified")
    public @ResponseBody AccountInfoVo updateAccountDetails(@RequestBody List<TransactionVo> transactionVos,  @PathVariable("sortCode") String sortCodeStr, @PathVariable("accountNumber") String accountNumberStr ) throws ApplicationException{
        AccountInfoVo accountInfoVo  = new AccountInfoVo();
        LOG.info("Started executing updateAccountDetails");
        Long sortCode=null;
        if (CommonUtil.isNumericRegex(sortCodeStr) && sortCodeStr.length() == 6) {
            accountInfoVo.setSortCode(Long.valueOf(sortCodeStr));
        } else {
            throw new ApplicationException(
                    " Sort Code  is mandatory and is six digit numeric value");
        }
        
        if (CommonUtil.isNumericRegex(accountNumberStr) ) {
            accountInfoVo.setAccountNumber(Long.valueOf(accountNumberStr));
        } else {
            throw new ApplicationException(
                    " Account Number  is mandatory and is numeric value");
        }
        
        accountInfoVo.setTransactions(transactionVos);
        
        AccountInfoVo result = accountInfoService.createOrUpdateAccountDetails(accountInfoVo);
        
        
        LOG.info("Finished executing updateAccountDetails");
        return result;
    }
    
    
    
    
    /**
     * Method to get account details
     * 
     * @param accountInfoVo
     * @return
     * @throws ApplicationException
     */
    @RequestMapping(value = "/accountdetails", method = RequestMethod.POST, headers = "Accept=application/json")
    @ApiOperation(value="This api retrieve account information based on valid sort code and account number is specified")
    public @ResponseBody Map getAccountDetails(@RequestBody  @ApiParam(
            value = "JSON format input, keys allowed are accountNumber : numeric value, sortCode: six digit numeric value sample request body :  {\"accountNumber\":\"13\",\"sortCode\":\"200013\"}"
        ) Map inputParam)
            throws ApplicationException {
        LOG.info("Started executing getAccountDetails");
        long accountNumber;
        long sortCode;
        Map result = new HashMap();
        String accountNumberStr = inputParam.get(ACCOUNT_NUMBER) != null
                ? inputParam.get(ACCOUNT_NUMBER).toString()
                : null;
        String sortCodeStr = inputParam.get(SORT_CODE) != null
                ? inputParam.get(SORT_CODE).toString()
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

        result.put(
                "ThirdpartyOutput",
                CommonUtil.getThirdPartyResponse(gitHubUrl,
                        Integer.valueOf(connectionTimeOut)));

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
