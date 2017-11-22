package codemonk.basicsofprogramming.basicsofio.examples;

import java.util.Scanner;

/**
 * Created by Nandan Mankad on 22-Nov-17.
 */
public class Factorial {
    public static void main(String args[] ) throws Exception {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int ans = 1;
        for (int i = n; i >= 1; i--) {
            ans = ans * i;
        }
        System.out.println(ans);
    }
}
