package codemonk.heapandpriorityqueue.examples;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * SOLVED SUCCESSFULLY - Though this is not an efficient solution.
 *
 * Created by Nandan Mankad on 02-Oct-17.
 *
 * ###################################################################################################################################################################
 * Approach: See efficient approach {@link MonkAndMultiplication2}
 *
 * In this problem the approach is to iterate over each element in th array and run a buildMaxHeap upto 1 to the current element of the array. So each time we have a new Heap.
 * Then for each Heap we can extract the maximum element 3 times to get 3 largest elements.
 *
 * Time complexity: buildMaxHeap is O(N) and we do this for each N. So total time complexity is O(N^2)
 * ###################################################################################################################################################################
 *
 The Monk learned about priority queues recently and asked his teacher for an interesting problem. So his teacher came up with a simple problem. He now has an integer array A. For each index i, he wants to find the product of the largest, second largest and the third largest integer in the range [1,i].
 Note: Two numbers can be the same value-wise but they should be distinct index-wise.

 Input:
 The first line contains an integer N, denoting the number of elements in the array A.
 The next line contains N space separated integers, each denoting the ith integer of the array A.

 Output:
 Print the answer for each index in each line. If there is no second largest or third largest number in the array A upto that index, then print "-1", without the quotes.

 Constraints:
 1 <= N <= 100000
 0 <= A[i] <= 1000000
 *
 */
public class MonkAndMultiplication {
    public static void main(String[] args) throws IOException {
        // Using 1 based index here for ease of using as heap.
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        List<Integer> arr1 = Arrays.asList(reader.readLine().split(" ")).stream().map(st -> Integer.parseInt(st)).collect(Collectors.toList());
        int length = arr1.get(0) + 1;
        int arr[] = new int[length];
        int resultArr[] = new int[length];
        arr[0] = -1;
        arr1 = Arrays.asList(reader.readLine().split(" ")).stream().map(st -> Integer.parseInt(st)).collect(Collectors.toList());
        for (int i = 1; i < length; i++) {
            arr[i] = arr1.get(i-1);
        }

        for (int i = 1; i < length; i++) {
            if (i < 3) {
                writer.write(-1 + "\n");
            } else {
                int res[] = buildMaxHeap(arr, i + 1);
                int x = extractMaximum(res, res.length);
                int y = extractMaximum(res, res.length - 1);
                int z = extractMaximum(res, res.length - 2);
                int ans = x * y * z;
                writer.write(ans + "\n");
            }
        }
        writer.flush();
    }

    private static void maxHeapify (int arr[], int i, int N) {
        int left = 2 * i;
        int right = 2 * i + 1;
        int largest = 0;

        if (left < N && arr[left] > arr[i]) {
            largest = left;
        } else {
            largest = i;
        }

        if (right < N && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != i) {
            swap(arr, i, largest);
            maxHeapify(arr, largest, N);
        }
    }

    private static int[] buildMaxHeap(int arr[], int length) {
        int N = length;
        int resultantArray [] = new int[length];
        for (int i = 0; i < length; i++) {
            resultantArray[i] = arr[i];
        }
        for (int i = N / 2; i > 0; i--) {
            maxHeapify(resultantArray,i, N);
        }
        return resultantArray;
    }
    private static void swap(int arr[], int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static int getMaximum(int arr[]) {
        return arr[1];
    }

    private static int extractMaximum(int arr[], int length) {
        int result = arr[1];
        if (length > 1) {
            arr[1] = arr[length - 1];
            maxHeapify(arr, 1, length - 1);
            length--;
        } else {
            System.out.println("No elements in the queue.");
            return -1;
        }
        return result;
    }
}