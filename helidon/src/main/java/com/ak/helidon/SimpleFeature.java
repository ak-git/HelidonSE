package com.ak.helidon;

import io.helidon.service.registry.Service;
import io.helidon.webserver.http.HttpFeature;
import io.helidon.webserver.http.HttpRouting;

/**
 * Http features are automatically discovered by Helidon WebServer when running with
 * {@link io.helidon.service.registry.ServiceRegistryManager#start(io.helidon.service.registry.Binding)}.
 */
@Service.Singleton
final class SimpleFeature implements HttpFeature {
  private final SimpleService service;

  @Service.Inject
  SimpleFeature(SimpleService service) {
    this.service = service;
  }

  @Override
  public void setup(HttpRouting.Builder builder) {
    builder.register("/simple-greet", service);
  }
}
