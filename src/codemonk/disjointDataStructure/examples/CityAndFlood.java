package codemonk.disjointDataStructure.examples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SOLVED SUCCESSFULLY
 *
 * Created by Nandan Mankad on 05-Nov-17.
 *
 * ###################################################################################################################################################################
 * Approach: Here to calculate the distinct empires, we just need to calculate how many distinct connected component are present. This can be easily done through basic
 * Disjoint Set / Union Find approach.
 *
 * Time Complexity: Here the main function is Union which takes O(log N) time to unite two cities. We need to do this K = N times.
 * So total time complexity would be O(N log N).
 *
 * ###################################################################################################################################################################
 *
 Fatland is a town that started with N distinct empires, namely empires 1, 2, ..., N. But over time, the armies of some of these empires have taken over other ones. Each takeover occurred when the army of empire i invaded empire j. After each invasion, all of empire j became part of empire i, and empire j was renamed as empire i.
 Empire Huang, leader of Badland, wants to invade Fatland. To do this, he needs to calculate how many distinct empires still remain in Fatland after all the takeovers. Help him with this task.

 Input:

 The first line contains an integer N, the number of empires that were originally in Fatland.
 The second line contains an integer K, denoting the number of takeovers that took place.
 Each of the next K lines contains 2 space-separated integers i, j, representing that the army of empire i took over that of empire j.
 As a result, empire j does not exist anymore and is now renamed as empire i. It is guaranteed that empire i still exists.

 Output: Output one integer, the number of empires that exist in Fatland.
 Constraints:
 1 <= N <= 10^5
 1 <= K <= 10^5
 *
 */
public class CityAndFlood {
    public static void main(String[] args) {
        FastReader reader = new FastReader();
        int N = reader.nextInt();
        int K = reader.nextInt();

        int arr[] = new int[N];
        int size[] = new int[N];
        int ans = 0;

        initialise(arr, size);

        for (int k = 0; k < K; k++) {
            int i = reader.nextInt() - 1;
            int j = reader.nextInt() - 1;
            union(arr, size, i, j);
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == i) {
                ans++;
            }
        }
        System.out.println(ans);
    }

    private static void initialise (int arr[], int size[]) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }

        for (int i = 0; i < size.length; i++) {
            size[i] = 1;
        }
    }

    private static int root (int arr[], int a) {
        while (arr[a] != a) {
            arr[a] = arr[arr[a]];
            a = arr[a];
        }
        return arr[a];
    }

    private static void union (int arr[], int size[], int a, int b) {
        int rootA = root(arr, a);
        int rootB = root(arr, b);

        if (size[rootA] > size[rootB]) {
            arr[rootB] = rootA;
            size[rootA] += size[rootB];
        } else {
            arr[rootA] = rootB;
            size[rootB] += size[rootA];
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
