package codemonk.basicsofprogramming.basicsofio.examples;

import java.util.Scanner;

/**
 *
 * SOLVED SUCCESSFULLY
 *
 * Created by Nandan Mankad on 22-Nov-17.
 *
 * #####################################################################################################################################################################
 * Approach: Read each integer and multiply to the product. The catch is just to keep modulo 10^9 + 7 so that the number
 * doesn't go out of range.
 *
 * Time Complexity: O(N) as we just find product of each number.
 * #####################################################################################################################################################################
 You have been given an array A of size N consisting of positive integers.
 You need to find and print the product of all the number in this array Modulo 10^9 + 7.

 Input Format:
 The first line contains a single integer N denoting the size of the array.
 The next line contains N space separated integers denoting the elements of the array

 Output Format:
 Print a single integer denoting the product of all the elements of the array Modulo 10^9 + 7.

 Constraints:
 1 ≤ N ≤ 10^3
 1 ≤ A[i] ≤ 10^3
 *
 */
public class FindProduct {
    private static int BIG_PRIME = 1000000007;
    public static void main(String args[] ) throws Exception {
        //Scanner
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        long ans = 1;
        for (int i = 0; i < N; i++) {
            ans = (ans * s.nextInt()) % BIG_PRIME;
        }
        System.out.println(ans);
    }
}
