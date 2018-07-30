package robert.trening;

// In Java not a tail-end recursion
public class FibonacciRecursionImp implements Fibonacci {

  public long compute(int n) {
    if (n < 0) {
      throw new IllegalArgumentException("The argument must be positive");
    } else if (n > 92) {
      throw new IllegalArgumentException("The maximum input value for long type output is 92");
    } else if (n < 2) {
      return (long) n;
    }
    return compute(n - 1) + compute(n - 2);
  }

  public static void main(String... arg) {
    int input = 50; // 12586269025
    System.out
        .println("For " + input + " Fibonacci is " + new FibonacciRecursionImp().compute(input));
  }
}