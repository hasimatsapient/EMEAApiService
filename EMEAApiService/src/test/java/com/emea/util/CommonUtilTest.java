package com.emea.util;

import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.http.client.ClientProtocolException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.api.mockito.PowerMockito;

import com.emea.dto.AccountInfoVo;
import com.emea.dto.TransactionVo;
import com.emea.exception.ApplicationException;
import com.emea.model.AccountInfoBo;
import com.emea.model.TransactionBo;

@RunWith(MockitoJUnitRunner.class)
public class CommonUtilTest {

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testInitSpringProfileWithProperties() {
        Properties properties = new Properties();
        properties.put("test", "test");
        CommonUtil.initSpringProfile(properties);
        assertThat(System.getProperty("test"), equalToIgnoringCase("test"));
    }
    @Test
    public void testInitSpringProfileWithNullProperties() {

        CommonUtil.initSpringProfile(null);
    }
    @Test
    public void testInitSpringProfileWithoutProperties() {
        Properties properties = new Properties();
        CommonUtil.initSpringProfile(properties);
    }
    @Test
    public void testIsNumericRegexWithString() {

        assertFalse(CommonUtil.isNumericRegex("test"));
    }

    @Test
    public void testIsGetThirdPartyResponse() {

        assertNotNull(CommonUtil.getThirdPartyResponse(
                "https://api.github.com/users/hadley/orgs", 1000));
    }

    @Test
    public void testIsNumericRegexWithNumeric() {
        assertTrue(CommonUtil.isNumericRegex("23"));
    }
    @Test
    public void testIsNumericRegexWithNull() {
        assertFalse(CommonUtil.isNumericRegex(null));
    }
    @Test
    public void testConvertAccountInfoBoToVo() {
        // assertFalse(CommonUtil.isNumericRegex(null) );
        AccountInfoBo accountInfoBo = new AccountInfoBo();
        TransactionBo transactionBo = new TransactionBo();
        transactionBo.setId(1L);
        Set<TransactionBo> transactionBos = new HashSet<>();

        accountInfoBo.setId(1L);
        accountInfoBo.setSortCode(2L);
        accountInfoBo.setTransactionBos(transactionBos);
        AccountInfoVo accountInfoVo = CommonUtil
                .convertAccountInfoBoToVo(accountInfoBo);
        assertNotNull(accountInfoVo);
    }
    
    @Test
    public void testConvertAccountInfoVoToBo() throws ApplicationException {
        // assertFalse(CommonUtil.isNumericRegex(null) );
        AccountInfoVo accountInfoVo = new AccountInfoVo();
        TransactionVo transactionVo = new TransactionVo();
        transactionVo.setTransactionId("1");
        List<TransactionVo> transactionVos = new ArrayList<>();
        transactionVos.add(transactionVo);
        
        accountInfoVo.setAccountNumber(1L);
        accountInfoVo.setSortCode(2L);
        accountInfoVo.setTransactions(transactionVos);
        AccountInfoBo accountInfoBo = CommonUtil
                .convertAccountInfoVoToBo(accountInfoVo, true);
        assertNotNull(accountInfoBo);
    }
    
    @Test(expected=ApplicationException.class)
    public void testConvertAccountInfoVoToBoWithException() throws ApplicationException {
        // assertFalse(CommonUtil.isNumericRegex(null) );
        AccountInfoVo accountInfoVo = new AccountInfoVo();
        TransactionVo transactionVo = new TransactionVo();
        transactionVo.setTransactionId("1");
        List<TransactionVo> transactionVos = new ArrayList<>();
        transactionVos.add(transactionVo);
        transactionVo.setInterimBookedBalanceAmount("fds");
        accountInfoVo.setAccountNumber(1L);
        accountInfoVo.setSortCode(2L);
        accountInfoVo.setTransactions(transactionVos);
        AccountInfoBo accountInfoBo = CommonUtil
                .convertAccountInfoVoToBo(accountInfoVo, true);
       // assertNotNull(accountInfoBo);
    }
    
    @Test(expected=ApplicationException.class)
    public void testConvertAccountInfoVoToBoWithInvalidDate() throws ApplicationException {
        // assertFalse(CommonUtil.isNumericRegex(null) );
        AccountInfoVo accountInfoVo = new AccountInfoVo();
        TransactionVo transactionVo = new TransactionVo();
        transactionVo.setTransactionId("1");
        List<TransactionVo> transactionVos = new ArrayList<>();
        transactionVos.add(transactionVo);
        transactionVo.setInterimBookedBalanceAmount("10.0");
        transactionVo.setInterimBookedBalanceAmount("10.0");
        transactionVo.setPostedDateTime("");
        accountInfoVo.setAccountNumber(1L);
        accountInfoVo.setSortCode(2L);
        accountInfoVo.setTransactions(transactionVos);
        AccountInfoBo accountInfoBo = CommonUtil
                .convertAccountInfoVoToBo(accountInfoVo, true);
       // assertNotNull(accountInfoBo);
    }
    
