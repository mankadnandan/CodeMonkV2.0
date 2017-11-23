package codemonk.basicsofprogramming.basicsofio.examples;

import java.util.Scanner;

/**
 *
 * SOLVED SUCCESSFULLY
 *
 * Created by Nandan Mankad on 22-Nov-17.
 *
 * #####################################################################################################################################################################
 * Approach: Just multiply all integer from 1 to N.
 *
 * Time Complexity: O(N)
 * #####################################################################################################################################################################
 *
 You have been given a positive integer N. You need to find and print the Factorial of this number.
 The Factorial of a positive integer N refers to the product of all number in the range from 1 to N.
 You can read more about the factorial of a number here.

 Input Format:
 The first and only line of the input contains a single integer N denoting the number whose factorial you need to find.

 Output Format
 Output a single line denoting the factorial of the number N.

 Constraints
 1 ≤ N ≤ 10
 *
 */
public class Factorial {
    public static void main(String args[] ) throws Exception {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int ans = 1;
        for (int i = n; i >= 1; i--) {
            ans = ans * i;
        }
        System.out.println(ans);
    }
}
