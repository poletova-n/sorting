package ru.mail.polis.sort;

public class HeapSort<T> extends AbstractSortOnComparisons<T> implements Sort<T> {

    private int size=0;

    private void buildUp(int index,T[] heap) {
        while (index > 1 && lesser(heap[index],heap[index >> 1])) {
            T tmp = heap[index];
            heap[index] = heap[index >> 1];
            heap[index >> 1] = tmp;

            index >>= 1;
        }
    }

    private void buildDown(int index,T[] heap) {
        int leftChild = index * 2;
        int rightChild = index * 2 + 1;

        int max = index;

        if (leftChild <= size && lesser(heap[leftChild],heap[max])) {
            max = leftChild;
        }

        if (rightChild <= size && lesser(heap[rightChild],heap[max])) {
            max = rightChild;
        }

        if (max != index) {
            T tmp = heap[index];
            heap[index] = heap[max];
            heap[max] = tmp;

            buildDown(max,heap);
        }
    }

    private void insert(T value,T[] heap) {
        size++;
        heap[size] = value;
        buildUp(size,heap);
    }

    private T extract(T[] heap) {
        T tmp = heap[1];
        heap[1] = heap[size];
        size--;
        buildDown(1,heap);
        return tmp;
    }

    @Override
    public void sort(T[] array) {
        T[] heap = (T[])new Object[array.length+1];
        for(int i=0;i<array.length;++i){
            insert(array[i],heap);
        }
        for(int i=0;i<array.length;++i){
            array[i]=extract(heap);
        }
    }
}
