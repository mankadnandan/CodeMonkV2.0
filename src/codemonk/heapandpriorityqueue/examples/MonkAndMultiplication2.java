package codemonk.heapandpriorityqueue.examples;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * SOLVED SUCCESSFULLY - With Efficiency.
 *
 * Created by Nandan Mankad on 04-Oct-17.
 *
 * *###################################################################################################################################################################
 * Approach: See in-efficient approach {@link MonkAndMultiplication}
 *
 * In this problem the approach is to consider the heap as a zero-element heap initially. Then iterate over each element in th array and add the element in the Heap.
 *
 * After adding each element, extract maximum element 3 times and get the product. Now again add these 3 elements removed into the heap through add method.
 *
 * Continue doing this for the next element in the array.
 *
 * Time complexity:
 *
 * add is O(log(N)) and time complexity of extractMaximum is also O(log(N)).
 *
 * Though we do this addition for each element in the array. So total time for adding all elements would be O(N log(N)).
 *
 * So total time complexity is O(N log(N)).
 *###################################################################################################################################################################
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
public class MonkAndMultiplication2 {

    static int currentLength = 0;
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
                add(resultArr, arr[i]);
                writer.write(-1 + "\n");
            } else {
                add(resultArr, arr[i]);
                int x = extractMax(resultArr);
                int y = extractMax(resultArr);
                int z = extractMax(resultArr);
                long ans = (long)x*(long) y* (long)z;
                writer.write(ans + "\n");
                add(resultArr, x);
                add(resultArr, y);
                add(resultArr, z);
            }
        }
        writer.flush();

        /*for (int i = 0; i < resultArr.length;i++) {
            System.out.print(resultArr[i] + " ");
        }
        System.out.println();

        for (int i = 1; i < length; i++) {
            System.out.print(extractMax(resultArr) + " ");
        }*/
    }

    public static void add(int resultArr[], int ele) {
        int currInsertLocation = currentLength + 1;
        resultArr[currInsertLocation] = ele;
        int parent = (currInsertLocation) / 2;
        while (parent > 0 && resultArr[parent] < resultArr[currInsertLocation]) {
            swap(resultArr, parent, currInsertLocation);
            currInsertLocation = parent;
            parent = (parent) / 2;
        }
        currentLength++;
    }

    public static void maxHeapify(int arr[], int i, int N) {
        int left = 2 * i;
        int right = 2 * i + 1;
        int largest = 0;

        if (left <= N && arr[left] > arr[i]) {
            largest = left;
        } else {
            largest = i;
        }

        if (right <= N && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != i) {
            swap(arr, largest, i);
            maxHeapify(arr, largest, N);
        }

    }

    public static int extractMax (int resultArr[]) {
        int result = resultArr[1];
        if (currentLength >= 1) {
            resultArr[1] = resultArr[currentLength];
            maxHeapify(resultArr, 1, currentLength-1);
            currentLength--;
        }
        return result;
    }

    public static void swap (int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }
}
