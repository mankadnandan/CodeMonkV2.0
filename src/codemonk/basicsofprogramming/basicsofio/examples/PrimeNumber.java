package codemonk.basicsofprogramming.basicsofio.examples;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * SOLVED SUCCESSFULLY
 *
 * Created by Nandan Mankad on 5/14/2017.
 *
 * #####################################################################################################################################################################
 * Approach: We use the square root approach here to find the Prime number. For each number from 1 to N, we check if the any number form 2 to sqrt(number)
 * divides the number. If yes its not prime, else its prime.
 *
 * Time Complexity: O(N * sqrt(N))
 *
 * #####################################################################################################################################################################
 *
 You are given an integer N. You need to print the series of all prime numbers till N.

 Input Format

 The first and only line of the input contains a single integer N denoting the number till where you need to find the series of prime number.

 Output Format

 Print the desired output in single line separated by spaces.

 Constraints

 1<=N<=1000
 *
 */
public class PrimeNumber {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        List<Integer> primeNum = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            boolean isPrime = true;
            for (int j = 2; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                primeNum.add(i);
            }
        }

        for (Integer x : primeNum) {
            System.out.print(x + " ");
        }

    }
}
