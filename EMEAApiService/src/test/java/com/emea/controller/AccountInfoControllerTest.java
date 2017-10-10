package com.emea.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.emea.dto.AccountInfoVo;
import com.emea.model.AccountInfoBo;
import com.emea.service.AccountInfoService;

/*@RunWith(SpringRunner.class)
 @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
 */

/*@RunWith(SpringJUnit4ClassRunner.class)
 //@ContextConfiguration(classes = {AccountInfoBo.class,AccountInfoDaoImpl.class,AccountInfoService.class },loader=AnnotationConfigContextLoader.class,locations = { "conf/app-context.xml" })
 @ContextConfiguration({ "classpath:conf/test-app-config.xml" })*/

/*@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath:conf/test-app-config.xml"})*/
public class AccountInfoControllerTest extends InitDb {

    private MediaType contentType = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Autowired
    private WebApplicationContext context;
    
    @PersistenceContext
    private EntityManager entityManager;
    private MockMvc mockMvc;
    @Autowired
    private AccountInfoService accountInfoService;

    @Autowired
    private AccountInfoController accountInfoController;

    private AccountInfoVo accountInfoVo;
    @Mock
    AccountInfoBo accountInfoBo;
    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.accountInfoVo = new AccountInfoVo();
        mockMvc = MockMvcBuilders.standaloneSetup(accountInfoController)
                .build();
        
       /* mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
               // .apply(springSecurity()) 1
                .build();*/
    }

    /*@Test
    @Transactional(Transactional.TxType.REQUIRES_NEW)*/
    public void testGetAccountDetailsInvalidUrl() throws Exception {
        byte[] json = "{/\"accountNumber/\": /\"1/\",/\"sortCode/\":/\"1/\"}"
                .getBytes();

        try {
            mockMvc.perform(
                    post("emeaapiservice/getAccountDetails").content(json)
                            .contentType(contentType)).andExpect(
                    status().isNotFound());
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
   /* @Test
    @Transactional(Transactional.TxType.REQUIRES_NEW)*/
    public void testGetAccountDetails() throws Exception {
        byte[] json = "{/\"accountNumber/\": /\"1/\",/\"sortCode/\":/\"1/\"}"
                .getBytes();

        try {
           /* mockMvc.perform(MockMvcRequestBuilders.post("/emeaapiservice/getAccountDetails").content(json).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().
                    
                    string(equalTo("[{\"id\":1,\"name\":\"Hasim\",\"location\":\"London\",\"text\":\"Hello good morning\"}]")));
            */
            
            mockMvc.perform(
                    post("/emeaapiservice/getAccountDetails").content(json)
                            .contentType(contentType)).andExpect(status().isOk());
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
