package com.emea.util;

import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.emea.dto.AccountInfoVo;
import com.emea.model.AccountInfoBo;
import com.emea.model.TransactionBo;

@RunWith(MockitoJUnitRunner.class)
public class CommonUtilTest {

    
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void initSpringProfileWithProperties() {   
        Properties properties = new Properties();
        properties.put("test", "test");
        CommonUtil.initSpringProfile(properties);
        assertThat(System.getProperty("test"), equalToIgnoringCase("test"));
    }
    @Test
    public void initSpringProfileWithNullProperties() {   
        
        CommonUtil.initSpringProfile(null);
    }
    @Test
    public void initSpringProfileWithoutProperties() {   
        Properties properties = new Properties();
        CommonUtil.initSpringProfile(properties);
    }
    @Test
    public void isNumericRegexWithString() {   
        
        assertFalse(CommonUtil.isNumericRegex("test") );
    }
    @Test
    public void isNumericRegexWithNumeric() {   
        assertTrue(CommonUtil.isNumericRegex("23") );
    }
    @Test
    public void isNumericRegexWithNull() {   
        assertFalse(CommonUtil.isNumericRegex(null) );
    }
    @Test
    public void convertAccountInfoBoToVo() {   
       // assertFalse(CommonUtil.isNumericRegex(null) );
        AccountInfoBo accountInfoBo=new AccountInfoBo();
        TransactionBo transactionBo = new TransactionBo();
        transactionBo.setId(1L);
        Set<TransactionBo> transactionBos = new HashSet<>();
        
        accountInfoBo.setId(1L);
        accountInfoBo.setSortCode(2L);
        accountInfoBo.setTransactionBos(transactionBos);
        AccountInfoVo accountInfoVo = CommonUtil.convertAccountInfoBoToVo(accountInfoBo);
        assertNotNull(accountInfoVo);
    }
    @Test
    public void convertAccountInfoBoToVoWithNoAccount() {   
        
        AccountInfoVo accountInfoVo = CommonUtil.convertAccountInfoBoToVo(null);
        assertNull(accountInfoVo);
    }
    @Test
    public void convertAccountInfoBoToVoWithNoTransaction() {   
       // assertFalse(CommonUtil.isNumericRegex(null) );
        AccountInfoBo accountInfoBo=new AccountInfoBo();
       
        
        accountInfoBo.setId(1L);
        accountInfoBo.setSortCode(2L);
        accountInfoBo.setTransactionBos(null);
        AccountInfoVo accountInfoVo = CommonUtil.convertAccountInfoBoToVo(accountInfoBo);
        assertNotNull(accountInfoVo);
        assertNull(accountInfoVo.getTransactions());
    }
    @Test
    public void convertDateToString() {   
        Calendar c = Calendar.getInstance();
        c.set(Calendar.MONTH, 10);
        c.set(Calendar.DATE, 10);
        c.set(Calendar.YEAR, 2017);
        Date dt = c.getTime();
        String date = CommonUtil.convertDateToString(dt);
       //2017-11-10
        assertThat(date, equalToIgnoringCase("2017-11-10"));
    }
    @Test
    public void convertDateToStringWithNull() {   
       
        assertNull( CommonUtil.convertDateToString(null));
       
    }
    @Test
    public void getThirdPartyResponseWithNoUrl() {   
       
        assertThat( CommonUtil.getThirdPartyResponse(null), equalToIgnoringCase(""));
       
    }
    @Test
    public void getThirdPartyResponseWith() {   
       
        assertNotNull( CommonUtil.getThirdPartyResponse("https://api.github.com/users/hadley/orgs"));
       
    }
    @Test
    public void readResourceAsString() {   
       
        assertNotNull( CommonUtil.readResourceAsString("conf/test.sql"));
       
    }
   //readResourceAsString(String)
}
