package ru.mail.polis.sort;

public class QuickSort2<T> extends AbstractSortOnComparisons<T> implements Sort<T>{

    public void sort(T[] array) {
        sort(array, 0, array.length - 1);
    }

    private void sort(T[] input, int lowIndex, int highIndex) {
        if (highIndex <= lowIndex) return;
        T pivot1 = input[lowIndex];
        T pivot2 = input[highIndex];
        if (greater(pivot1, pivot2)) {
            swap(input, lowIndex, highIndex);
            pivot1 = input[lowIndex];
            pivot2 = input[highIndex];
        } else if (pivot1 == pivot2) {
            while (pivot1.equals(pivot2) && lowIndex < highIndex) {
                lowIndex++;
                pivot1 = input[lowIndex];
            }
        }


        int i = lowIndex + 1;
        int lt = lowIndex + 1;
        int gt = highIndex - 1;

        while (i <= gt) {

            if (lesser(input[i], pivot1)) {
                swap(input, i++, lt++);
            } else if (lesser(pivot2, input[i])) {
                swap(input, i, gt--);
            } else {
                i++;
            }

        }
        swap(input, lowIndex, --lt);
        swap(input, highIndex, ++gt);

        sort(input, lowIndex, lt - 1);
        sort(input, lt + 1, gt - 1);
        sort(input, gt + 1, highIndex);

    }
}