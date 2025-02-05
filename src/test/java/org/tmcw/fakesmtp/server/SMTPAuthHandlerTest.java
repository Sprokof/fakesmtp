package org.tmcw.fakesmtp.server;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class SMTPAuthHandlerTest {

    public SMTPAuthHandlerTest() {
    }

    @Test
    public void testAuth() {
        final SMTPAuthHandler instance = new SMTPAuthHandler();

        assertEquals(SMTPAuthHandler.PROMPT_USERNAME, instance.auth("anything"));
        assertEquals(SMTPAuthHandler.PROMPT_PASSWORD, instance.auth("anything"));
        assertNull(instance.auth("anything"));
    }

    @Test
    public void testGetIdentity() {
        final SMTPAuthHandler instance = new SMTPAuthHandler();
        assertEquals(SMTPAuthHandler.USER_IDENTITY, instance.getIdentity());
    }

}
