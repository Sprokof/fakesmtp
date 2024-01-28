package org.tmcw.fakesmtp;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.subethamail.smtp.server.SMTPServer;
import org.tmcw.fakesmtp.spi.MailHandler;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SMTPServerBuilderTest {

    final SMTPServerBuilder instance = new SMTPServerBuilder();

    @Test
    void testBuild() throws UnknownHostException {
        final List<MailHandler> mailHandlerList = Collections.emptyList();
        final List<String> relayDomains = Arrays.asList("evil.com");
        final int port = 345;
        final InetAddress bindAddress = InetAddress.getByName("ddg.gg");

        final SMTPServer result = instance.build(mailHandlerList, relayDomains, port, bindAddress);

        assertEquals(bindAddress, result.getBindAddress());
        assertEquals(port, result.getPort());
    }

}
