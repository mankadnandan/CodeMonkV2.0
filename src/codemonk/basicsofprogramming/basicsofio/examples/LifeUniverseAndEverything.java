package codemonk.basicsofprogramming.basicsofio.examples;

import java.util.Scanner;

/**
 *
 * SOLVED SUCCESSFULLY
 *
 * Created by Nandan Mankad on 22-Nov-17.
 *
 * #####################################################################################################################################################################
 * Approach: Write each number from input to output. Stop when read 42.
 *
 * Time Complexity: O(N)
 * #####################################################################################################################################################################
 *
 Your program is to use the brute-force approach in order to find the Answer to Life, the Universe, and Everything.
 More precisely... rewrite small numbers from input to output. Stop processing input after reading in the number 42.
 All numbers at input are integers of one or two digits.
 *
 *
 */
public class LifeUniverseAndEverything {
    public static void main(String args[] ) throws Exception {
        Scanner in = new Scanner(System.in);
        while (true) {
            int n = in.nextInt();
            if (n == 42) {
                break;
            } else {
                System.out.println(n);
            }
        }
    }
}
