package ru.mail.polis.sort;

import ru.mail.polis.structures.IntKeyObject;
import ru.mail.polis.structures.IntKeyStringValueObject;

/**
 * Created by Nechaev Mikhail
 * Since 27/11/2017.
 */
public class CountingSort<T extends IntKeyObject> implements Sort<T> {

    public CountingSort() {
        /* empty */
    }

    private int findMax(T[] array){
        int max=array[0].getKey();
        for(int i=1;i<array.length;++i){
            if(max<array[i].getKey()){
                max=array[i].getKey();
            }
        }
        return max;
    }
    @Override
    public void sort(T[] array) {
        int max = findMax(array);
        int[] count = new int[max + 1];
        for (T x : array) {
            count[x.getKey()]++;
        }
        for (int i = 1; i <= max; i++) {
            count[i] += count[i - 1];
        }
        T[] res = (T[])new IntKeyObject[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            res[--count[array[i].getKey()]] = array[i];
        }
        System.arraycopy(res, 0, array, 0, array.length);
    }

    public static void main(String[] args) {
        IntKeyStringValueObject[] array = new IntKeyStringValueObject[] {new IntKeyStringValueObject(8,"jh"),
                new IntKeyStringValueObject(11,"fd"),
                new IntKeyStringValueObject(95,"fid"),
                new IntKeyStringValueObject(51,"fj"),
                new IntKeyStringValueObject(89,"rrr"),
                };
        CountingSort<IntKeyStringValueObject> cs = new CountingSort<>();
        cs.sort(array);
        for (int i=0;i<array.length;i++){
            System.out.println(array[i].getKey()+" "+array[i].getValue());
        }
    }
}
