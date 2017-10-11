package com.emea.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserTest {
    @Test
    public void testUser() {
        User user = new User();
        user.setActivated(true);
        user.setActivationKey("test");
        user.setAuthorities(null);
        user.setEmail("test@abc.com");
        user.setPassword("test");
        user.setResetPasswordKey("test");
        user.setUsername("test");

        User user1 = new User();
        user1.setUsername("test");

        assertNotNull(user.toString());
        assertNotNull(user.hashCode());
        assertNotNull(user.getEmail());
        assertNotNull(user.getActivationKey());
        assertNotNull(user.getResetPasswordKey());
        assertEquals(true, user.equals(user1));
        assertEquals(false, user.equals(null));

        user1 = new User();
        user1.setUsername("test1");
        assertEquals(false, user.equals(user1));
    }
}
