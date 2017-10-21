package codemonk.heapandpriorityqueue.examples;

import java.io.*;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * SOLVED SUCCESSFULLY
 *
 * Created by Nandan Mankad on 21-Oct-17.
 *
 * ###################################################################################################################################################################
 * Approach: The problem involves important Heap / Priority Queue Concepts. The important implementations of buildMinHeap(), minHeapify(), addInMinHeap(), extractMinimum()
 * are enough to solve the problem. Though with couple of other catches:
 *
 * 1. Addition would lead to numbers larger than int can handle so typecasting to long is important.
 * 2. FAST I/O is a must required for this problem. See the implementation of {@link FastReader} from following link:
 * http://www.geeksforgeeks.org/fast-io-in-java-in-competitive-programming/
 *
 * The second point was the decider in getting this problem complete AC.
 *
 * Time Complexity: Initially we buildMinHeap so thats O(N). Then every query Q, we run extractMin and addInMinHeap K times i.e. Q * KLog(N)
 *
 * So O(N + Q * K Log(N))
 *
 * Total complexity of O(Q * K Log(N)) in total.
 *
 * ###################################################################################################################################################################
 *
 * Little Monk also met who he thinks is the new God of Indian cricket: Virat Kohli. Now Monk is extremely fond of Kohli -- not just as a T20 player, but a player in all formats. He loves the statistics involved in Kohli's career.

 Little Monk knows that Kohli has played N matches in all three formats of his career, ODI, T20 and Test Cricket. He wants to show-off his knowledge about Kohli's career so he decides to answer Q questions asked by Kohli. Kohli gives the Monk three different arrays with N numbers each denoting the runs in the ith ODI match, ith Test Match and ith T-20 match respectively.

 The value of Kohli's ith match would be the sum of his score in the ith T-20 match, ith Test match and ith ODI match. Kohli knows that Monk is extremely quick at finding the k th smallest value of all his innings, so he twists his query a bit. He asks the Monk to delete the kth smallest value once that is answered by the Monk. If Kohli comes up with a number k which is greater than the number of matches remaining in Kohli's career, the Monk should say that the answer is
 −1.

 So much complication confuses the Little Monk and he asks for your help!

 Input format:
 The first line contains an integer N, which denotes the number of matches played by Virat Kohli. The next three lines contain N integers each denoting the number of runs scored in ODI, T20 and Test respectively. The next line contains an integer Q, denoting the number of questions Virat is going to ask. The next Q lines contain an integer K, denoting the Kth smallest value which the Monk has to answer.

 Output format:
 Print answer to each query in a new line. In case of an invalid query, print −1.

 Constraints:
 1 ≤ N ≤ 10^6
 1 ≤ Ni ≤ 10^9
 1 ≤ K ≤ 10^3
 1 ≤ Qi ≤ 10^4
 Note: The test data contains a lot of I/O operations, it is thus recommended to use faster input / output mechanisms.
 *
 */
public class LittleMonkAndVirat {

    private static int currentMinHeapSize = 0;

    public static void main(String[] args) throws IOException {
        FastReader s=new FastReader();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = s.nextInt();
        int odiScoreArr[] = new int[N];
        int t20ScoreArr[] = new int[N];
        int testScoreArr[] = new int[N];
        for (int i = 0; i < N; i++) {
            odiScoreArr[i] = s.nextInt();
        }
        for (int i = 0; i < N; i++) {
            t20ScoreArr[i] = s.nextInt();
        }
        for (int i = 0; i < N; i++) {
            testScoreArr[i] = s.nextInt();
        }

        long sumofScoreArr[] = new long[N+1];

        for (int i = 1; i < N+1; i++) {
            sumofScoreArr[i] = (long) odiScoreArr[i-1] + t20ScoreArr[i-1] + testScoreArr[i-1];
        }

        int Q = s.nextInt();

        currentMinHeapSize = N;
        buildMinHeap(sumofScoreArr);

        for (int i = 0; i < Q; i++) {
            int K =  s.nextInt();
            if (K <= currentMinHeapSize) {
                long tempArr[] = new long[K];
                for (int j = 0; j < K; j++) {
                    tempArr[j] = extractMin(sumofScoreArr);
                }
                writer.write(tempArr[K-1] + "\n");
                for (int j = 0; j < K-1; j++) {
                    addInMinHeap(sumofScoreArr, tempArr[j]);
                }
            } else {
                writer.write(-1 + "\n");
            }
        }
        writer.flush();
    }

    private static void buildMinHeap (long arr[]) {
        for (int i = currentMinHeapSize / 2; i >= 1; i--) {
            minHeapify(arr, i);
        }
    }

    private static void minHeapify (long arr[], int i) {

        int left = 2 * i;
        int right = 2 * i + 1;
        int smallest = 0;

        if (left <= currentMinHeapSize && arr[left] < arr[i]) {
            smallest = left;
        } else {
            smallest = i;
        }

        if (right <= currentMinHeapSize && arr[right] < arr[smallest]) {
            smallest = right;
        }

        if (smallest != i) {
            swap(arr, smallest, i);
            minHeapify(arr, smallest);
        }
    }

    private static void addInMinHeap (long arr[], long ele) {
        int posToAdd = currentMinHeapSize + 1;
        arr[posToAdd] = ele;
        int parent = posToAdd / 2;
        while (parent > 0 && arr[posToAdd] < arr[parent]) {
            swap(arr, posToAdd, parent);
            posToAdd = parent;
            parent = parent / 2;
        }
        currentMinHeapSize++;
    }

    private static long extractMin(long arr[]) {
        long result = arr[1];
        arr[1] = arr[currentMinHeapSize];
        currentMinHeapSize--;
        minHeapify(arr, 1);
        return result;
    }


    private static void swap (long arr[], int i, int j) {
        long temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static long[] convertToLongArray (List<Integer> integers) {
        long[] ret = new long[integers.size()];
        for (int i=0; i < ret.length; i++)
        {
            ret[i] = integers.get(i).intValue();
        }
        return ret;
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
                catch (IOException  e)
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
