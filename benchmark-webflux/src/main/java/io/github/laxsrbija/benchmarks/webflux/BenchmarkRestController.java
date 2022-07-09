package io.github.laxsrbija.benchmarks.webflux;

import java.time.Duration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class BenchmarkRestController {

  @GetMapping("text")
  public String text() {
    return "Hello world!";
  }

  @GetMapping("text/{delay}")
  public Mono<String> textWithDelay(@PathVariable("delay") long delay) {
    return Mono.just("Hello world!")
        .delayElement(Duration.ofMillis(delay));
  }
}
