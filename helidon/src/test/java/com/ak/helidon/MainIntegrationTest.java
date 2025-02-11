package com.ak.helidon;

import io.helidon.webclient.http1.Http1Client;
import io.helidon.webserver.testing.junit5.ServerTest;

@ServerTest
class MainIntegrationTest extends AbstractMainTest {
    MainIntegrationTest(Http1Client client) {
        super(client);
    }
}