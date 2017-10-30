package codemonk.disjointDataStructure.examples;

import java.io.*;
import java.util.*;

/**
 *
 * SOLVED SUCCESSFULLY
 *
 * Created by Nandan Mankad on 24-Oct-17.
 *
 * ###################################################################################################################################################################
 * Approach: This is a Sample Problem from the learning of the Disjoint Set / Union Find. This has a typical implementation of
 * union(), find(), root() with path compression. Here the quantity of the output of the answer is huge and hence the
 * Fast I/O is mandatory.
 * ###################################################################################################################################################################
 * You are given number of nodes, N, and number of edges, M, of a undirected connected graph. After adding each edge, print the size of all the connected components
 * (in increasing order).
 Input:
 First line contains two integers, N and M, number of nodes and number of edges. Next M lines contains two integers each, X and Y, denoting that there is an edge between X and
 Y.

 Output:
 For each edge, print the size of all the connected components (in increasing order) after adding that edge.

 Constraints:
 1 ≤ N ≤ 10^3
 1 ≤ M ≤ N − 1
 1 ≤ X , Y ≤ N
 *
 *
 */
public class DisjointSetUnionExample {
    public static void main(String[] args) throws IOException {
        FastReader in = new FastReader();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = in.nextInt();
        int M = in.nextInt();
        int arr[] = new int[N+1];
        int size[] = new int[N+1];
        initialize(arr, size);
        List<Integer> tempArrList;
        for (int i = 0; i < M; i++) {
            int from = in.nextInt();
            int to = in.nextInt();
            union(arr, size, from, to);
            tempArrList = new ArrayList<>();
            for (int j = 1; j < N + 1; j++) {
                if (arr[j] == j) {
                    tempArrList.add(size[j]);
                }
            }
            Collections.sort(tempArrList);
            for (Integer ele : tempArrList) {
                writer.write(ele + " ");
            }
            writer.write("\n");
        }
        writer.flush();
    }

    private static void initialize (int arr[], int size[]) {
        for (int i = 1; i < arr.length; i++) {
            arr[i] = i;
            size[i] = 1;
        }
    }

    private static int root(int arr[], int a) {
        while (arr[a] != a) {
            arr[a] = arr[arr[a]];
            a = arr[a];
        }
        return arr[a];
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

        if (size[rootA] < size[rootB]) {
            arr[rootA] = rootB;
            size[rootB] += size[rootA];
        } else {
            arr[rootB] = rootA;
            size[rootA] += size[rootB];
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