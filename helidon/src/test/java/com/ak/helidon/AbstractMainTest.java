package com.ak.helidon;


import io.helidon.http.Status;
import io.helidon.webclient.api.ClientResponseTyped;
import io.helidon.webclient.http1.Http1Client;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

abstract class AbstractMainTest {
  private final Http1Client client;

  protected AbstractMainTest(Http1Client client) {
    this.client = client;
  }

  @ParameterizedTest
  @CsvSource(delimiter = '|', textBlock = """
      /simple-greet |    '' | Hello World!
             /greet |    '' | Hello World!
         /greet/Joe |    '' | Hello Joe!
             /greet | Howdy | Howdy World!
         /greet/Joe | Howdy | Howdy Joe!
      """)
  void greetGet(String path, String config, String greet) {
    if (!config.isEmpty()) {
      ClientResponseTyped<String> response = client.put("/greet/greeting").submit(config, String.class);
      assertThat(response.status()).isEqualTo(Status.NO_CONTENT_204);
    }

    ClientResponseTyped<String> response = client.get(path).request(String.class);
    assertThat(response.status()).isEqualTo(Status.OK_200);
    assertThat(response.entity()).isEqualTo(greet);
  }
}
