package codemonk.disjointDataStructure.examples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * SOLVED SUCCESSFULLY
 *
 * Created by Nandan Mankad on 11-Nov-17.
 *
 * ###################################################################################################################################################################
 * Approach: Here we need to find the number of different numbers which can be created based on Monk's conditions.
 * Since conditions are of the form of A B, where A to B are palindromes, the key idea here is to realise that number at A and B would be same
 * to make A to B a palindrome. Additionally, numbers at A+1 and B-1 has to be same to make A to B a palindrome. Hence we can deduce that numbers at A & B,
 * A + 1 & B - 1, A + 2 & B - 2 and so on, should be same to make A to B a palindrome.
 *
 * Now to use this idea, we use the disjoint set for the positions which has to have a same number. So, we union A & B, A + 1 & B - 1, A + 2 & B - 2 and so on.
 * In this way we get multiple disjoint set of connected components where each connected component can be assigned a particular number. So, each connected component
 * can assign any of the 10 possibilities of 0 to 9 i.e. 10 different ways for each component.
 *
 * Hence total number of different ways to do this is (10 ^ num of connected component) which is our answer.
 *
 * Time Complexity: Here on giving the position of palindrome as A B, we need to call union for all the numbers in between A & B. Hence For each query it takes
 * O(N log N) for union. And there are Q such queries. So total time build the graph is O (Q * N log N). After building the graph we just need the total number of
 * connected component which can be found in O (N) and then multiply 10 for each connected component.
 *
 * Hence total time complexity: O (Q * N log N).
 *
 * ###################################################################################################################################################################
 *
 Monk loves maths and is always curious to learn new things. Recently, he learned about palindromes.
 Now, he decided to give his students a problem which uses maths and the concept of palindromes.
 So, he wants the students to find how many different numbers they can create according to the following conditions.
 He will give the students an integer N which will denote the total number of digits in the final number.
 Also he will provide them with Q conditions where these conditions will be of form A B where the number formed by concatenating the digits from the
 Ath position to Bth position is a palindrome. You can learn more about palindromes here.
 Note- Numbers with leading zeros are also to be considered.

 Input:
 First line consists of the integer N denoting the number of digits in final number.
 Next line consists of the integer Q denoting the number of conditions.
 Next Q lines will be of the form A B which denotes that the number formed by concatenating the digits from the Ath position to Bth position of the final number is a palindrome.

 Output:
 Print the number of different numbers the students can create by following the conditions. Since the answer can be very large, print it modulo 10^9+7.

 Constraints:

 1 ≤ N ≤ 10^3
 0 ≤ Q ≤ 10^4
 1 ≤ A ≤ B ≤ N
 *
 */
public class MonkAndPalindromes {
    public static void main(String[] args) {
        FastReader reader = new FastReader();
        int N = reader.nextInt();
        int Q = reader.nextInt();

        int arr[] = new int[N];
        int size[] = new int[N];

        initialise(arr, size);

        for (int i = 0; i < Q; i++) {
            int a = reader.nextInt() - 1;
            int b = reader.nextInt() - 1;

            if (a < b) {
                while (a < b && a != b) {
                    union(arr, size, a, b);
                    a++;
                    b--;
                }
            } else if (b < a){
                while (b < a && a != b) {
                    union(arr, size, a, b);
                    a--;
                    b++;
                }
            }
        }
        int countDisjointSet = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == i) {
                countDisjointSet++;
            }
        }
        int ans = 1;
        for (int i = 1; i <= countDisjointSet; i++) {
            ans = ((ans % 1000000007) * 10) % 1000000007;
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
        return a;
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

        if (rootA == rootB) {
            return;
        }

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
