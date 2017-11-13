package codemonk.basicsofprogramming.basicsofio.examples;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * SOLVED SUCCESSFULLY
 *
 * Created by Nandan Mankad on 5/13/2017.
 */
public class MagicalWord {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int noOfTests = in.nextInt();

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