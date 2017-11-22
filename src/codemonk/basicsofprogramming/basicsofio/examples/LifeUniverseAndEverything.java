package codemonk.basicsofprogramming.basicsofio.examples;

import java.util.Scanner;

/**
 * Created by Nandan Mankad on 22-Nov-17.
 */
public class LifeUniverseAndEverything {
    public static void main(String args[] ) throws Exception {
        Scanner in = new Scanner(System.in);
        while (true) {
            int n = in.nextInt();
            if (n == 42) {
                break;
            } else {
                System.out.println(n);
            }
        }
    }
}
