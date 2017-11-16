package codemonk.basicsofprogramming.basicsofio.examples;

import java.util.Scanner;

/**
 *
 * SOLVED SUCCESSFULLY
 *
 * Created by Nandan Mankad on 5/15/2017.
 *
 * #####################################################################################################################################################################
 * Approach: Pretty basic implementation type problem. For checking a string to be a palindrome, we start from first and last letter to the middle
 * of the String. If all characters match then palindrome else not
 *
 * Time Complexity: O (N/2) as we traverse the string from start or end up to middle.
 *
 * #####################################################################################################################################################################
 You have been given a String S.
 You need to find and print whether this string is a palindrome or not.
 If yes, print "YES" (without quotes), else print "NO" (without quotes).

 Input Format
 The first and only line of input contains the String S. The String shall consist of lowercase English alphabets only.

 Output Format
 Print the required answer on a single line.

 Constraints

 1 ≤ |S| ≤ 100

 Note: String S consists of lowercase English Alphabets only.
 *
 */
public class PalindromicString {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();

        char[] chArr = s.toCharArray();
        int i = 0;
        int j = chArr.length - 1;
        boolean isPalindromic = true;
        for (; i < chArr.length / 2; i++, j--) {
            if (!(chArr[i] == chArr[j])) {
                isPalindromic = false;
                break;
            }
        }
        if (isPalindromic) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
