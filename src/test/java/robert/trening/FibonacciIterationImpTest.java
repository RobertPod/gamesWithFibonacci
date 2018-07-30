package robert.trening;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class FibonacciIterationImpTest {

  private FibonacciIterationImp fibonacci;

  @Before
  public void init() {
    fibonacci = new FibonacciIterationImp();
  }

  @Test
  public void computePossitiveCase() {
    assertEquals(514229L, fibonacci.compute(29));
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