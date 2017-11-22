package codemonk.basicsofprogramming.basicsofio.examples;

import java.util.Scanner;

/**
 * Created by Nandan Mankad on 22-Nov-17.
 */
public class FindProduct {
    private static int BIG_PRIME = 1000000007;
    public static void main(String args[] ) throws Exception {
        //Scanner
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        long ans = 1;
        for (int i = 0; i < N; i++) {
            ans = (ans * s.nextInt()) % BIG_PRIME;
        }
        System.out.println(ans);
    }
}
