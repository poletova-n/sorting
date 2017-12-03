package ru.mail.polis.bench;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import ru.mail.polis.sort.BubbleSort;
import ru.mail.polis.sort.CountingSort;
import ru.mail.polis.sort.HeapSort;
import ru.mail.polis.sort.LSDSort;
import ru.mail.polis.sort.MergeSort;
import ru.mail.polis.sort.QuickSort1;
import ru.mail.polis.sort.QuickSort2;
import ru.mail.polis.sort.SortUtils;
import ru.mail.polis.structures.ArrayType;
import ru.mail.polis.structures.SimpleInteger;

/**
 * Created by Nechaev Mikhail
 * Since 27/11/2017.
 */

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class SimpleIntegerSortBench {
    //todo: классы extends AbstractSortOnComparisons и LSDSort
    MergeSort<SimpleInteger> ms = new MergeSort<>();
    HeapSort<SimpleInteger> hs = new HeapSort<>();
    QuickSort1<SimpleInteger> qs1 = new QuickSort1<>();
    QuickSort2<SimpleInteger> qs2 = new QuickSort2<>();
    LSDSort<SimpleInteger> lsd = new LSDSort<>();

    SimpleInteger[][] data;
    SimpleInteger[] curr;
    int[][] bubbleData;
    int[] bubbleCurr;

    int index;


    @Setup(value = Level.Trial)
    public void setUpTrial() {
        data = new SimpleInteger[10][10000];
        bubbleData = new int[10][10000];
        ArrayType type = ArrayType.RANDOM;
        switch (type){
        case SAME:{
            for (int i = 0; i < 10; i++) {
                for(int j=0;j<10000;j++){
                    Random r = new Random();
                    int n = r.nextInt(3);
                    data[i][j]=new SimpleInteger(n);
                    bubbleData[i][j]=n;
                }
            }
            break;
        }
        case RANDOM:{
            for (int i = 0; i < 10; i++) {
                for(int j=0;j<10000;j++){
                    Random r = new Random();
                    int n = r.nextInt(10000);
                    data[i][j]=new SimpleInteger(n);
                    bubbleData[i][j]=n;
                }
            }
            break;
        }
        case UNIQUE:{
            for (int i = 0; i < 10; i++) {
                for(int j=0;j<10000;j++){
                    data[i][j]=new SimpleInteger(10000-j);
                    bubbleData[i][j]=10000-j;
                }
            }
            break;
        }
        }


    }

    @Setup(value = Level.Invocation)
    public void setUpInvocation() {
        curr = Arrays.copyOf(data[index], data[index].length);
        bubbleCurr = Arrays.copyOf(bubbleData[index],bubbleData[index].length);
        index = (index + 1) % 10;
    }

    @Benchmark
    public void measureBubbleSort() {
        BubbleSort.sort(bubbleCurr);
    }

    @Benchmark
    public void measureMergeSort() {
        ms.sort(curr);
    }

    @Benchmark
    public void measureHeapSort() {
        hs.sort(curr);
    }

    @Benchmark
    public void measureQuickSort1() {
        qs1.sort(curr);
    }

    @Benchmark
    public void measureQuickSort2() {
        qs2.sort(curr);
    }

    @Benchmark
    public void measureLSDSort() {
        lsd.sort(curr);
    }


    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(ru.mail.polis.bench.SimpleIntegerSortBench.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}


