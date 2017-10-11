package com.emea.exception;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ErrorResponseTest {
    @Test
    public void testErrorResponse() {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(1);
        errorResponse.setMessage("test error");

        assertEquals(1, errorResponse.getErrorCode());
        assertEquals("test error", errorResponse.getMessage());
    }
}
