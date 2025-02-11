package com.ak.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class MainApp {
  private static final Logger LOGGER = LoggerFactory.getLogger(MainApp.class);

  private MainApp() {
  }

  public static void main(String[] args) {
    LOGGER.atInfo().setMessage(() -> "Hello World!").addKeyValue("time", () -> ZonedDateTime.now(ZoneId.systemDefault())).log();
  }
}
