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
import org.openjdk.jmh.runner.options.TimeValue;

import static org.junit.Assert.assertEquals;
import static org.openjdk.jmh.annotations.Mode.Throughput;

public class FibonacciComparisonOfImplementationsJMHTest {

  @State(Scope.Thread)
  public static class MyState {

    FibonacciIterationImp iterationImp;
    FibonacciStreamImp fibonacciStreamImp;

    @Setup
    public void init() {
      iterationImp = new FibonacciIterationImp();
      fibonacciStreamImp = new FibonacciStreamImp();
    }
  }

  @Benchmark
  public void iterationImp_30(MyState state) {
    assertEquals("Result", 832040, state.iterationImp.compute(30));
  }

  @Benchmark
  public void iterationImp_60(MyState state) {
    assertEquals("Result", 1548008755920L, state.iterationImp.compute(60));
  }

  @Benchmark
  public void iterationImp_90(MyState state) {
    assertEquals("Result", 2880067194370816120L, state.iterationImp.compute(90));
  }

  @Benchmark
  public void streamImp_30(MyState state) {
    assertEquals("Result", 832040, state.fibonacciStreamImp.compute(30));
  }

  @Benchmark
  public void streamImp_60(MyState state) {
    assertEquals("Result", 1548008755920L, state.fibonacciStreamImp.compute(60));
  }

  @Benchmark
  public void streamImp_90(MyState state) {
    assertEquals("Result", 2880067194370816120L, state.fibonacciStreamImp.compute(90));
  }

  @Test
  public void computeNegativeCaseTryCatch() throws RunnerException {

    Options opt = new OptionsBuilder()
        .include(FibonacciComparisonOfImplementationsJMHTest.class.getSimpleName())
        .warmupIterations(2)
        .warmupTime(TimeValue.seconds(1))
        .measurementIterations(2)
        .measurementTime(TimeValue.seconds(2))
        .mode(Throughput)
        .forks(1)
        .build();

    new Runner(opt).run();
  }

}