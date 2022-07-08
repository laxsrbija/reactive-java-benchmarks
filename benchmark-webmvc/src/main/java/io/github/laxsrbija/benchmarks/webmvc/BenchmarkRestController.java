package io.github.laxsrbija.benchmarks.webmvc;

import java.util.concurrent.locks.LockSupport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BenchmarkRestController {

  @GetMapping("text")
  public String text() {
    return "Hello world!";
  }

  @GetMapping("text-with-delay")
  public String textWithDelay() {
    LockSupport.parkNanos(50_000_000);
    return "Hello world!";
  }
}
