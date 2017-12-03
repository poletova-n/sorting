package ru.mail.polis.sort;

import java.util.Arrays;

public class MergeSort<T> extends AbstractSortOnComparisons<T> implements Sort<T>{

    @Override
    public void sort(T[] arr){
        T[] tmp = arr;
        arr=mergeSort(tmp);
    }


    public T[] mergeSort(T[] arr){
        if(arr.length < 2) return arr;
        int m = arr.length / 2;
        T[] arr1 = Arrays.copyOfRange(arr, 0, m);
        T[] arr2 = Arrays.copyOfRange(arr, m, arr.length);
        return merge(mergeSort(arr1), mergeSort(arr2));
    }

    public T[] merge(T[] arr1,T[] arr2){
        int n = arr1.length + arr2.length;
        T[] arr = (T[])new Object[n];
        int i1=0;
        int i2=0;
        for(int i=0;i<n;i++){
            if(i1 == arr1.length){
                arr[i] = arr2[i2++];
            }else if(i2 == arr2.length){
                arr[i] = arr1[i1++];
            }else{
                if(lesser(arr1[i1],arr2[i2])){
                    arr[i] = arr1[i1++];
                }else{
                    arr[i] = arr2[i2++];
                }
            }
        }
        return arr;
    }
}
