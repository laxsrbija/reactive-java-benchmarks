package io.github.laxsrbija.benchmarks.webmvc;

import java.net.URI;
import java.util.concurrent.locks.LockSupport;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class BenchmarkRestController {

  private final RestTemplate restTemplate = new RestTemplate();

  @GetMapping("text")
  public String text() {
    return "Hello world!";
  }

  @GetMapping("text/{delay}")
  public String textWithDelay(@PathVariable("delay") long delay) {
    LockSupport.parkNanos(delay * 1_000_000);
    return "Hello world!";
  }

  @GetMapping("remote/{localDelay}")
  public String remoteWithDelay(
      @PathVariable("localDelay") final long localDelay,
      @RequestParam("server") final String server) {
    final URI uri = URI.create(server);

    final ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
    LockSupport.parkNanos(localDelay * 1_000_000);

    return response.getBody();
  }
}
