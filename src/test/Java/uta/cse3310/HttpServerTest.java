package uta.cse3310;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HttpServerTest {

    private HttpServer httpServer;

    @BeforeEach
    public void setUp() {
        httpServer = new HttpServer(8080, "./html");
    }

    @Test
    public void testHttpServerInitialization() {
        assertNotNull(httpServer);
    }
}
