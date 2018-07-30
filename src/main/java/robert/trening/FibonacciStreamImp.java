package robert.trening;

import java.util.stream.Stream;

public class FibonacciStreamImp implements Fibonacci {

  public long compute(int n) {
    if (n < 0) {
      throw new IllegalArgumentException("The argument must be positive");
    } else if (n > 92) {
      throw new IllegalArgumentException("The maximum input value for long type output is 92");
    }

    if (n <= 1) {
      return n;
    } else {
      return Stream.iterate(new long[]{0, 1}, p -> new long[]{p[1], p[0] + p[1]})
          .limit(n)
          .skip(n - 1)
          .findFirst()
          .get()[1];
    }
  }

  public static void main(String... arg) {
    int input = 6;
    System.out.println("For " + input + " Fibonacci is " + new FibonacciStreamImp().compute(input));
  }
}
