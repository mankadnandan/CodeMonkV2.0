package codemonk.disjointDataStructure.examples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * SOLVED SUCCESSFULLY
 *
 * Created by Nandan Mankad on 05-Nov-17.
 *
 * ###################################################################################################################################################################
 * Approach: We are here to find the number of ways he can set the city ablaze after taking minimum risk. First we would use Disjoint Set / Union Find to arrange the
 * city into connected components. While doing that we would make sure that at any time, the root of the connected component has minimum risk by setting the minimum
 * risk value to the root. We also keep an array for size of the minimum risk value - indicating the number value cities that have current minimum value.
 *
 * Finally for each root of the connected component, we just multiply this number of minimum risk value for that components to get the number of ways we can set the
 * town ablaze with minimum risk.
 * ###################################################################################################################################################################
 *
 Fatland is a town with N cities numbered 1, 2, ..., N, connected with 2-way roads. Vincent is a villian who wants to set the entire town ablaze.
 For each city, there is a certain risk factor E[i] for setting that city ablaze.
 But once a city c is set ablaze, all cities that can be reached from c will be set ablaze immediately.

 The overall risk factor of setting some number of cities ablaze is the sum of the risk factors of setting each of the cities ablaze.
 Vincent's goal is to set the whole town ablaze with minimal risk by picking some set of cities to set ablaze.
 Let M be the minimum risk he takes when setting the entire city ablaze. Calculate the number of ways to pick a set of cities to obtain a risk of M.

 Input:

 The first line contains an integer N, the number of cities in Fatland.
 The second line contains N space-separated integers, the i-th integer representing E[i]. (1-based index)
 The third line contains an integer K, denoting the number 2-way roads.
 Each of the next K lines contains 2 space-separated integers i, j, a road between cities i and j.

 Output:

 Output one integer, W. The number of ways to pick a set of cities to obtain a risk of M, so that the entire town is set ablaze is U.
 W is the remainder when U is divided by 109+7=1000000007.

 Constraints:

 1 <= N <= 10^3
 1 <= K <= 10^3
 1 <= E[i] <= 10^3
 *
 */
public class CityAndFiremanVincent {
    public static void main(String[] args) {
        FastReader reader = new FastReader();
        int N = reader.nextInt();
        int arr[] = new int[N];
        int size[] = new int[N];
        int risk[] = new int[N];
        int minRiskSize[] = new int[N];
        int ans = 1;
        for (int i = 0; i < N; i++) {
            risk[i] = reader.nextInt();
        }
        int K = reader.nextInt();
        initialise(arr, size, minRiskSize);

        for (int k = 0; k < K; k++) {
            int i = reader.nextInt() - 1;
            int j = reader.nextInt() - 1;
            union(arr, size, risk, minRiskSize, i, j);
        }

        for (int i = 0; i < N; i++) {
            if (arr[i] == i) {
                ans = ((ans % 1000000007) * (minRiskSize[i] % 1000000007)) % 1000000007;
            }
        }
        System.out.println(ans);
    }

    private static void initialise (int arr[], int size[], int minRiskSize[]) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        for (int i = 0; i < size.length; i++) {
            size[i] = 1;
        }
        for (int i = 0; i < minRiskSize.length; i++) {
            minRiskSize[i] = 1;
        }
    }

    private static int root (int arr[], int a) {
        while (arr[a] != a) {
            arr[a] = arr[arr[a]];
            a = arr[a];
        }
        return a;
    }

    private static void union (int arr[], int size[], int risk[], int minRiskSize[], int a, int b) {
        int rootA = root(arr, a);
        int rootB = root(arr, b);
        if (rootA == rootB) {
            return;
        }
        int riskA = risk[rootA];
        int riskB = risk[rootB];
        if (size[rootA] > size[rootB]) {
            arr[rootB] = rootA;
            size[rootA] += size[rootB];
            risk[rootA] = Math.min(riskA, riskB);
            if (riskA == riskB) {
                minRiskSize[rootA] += minRiskSize[rootB];
            } else if (riskB < riskA) {
                minRiskSize[rootA] = minRiskSize[rootB];
            }
        } else {
            arr[rootA] = rootB;
            size[rootB] += size[rootA];
            risk[rootB] = Math.min(riskA, riskB);
            if (riskA == riskB) {
                minRiskSize[rootB] += minRiskSize[rootA];
            } else if (riskA < riskB) {
                minRiskSize[rootB] = minRiskSize[rootA];
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
