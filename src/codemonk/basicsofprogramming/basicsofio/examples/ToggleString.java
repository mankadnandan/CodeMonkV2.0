package codemonk.basicsofprogramming.basicsofio.examples;

import java.util.Scanner;

/**
 * SOLVED SUCCESSFULLY
 *
 * Created by Nandan Mankad on 5/15/2017.
 *
 * #####################################################################################################################################################################
 * Approach: Simple approach of iterating over each character of the String and checking if the charcter is UpperCase change it to lowercase and vice-versa.
 *
 * Time Complexity: O(|S|), where |S| is the length of the String.
 * #####################################################################################################################################################################
 *
 You have been given a String S consisting of uppercase and lowercase English alphabets.
 You need to change the case of each alphabet in this String.
 That is, all the uppercase letters should be converted to lowercase and all the lowercase letters should be converted to uppercase.
 You need to then print the resultant String to output.

 Input Format
 The first and only line of input contains the String S

 Output Format
 Print the resultant String on a single line.

 Constraints

 1 ≤ |S| ≤ 100 where |S| denotes the length of string S.
 *
 */
public class ToggleString {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();

        char[] chArr = s.toCharArray();

        for (int i = 0; i < chArr.length; i++) {
            if (Character.isUpperCase(chArr[i])) {
                chArr[i] = Character.toLowerCase(chArr[i]);
            } else {
                chArr[i] = Character.toUpperCase(chArr[i]);
            }
        }

        System.out.println(chArr);

    }
}
