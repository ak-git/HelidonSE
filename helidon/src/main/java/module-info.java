module com.ak.helidon {
  requires org.jspecify;
  requires java.logging;
  requires io.helidon.webserver;
  requires io.helidon.logging.common;
  requires io.helidon.config;
  requires telegram.bot.api;
  requires kotlin.stdlib;
  exports com.ak.helidon;
}