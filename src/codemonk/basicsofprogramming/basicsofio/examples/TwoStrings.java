package codemonk.basicsofprogramming.basicsofio.examples;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * SOLVED SUCCESSFULLY
 *
 * Created by Nandan Mankad on 21-Nov-17.
 *
 * #####################################################################################################################################################################
 * Approach: This can be done through hashing and checking the count of each characters. Count of each characters should match.
 * Though we have solved this in a different way by sorting each string and comparing the Strings character by character.
 *
 * Time Complexity: O(N) for hashing technique. O(N log(N)) for sorting technique.
 * #####################################################################################################################################################################
 *
 Given two strings of equal length, you have to tell whether they both strings are identical.

 Two strings S1 and S2 are said to be identical, if any of the permutation of string S1 is equal to the string S2.
 See Sample explanation for more details.

 Input :
 First line, contains an intger 'T' denoting no. of test cases.
 Each test consists of a single line, containing two space separated strings S1 and S2 of equal length.

 Output:
 For each test case, if any of the permutation of string S1 is equal to the string S2 print YES else print NO.

 Constraints:
 1 <= T <=100
 1 <= |S1| = |S2| <= 10 ^ 5
 String is made up of lower case letters only.
 *
 */
public class TwoStrings {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            char s1[] = in.next().toCharArray();
            char s2[] = in.next().toCharArray();
            Arrays.sort(s1);
            Arrays.sort(s2);
            if (new String(s1).equals(new String(s2))) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}
