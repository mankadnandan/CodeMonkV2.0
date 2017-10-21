package codemonk.heapandpriorityqueue.examples;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * SOLVED SUCCESSFULLY.
 *
 * Created by Nandan Mankad on 17-Oct-17.
 *
 * ###################################################################################################################################################################
 * Approach: Here we need to find the distance of Kth nearest hostel from the Deans office. The important thing to realize is that once we find K nearest hostels,
 * we would just need to make sure that only if any new Hostel are added which is "nearer" than the Kth nearest hostel then only we might need to shuffle the positions.
 * Else we can discard the hotel which is farther because we are anyways not going to need it.
 *
 * Thus here we can make a MAX heap of size K at max, and add elements to it as required. If the size is K and a new hostel is being added, we check if the new hostel is
 * nearer than the Kth Hostel. If yes, we kick out the Kth hostel from the Heap and add the new Hostel and maintain heap properties. Else we just discard the new hostel.
 *
 * For result, we just get the max of the heap as thats the Kth nearest hostel,
 *
 * Time Complexity: Adding Element and extractMaximum takes Log(N) time. Here N is at max K, so log(K). And there can be Q queries.
 * So total time complexity is O(Q Log(K))
 * ###################################################################################################################################################################
 * Dean of NIT Silchar is going to visit Hostels of NIT Silchar. As you know that he is a very busy person so he decided to visit only first "K" nearest Hostels. Hostels are situated in 2D plane. You are given the coordinates of hostels and you have to answer the Rocket distance of Kth nearest hostel from origin ( Dean's place ) .

 Input:
 First line of input contains Q Total no. of queries and K
 There are two types of queries:

 first type : 1 x y
 For query of 1st type, you came to know about the co-ordinates ( x , y ) of newly constructed hostel.
 second type : 2
 For query of 2nd type, you have to output the Rocket distance of Kth nearest hostel till now.

 The Dean will always stay at his place ( origin ).
 It is gauranted that there will be atleast k queries of type 1 before first query of type 2.

 Rocket distance between two points ( x2 , y2 ) and ( x1 , y1 ) is defined as (x2 - x1)2 + (y2 - y1)2
 Output:
 For each query of type 2 output the Rocket distance of Kth nearest hostel from Origin.

 Constraints
 1 < = k < = Q < = 10^5
 -10^6 < = x , y < = 10^6
 *
 */
public class HostelVisit {

    public static int currentHeapSize = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        List<Integer> arr1 = Arrays.asList(reader.readLine().split(" ")).stream().map(st -> Integer.parseInt(st)).collect(Collectors.toList());
        int Q = arr1.get(0);
        int K = arr1.get(1);
        long arr[] = new long[K+1];

        for (int i = 0; i < Q; i++) {
            arr1 = Arrays.asList(reader.readLine().split(" ")).stream().map(st -> Integer.parseInt(st)).collect(Collectors.toList());
            int qType = arr1.get(0);
            if (qType == 1) {
                long x = arr1.get(1);
                long y = arr1.get(2);
                long rocketDistance = (x * x) + (y * y);
                if (currentHeapSize == K) {
                    if (rocketDistance < arr[1]) {
                        extractMaximum(arr);
                        add(arr, rocketDistance);
                    }
                } else {
                    add(arr, rocketDistance);
                }
            } else {
                writer.write(arr[1] + "\n");
            }
        }
        writer.flush();

    }

    public static void maxHeapify (long arr[], int i) {
        int left = 2 * i;
        int right = 2 * i + 1;
        int largest = 0;

        if (left <= currentHeapSize && arr[left] > arr[i]) {
            largest = left;
        } else {
            largest = i;
        }

        if (right <= currentHeapSize && arr[right] > arr[largest]) {
            largest = right;
        }

        if (largest != i) {
            swap(arr, i, largest);
            maxHeapify(arr, largest);
        }
    }

    public static void swap (long arr[] , int i, int j) {
        long temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static long extractMaximum (long arr[]) {
        long result = arr[1];
        arr[1] = arr[currentHeapSize];
        currentHeapSize--;
        maxHeapify(arr, 1);
        return result;
    }

    public static void add (long arr[], long ele) {
        int posToAdd = currentHeapSize + 1;
        arr[posToAdd] = ele;
        int parent = posToAdd / 2;
        while (parent > 0 && arr[posToAdd] > arr[parent]) {
            swap(arr, posToAdd, parent);
            posToAdd = parent;
            parent = parent / 2;
        }
        currentHeapSize++;
    }
}