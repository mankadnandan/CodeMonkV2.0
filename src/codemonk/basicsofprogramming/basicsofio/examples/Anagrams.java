package codemonk.basicsofprogramming.basicsofio.examples;

import java.util.Scanner;

/**
 *
 * SOLVED SUCCESSFULLY
 *
 * Created by Nandan Mankad on 30-Nov-17.
 *
 * #####################################################################################################################################################################
 * Approach: For each case, we maintain a counter for each character of both the Strings in an ASCII indexed based array.
 * Finally, we just loop over all the ascii characters and take the sum of absolute difference of each character, which
 * is our answer.
 *
 * Time Complexity: O(N) for each String. Total Complexity O(N).
 * #####################################################################################################################################################################

 Given two strings, a and b , that may or may not be of the same length, determine the minimum number of character deletions required to make a and b anagrams. Any characters can be deleted from either of the strings.

 Input :

 test cases,t
 two strings a and b, for each test case
 Output:

 Desired O/p

 Constraints :

 string lengths<=10000

 Note :

 Anagram of a word is formed by rearranging the letters of the word.

 For e.g. -> For the word RAM - MAR,ARM,AMR,RMA etc. are few anagrams.

 */
public class Anagrams {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int noOfTest = in.nextInt();
        for (int i = 0; i < noOfTest; i++) {
            String stringA = in.next();
            String stringB = in.next();
            int arr1[] = new int[128];
            int arr2[] = new int[128];
            int ans = 0;
            for (int j = 0; j < stringA.length(); j++) {
                arr1[stringA.charAt(j)]++;
            }
            for (int j = 0; j < stringB.length(); j++) {
                arr2[stringB.charAt(j)]++;
            }
            for (int j = 0; j < 128; j++) {
                ans = ans + Math.abs(arr1[j] - arr2[j]);
            }
            System.out.println(ans);
        }
    }
}
