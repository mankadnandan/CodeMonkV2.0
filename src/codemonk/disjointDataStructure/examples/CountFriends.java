package codemonk.disjointDataStructure.examples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * SOLVED SUCCESSFULLY
 *
 * Created by Nandan Mankad on 02-Nov-17.
 *
 * ###################################################################################################################################################################
 * Approach: This is also a simple union-find case, where we form a graph of friends with the help of relationships. Now for each student i, the friends are everyone
 * else in the same connected component. Hence we just print the size of the root element - 1.
 *
 * Time Complexity: Time taken to find the root of an element is O(log N) with path compression. Hence each union would take O(log N). There would be M = N such union,
 * so the total time taken would be O(N log N).
 *
 * ###################################################################################################################################################################
 *
 * There are N students and M relationships of the form u v, which means that student u and student v are friends. If two students are not friends directly but they
 * have a mutual friend, then they too become friends. Your task is to count the number of friends of the ith student where 1 ≤ i ≤ N.

 Input:
 The first line consists of two integers  N and M denoting the number of students and the number of relationships respectively. The next M lines consists of two integers
 u and v denoting that student u and student v are friends. u and v can never be equal and relationships are not repeated.

 Output:
 Print N space separated integers which tells us the number of friends of the ith student.

 Constraints:

 1≤ N ≤ 10^5
 1 ≤ M ≤ 10^5
 1 ≤ u , v ≤ N
 *
 */
public class CountFriends {
    public static void main(String[] args) {
        FastReader reader = new FastReader();
        int N = reader.nextInt();
        int M = reader.nextInt();
        int arr[] = new int[N];
        int size[] = new int[N];
        initialize(arr, size);
        for (int i = 0; i < M; i++) {
            int u = reader.nextInt() - 1;
            int v = reader.nextInt() - 1;
            union(arr, size, u, v);
        }

        for (int  i = 0; i < size.length; i++) {
            int temp = i;
            while (arr[temp] != temp) {
                temp = arr[temp];
            }
            System.out.print(size[temp] - 1 + " ");
        }

    }

    private static void initialize(int arr[], int size[]) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        for (int i = 0; i < size.length; i++) {
            size[i] = 1;
        }
    }

    private static int root (int arr[], int a) {
        while (arr[a] != a) {
            arr[a] = arr[arr[a]]; // Path Compression
            a = arr[a];
        }
        return a;
    }

    private static boolean find (int arr[], int a, int b) {
        if (root(arr, a) == root(arr, b)) {
            return true;
        } else {
            return false;
        }
    }

    private static void union (int arr[], int size[], int a, int b) {
        int rootA = root(arr, a);
        int rootB = root(arr, b);

        if (rootA != rootB) {
            if (size[rootA] > size[rootB]) {
                arr[rootB] = rootA;
                size[rootA] += size[rootB];
            } else {
                arr[rootA] = rootB;
                size[rootB] += size[rootA];
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
