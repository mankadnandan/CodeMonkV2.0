package codemonk.disjointDataStructure.examples;

import java.io.*;
import java.util.StringTokenizer;

/**
 *
 * SOLVED SUCCESSFULLY
 *
 * Created by Nandan Mankad on 03-Nov-17.
 *
 * ###################################################################################################################################################################
 * Approach: This problem can surely be solved with Disjoint Set / Union Find. The new part is only maintaining the strength array here. The approach to do that is
 * that we always keep the strength of root of any connected component updated with the maximum strength among those connected nodes. When we try to join to sub tree,
 * the root has the maximum strength, so we just compare the strength of the roots and keep the strength of the maximum as the strength of the root.
 *
 * Time Complexity: Union would take O(N log N), which inlcudes maintaining of the strength array. Also, we have Q queries, and each query would traverse through the
 * root, so each query would take O (log N). This makes the all the queries to run in O(Q log N) = O(N log N).
 *
 * So, total time complexity is O(N log N).
 *
 * ###################################################################################################################################################################
 *
 Owl Arena is hosting a fight competition and many owls decided to take part in it. Strength of an owl is the number associated with that owl.

 Rules of the competition are:
 An owl wins if its strength is greater than that of another.
 An owl can ask his friend to fight that match for him.
 Note : If A and B are friends, and B and C are friends, then A and C are also friends.

 Input:
 First line contains the number of owls participating N and the number of connections M. M lines follow.
 Each line contains two integers u and v denoting that they are friends.
 Next line contains Q, the number of queries. Q lines follow.
 Each line contains two integers u and v. You need to tell who wins.
 Output:
 In each query, output the number of the owl that will win the match. If the owls( u and v) are in the same friend circle, output TIE.

 Constraints:
 1 ≤ N, M, Q ≤ 10^5
 u, v ≤ N
 *
 */
public class OwlFight {
    public static void main(String[] args) throws Exception{
        FastReader reader = new FastReader();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = reader.nextInt();
        int M = reader.nextInt();

        int arr[] = new int[N];
        int size[] = new int[N];
        int strength[] = new int[N];

        initialise(arr, size, strength);

        for (int i = 0; i < M; i++) {
            int u = reader.nextInt() - 1;
            int v = reader.nextInt() - 1;
            union(arr, size, strength, u, v);
        }

        int Q = reader.nextInt();

        for (int i = 0; i < Q; i++) {
            int u = reader.nextInt() - 1;
            int v = reader.nextInt() - 1;

            int rootU = root(arr, u);
            int rootV = root(arr, v);

            if (rootU == rootV) {
                writer.write("TIE\n");
            } else {
                if (strength[rootU] > strength[rootV]) {
                    writer.write(u + 1 + "\n");
                } else {
                    writer.write(v + 1 + "\n");
                }
            }
        }
        writer.flush();
    }

    private static void initialise (int arr[], int size[], int strength[]) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }

        for (int i = 0; i < size.length; i++) {
            size[i] = 1;
        }

        for (int i = 0; i < strength.length; i++) {
            strength[i] = i;
        }
    }

    private static int root (int arr[], int a) {
        while (arr[a] != a) {
            arr[a] = arr[arr[a]];
            a = arr[a];
        }
        return a;
    }

    private static void union (int arr[], int size[], int strength[], int a, int b) {
        int rootA = root(arr, a);
        int rootB = root(arr, b);

        if (rootA == rootB) {
            return;
        }

        if (size[rootA] > size[rootB]) {
            arr[rootB] = rootA;
            size[rootA] += size[rootB];
            if (strength[rootB] > strength[rootA]) {
                strength[rootA] = strength[rootB];
            }

        } else {
            arr[rootA] = rootB;
            size[rootB] += size[rootA];
            if (strength[rootA] > strength[rootB]) {
                strength[rootB] = strength[rootA];
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
