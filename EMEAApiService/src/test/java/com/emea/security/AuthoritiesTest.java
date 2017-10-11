package com.emea.security;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AuthoritiesTest {

    @Test
    public void testEnumValue() {
        assertEquals("ROLE_ANONYMOUS", Authorities.ROLE_ANONYMOUS.name());
        assertEquals("ROLE_USER", Authorities.ROLE_USER.name());
        assertEquals("ROLE_ADMIN", Authorities.ROLE_ADMIN.name());
        assertEquals("ROLE_CLIENT", Authorities.ROLE_CLIENT.name());
        assertEquals(Authorities.ROLE_CLIENT,
                Authorities.valueOf("ROLE_CLIENT"));
        assertNotNull(Authorities.values());

    }
}
