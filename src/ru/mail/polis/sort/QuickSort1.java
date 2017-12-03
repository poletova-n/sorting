package ru.mail.polis.sort;

public class QuickSort1<T> extends AbstractSortOnComparisons<T> {

    public int rnd(int min, int max)
    {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

    public int partition(T[] a,int left, int right){

        T p=a[rnd(left, right)];
        int i=left;
        int j=right;
        while(i<=j){
            while(lesser(a[i],p) ){
                i++;
            }
            while(greater(a[j],p)){
                j--;
            }
            if(i<=j){
                swap(a,i,j);
                i++;
                j--;
            }
        }
        return i;
    }

    public void quickSort(T[] a, int left, int right){
        if(left>=right){
            return;
        }
        if(right-left<20){
            for (int i=1;i<a.length;i++){
                for(int j=i; j>0 && lesser(a[j],a[j-1]);j--){
                    swap(a,j-1,j);
                }
            }
        } else {
            int ind = partition(a, left, right);
            quickSort(a, left, ind - 1);

            quickSort(a, ind, right);
        }
    }

    @Override
    public void sort(T[] array) {
        quickSort(array,0,array.length-1);
    }

    public static void main(String[] args) {
        Integer[] mas = new Integer[] {8,9,6,4,37,89,64,25};
        QuickSort1<Integer> qs1= new QuickSort1<>();
        qs1.sort(mas);
        for (Integer ma : mas) {
            System.out.println(ma);
        }
    }
}
