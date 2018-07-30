package robert.trening;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.runners.Parameterized.*;

@RunWith(Parameterized.class)
public class FibonacciParametrizedTest {

  @Parameter()
  public int input;
  @Parameter(1)
  public long result;

  @Parameters(name = "{index}: fib({0})={1}")
  public static Collection<Object[]> data() {
    Object[][] data = new Object[][]{{0, 0}, {1, 1}, {2, 1}, {3, 2},
        {5, 5}, {29, 514229L}, {19, 4181},
        {50, 12586269025L},
        {91, 4660046610375530309L},
        {92, 7540113804746346429L}};
    return Arrays.asList(data);
  }

  @Test
  public void computePossitiveCase() {
    FibonacciImp fibonacci = new FibonacciImp();

    assertEquals("Result", result, fibonacci.compute(input));
  }
}