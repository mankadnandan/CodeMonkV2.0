package codemonk.basicsofprogramming.basicsofio.examples;

import java.util.Scanner;

/**
 *
 * SOLVED SUCCESSFULLY
 *
 * Created by Nandan Mankad on 5/14/2017.
 *
 * #####################################################################################################################################################################
 * Approach: The approach here is mainly based on the seating arrangement diagram. Though mainly it is to offset your given number by 12, as each compartment has
 * 12 seats. This will give you the position of the seat in a single compartment.
 *
 * Time Complexity: Its O(1) for each test case. So total time complexity is O(T).
 * #####################################################################################################################################################################
 *
 Akash and Vishal are quite fond of travelling. They mostly travel by railways.
 They were travelling in a train one day and they got interested in the seating arrangement of their compartment.
 The compartment looked something like: For this refer to the diagram on Hacker Earth.

 So they got interested to know the seat number facing them and the seat type facing them. The seats are denoted as follows :

 Window Seat : WS
 Middle Seat : MS
 Aisle Seat : AS

 You will be given a seat number, find out the seat number facing you and the seat type, i.e. WS, MS or AS.

 INPUT
 First line of input will consist of a single integer T denoting number of test-cases. Each test-case consists of a single integer N denoting the seat-number.

 OUTPUT
 For each test case, print the facing seat-number and the seat-type, separated by a single space in a new line.

 CONSTRAINTS
 1 <= T <= 10^5
 1 <= N <= 10^8
 *
 */
public class SeatingArrangement {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int noOfTests = in.nextInt();

        int arr[] = new int[13];
        String arrSeat[] = new String[13];

        arr[1] = 12;
        arr[2] = 11;
        arr[3] = 10;
        arr[4] = 9;
        arr[5] = 8;
        arr[6] = 7;
        arr[7] = 6;
        arr[8] = 5;
        arr[9] = 4;
        arr[10] = 3;
        arr[11] = 2;
        arr[12] = 1;

        arrSeat[1] = "WS";
        arrSeat[2] = "MS";
        arrSeat[3] = "AS";
        arrSeat[4] = "AS";
        arrSeat[5] = "MS";
        arrSeat[6] = "WS";
        arrSeat[7] = "WS";
        arrSeat[8] = "MS";
        arrSeat[9] = "AS";
        arrSeat[10] = "AS";
        arrSeat[11] = "MS";
        arrSeat[12] = "WS";

        for (int i = 0; i < noOfTests; i++) {
            int n = in.nextInt();

            int tempN = n % 12;
            int temp = n / 12;
            if (tempN == 0) {
                tempN = 12;
                temp = temp - 1;
            }

            System.out.print(12 * temp + arr[tempN] + " ");
            System.out.println(arrSeat[tempN]);

        }
    }
}
