package com.emea.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AuthorityTest {
    @Test
    public void testAuthority() {
        Authority authority = new Authority();
        authority.setName("Test");

        Authority authority1 = new Authority();
        authority1.setName("Test");

        assertNotNull(authority.toString());
        assertEquals(true, authority.equals(authority1));
        assertEquals(false, authority.equals(null));

    }
}
