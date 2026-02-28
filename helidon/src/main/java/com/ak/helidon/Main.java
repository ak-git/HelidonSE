
package com.ak.helidon;


import io.helidon.config.Config;
import io.helidon.logging.common.LogConfig;
import io.helidon.service.registry.Service;
import io.helidon.service.registry.ServiceRegistryManager;

import java.util.logging.Logger;

@Service.GenerateBinding
public class Main {
  static {
    LogConfig.initClass();
  }
  private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

  private Main() {
  }

  public static void main() {
    LogConfig.configureRuntime();
    ServiceRegistryManager.start(ApplicationBinding.create());
    LOGGER.info(
        () -> "WEB server is up! http://localhost:%d/simple-greet%n"
            .formatted(Config.create().get("server").get("port").asInt().orElseThrow())
    );
  }
}