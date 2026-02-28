package com.ak.helidon;

import io.helidon.service.registry.Service;
import io.helidon.webserver.http.HttpFeature;
import io.helidon.webserver.http.HttpRouting;

/**
 * Http features are automatically discovered by Helidon WebServer when running with
 * {@link io.helidon.service.registry.ServiceRegistryManager#start(io.helidon.service.registry.Binding)}.
 */
@Service.Singleton
final class GreetFeature implements HttpFeature {
  private final GreetService greetService;

  @Service.Inject
  GreetFeature(GreetService greetService) {
    this.greetService = greetService;
  }

  @Override
  public void setup(HttpRouting.Builder builder) {
    builder.register("/greet", greetService);
  }
}
