package robert.trening;

import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.ipc.netty.http.server.HttpServer;

import java.time.Duration;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;
import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

public class FibonacciReactiveFuncNIOWebServerImp implements Fibonacci {

  public long compute(int n) {
    if (n < 0) {
      throw new IllegalArgumentException("The argument must be positive");
    }
    if (n > 92) {
      throw new IllegalArgumentException("The maximum input value for long type output is 92");
    }

    new FibonacciReactiveFuncNIOWebServerImp().start();
    Mono<String> value = WebClient.create("http://localhost:8080")
        .get()
        .uri("/fib/{n}", n)
        .accept(MediaType.TEXT_HTML)
        .exchange()
        .flatMap(resp -> resp.bodyToMono(String.class));

    return Long.parseLong(value.block(Duration.ofMinutes(20)));
  }

  private void start() {
    RouterFunction<?> route = route(GET("/fib/{n}"),
        request -> {
          final long n = Long.parseLong(request.pathVariable("n"));
          if (n < 2) {
            return ServerResponse.ok().contentType(MediaType.TEXT_HTML)
                .body(fromObject(String.valueOf(n)));
          } else {
            Mono<Long> n_1 = WebClient.create("http://localhost:8080")
                .get()
                .uri("/fib/{n}", (n - 1))
                .accept(MediaType.TEXT_HTML)
                .exchange()
                .flatMap(resp -> resp.bodyToMono(String.class))
                .map(Long::parseLong);
            Mono<Long> n_2 = WebClient.create("http://localhost:8080")
                .get()
                .uri("/fib/{n}", (n - 2))
                .accept(MediaType.TEXT_HTML)
                .exchange()
                .flatMap(resp -> resp.bodyToMono(String.class))
                .map(Long::parseLong);

            Mono<String> result = n_1.flatMap(a -> n_2.map(b -> a + b))
                .map(String::valueOf);
            return ServerResponse.ok().contentType(MediaType.TEXT_HTML).body(fromPublisher(result,
                String.class));
          }
        });

    HttpHandler httpHandler = RouterFunctions.toHttpHandler(route);
    ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(httpHandler);
    HttpServer server = HttpServer.create("localhost", 8080);
    server.start(adapter);
  }


  public static void main(String... arg) {
    int input = 6;
    System.out
        .println("For " + input + " Fibonacci is " + new FibonacciReactiveFuncNIOWebServerImp()
            .compute(input));
  }
}
