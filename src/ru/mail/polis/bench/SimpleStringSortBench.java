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
import ru.mail.polis.sort.HeapSort;
import ru.mail.polis.sort.LSDSort;
import ru.mail.polis.sort.MergeSort;
import ru.mail.polis.sort.QuickSort1;
import ru.mail.polis.sort.QuickSort2;
import ru.mail.polis.structures.ArrayType;
import ru.mail.polis.structures.SimpleInteger;
import ru.mail.polis.structures.SimpleString;

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
public class SimpleStringSortBench {
    //todo: классы extends AbstractSortOnComparisons и LSDSort
    MergeSort<SimpleString> ms = new MergeSort<>();
    HeapSort<SimpleString> hs = new HeapSort<>();
    QuickSort1<SimpleString> qs1 = new QuickSort1<>();
    QuickSort2<SimpleString> qs2 = new QuickSort2<>();
    LSDSort<SimpleString> lsd = new LSDSort<>();

    SimpleString[][] data;
    SimpleString[] curr;

    int index;


    @Setup(value = Level.Trial)
    public void setUpTrial() {
        data = new SimpleString[10][10000];
        ArrayType type = ArrayType.UNIQUE;
        switch (type){
        case SAME:{
            for (int i = 0; i < 10; i++) {
                for(int j=0;j<10000;j++){
                    Random r = new Random();
                    int n = r.nextInt(3);
                    data[i][j]=new SimpleString(String.valueOf((char)(n+'a')));
                }
            }
            break;
        }
        case RANDOM:{
            for (int i = 0; i < 10; i++) {
                for(int j=0;j<10000;j++){
                    Random r = new Random();
                    char c1=(char)(r.nextInt(25)+'a');
                    char c2=(char)(r.nextInt(25)+'a');
                    char c3=(char)(r.nextInt(25)+'a');
                    String s=String.valueOf(new char[]{c1,c2,c3});
                    data[i][j]=new SimpleString(s);
                }
            }
            break;
        }
        case UNIQUE:{
            for (int i = 0; i < 10; i++) {
                for(int j=0;j<10000;j++){
                    char c1=(char)('a'+(j%25));
                    char c2=(char)((j/25)%25+'a');
                    char c3=(char)((j/625)%25+'a');
                    String s=String.valueOf(new char[]{c1,c2,c3});
                    data[i][j]=new SimpleString(s);
                }
            }
            break;
        }
        }


    }

    @Setup(value = Level.Invocation)
    public void setUpInvocation() {
        curr = Arrays.copyOf(data[index], data[index].length);
        index = (index + 1) % 10;
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
                .include(ru.mail.polis.bench.SimpleStringSortBench.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}
