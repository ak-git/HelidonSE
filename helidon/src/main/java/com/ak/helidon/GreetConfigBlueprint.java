package com.ak.helidon;

import io.helidon.builder.api.Option;
import io.helidon.builder.api.Prototype;

@Prototype.Blueprint
@Prototype.Configured("app")
interface GreetConfigBlueprint {
  @Option.Configured("greeting")
  @Option.Default("Ciao")
  String greeting();
}
