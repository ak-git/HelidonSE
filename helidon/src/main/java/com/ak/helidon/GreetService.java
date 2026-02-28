package com.ak.helidon;

import io.helidon.config.Config;
import io.helidon.http.Status;
import io.helidon.service.registry.Service;
import io.helidon.webserver.http.HttpRules;
import io.helidon.webserver.http.HttpService;
import io.helidon.webserver.http.ServerResponse;

import java.util.concurrent.atomic.AtomicReference;

@Service.Singleton
final class GreetService implements HttpService {
  private final AtomicReference<String> greeting = new AtomicReference<>(GreetConfig.create(Config.create().get("app")).greeting());

  @Override
  public void routing(HttpRules rules) {
    rules
        .get("/", (_, res) -> sendResponse(res, "World"))
        .get("/{name}", (req, res) -> sendResponse(res, req.path().pathParameters().get("name")))
        .put("/greeting", (req, res) -> updateGreeting(req.content().as(String.class), res));
  }

  private void sendResponse(ServerResponse response, String name) {
    response.send("%s %s!".formatted(greeting.get(), name));
  }

  private void updateGreeting(String update, ServerResponse response) {
    greeting.set(update);
    response.status(Status.NO_CONTENT_204).send();
  }
}
