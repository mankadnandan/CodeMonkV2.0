package codemonk.basicsofprogramming.basicsofio.examples;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * SOLVED SUCCESSFULLY
 *
 * Created by Nandan Mankad on 5/13/2017.
 *
 * #####################################################################################################################################################################
 * Approach: Here we are given a String. Our job is to make the ascii value of each character of the string prime. So first we find all the prime numbers from 0 to 127.
 * This is done through SQRT method of finding primes. After that for each character of string, we maintain two vectors. chL and chU. One which would traverse downwards
 * from current character ascii and and the other would climb up. The first one to reach prime ascii is our character.
 *
 * Time Complexity: Finding prime number would be O (N * sqrt(N)), that would be constant as we want to find only upto numbers 128. After that for each string we travel
 * max upto 128 charcters.
 *
 * So Total Time Complexity is O(T * |S|)
 * #####################################################################################################################################################################
 *
 Dhananjay has recently learned about ASCII values. He is very fond of experimenting.With his knowledge of ASCII values and character he has developed a special word and named it Dhananjay's Magical word.
 A word which consist of alphabets whose ASCII values is a prime number is an Dhananjay's Magical word. An alphabet is Dhananjay's Magical alphabet if its ASCII value is prime.
 Dhananjay's nature is to boast about the things he know or have learnt about. So just to defame his friends he gives few string to his friends and ask them to convert it to Dhananjay's Magical word.
 None of his friends would like to get insulted. Help them to convert the given strings to Dhananjay's Magical Word.

 Rules for converting:
 1.Each character should be replaced by the nearest Dhananjay's Magical alphabet.
 2.If the character is equidistant with 2 Magical alphabets. The one with lower ASCII value will be considered as its replacement.

 Input format:
 First line of input contains an integer T number of test cases. Each test case contains an integer N (denoting the length of the string) and a string S.

 Output Format:
 For each test case, print Dhananjay's Magical Word in a new line.

 Constraints:
 1 <= T <= 100
 1 <= |S| <= 500
 *
 */
public class MagicalWord {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int noOfTests = in.nextInt();

        // Keeping a set of all prime ascii values.
        Set<Integer> primeSet = new HashSet<>();

        for (int i = 2; i < 128; i++) {
            boolean isPrime = true;
            for (int j = 2; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    isPrime = false;
                }
            }
            if (isPrime) {
                primeSet.add(i);
            }
        }

        for (int i = 0; i < noOfTests; i++) {
            int noOfChar = in.nextInt();
            char s[] = in.next().toCharArray();
            for (int j = 0; j < s.length; j++) {
                int chL = s[j];
                int chU = s[j];
                while (true) {
                    if (((chL >= 65 && chL <= 90) || (chL >= 97 && chL <= 122)) && primeSet.contains(chL)) {
                        s[j] = (char) (chL);
                        break;
                    } else if (((chU >= 65 && chU <= 90) || (chU >= 97 && chU <= 122)) && primeSet.contains(chU)) {
                        s[j] = (char) (chU);
                        break;
                    } else {
                        chL = chL - 1;
                        chU = chU + 1;
                    }
                }
            }
            System.out.println(s);
        }
    }
}