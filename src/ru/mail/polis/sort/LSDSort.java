package ru.mail.polis.sort;

import ru.mail.polis.structures.IntKeyStringValueObject;
import ru.mail.polis.structures.Numerical;
import ru.mail.polis.structures.SimpleInteger;

/**
 * Created by Nechaev Mikhail
 * Since 27/11/2017.
 */
public class LSDSort<T extends Numerical> implements Sort<T> {

    public LSDSort() {
        /* empty */
    }

    @Override
    public void sort(T[] array) {
        final int r = array[0].getDigitMaxValue();
        int d = array[0].getDigitCount();
        for (int i = 1; i < array.length; i++) {
            if (array[i].getDigitCount() > d) {
                d = array[i].getDigitCount();
            }
        }

        for (int k = 0; k < d; k++) {
            int[] count = new int[r+1];
            for (T x : array) {
                count[x.getDigit(k)]++;
            }
            for (int i = 1; i < r; i++) {
                count[i] += count[i - 1];
            }
            T[] res = (T[]) new Numerical[array.length];
            for (int i = array.length - 1; i >= 0; i--) {
                res[--count[array[i].getDigit(k)]] = array[i];
            }
            System.arraycopy(res, 0, array, 0, array.length);
        }
    }
    public static void main(String[] args) {
        SimpleInteger[] array = new SimpleInteger[] {new SimpleInteger(9),
                new SimpleInteger(8),
                new SimpleInteger(11),
                new SimpleInteger(56),
                new SimpleInteger(48),
                new SimpleInteger(82),
                new SimpleInteger(53)};
        LSDSort<SimpleInteger> cs = new LSDSort<>();
        cs.sort(array);
        for (int i=0;i<array.length;i++){
            System.out.println(array[i].getData());
        }
    }
    }
