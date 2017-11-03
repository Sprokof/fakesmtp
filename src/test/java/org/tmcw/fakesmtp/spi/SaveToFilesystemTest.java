package org.tmcw.fakesmtp.spi;

import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.Jimfs;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.mail.internet.MimeMessage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class SaveToFilesystemTest {

    @Test
    public void testHandle() throws Exception {
        final String from = "user@localhost";
        final String recipient = "admin@localhost";
        final String rawMessage = "headers+bodü";
        final MimeMessage mimeMessage = null;

        final FileSystem fs = Jimfs.newFileSystem(Configuration.unix());
        final Path outputPath = fs.getPath("/output");
        Files.createDirectory(outputPath);

        final SaveToFilesystem instance = new SaveToFilesystem(outputPath);
        instance.handle(from, recipient, rawMessage, mimeMessage);

        assertEquals(Files.list(outputPath).count(), 1);

        final Path firstFile = Files.list(outputPath).findFirst().get();
        final String fileName = firstFile.getFileName().toString();

        final String expectedPrefix = new SimpleDateFormat("yyyy-MM-dd'T'HH")
                .format(new Date());

        assertTrue(fileName.startsWith(expectedPrefix));
        assertTrue(fileName.endsWith(SaveToFilesystem.FILE_EXTENSION));
        assertEquals(Files.readAllLines(firstFile, StandardCharsets.UTF_8).get(0), rawMessage);
    }

}
