package codemonk.basicsofprogramming.basicsofio.examples;

import java.util.Scanner;

/**
 * Created by Nandan Mankad on 22-Nov-17.
 */
public class RoyAndProfilePicture {
    public static void main(String args[] ) throws Exception {
        Scanner s = new Scanner(System.in);
        int L = s.nextInt();
        int N = s.nextInt();
        for (int i = 0; i < N; i++) {
            int W = s.nextInt();
            int H = s.nextInt();

            if (W >= L && H >= L) {
                if (W == H) {
                    System.out.println("ACCEPTED");
                } else {
                    System.out.println("CROP IT");
                }
            } else {
                System.out.println("UPLOAD ANOTHER");
            }

        }
    }
}
