module com.ak.helidon {
  requires static org.jspecify;
  requires java.logging;
  requires io.helidon.logging.common;
  requires io.helidon.config;
  requires io.helidon.config.metadata;
  requires io.helidon.webserver;
  exports com.ak.helidon;
}