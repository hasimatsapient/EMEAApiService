package com.emea.dto;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AccountInfoVoTest {
    @Test
    public void testAccountInfoVo() {
        AccountInfoVo accountInfoVo = new AccountInfoVo();
        accountInfoVo.setAccountNumber(1L);
        accountInfoVo.setSortCode(1L);
        assertEquals(true, accountInfoVo.getAccountNumber().equals(1L));
        assertEquals(true, accountInfoVo.getSortCode().equals(1L));
    }
}
