package com.emea.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
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
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.ResourceUtils;

import com.emea.dto.AccountInfoVo;
import com.emea.dto.TransactionVo;
import com.emea.exception.ApplicationException;
import com.emea.model.AccountInfoBo;
import com.emea.model.TransactionBo;

/**
 * This is a common utility class
 * 
 * @author hmolla
 *
 */
public class CommonUtil {
    private static final String ERROR_OCCURED = "Error occurred";
    private static final String PATH_APP_PROPERTIES = "conf/application.properties";
    private static final String pattern = "dd-MM-yyyy";
    private static Logger LOG = Logger.getLogger(CommonUtil.class);

    /**
     * Method to load properties file.
     * 
     * @return
     */
    public static Properties loadApplicationProperties() {
        Properties properties = null;
        try {
            properties = PropertiesLoaderUtils
                    .loadProperties(new FileSystemResource(ResourceUtils
                            .getFile(PATH_APP_PROPERTIES)));
        } catch (final FileNotFoundException e) {
            LOG.error(ERROR_OCCURED, e);
        } catch (final IOException e) {
            LOG.error(ERROR_OCCURED, e);
        }
        return properties;

    }

    /**
     * Read SQL from file and convert to String.
     * 
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
     * 
     * @param properties
     */
    public static void initSpringProfile(Properties properties) {
        if (properties != null) {
            for (String propertyName : properties.stringPropertyNames()) {
                String propertyValue = properties.getProperty(propertyName);
                System.setProperty(propertyName, propertyValue);
            }
        }
    }

    /**
     * Method to find a string is numeric or not.
     * 
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
     * 
     * @param accountInfoBo
     * @return
     */
    public static AccountInfoVo convertAccountInfoBoToVo(
            AccountInfoBo accountInfoBo) {
        AccountInfoVo accountInfoVo = null;
        if (accountInfoBo != null) {
            accountInfoVo = new AccountInfoVo();
            accountInfoVo.setAccountNumber(accountInfoBo.getId());
            accountInfoVo.setSortCode(accountInfoBo.getSortCode());

            Set<TransactionBo> transactions = accountInfoBo.getTransactionBos();
            if (transactions != null && transactions.size() > 0) {
                List<TransactionVo> transactionVos = new ArrayList<>();

                for (TransactionBo transactionBo : transactions) {
                    transactionVos.add(convertTransactionBoToVo(transactionBo));
                }

                accountInfoVo.setTransactions(transactionVos);
            }

        }

        return accountInfoVo;
    }

    /**
     * Method to convert Bo object to Vo object of Transaction
     * 
     * @param transactionBo
     * @return
     */
    public static TransactionVo convertTransactionBoToVo(
            TransactionBo transactionBo) {
        TransactionVo transactionVo = null;
        if (transactionBo != null) {
            transactionVo = new TransactionVo();
            transactionVo
                    .setTransactionId(String.valueOf(transactionBo.getId()));
            transactionVo.setPermanentAccountNumber(String
                    .valueOf(transactionBo.getPermanentAccountNumber()));
            transactionVo.setTransactionAmount(BigDecimal.valueOf(
                    transactionBo.getTransactionAmount()).toPlainString());
            transactionVo.setTransactionCurrencyCode(String
                    .valueOf(transactionBo.getTransactionCurrencyCode()));
            transactionVo.setCreditDebitIndicator(String.valueOf(transactionBo
                    .getCreditDebitInd()));
            transactionVo.setTransactionStatus(String.valueOf(transactionBo
                    .getTransactionStatus()));
            transactionVo.setPostedDateTime(convertDateToString(transactionBo
                    .getPostedDateTime()));
            transactionVo.setBookedDateTime(convertDateToString(transactionBo
                    .getBookedDateTime()));
            transactionVo.setTransactionDescription(String
                    .valueOf(transactionBo.getTransactionDescription()));
            transactionVo.setType(String.valueOf(transactionBo.getType()));
            transactionVo.setInterimBookedBalanceAmount(BigDecimal.valueOf(
                    transactionBo.getInterimBookedBalanceAmount())
                    .toPlainString());
            transactionVo.setInterimBookedBalanceCurrencyCode(String
                    .valueOf(transactionBo
                            .getInterimBookedBalanceCurrencyCode()));
            transactionVo.setBankTransactionCode(String.valueOf(transactionBo
                    .getBankTransactionCode()));
            transactionVo.setBankTransactionSubCode(String
                    .valueOf(transactionBo.getBankTransactionSubCode()));
            transactionVo.setProprietaryTransactionCode(String
                    .valueOf(transactionBo.getProprietaryTransactionCode()));
            transactionVo.setProprietaryTransactionIssuer(String
                    .valueOf(transactionBo.getProprietaryTransactionIssuer()));
            transactionVo.setMerchantName(String.valueOf(transactionBo
                    .getMerchantName()));
            transactionVo.setMerchantCategoryCode(String.valueOf(transactionBo
                    .getMerchantCategoryCode()));
            transactionVo.setInterimBookedCreditDebitIndicator(String
                    .valueOf(transactionBo
                            .getInterimBookedCreditDebitIndicator()));
        }

        return transactionVo;
    }

    /**
     * Method to convert date to string
     * 
     * @param date
     * @return
     */
    public static String convertDateToString(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        if (date != null) {
            return simpleDateFormat.format(date);
        } else {
            return null;
        }
    }

