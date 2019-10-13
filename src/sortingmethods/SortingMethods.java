/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sortingmethods;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Karol
 */
public class SortingMethods {

    public static void main(String[] args) {
        SortingMethods sm = new SortingMethods();
        int[] array = sm.generateRandomArray();

        System.out.println("-".repeat(array.length*5));
        sm.printArray("Unsorted array", array);

        sm.BubbleSort(array.clone());
        sm.ImprovedBubbleSort(array.clone());
        sm.InsertionSort(array.clone());
        sm.MergeSort(array.clone(), array.length);
        sm.CountSort(array.clone());
        sm.BucketSort(array.clone());
        sm.SelectionSort(array.clone());
    }

    //generating unsorted array with non-repeating random values from 1 to n
    public int[] generateRandomArray() {
        Random r = new Random();
        Scanner sc = new Scanner(System.in);
        System.out.print("Set the size of the array: ");
        int n = sc.nextInt();

        int[] ta = new int[n];
        for (int i = 0; i < ta.length; i++) {
            int t;
            boolean tr;
            do {
                final int temp = r.nextInt(n) + 1;
                tr = Arrays.stream(ta).anyMatch((value) -> temp == value);
                t = temp;
            } while (tr);
            ta[i] = t;
        }
        return ta;
    }

    //Method to print arrays
    public void printArray(String title, int[] arr) {
        System.out.println(title.concat(" | n=" + arr.length));
        System.out.println("-".repeat(arr.length*5));
        for (int x : arr) {
            System.out.printf("%5d", x);
        }
        System.out.println("");
        System.out.println("-".repeat(arr.length*5));
    }

    //Bubble sort
    public int[] BubbleSort(int[] arr) {
        for (int j = 0; j < arr.length - 1; j++) {
            for (int i = 0; i < arr.length - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                }
            }
        }
        this.printArray("Bubble sort", arr);
        return arr;
    }

    //Improved Bubble sort - best case in O(n) and worst case in O(n^2)
    public int[] ImprovedBubbleSort(int[] arr) {
        boolean flag = true;
        for (int j = 0; j < arr.length - 1 && flag; j++) {
            flag = false;
            for (int i = 0; i < arr.length - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    flag = true;
                }
            }
        }
        this.printArray("Improved Bubble sort", arr);
        return arr;
    }

    //insertion sort
    public int[] InsertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int v = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] > v) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = v;
        };
        this.printArray("Insertion sort", arr);
        return arr;
    }

    //merge sort
    public int[] MergeSort(int[] arr, int n) {

        if (arr.length < 2) {
            return arr;
        }
        int m = arr.length / 2;

        int[] t1 = new int[m];
        int[] t2 = new int[arr.length - m];

        for (int i = 0; i < m; i++) {
            t1[i] = arr[i];
        }
        for (int i = m; i < arr.length; i++) {
            t2[i - m] = arr[i];
        }

        MergeSort(t1, n);
        MergeSort(t2, n);
        merge(arr, t1, t2, t1.length, t2.length);

        if (arr.length == n) {
            printArray("Merge sort", arr);
        }
        return arr;
    }

    private void merge(int[] arr, int[] a1, int[] a2, int l1, int l2) {
        int i = 0, j = 0, k = 0;
        while (i < l1 && j < l2) {
            if (a1[i] <= a2[j]) {
                arr[k++] = a1[i++];
            } else {
                arr[k++] = a2[j++];
            }
        }
        while (i < l1) {
            arr[k++] = a1[i++];
        }
        while (j < l2) {
            arr[k++] = a2[j++];
        }
    }

    //count sort
    public int[] CountSort(int[] arr) {

        int[] temp = new int[arr.length];
        int min = getMin(arr);
        int max = getMax(arr);

        int[] count = new int[max - min + 1];

        for (int i = 0; i < arr.length; i++) {
            count[arr[i] - min]++;
        }

        count[0]--;
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            temp[count[arr[i] - min]--] = arr[i];
        }

        printArray("Count sort", temp);
        return temp;
    }

    //bucket sort
    public int[] BucketSort(int[] arr) {
        int[] bucket = new int[getMax(arr) + 1];

        for (int i = 0; i < arr.length; i++) {
            bucket[arr[i]]++;
        }

        int outPos = 0;
        for (int i = 0; i < bucket.length; i++) {
            for (int j = 0; j < bucket[i]; j++) {
                arr[outPos++] = i;
            }
        }

        printArray("Bucket sort", arr);
        return arr;
    }
    //radix sort

    //Selection sort
    public int[] SelectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }
        this.printArray("Selection sort", arr);
        return arr;
    }

    public int getMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    public int getMin(int[] arr) {
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        return min;
    }
}
