package com.ak.helidon;

import io.helidon.webserver.http.HttpRouting;
import io.helidon.webserver.testing.junit5.DirectClient;
import io.helidon.webserver.testing.junit5.RoutingTest;
import io.helidon.webserver.testing.junit5.SetUpRoute;

@RoutingTest
class MainRoutingTest extends AbstractMainTest {
  MainRoutingTest(DirectClient client) {
    super(client);
  }

  @SetUpRoute
  static void routing(HttpRouting.Builder builder) {
    new GreetFeature(new GreetService()).setup(builder);
    new SimpleFeature(new SimpleService()).setup(builder);
  }
}