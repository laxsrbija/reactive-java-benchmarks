import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;
import java.time.Duration;

public class LoadSimulation extends Simulation {

  final String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/");
  final String endpoint = System.getProperty("endpoint", "text");

  final HttpProtocolBuilder httpProtocol =
      http
        .baseUrl(baseUrl)
        .acceptHeader("text/html")
        .userAgentHeader("Gatling");

  final ScenarioBuilder scn =
      scenario("Load tests")
        .forever()
        .on(exec(http("Test")
            .get(endpoint)));

  {
    setUp(
      scn.injectClosed(
        incrementConcurrentUsers(256)
          .times(32)
          .eachLevelLasting(20)
          .separatedByRampsLasting(10)
          .startingFrom(0))
        .protocols(httpProtocol))
      .maxDuration(Duration.ofMinutes(20));
  }
}
