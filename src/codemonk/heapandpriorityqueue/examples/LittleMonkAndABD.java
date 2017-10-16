package codemonk.heapandpriorityqueue.examples;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Nandan Mankad on 15-Oct-17.
 *
 * ###################################################################################################################################################################
 * Approach: Here we just need to sort the array to get the Kth Smallest or Largest innings. We can use any sorting method, though since
 * we are learning Heaps, we would be using Heap Sort.
 *
 * The interesting thing here is the inputs and the outputs. Since we have lot of things to be read, SCANNER is too slow for it and
 * due to SCANNER the solution gets timed out. We use Buffered reader and writer to solve this.
 *
 * Time Complexity: Time complexity is O(N log(N)) for HeapSort.
 *
 * ###################################################################################################################################################################
 *
 *
 * Little Monk meets his another favorite cricketer this time: A-B-D. Little Monk says that he is the biggest fan of ABD. ABD does not believe the Monk at all, and asks him to prove how much does he know about ABD's career.

 So, ABD tells the Monk that given his latest N innings, he is going to ask him Q number of questions about his career which would involve questions of two types:

 Find the kth smallest score of his career - denoted by a query of type: "k S", where k is an integer and S denotes smallest.
 Find the kth largest score of his career - denoted by a query of type: "k L", where k is an integer and L denotes largest.
 Help Little Monk answer as many queries as possible!

 Input format:
 The first line contains an integer N, which denotes the number of innings played by ABD which have to be dealt by The Monk. The next line contains N space separated integers denoting the number of scores made by ABD. The next line contains an integer Q denoting the number of questions ABD is going to be asking. After that, the next Q lines will contain a query like the ones mentioned above.

 Output format:
 Print the required answer for each query on a newline.

 Constraints:
 1
 1 ≤ N ≤ 10^6
 1 ≤ Q ≤ 10^6
 1 ≤ K ≤ 10^5
 1 ≤ Ni ≤ 10^9
 *
 */
public class LittleMonkAndABD {
    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        List<Integer> arr1 = Arrays.asList(reader.readLine().split(" ")).stream().map(st -> Integer.parseInt(st)).collect(Collectors.toList());
        int N = arr1.get(0);
        int arr[] = new int[N+1];
        arr1 = Arrays.asList(reader.readLine().split(" ")).stream().map(st -> Integer.parseInt(st)).collect(Collectors.toList());
        for (int i = 0; i < arr1.size(); i++) {
            arr[i+1] = arr1.get(i);
        }
        arr1 = Arrays.asList(reader.readLine().split(" ")).stream().map(st -> Integer.parseInt(st)).collect(Collectors.toList());
        int Q = arr1.get(0);
        buildMaxHeap(arr, N);
        heapSort(arr, N);

        for (int i = 0; i < Q; i++) {
            List<Object> arr2 = Arrays.asList(reader.readLine().split(" "));
            int ele = Integer.parseInt(arr2.get(0).toString());
            char ch = arr2.get(1).toString().toCharArray()[0];
            if (ch == 'S') {
                writer.write(arr[ele] + "\n");
            } else {
                writer.write(arr[N - ele + 1] + "\n");
            }
        }
        writer.flush();
    }

    public static void heapSort (int arr[], int N) {
        for (int i = 1; i <= N; i++) {
            swap(arr, 1, N - i + 1);
            maxHeapify(arr, 1, N - i);
        }
    }

    public static void buildMaxHeap (int arr[], int N) {
        for (int i = N / 2; i >= 1; i--) {
            maxHeapify(arr, i, N);
        }
    }

    public static void maxHeapify(int arr[], int i, int N) {
        int left = i * 2;
        int right = i * 2 + 1;
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
            swap(arr, i, largest);
            maxHeapify(arr, largest, N);
        }
    }

    public static void swap (int arr[], int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}