
package com.ak.helidon;


import io.helidon.config.Config;
import io.helidon.logging.common.LogConfig;
import io.helidon.webserver.WebServer;
import io.helidon.webserver.http.HttpRouting;

import java.util.logging.Logger;

public class Main {
  private Main() {
  }

  public static void main() {
    LogConfig.configureRuntime();
    WebServer server = WebServer.builder().config(Config.create().get("server")).routing(Main::routing).build().start();
    Logger.getLogger(Main.class.getName()).info(() -> "WEB server is up! http://localhost:%d/%n".formatted(server.port()));
  }

  static void routing(HttpRouting.Builder routing) {
    routing.register("/", new BotService());
  }
}