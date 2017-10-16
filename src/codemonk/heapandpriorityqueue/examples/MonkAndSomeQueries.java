package codemonk.heapandpriorityqueue.examples;

import java.util.Scanner;

/**
 * SOLVED SUCCESSFULLY
 *
 * Created by Nandan Mankad on 09-Oct-17.
 *
 * ###################################################################################################################################################################
 * Approach: The problem is very basic implementation of two Data Structures of MAX heap and MIN Heap. On each addition or
 * removal of elements, we maintain both the min heap and max heap structure.
 *
 * When ever we need to get the maximum and minimum elements we just get the first element from max and min heap
 * respectively.
 *
 * Time Complexity: All the methods of addInMaxHeap, addInMinHeap and removeElement are of the complexity O (log (N)).
 *
 * getMaximum and getMinimum are O(1)
 *
 * We use any one of the above method for each query.
 *
 * Total time complexity is O(Q log(N))
 *
 * ###################################################################################################################################################################
 *
 * Monk was asked to answer some queries in an interview. He is given an empty array A. Queries are of 4 types:-
 1. 1 X - Add number X to the array A.
 2. 2 X - Remove a single instance of number X from the array A. If not possible, print "-1" without the quotes.
 3. 3 - Find the maximum element in the array A.
 4. 4 - Find the minimum element in the array A.

 Input:
 The first line contains the integer Q.
 The next Q lines will each contain a query like the ones mentioned above.

 Output:
 For queries 3 and 4, print the answer in a new line. If the array is empty for query 3 and 4, then print "-1" without the quotes.

 Constraints:
 1 <= Q <= 100000
 1 <= X <= 100000
 *
 */
public class MonkAndSomeQueries {
    public static int currentMaxHeapSize = 0;
    public static int currentMinHeapSize = 0;
    public static int minHeaparr[] = new int[100002];
    public static int maxHeaparr[] = new int[100002];
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int noOfQueries = in.nextInt();

        for (int i = 0; i < noOfQueries; i++) {
            int queryType = in.nextInt();

            if (queryType == 1) {
                int ele = in.nextInt();
                addInMaxHeap(ele);
                addInMinHeap(ele);
            } else if (queryType == 2) {
                int ele = in.nextInt();
                removeElement(ele);
            } else if (queryType == 3) {
                System.out.println(getMaximum());
            } else if (queryType == 4) {
                System.out.println(getMinimum());
            }
        }

    }

    public static void addInMaxHeap (int ele) {
        int currMax = currentMaxHeapSize + 1;
        maxHeaparr[currMax] = ele;
        int parent = currMax / 2;
        while (parent > 0 && maxHeaparr[parent] < maxHeaparr[currMax]) {
            swap(maxHeaparr, parent, currMax);
            currMax = parent;
            parent = currMax / 2;
        }
        currentMaxHeapSize++;
    }

    public static void addInMinHeap (int ele) {
        int currMin = currentMinHeapSize + 1;
        minHeaparr[currMin] = ele;
        int parent = currMin / 2;
        while (parent > 0 && minHeaparr[parent] > minHeaparr[currMin]) {
            swap(minHeaparr, parent, currMin);
            currMin = parent;
            parent = currMin / 2;
        }
        currentMinHeapSize++;
    }

    public static void removeElement (int ele) {

        int elePresent = isElePresentInMax(ele);

        if (elePresent != -1) {
            maxHeaparr[elePresent] = maxHeaparr[currentMaxHeapSize];
            currentMaxHeapSize--;
            maxHeapify(elePresent);
        } else {
            System.out.println(-1);
        }

        elePresent = isElePresentInMin(ele);

        if (elePresent != -1) {
            minHeaparr[elePresent] = minHeaparr[currentMinHeapSize];
            currentMinHeapSize--;
            minHeapify(elePresent);
        }
    }

    public static int isElePresentInMax (int ele) {
        for (int i = 1; i <= currentMaxHeapSize; i++) {
            if (ele == maxHeaparr[i]) {
                return i;
            }
        }
        return -1;
    }

    public static int isElePresentInMin (int ele) {
        for (int i = 1; i <= currentMinHeapSize; i++) {
            if (ele == minHeaparr[i]) {
                return i;
            }
        }
        return -1;
    }

    public static void maxHeapify (int i) {
        int left = 2 * i;
        int right = 2 * i + 1;
        int largest = 0;

        if (left <= currentMaxHeapSize && maxHeaparr[left] > maxHeaparr[i]) {
            largest = left;
        } else {
            largest = i;
        }

        if (right <= currentMaxHeapSize && maxHeaparr[right] > maxHeaparr[largest]) {
            largest = right;
        }

        if (largest != i) {
            swap(maxHeaparr, largest, i);
            maxHeapify(largest);
        }
    }

    public static void minHeapify (int i) {
        int left = 2 * i;
        int right = 2 * i + 1;
        int smallest = 0;

        if (left <= currentMinHeapSize && minHeaparr[left] < minHeaparr[i]) {
            smallest = left;
        } else {
            smallest = i;
        }

        if (right <= currentMinHeapSize && minHeaparr[right] < minHeaparr[smallest]) {
            smallest = right;
        }

        if (smallest != i) {
            swap(minHeaparr, smallest, i);
            minHeapify(smallest);
        }
    }

    public static int getMaximum () {
        if (currentMaxHeapSize > 0) {
            return maxHeaparr[1];
        } else {
            return -1;
        }
    }

    public static int getMinimum () {
        if (currentMinHeapSize > 0) {
            return minHeaparr[1];
        } else {
            return -1;
        }
    }

    public static void swap (int arr[], int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
