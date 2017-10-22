package codemonk.heapandpriorityqueue.examples;

import java.io.*;
import java.util.StringTokenizer;

/**
 *
 * SOLVED SUCCESSFULLY
 *
 * Created by Nandan Mankad on 22-Oct-17.
 *
 * ###################################################################################################################################################################
 * Approach: Here Monk should pick up the bag with largest number of candies first. So we use Max Heap for it. Also, once he eats and drop the bag, the candies become
 * (current Num of candies) / 2. So we modify that and run maxHeapify on the root.
 *
 * Time Complexity: buildMaxHeap is O(N) and applyFunction is O(Log N). We applyFunction K times, so total complexity is O(N + K Log(N))
 *
 * Hence Effective complexity of O(K Log(N)) in total.
 *
 * ###################################################################################################################################################################

 *
 * Our Monk loves candy!
 While taking a stroll in the park, he stumbled upon N Bags with candies. The i'th of these bags contains Ai candies.
 He picks up a bag, eats all the candies in it and drops it on the ground. But as soon as he drops the bag, the number of candies in the bag increases magically! Say the bag that used to contain X candies (before eating), now contains [X/2] candies! ,where [x] is the greatest integer less than x (Greatest Integer Function).
 Amazed by the magical spell, Monk can now have a lot more candies! But he has to return home in K minutes. In a single minute,Monk can consume all the candies in a single bag, regardless of the number of candies in it.
 Find the maximum number of candies that Monk can consume.

 Input:
 First line contains an integer T. T test cases follow.
 First line of each test case contains two space-separated integers N and K.
 Second line of each test case contains N space-separated integers,the number of candies in the bags.

 Output:
 Print the answer to each test case in a new line.

 Constraints:
 1 ≤ T ≤ 10
 1 ≤ N ≤ 10^5
 0 ≤ K ≤ 10^5
 0 ≤ Ai ≤ 10^10
 *
 */
public class MonkAndTheMagicalCandyBags {

    private static int currentHeapSize = 0;

    public static void main(String[] args) throws IOException {
        FastReader reader = new FastReader();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = reader.nextInt();

        for (int i = 0; i < T; i++) {
            int N = reader.nextInt();
            int K = reader.nextInt();
            long arr[] = new long[N+1];
            for (int j = 1; j < N+1; j++) {
                arr[j] = reader.nextLong();
            }
            currentHeapSize = N;
            buildMaxHeap(arr);
            long answer = 0;
            for (int j = 0; j < K; j++) {
                answer = answer + applyFunction(arr);
            }
            writer.write(answer + "\n");
        }
        writer.flush();
    }

    private static long applyFunction(long arr[]) {
        long result = arr[1];
        arr[1] = (long) Math.floor(arr[1] / 2);
        maxHeapify(arr, 1);
        return result;
    }

    private static void buildMaxHeap(long arr[]) {
        for (int i = currentHeapSize / 2; i >= 1; i--) {
            maxHeapify(arr, i);
        }
    }

    private static void maxHeapify(long arr[], int i) {
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
            swap(arr, largest, i);
            maxHeapify(arr, largest);
        }
    }

    private static void swap(long arr[], int i, int j) {
        long temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static class FastReader
    {
        BufferedReader br;
        StringTokenizer st;

        public FastReader()
        {
            br = new BufferedReader(new
                    InputStreamReader(System.in));
        }

        String next()
        {
            while (st == null || !st.hasMoreElements())
            {
                try
                {
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt()
        {
            return Integer.parseInt(next());
        }

        long nextLong()
        {
            return Long.parseLong(next());
        }

        double nextDouble()
        {
            return Double.parseDouble(next());
        }

        String nextLine()
        {
            String str = "";
            try
            {
                str = br.readLine();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return str;
        }
    }

}
