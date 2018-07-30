package robert.trening;

import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import static org.junit.Assert.assertEquals;
import static org.openjdk.jmh.annotations.Mode.Throughput;
import static org.openjdk.jmh.runner.options.TimeValue.seconds;

public class FibonacciCompRecursionImpJMHTest {

  @State(Scope.Thread)
  public static class MyState {

    FibonacciRecursionImp recursionImp;
    FibonacciIterationImp iterationImp;
    FibonacciImp fibonacciImp;

    @Setup
    public void init() {
      recursionImp = new FibonacciRecursionImp();
      iterationImp = new FibonacciIterationImp();
      fibonacciImp = new FibonacciImp();
    }
  }

  @Benchmark
  public void Acompute_08(MyState state) {
    assertEquals("Result", 21, state.recursionImp.compute(8));
  }

  @Benchmark
  public void Bcompute_16(MyState state) {
    assertEquals("Result", 987, state.recursionImp.compute(16));
  }

  @Benchmark
  public void Ccompute_24(MyState state) {
    assertEquals("Result", 46368, state.recursionImp.compute(24));
  }

  @Benchmark
  public void Dcompute_32(MyState state) {
    assertEquals("Result", 2178309, state.recursionImp.compute(32));
  }

  @Benchmark
  public void Ecompute_40(MyState state) {
    assertEquals("Result", 102334155, state.recursionImp.compute(40));
  }

  @Benchmark
  public void JcomputeIiteration_40(MyState state) {
    assertEquals("Result", 102334155, state.iterationImp.compute(40));
  }

  @Benchmark
  public void OcomputeFibonacci_40(MyState state) {
    assertEquals("Result", 102334155, state.fibonacciImp.compute(40));
  }


  @Test
  public void computeNegativeCaseTryCatch() throws RunnerException {

    Options opt = new OptionsBuilder()
        .include(FibonacciCompRecursionImpJMHTest.class.getSimpleName())
        .warmupIterations(2)
        .warmupTime(seconds(1))
        .measurementIterations(2)
        .measurementTime(seconds(2))
        .mode(Throughput)
        .forks(1)
        .build();

    new Runner(opt).run();
  }

}