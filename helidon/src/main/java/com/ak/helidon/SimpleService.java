package com.ak.helidon;

import io.helidon.service.registry.Service;
import io.helidon.webserver.http.HttpRules;
import io.helidon.webserver.http.HttpService;

@Service.Singleton
final class SimpleService implements HttpService {
  @Override
  public void routing(HttpRules rules) {
    rules.get("/", (_, res) -> res.send("Hello World!"));
  }
}
