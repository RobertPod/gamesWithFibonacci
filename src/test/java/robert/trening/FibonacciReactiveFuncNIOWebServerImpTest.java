package robert.trening;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class FibonacciReactiveFuncNIOWebServerImpTest {

  FibonacciReactiveFuncNIOWebServerImp fibonacci;

  @Before
  public void init() {
    fibonacci = new FibonacciReactiveFuncNIOWebServerImp();
  }

  @Test(timeout = 30000L)
  public void computePossitiveCase() {
    assertEquals(55L, fibonacci.compute(10));
  }

  @Rule
  public ExpectedException exception = ExpectedException.none();

  @Test
  public void computeNegativeCaseRuleExpectedException() {
    exception.expect(IllegalArgumentException.class);
    exception.expectMessage("The argument must be positive");

    fibonacci.compute(-1);
  }

  @Test
  public void computeNegativeCaseRuleExpectedException1() {
    exception.expect(IllegalArgumentException.class);
    exception.expectMessage("The maximum input value for long type output is 92");

    fibonacci.compute(93);
  }

}