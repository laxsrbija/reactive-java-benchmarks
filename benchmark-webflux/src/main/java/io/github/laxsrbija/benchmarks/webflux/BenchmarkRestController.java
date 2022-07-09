package io.github.laxsrbija.benchmarks.webflux;

import java.net.URI;
import java.time.Duration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class BenchmarkRestController {

  @GetMapping("text")
  public Mono<String> text() {
    return Mono.just("Hello world!");
  }

  @GetMapping("text/{delay}")
  public Mono<String> textWithDelay(@PathVariable("delay") final long delay) {
    return Mono.just("Hello world!")
        .delayElement(Duration.ofMillis(delay));
  }

  @GetMapping("remote/{localDelay}")
  public Mono<String> remoteWithDelay(
      @PathVariable("localDelay") final long localDelay,
      @RequestParam("server") final String server) {
    final URI uri = URI.create(server);

    return WebClient.create()
        .get()
        .uri(uri)
        .retrieve()
        .bodyToMono(String.class)
        .delayElement(Duration.ofMillis(localDelay));
  }
}
