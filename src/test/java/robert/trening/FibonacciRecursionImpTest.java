package robert.trening;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class FibonacciRecursionImpTest {

  FibonacciRecursionImp fibonacci;

  @Before
  public void init() {
    fibonacci = new FibonacciRecursionImp();
  }

  @Test
  public void computePossitiveCase() {
    assertEquals(514229L, fibonacci.compute(29));
  }

  @Test(expected = IllegalArgumentException.class)
  public void computeNegativeCaseExpectedAttribute() {
    fibonacci.compute(-1);
  }

}