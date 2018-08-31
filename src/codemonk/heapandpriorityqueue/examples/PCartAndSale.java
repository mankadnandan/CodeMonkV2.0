package codemonk.heapandpriorityqueue.examples;

import java.util.Scanner;

/**
 *
 * SOLVED SUCCESSFULLY
 *
 * Created by Nandan Mankad on 8/30/2018.
 *
 * ###################################################################################################################################################################
 * Approach: Here Panik is always going to pick the item with the lowest price. So we can maintain a Min-Heap here. Afer picking
 * this item the price of the item changes. So after the change we run minHeapify on the root (item with lowest price) to
 * adjust the items. This will make sure the lowest price item will always be at the root (Min-Heap invariants are held.)
 *
 * Time Complexity : We do M operations for picking M items, and for each operation we adjust the price and hence the heap.
 * Adjusting the heap takes O(log (N)).
 *
 * Hence Effective complexity of O(M Log(N)) in total.
 *
 * ###################################################################################################################################################################
 *
 Pcart is an online shopping website with N different items for sale such that all items are available in infinite quantities.
 Panik is opening a shop and wants to buy M items for his shop at the lowest possible price. The price of the ith item is A[i].
 But there is a catch, Panik can order only 1 item at a time and after ordering it, the price of the chosen item changes
 according to the following expression:

 New price=(original price xor Pcart constant(X))+1

 Panik is not aware of this expression so he always purchases the item with the lowest price in a single transaction.
 Predict the amount Panik would have to pay to purchase M items. As the answer can be very large, print the ans%1e9+7

 INPUT

 The first line contains N M X
 The Second line contains N-space separated integers denoting the original price of each item.

 OUTPUT

 Print a single integer denoting the expected amount Panik has to spend.

 CONSTRAINTS

 0<=N,M,X,A[i]<=10^5
 *
 */
public class PCartAndSale {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int M = in.nextInt();
        int X = in.nextInt();

        int arr[] = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = in.nextInt();
        }

        buildMinHeap(arr);

        long res = 0;

        for (int i = 0; i < M; i++) {
            res = res % 1000000007 + arr[0];
            arr[0] = (arr[0] ^ X) + 1;
            minHeapify(arr, 0);
        }

        System.out.println(res);

    }

    private static void buildMinHeap(int arr[]) {
        for (int i = arr.length / 2; i >= 0; i--) {
            minHeapify(arr, i);
        }
    }

    private static void minHeapify(int arr[], int node) {
        int left = node * 2;
        int right = node * 2 + 1;

        int smallest = node;

        if (left < arr.length && arr[smallest] > arr[left]) {
            smallest = left;
        }

        if (right < arr.length && arr[smallest] > arr[right]) {
            smallest = right;
        }

        if (smallest != node) {
            swap(arr, node, smallest);
            minHeapify(arr, smallest);
        }
    }

    private static void swap(int arr[], int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }
}
