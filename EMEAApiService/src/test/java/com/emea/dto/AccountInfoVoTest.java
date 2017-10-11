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
        accountInfoVo.setAccountNumber(1);
        accountInfoVo.setSortCode(1);
        assertEquals(1, accountInfoVo.getAccountNumber());
        assertEquals(1, accountInfoVo.getSortCode());
    }
}
