package codemonk.heapandpriorityqueue.examples;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * SOLVED SUCCESSFULLY
 *
 * Created by Nandan Mankad on 06-Oct-17.
 *
 * ###################################################################################################################################################################
 * Approach: Here we know the  seating capacity of each row upfront. To maximize the total money the club will get, at any point, the ticket for the line with most
 * number of empty seats needs to be sold. We use Heap here to make sure the row with maximum seat available is at the root. Then we sell that ticket first, reduce the
 * capacity by one and run maxHeapify on the root again to make sure the reduction in the seat capacity is accounted and the new root becomes the one with maximum
 * seating capacity available.
 *
 * Time Complexity: Build Max Heap takes O(N). Then for each seat sold we run maxHeapify which runs in log(N),
 *
 * Time complexity of O(N log(N)) in total.
 *
 * ###################################################################################################################################################################
 *
 * Monk's favourite game is Football and his favourite club is "Manchester United". Manchester United has qualified for the Champions League Final which is
 * to be held at the Wembley Stadium in London. So, he decided to go there and watch his favourite team play. After reaching the stadium, he saw that many people
 * have lined up for the match tickets. He knows that there are M rows in the stadium with different seating capacities. They may or may not be equal.
 * The price of the ticket depends on the row. If the row has K(always greater than 0) vacant seats, then the price of the ticket will be K
 * pounds(units of British Currency). Now, every football fan standing in the line will get a ticket one by one.
 * Given the seating capacities of different rows, find the maximum possible pounds that the club will gain with the help of the ticket sales.

 Input:
 The first line consists of M and N. M denotes the number of seating rows in the stadium and N denotes the number of football fans waiting in
 the line to get a ticket for the match.
 Next line consists of M space separated integers X[1],X[2],X[3].... X[M] where X[i] denotes the number of empty seats initially in the ith row.

 Output:
 Print in a single line the maximum pounds the club will gain.

 Constraints:
 1 <= M <= 1000000
 1 <= N <= 1000000
 1 <= X[i] <= 1000000
 Sum of X[i] for all 1 <= i <= M will always be greater than N.
 *
 */
public class MonkAndChampionsLeague {
    public static void main(String[] args) throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> arr1 = Arrays.asList(reader.readLine().split(" ")).stream().map(st -> Integer.parseInt(st)).collect(Collectors.toList());
        int M = arr1.get(0);
        int N = arr1.get(1);

        arr1 = Arrays.asList(reader.readLine().split(" ")).stream().map(st -> Integer.parseInt(st)).collect(Collectors.toList());
        int arr[] = new int[arr1.size() + 1];

        for (int i = 1; i < arr.length; i++) {
            arr[i] = arr1.get(i - 1);
        }

        buildMaxHeap(arr, arr.length - 1);
        int total = 0;
        for (int i = 0; i < N; i++) {
            total = total + arr[1];
            arr[1]--;
            maxHeapify(arr,1,arr.length-1);
        }
        System.out.println(total);
    }

    private static void maxHeapify (int arr[], int i, int N) {
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

    private static void buildMaxHeap(int arr[], int N) {
        for (int i = N / 2; i >= 1; i --) {
            maxHeapify(arr, i, N);
        }
    }

    private static void swap (int arr[], int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}