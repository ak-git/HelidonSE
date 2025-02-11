package com.ak.helidon;

import io.helidon.webserver.testing.junit5.DirectClient;
import io.helidon.webserver.testing.junit5.RoutingTest;

@RoutingTest
class MainRoutingTest extends AbstractMainTest {
  MainRoutingTest(DirectClient client) {
    super(client);
  }
}