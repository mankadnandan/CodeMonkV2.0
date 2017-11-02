package codemonk.disjointDataStructure.examples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by Nandan Mankad on 28-Oct-17.
 *
 *
 * Monk is having a hard time teaching the 2nd standard students. He wants to divide the students into small groups so that he can conduct some fun-filled activities for them.
 * But students also want their friends in the same group. So, if student A is a friend of student B, and student B is a friend of student C, then the students A, B and
 * C must be in the same group, otherwise they will start crying. After dividing the students, he will choose a leader from each group who will lead their respective groups.
 * Now he wants to know the number of ways he can choose the group leaders from all the groups. Print this answer modulo 10^9 + 7.

 Note: Two ways A and B will be considered different if at least 1 person is a leader in group A, and is not a leader in group B, or vice-versa.

 Input:
 The first line consists of two integers N and M denoting the number of students and the number of relationships respectively. The next M lines consists of two integers
 u and v denoting that student u and student v are friends. u and v can never be equal and relationships are not repeated.

 Output:
 Print the answer modulo 10^9 + 7 in a single line.

 Constraints:
 1 ≤ N ≤ 10^5
 1 ≤ M ≤ 10^5
 1 ≤ u , v ≤ N
 *
 */
public class TeachersDilemma {

    public static void main(String[] args) {
        FastReader reader = new FastReader();
        int N = reader.nextInt();
        int M = reader.nextInt();

        int arr[] = new int[N];
        int size[] = new int[N];
        long ans = 1;

        initialize(arr, size);

        for (int i = 0; i < M; i++) {
            int u = reader.nextInt() - 1;
            int v = reader.nextInt() - 1;
            union(arr, size, u, v);
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == i) {
                ans = (ans % 1000000007 *  size[i] % 1000000007) % 1000000007;
            }
        }

        System.out.println(ans);

    }

    private static void initialize (int arr[], int size[]) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
            size[i] = 1;
        }
    }

    private static int root (int arr[], int i) {
        while (arr[i] != i) {
            arr[i] = arr[arr[i]]; // Path Compression
            i = arr[i];
        }
        return i;
    }

    private static boolean find (int arr[], int a, int b) {
        int rootA = root(arr, a);
        int rootB = root(arr, b);

        if (rootA == rootB) {
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
