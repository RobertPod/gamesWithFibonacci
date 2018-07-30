package robert.trening;

public class FibonacciIterationImp implements Fibonacci {

  public long compute(int n) {
    if (n < 0) {
      throw new IllegalArgumentException("The argument must be positive");
    } else if (n > 92) {
      throw new IllegalArgumentException("The maximum input value for long type output is 92");
    }

    if (n <= 1) {
      return (long) n;
    } else {
      long fibo = 0;
      long fibo_2 = 0;
      long fibo_1 = 1;

      for (int i = 2; i <= n; i++) {
        fibo = fibo_1 + fibo_2;
        fibo_2 = fibo_1;
        fibo_1 = fibo;
      }
      return fibo;
    }
  }

  public static void main(String... arg) {
    int input = 92; // 7540113804746346429
    System.out
        .println("For " + input + " Fibonacci is " + new FibonacciIterationImp().compute(input));
  }
}
