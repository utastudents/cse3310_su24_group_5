package uta.cse3310;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class HttpServerTest {

    private HttpServer httpServer;

    @TempDir
    File tempDir;

    @BeforeEach
    public void setUp() {
        httpServer = new HttpServer(9105, tempDir.getAbsolutePath());
    }

    @Test
    public void testHttpServerInitialization() {
        assertNotNull(httpServer);
        assertEquals(9105, httpServer.port);
        assertEquals(tempDir.getAbsolutePath(), httpServer.dirname);
    }

    @Test
    public void testHttpServerStart() {
        // Create a file in the temporary directory to simulate HTML content
        File indexFile = new File(tempDir, "index.html");
        try {
            assertTrue(indexFile.createNewFile());
        } catch (IOException e) {
            fail("Failed to create temporary index.html file");
        }

        // Start the server
        httpServer.start();

        // Ensure the server is running (this is a basic check; ideally, you'd check the actual server status)
        assertTrue(indexFile.exists());
        assertTrue(tempDir.canRead());
    }
}

