package codemonk.heapandpriorityqueue.examples;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * SOLVED SUCCESSFULLY
 *
 * Created by Nandan Mankad on 22-Oct-17.
 *
 * * ###################################################################################################################################################################
 * Approach: Here the ball should be bowled by the bowler which has maximum number of balls left to be bowled. So we can use Heap to order the Bowlers based on their
 * balls left. In case the balls left are same then we order the bolwer based on bowler number through Priority queue. Once a ball is bowled, we decrement the ball left
 * count by one and run max heapify on it again to make sure our heap is all set for the order of next bowler.
 *
 * Time Complexity: Build Max Heap is O(N) and Every K deliveries would require O(Log(N)) work for max Heapifying after count decrement. So total complexity of
 * O(N + K Log(N))
 *
 * Hence Effective complexity of O(K Log(N)) in total.
 *
 * ###################################################################################################################################################################
 *
 * Little Monk knows what a dangerous batsman the captain of Australia, Steven Smith is. The bigger problem is that Monk and his team have no idea about the weakness of Smith. But their coach tells them Smith's trick of scoring runs. His trick is pretty simple: he is going to score maximum runs off bowlers who have minimum balls left to be bowled.

 For instance, if there are three bowlers -- with 10, 11, 6 balls left respectively in their quota, then Smith would be able to score maximum runs off bowler number 3,
 then bowler number 1 and then bowler number 2. Let's say bowler 2 bowls the first ball so he'll also have 10 balls remaining, similar to bowler number 1. Now bowler 2 and
 bowler 1 are on the same number of balls remaining, any of them can bowl the next delivery. But, Monk as the captain in such a case prefers the bowler with in order
 of occurrence. So, the next ball will be bowled by bowler 1.

 So as the captain of the team, Monk needs to know the order of bowlers so that Smith scores minimum runs possible off the K balls he is going to face!

 Note: None of the bowlers can get Steven Smith out. Also, they are not allowed to bowl once their number of deliveries left is 0.

 Important: Since the input might be large, participants are expected to use fast I/O techniques.

 Input format:
 The first line contains two integers N and K, which denote the number of bowlers faced by Smith and the total number of balls Smith is going to play.
 The next line contains N space separated integers denoting the quota of each bowler.

 Output format:
 Print K space separated integers denoting the order of the bowlers chosen by Monk. The bowlers are 1-indexed.

 Constraints:
 1 ≤ N ≤ 10^6
 1 ≤ K ≤ minimum (Σ Ni , 10^6)
 1 ≤ Ni ≤ 10^9
 *
 */
public class LittleMonkAndSteveSmith {
    public static void main(String[] args) throws IOException{
        FastReader reader = new FastReader();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = reader.nextInt();
        int K = reader.nextInt();
        PriorityQueue<Bowler> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            Bowler b = new Bowler(i+1, reader.nextInt());
            pq.add(b);
        }
        pq.buildMaxHeap();
        for (int i = 0; i < K; i++) {
            int ans = pq.ballDelivered();
            writer.write(ans + " ");
        }
        writer.flush();
    }

    static class PriorityQueue<T extends Comparable<T>> {
        List<T> arr;
        int pqSize;
        PriorityQueue() {
            arr = new ArrayList<>();
            pqSize = 0;
        }

        public void add (T t) {
            if (arr.size() == 0) {
                arr.add(t);
            }
            arr.add(t);
            pqSize++;
        }

        private int ballDelivered() {
            int result = ((Bowler) arr.get(1)).bowlerNum;
            ((Bowler) arr.get(1)).ballsLeft--;
            maxHeapify(1);
            return result;
        }

        private void buildMaxHeap() {
            for (int i = pqSize / 2; i >= 1; i--) {
                maxHeapify(i);
            }
        }

        private void maxHeapify(int i) {
            int left = 2 * i;
            int right = 2 * i + 1;
            int largest = 0;

            if (left <= pqSize && arr.get(left).compareTo(arr.get(i)) == 1) {
                largest = left;
            } else {
                largest = i;
            }

            if (right <= pqSize && arr.get(right).compareTo(arr.get(largest)) == 1) {
                largest = right;
            }

            if (largest != i) {
                swap(largest, i);
                maxHeapify(largest);
            }
        }

        private void swap(int i, int j) {
            T temp = arr.get(i);
            arr.set(i, arr.get(j));
            arr.set(j, temp);
        }

    }

    static class Bowler implements Comparable<Bowler> {
        int bowlerNum;
        int ballsLeft;

        Bowler (int bowlerNum, int ballsLeft) {
            this.bowlerNum = bowlerNum;
            this.ballsLeft = ballsLeft;
        }

        @Override
        public int compareTo(Bowler o) {
            if (this.ballsLeft > o.ballsLeft) {
                return 1;
            } else if (this.ballsLeft < o.ballsLeft) {
                return -1;
            } else {
                if (this.bowlerNum < o.bowlerNum) {
                    return 1;
                } else {
                    return -1;
                }
            }
        }
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
