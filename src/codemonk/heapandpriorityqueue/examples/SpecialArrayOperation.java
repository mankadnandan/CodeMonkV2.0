package codemonk.heapandpriorityqueue.examples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 *
 * SOLVED SUCCESSFULLY
 *
 * Created by Nandan Mankad on 9/6/2018.
 *
 * #########################################################################################################################
 * Approach: Here we can maintain two Heaps. One Max Heap for getting Max element each time and another Min-Heap for
 * getting Min element each time. Now we can remove Max element from Max Heap in O(log(N)), though we would also need
 * to remove this Max element from Min-heap. That would be too slow as we have to go through each element to remove the
 * appropriate one. Hence using Heap would be a problem.
 *
 * So here we need a data structure which can insert and remove elements in O(log(N)) time. We can use ordered
 * implementation of MultiSet (TreeMultiSet). Here we create a MultiSet class behaviour by backing data structure as
 * Tree Map.
 *
 * See the implementation for exact details of the way the problem is solved.
 *
 * Time Complexity: O(N log(N))
 *
 * #########################################################################################################################
 *
 You are given an array A of size N. You can perform an operation in which you will remove the largest and the smallest
 element from the array and add  their difference back into the array. So, the size of the array will decrease by 1
 after each operation. You are given Q tasks and in each task, you are given an integer K. For each task, you have to
 tell sum of all the elements in the array after K operations.

 Input:

 First line contains two space-separated integers N and Q, denoting the number of elements in array and number of
 queries respectively.

 Next line contains N space-separated integers denoting elements of the array.

 Next Q lines contain a single integer K.

 Output:

 For each task, print answer in a new line.

 Constraints:

 2 <= N <= 105

 1 <= Q <= 105

 0 <= A[i] <= 109

 0 <= K < N
 */
public class SpecialArrayOperation {
    static class Priority2Queue <E>
    {
        private MultiSet<E> q1;
        private MultiSet<E> q2;

        public Priority2Queue (Comparator<E> c1, Comparator<E> c2) {
            q1 = new MultiSet<E>(c1);
            q2 = new MultiSet<E>(c2);
        }

        public void add (E e) {
            q1.add(e);
            q2.add(e);
        }

        private E pollx (MultiSet<E> a, MultiSet<E> b) {
            E top = a.first().getKey();
            a.remove(top);
            b.remove(top);
            return top;
        }

        public E poll1 () { return pollx(q1, q2); }
        public E poll2 () { return pollx(q2, q1); }

        public MultiSet<E> getQ1() {
            return q1;
        }
    }

    public static void main(String[] args) {
        FastReader reader = new FastReader();
        int N = reader.nextInt();
        int Q = reader.nextInt();

        int arr[] = new int[N];

        long S = 0;
        long ansArr[] = new long[N];

        Priority2Queue<Integer> priority2Queue = new Priority2Queue<>(new AscendingComparator(), new DescendingComparator());

        for (int i = 0; i < N; i++) {
            arr[i] = reader.nextInt();
            S += arr[i];
            priority2Queue.add(arr[i]);
        }

        ansArr[0] = S;

        for (int i = 1; i < N; i++) {
            int min = priority2Queue.poll1();
            int max = priority2Queue.poll2();
            int diff = Math.abs(max - min);
            priority2Queue.add(diff);
            ansArr[i] = S - max - min + diff;
            S = ansArr[i];
        }

        for (int i = 0; i < Q; i++) {
            int K = reader.nextInt();
            System.out.println(ansArr[K]);
        }
    }

    static class MultiSet<T> {

        Comparator<T> comparator;

        NavigableMap<T, Integer> container;

        public MultiSet(Comparator comparator) {
            this.comparator = comparator;
            this.container = new TreeMap<>(comparator);
        }

        public void add(T t) {
            Integer count = container.get(t);
            if (count != null) {
                container.put(t, ++count);
            } else {
                container.put(t, 1);
            }
        }

        public void remove(T t) {
            Integer count = container.get(t);
            if (count != null && count > 1) {
                container.put(t, --count);
            } else {
                container.remove(t);
            }
        }

        public Map.Entry<T, Integer> first() {
            return container.firstEntry();
        }

        public Set<Map.Entry<T, Integer>> getEntrySet() {
            return container.entrySet();
        }

    }

    static class AscendingComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            if(o1 > o2)
                return 1;
            if(o1 < o2)
                return -1;
            return 0;
        }
    }

    static class DescendingComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            if(o1 > o2)
                return -1;
            if(o1 < o2)
                return 1;
            return 0;
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
