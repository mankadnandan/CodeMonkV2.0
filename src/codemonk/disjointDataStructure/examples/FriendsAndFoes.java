package codemonk.disjointDataStructure.examples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * SOLVED SUCCESSFULLY
 *
 * Created by Nandan Mankad on 10-Nov-17.
 *
 * ###################################################################################################################################################################
 * Approach: Here important information is that we just want to pick up a single group which is the largest group that does'nt have any two enemies in it together
 * in the same group.
 *
 * Here we keep an extra array isSpecialGroupCandidate indicating if the group is eligible candidate to become a special group. Now first we build the graph by
 * doing a union for all the friendship relationship. Hence we build a graph of Disjoint Set connected component for friendship relationship.
 *
 * Next, we start iterating over the enemy relationship. For each enemy we see whether both the enemies are in the same group. If yes, we mark the
 * group outright as not eligible for special group.
 *
 * After marking out the group in this way, we are left with the group that can qualify for being a special group. But only one group of these who
 * have maximum number of friends inside can be a special group. So here our size[] array would come handy for us as we already have individual group size in it.
 * Hence we just iterate over each group, and keep track of which group having maximum size. The maxSize is our answer.
 *
 * Time Complexity: O(M1 * log N) for building friendship relationships. O(M2 * log N) for finding if enemies are in same group. After that O(N) to find the maxSize
 * among the eligible groups.
 *
 * Hence total time complexity is O(M1 * log N) + O(M1 * log N) i.e. O((M1+M2) * log N).
 *
 * ###################################################################################################################################################################
 *
 Monk will be organizing a party as he recently got a promotion. He wants to invite his childhood classmates.
 Monk had a total of N classmates and a lot of them were friends with each other. There used to be M1 friendship relations.
 However, time took its toll and some of them became enemies. A total of M2 enemy relations are formed.

 To avoid any nuisance in his party, Monk wants to invite only Special group of classmates in his party.
 Special group is the one where each person in the group is a friend of another and there should not be any two persons
 in that group who are enemies of each other. Now given the relationships among some of them, your task is to find the
 maximum number of classmates Monk can invite to his party.

 Note:

 1) Group of friends will be formed only by the given inputs. If given, A is a friend of B, B is a friend of C, then A,B,C forms a friendship group,
 and you cannot pick a subgroup from it.

 2) Two people are said to be friends of each other if their friend relation is given or they are connected through a chain of mutual friends. For example, if given
 A is a friend of B, and B is a friend of C then A and C are friends.

 3) Two people are said to be enemies of each other, only and only if their enemy relation is given. For example, if given A is a enemy of
 B, and B is a enemy of C, then  A and C are not enemies of each other, unless it is given in the input.

 4) There won't be any case, where both friend and enemy relation is given for two people.

 Input:
 The first line consists of integer N, denoting the number of Monk's classmates.
 The second line consists of integer M1 denoting the number of pair of friends.
 The next M1 lines consists of two integers of the form A and B who are friends with each other.
 The next line consists of integer M2 denoting the number of pair of enemies. The next M2 lines consists of two integers of the form A and B who are enemies.

 Output:
 Print in a single line, the maximum number of friends Monk can invite to his party provided they should form a Special group.

 Constraints:
 1 ≤ N ≤ 10^5
 1 ≤ M1+M2 ≤ min(2×10^5, N×(N−1)/2)
 1 ≤ A, B ≤ N
 *
 */
public class FriendsAndFoes {

    public static void main(String[] args) {
        FastReader reader = new FastReader();
        int N = reader.nextInt();
        int M1 = reader.nextInt();
        int arr[] = new int[N];
        int size[] = new int[N];
        boolean isSpecialGroupCandidate[] = new boolean[N];
        initialize(arr, size, isSpecialGroupCandidate);
        for (int i = 0; i < M1; i++) {
            int a = reader.nextInt() - 1;
            int b = reader.nextInt() - 1;
            union(arr, size, a, b);
        }
        int M2 = reader.nextInt();
        for (int i = 0; i < M2; i++) {
            int a = reader.nextInt() - 1;
            int b = reader.nextInt() - 1;
            boolean isConnected = find(arr, a, b);
            if (isConnected) {
                isSpecialGroupCandidate[root(arr, a)] = false;
            }
        }
        int maxSize = 0;
        for (int i = 0; i < isSpecialGroupCandidate.length; i++) {
            if (arr[i] == i && isSpecialGroupCandidate[i]) {
                if (maxSize < size[i]) {
                    maxSize = size[i];
                }
            }
        }
        System.out.println(maxSize);
    }

    private static void initialize (int arr[], int size[], boolean isSpecialGroupCandidate[]) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        for (int i = 0; i < size.length; i++) {
            size[i] = 1;
        }
        for (int i = 0; i < isSpecialGroupCandidate.length; i++) {
            isSpecialGroupCandidate[i] = true;
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
        int rootA =  root(arr, a);
        int rootB =  root(arr, b);
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
