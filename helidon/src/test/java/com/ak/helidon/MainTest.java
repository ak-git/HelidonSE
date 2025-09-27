package com.ak.helidon;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MainTest {
  @Test
  void test() {
    Assertions.assertDoesNotThrow(Main::main);
  }
}