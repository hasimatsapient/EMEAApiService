package com.emea.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.ResourceUtils;

import com.emea.dto.AccountInfoVo;
import com.emea.dto.TransactionVo;
import com.emea.model.AccountInfoBo;
import com.emea.model.TransactionBo;

/**
 * This is a common utility class
 * @author hmolla
 *
 */
public class CommonUtil {
	private static final String ERROR_OCCURED = "Error occurred";
    private static final String PATH_APP_PROPERTIES = "conf/application.properties";
    private static final String SERVER = "server";
    private static final String SPRING = "spring.";
    private static final String pattern = "yyyy-MM-dd";
    private static Logger LOG =   Logger.getLogger(CommonUtil.class);
	
    /**
     * Method to load properties file.
     * @return
     */
    public static Properties loadApplicationProperties() {
        Properties properties = null;
        try {
            properties = PropertiesLoaderUtils.loadProperties(new FileSystemResource(ResourceUtils.getFile(PATH_APP_PROPERTIES)));
        } catch (final FileNotFoundException e) {
            LOG.error(ERROR_OCCURED, e);
        } catch (final IOException e) {
            LOG.error(ERROR_OCCURED, e);
        }
        return properties;

    }
    
    /**
     * Read SQL from file and convert to String.
     * @param resource
     * @return
     */
    public static String readResourceAsString(String resource) {
        try (InputStream is = new ClassPathResource(resource).getInputStream()) {
            return IOUtils.toString(is);
        } catch (IOException e) {
        	LOG.error(ERROR_OCCURED, e);
           return null;
        }
    }
    
    /**
     * Method to load properties
     * @param properties
     */
    public static void initSpringProfile(Properties properties) {
       
        for (String propertyName : properties.stringPropertyNames()) {
                String propertyValue = properties.getProperty(propertyName);
                System.setProperty(propertyName, propertyValue);
        }

    }
    
    /**
     * Method to find a string is numeric or not.
     * @param str
     * @return
     */
    public static boolean isNumericRegex(String str) {
        if (str == null)
            return false;
        return str.matches("-?\\d+");
    }
    
    /**
     * Method to convert Bo object to Vo object of account information
     * @param accountInfoBo
     * @return
     */
    public static AccountInfoVo convertAccountInfoBoToVo(AccountInfoBo accountInfoBo){
        AccountInfoVo accountInfoVo = null;
        if(accountInfoBo!=null){
            accountInfoVo = new AccountInfoVo();
            accountInfoVo.setAccountNumber(accountInfoBo.getId());
            accountInfoVo.setSortCode(accountInfoBo.getSortCode());
            
           Set<TransactionBo> transactions= accountInfoBo.getTransactionBos();
           if(transactions!=null && transactions.size()>0){
               List<TransactionVo> transactionVos = new ArrayList<>();
               
               for(TransactionBo transactionBo: transactions){
                   transactionVos.add(convertTransactionBoToVo(transactionBo));
               }
               
               accountInfoVo.setTransactions(transactionVos);
           }
            
            
            
        }
        
        return accountInfoVo;
    }
   
    /**
     * Method to convert Bo object to Vo object of Transaction
     * @param transactionBo
     * @return
     */
    public static TransactionVo convertTransactionBoToVo(TransactionBo transactionBo){
        TransactionVo transactionVo = null;
        if(transactionBo!=null){
            transactionVo = new TransactionVo();
            transactionVo.setTransactionId(String.valueOf(transactionBo.getId()));
            transactionVo.setPermanentAccountNumber (String.valueOf(transactionBo.getPermanentAccountNumber()));
            transactionVo.setTransactionAmount(String.valueOf(transactionBo.getTransactionAmount()));
            transactionVo.setTransactionCurrencyCode(String.valueOf(transactionBo.getTransactionCurrencyCode()));
            transactionVo.setCreditDebitIndicator(String.valueOf(transactionBo.getCreditDebitInd()));
            transactionVo.setTransactionStatus(String.valueOf(transactionBo.getTransactionStatus()));
            transactionVo.setPostedDateTime(  convertDateToString(transactionBo.getPostedDateTime()));
            transactionVo.setBookedDateTime(convertDateToString(transactionBo.getBookedDateTime()) );
            transactionVo.setTransactionDescription(String.valueOf(transactionBo.getTransactionDescription()));
            transactionVo.setType(String.valueOf(transactionBo.getType()));
            transactionVo.setInterimBookedBalanceAmount(String.valueOf(transactionBo.getInterimBookedBalanceAmount()));
            transactionVo.setInterimBookedBalanceCurrencyCode(String.valueOf(transactionBo.getInterimBookedBalanceCurrencyCode()));
            transactionVo.setBankTransactionCode(String.valueOf(transactionBo.getBankTransactionCode()));
            transactionVo.setBankTransactionSubCode(String.valueOf(transactionBo.getBankTransactionSubCode()));
            transactionVo.setProprietaryTransactionCode(String.valueOf(transactionBo.getProprietaryTransactionCode()));
            transactionVo.setProprietaryTransactionIssuer(String.valueOf(transactionBo.getProprietaryTransactionIssuer()));
            transactionVo.setMerchantName(String.valueOf(transactionBo.getMerchantName()));
            transactionVo.setMerchantCategoryCode(String.valueOf(transactionBo.getMerchantCategoryCode()));
            transactionVo.setInterimBookedCreditDebitIndicator(String.valueOf(transactionBo.getInterimBookedCreditDebitIndicator()));
        }
        
        return transactionVo;
    }
    
    
    /**
     * Method to convert date to string
     * @param date
     * @return
     */
    public static String convertDateToString(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        if(date!=null){
           return  simpleDateFormat.format(date) ;
        } else {
            return null;
        }
    } 
    
    
    /**
     * Method to fetch data from third party url.
     * @param url
     * @return
     */
    public static String getThirdPartyResponse(String url){
        StringBuilder sb = new StringBuilder();
        if(StringUtils.isNotEmpty(url)){
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(url);
            
              HttpResponse response;
              BufferedReader rd=null;
            try {
                response = client.execute(request);
                rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
            } catch (ClientProtocolException e) {
                LOG.error(ERROR_OCCURED, e);
            } catch (IOException e) {
                LOG.error(ERROR_OCCURED, e);
            }
            
             try {
                  String line;
              while((line= rd.readLine())!=null){
                   sb.append(line);
               }
               
               
            } catch (IOException e) {
                LOG.error(ERROR_OCCURED, e);
            }
              
        }

          return sb.toString();
    }
    
    
   
}
