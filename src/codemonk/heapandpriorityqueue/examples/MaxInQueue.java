package codemonk.heapandpriorityqueue.examples;

import java.util.Scanner;

/**
 *
 * SOLVED SUCCESSFULLY
 *
 * Created by Nandan Mankad on 26-Jun-17.
 *
 * This is a Sample Problem from the learning of the Heaps and Priority Queue. This has a typical implementation of
 * MaxHeapify / Buid Max Heap / Sift Up.
 *
 */
public class MaxInQueue {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int arr[] = new int[110000];
        int n = in.nextInt();
        int currLen = n;
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        buildMaxHeap(arr, currLen);
        int q = in.nextInt();

        for (int i = 0; i < q; i++) {
            int type = in.nextInt();
            if (type == 1) {
                arr[currLen] = in.nextInt();
                siftUp(arr, currLen);
                currLen++;
            } else {
                System.out.println(arr[0]);
            }
        }
    }


    public static void siftUp (int arr[], int i) {
        while (true) {
            int parent = 0;
            if (i % 2 == 0) {
                parent = i / 2 - 1;
            } else {
                parent = i / 2;
            }
            if (parent >= 0 && arr[parent] < arr[i]) {
                swap(parent, i, arr);
                i = parent;
            } else {
                break;
            }
        }
    }

    public static void maxHeapify(int arr[], int i, int N) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int largest = i;

        if (left < N && arr[left] > arr[i]) {
            largest = left;
        }

        if (right < N && arr[right] > arr[largest]) {
            largest = right;
        }

        if (i != largest) {
            swap(i, largest, arr);
            maxHeapify(arr, largest, N);
        }

    }

    public static void buildMaxHeap (int arr[], int N) {

        for (int i = N / 2 - 1; i >= 0; i--) {
            maxHeapify(arr, i, N);
        }
    }

    private static void swap (int i, int j, int arr[]) {
        int temp;
        temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
