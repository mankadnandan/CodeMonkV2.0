package codemonk.basicsofprogramming.basicsofio.examples;

import java.util.Scanner;

/**
 *
 * SOLVED SUCCESSFULLY
 *
 * Created by Nandan Mankad on 22-Nov-17.
 *
 * #####################################################################################################################################################################
 * Approach: We iterate from l to r and see if each number is divisible by k.
 *
 * Time Complexity: O(N) where N = l to r.
 * #####################################################################################################################################################################
 *
 You have been given 3 integers - l, r and k. Find how many numbers between l and r (both inclusive) are divisible by k.
 You do not need to print these numbers, you just have to find their count.

 Input Format
 The first and only line of input contains 3 space separated integers l, r and k.

 Output Format
 Print the required answer on a single line.

 Constraints:
 1 ≤ l ≤ r ≤ 1000
 1 ≤ k ≤ 1000
 *
 */
public class CountDivisors {
    public static void main(String args[] ) throws Exception {
        //Scanner
        Scanner s = new Scanner(System.in);
        int l = s.nextInt();
        int r = s.nextInt();
        int k = s.nextInt();
        int count = 0;
        for (int i = l; i <= r; i++) {
            if (i % k == 0) {
                count++;
            }
        }

        System.out.println(count);
    }
}
