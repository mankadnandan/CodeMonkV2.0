package codemonk.basicsofprogramming.basicsofio.examples;

import java.util.Scanner;

/**
 * Created by Nandan Mankad on 22-Nov-17.
 */
public class CountDivisors {
    public static void main(String args[] ) throws Exception {
        //Scanner
        Scanner s = new Scanner(System.in);
        int l = s.nextInt();
        int r = s.nextInt();
        int k = s.nextInt();
        int count = 0;
        for (int i = l; i <= r; i++) {
            if (i % k == 0) {
                count++;
            }
        }

        System.out.println(count);
    }
}