    @Test
    public void testConvertAccountInfoVoToBoNoTransaction() throws ApplicationException {
        // assertFalse(CommonUtil.isNumericRegex(null) );
        AccountInfoVo accountInfoVo = new AccountInfoVo();
        TransactionVo transactionVo = new TransactionVo();
        transactionVo.setTransactionId("1");
        List<TransactionVo> transactionVos = new ArrayList<>();

        accountInfoVo.setAccountNumber(1L);
        accountInfoVo.setSortCode(2L);
        accountInfoVo.setTransactions(transactionVos);
        AccountInfoBo accountInfoBo = CommonUtil
                .convertAccountInfoVoToBo(accountInfoVo, true);
        assertNotNull(accountInfoBo);
    }
    
    @Test
    public void testConvertAccountInfoVoToBoUpdateFlow() throws ApplicationException {
        // assertFalse(CommonUtil.isNumericRegex(null) );
        AccountInfoVo accountInfoVo = new AccountInfoVo();
        TransactionVo transactionVo = new TransactionVo();
        transactionVo.setTransactionId("1");
        List<TransactionVo> transactionVos = new ArrayList<>();
        transactionVo.setTransactionId("1");
        transactionVos.add(transactionVo);
        accountInfoVo.setAccountNumber(1L);
        accountInfoVo.setSortCode(2L);
        accountInfoVo.setTransactions(transactionVos);
        AccountInfoBo accountInfoBo = CommonUtil
                .convertAccountInfoVoToBo(accountInfoVo, false);
        assertNotNull(accountInfoBo);
    }
    
    
    
    @Test
    public void testConvertAccountInfoBoToVoWithNoAccount() {

        AccountInfoVo accountInfoVo = CommonUtil.convertAccountInfoBoToVo(null);
        assertNull(accountInfoVo);
    }
    @Test
    public void testConvertAccountInfoBoToVoWithNoTransaction() {
        // assertFalse(CommonUtil.isNumericRegex(null) );
        AccountInfoBo accountInfoBo = new AccountInfoBo();

        accountInfoBo.setId(1L);
        accountInfoBo.setSortCode(2L);
        accountInfoBo.setTransactionBos(null);
        AccountInfoVo accountInfoVo = CommonUtil
                .convertAccountInfoBoToVo(accountInfoBo);
        assertNotNull(accountInfoVo);
        assertNull(accountInfoVo.getTransactions());
    }
    @Test
    public void testConvertDateToString() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MONTH, 10);
        c.set(Calendar.DATE, 10);
        c.set(Calendar.YEAR, 2017);
        Date dt = c.getTime();
        String date = CommonUtil.convertDateToString(dt);
        assertThat(date, equalToIgnoringCase("10-11-2017"));
    }
    
    @Test
    public void testConvertStringToDate() throws ParseException {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MONTH, 10);
        c.set(Calendar.DATE, 10);
        c.set(Calendar.YEAR, 2017);
        c.set(Calendar.HOUR_OF_DAY, 00);
        c.set(Calendar.MINUTE, 00);
        c.set(Calendar.SECOND, 00);
        c.set(Calendar.MILLISECOND, 00);
        
        Date dt = c.getTime();
        
        String dateStr ="10-11-2017";
        Date date = CommonUtil.convertStringDate(dateStr);
        
       
        assertTrue( c.getTime().getTime()==date.getTime());
    }
    @Test
    public void testConvertDateToStringWithNull() {

        assertNull(CommonUtil.convertDateToString(null));

    }
    @Test
    public void testGetThirdPartyResponseWithNoUrl() {

        assertThat(CommonUtil.getThirdPartyResponse(null, 1000),
                equalToIgnoringCase(""));

    }
    @Test
    public void testGetThirdPartyResponse() {

        assertNotNull(CommonUtil.getThirdPartyResponse(
                "https://api.github.com/users/hadley/orgs", 1000));

    }
    @Test
    public void testGetThirdPartyResponseWithException() throws Exception {
        InputStreamReader inputStreamReader = Mockito.mock(InputStreamReader.class);
        PowerMockito.whenNew(BufferedReader.class).withArguments(inputStreamReader).thenThrow(ClientProtocolException.class);
        assertNotNull(CommonUtil.getThirdPartyResponse(
                "https://api.github.com/users/hadley/orgs", 1000));

    }
    @Test
    public void testGeadResourceAsString() {

        assertNotNull(CommonUtil.readResourceAsString("conf/test.sql"));

    }

    @Test
    public void testGeadResourceAsStringWithException() {

        assertNull(CommonUtil.readResourceAsString("missingresource.txt"));

    }

    @Test
    public void testLoadApplicationProperties() {
        assertNotNull(CommonUtil.loadApplicationProperties());
    }
    
    //whenNew(NodeConnection.class).withArguments(credentials, config).thenReturn(connection);

}
