package robert.trening;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class FibonacciTest {

  FibonacciImp fibonacci;

  @Before
  public void init() {
    fibonacci = new FibonacciImp();
  }

  @Test
  public void computePossitiveCase() {
    assertEquals(514229L, fibonacci.compute(29));
  }

  @Test(expected = IllegalArgumentException.class)
  public void computeNegativeCaseExpectedAttribute() {
    fibonacci.compute(-1);
  }

  @Rule
  public ExpectedException exception = ExpectedException.none();

  @Test
  public void computeNegativeCaseRuleExpectedException() {
    exception.expect(IllegalArgumentException.class);
    exception.expectMessage("The maximum input value for long type output is 92");

    fibonacci.compute(93);
  }
}