package codemonk.basicsofprogramming.basicsofio.examples;

import java.util.Scanner;

/**
 *
 * SOLVED SUCCESSFULLY
 *
 * Created by Nandan Mankad on 21-Nov-17.
 *
 * #####################################################################################################################################################################
 * Approach: Read a character, and print the integer equivalent of the same.
 *
 * Time Complexity: O(1)
 * #####################################################################################################################################################################
 *
 Given a character C, print the ASCII value of that character.

 Input:
 First and only line in input contains a character C.

 Output:
 Print the ASCII value of the character  C.

 Constraints: C ∈ ASCII characters
 */
public class ASCIIValue {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        char c = in.next().charAt(0);
        System.out.println((int)c);
    }
}