    /**
     * Method to convert date to string
     * 
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date convertStringDate(String date) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        if (date != null) {
            return simpleDateFormat.parse(date);
        } else {
            return null;
        }
    }

    /**
     * Method to fetch data from third party url.
     * 
     * @param url
     * @return
     */
    public static String getThirdPartyResponse(String url, int timeout) {
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isNotEmpty(url)) {
            HttpClient client = new DefaultHttpClient();
            HttpParams params = client.getParams();
            HttpConnectionParams.setConnectionTimeout(params, timeout);
            HttpConnectionParams.setSoTimeout(params, timeout);
            HttpGet request = new HttpGet(url);

            HttpResponse response;
            BufferedReader rd = null;
            try {
                response = client.execute(request);
                rd = new BufferedReader(new InputStreamReader(response
                        .getEntity().getContent()));
                String line;
                if (rd != null) {
                    while ((line = rd.readLine()) != null) {
                        sb.append(line);
                    }
                }
            } catch (ClientProtocolException e) {
                LOG.error(ERROR_OCCURED, e);
            } catch (IOException e) {
                LOG.error(ERROR_OCCURED, e);
            }

        }

        return sb.toString();
    }

    public static AccountInfoBo convertAccountInfoVoToBo(
            AccountInfoVo accountInfoVo, boolean createFlow)
            throws ApplicationException {

        AccountInfoBo accountInfoBo = null;
        if (accountInfoVo != null) {
            accountInfoBo = new AccountInfoBo();

            if (accountInfoVo.getAccountNumber() != null) {
                accountInfoBo.setId(accountInfoVo.getAccountNumber());
            }

            if (accountInfoVo.getSortCode() != null) {
                accountInfoBo.setSortCode(accountInfoVo.getSortCode());
            }

            List<TransactionVo> transactionVos = accountInfoVo
                    .getTransactions();
            if (transactionVos != null && transactionVos.size() > 0) {
                Set<TransactionBo> transactions = new HashSet<>();

                for (TransactionVo transactionVo : transactionVos) {
                    TransactionBo transactionBo = convertTransactionVoToBo(
                            transactionVo, createFlow);
                    transactionBo.setAccountInfo(accountInfoBo);
                    transactions.add(transactionBo);
                }

                accountInfoBo.setTransactionBos(transactions);
            }

        }

        return accountInfoBo;

    }

    private static TransactionBo convertTransactionVoToBo(
            TransactionVo transactionVo, boolean createFlow)
            throws ApplicationException {
        TransactionBo transactionBo = null;
        if (transactionVo != null) {
            transactionBo = new TransactionBo();
            if (!createFlow) {
                transactionBo.setId(Long.valueOf(transactionVo
                        .getTransactionId()));
            }

            transactionBo.setPermanentAccountNumber(String
                    .valueOf(transactionVo.getPermanentAccountNumber()));
            try {
                Double transactionAmount = transactionVo.getTransactionAmount() != null
                        ? Double.valueOf(transactionVo.getTransactionAmount())
                        : 0.0;
                transactionBo.setTransactionAmount(transactionAmount);
                Double interimBookedBalanceAmount = transactionVo
                        .getInterimBookedBalanceAmount() != null
                        ? Double.valueOf(transactionVo
                                .getInterimBookedBalanceAmount()) : 0.0;
                transactionBo
                        .setInterimBookedBalanceAmount(interimBookedBalanceAmount);
            } catch (NumberFormatException ex) {
                LOG.error("Error occurred", ex);
                throw new ApplicationException(ex);
            }
            transactionBo.setTransactionCurrencyCode(String
                    .valueOf(transactionVo.getTransactionCurrencyCode()));
            transactionBo.setCreditDebitInd(String.valueOf(transactionVo
                    .getCreditDebitIndicator()));
            transactionBo.setTransactionStatus(String.valueOf(transactionVo
                    .getTransactionStatus()));
            try {
                transactionBo.setPostedDateTime(convertStringDate(transactionVo
                        .getPostedDateTime()));
                transactionBo.setBookedDateTime(convertStringDate(transactionVo
                        .getBookedDateTime()));
            } catch (ParseException e) {
                LOG.error("Error occurred", e);
                throw new ApplicationException(e);
            }
            transactionBo.setTransactionDescription(String
                    .valueOf(transactionVo.getTransactionDescription()));
            transactionBo.setType(String.valueOf(transactionVo.getType()));
            transactionBo.setInterimBookedBalanceCurrencyCode(String
                    .valueOf(transactionVo
                            .getInterimBookedBalanceCurrencyCode()));
            transactionBo.setBankTransactionCode(String.valueOf(transactionVo
                    .getBankTransactionCode()));
            transactionBo.setBankTransactionSubCode(String
                    .valueOf(transactionVo.getBankTransactionSubCode()));
            transactionBo.setProprietaryTransactionCode(String
                    .valueOf(transactionVo.getProprietaryTransactionCode()));
            transactionBo.setProprietaryTransactionIssuer(String
                    .valueOf(transactionVo.getProprietaryTransactionIssuer()));
            transactionBo.setMerchantName(String.valueOf(transactionVo
                    .getMerchantName()));
            transactionBo.setMerchantCategoryCode(String.valueOf(transactionVo
                    .getMerchantCategoryCode()));
            transactionBo.setInterimBookedCreditDebitIndicator(String
                    .valueOf(transactionVo
                            .getInterimBookedCreditDebitIndicator()));
        }

        return transactionBo;
    }

}
