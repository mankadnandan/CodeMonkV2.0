package codemonk.heapandpriorityqueue.examples;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * SOLVED SUCCESSFULLY
 *
 * Created by Nandan Mankad on 9/4/2018.
 *
 * #########################################################################################################################
 * Approach: This Problem can be solved through Kadane's algorithm, though there are two catches. First that Mavis wants to
 * buy cards with unique Power-Ups. Hence we need to tweak Kadane's algorithm to handle this that when a card with same
 * Power-Up is encountered we stop counting there and start again.
 *
 * For this we maintain a HashMap to see if we have already encountered a card, if yes, we start counting again by making
 * maxEnding here 0.
 *
 * Second catch is that all power-up can be negative so we need to handle that too gracefully.
 *
 * Time Complexity: O(N)
 *
 * #########################################################################################################################
 *

 Mavis went to market. On a shop, he saw Superhero Playing Cards. Each card has a Superhero and his power-up portrayed on it,
 i.e. each card has a power-up represented by an integer (can be negative, positive or zero).
 He thought of buying some cards with unique Power-Up, and he wants to maximize his total power-up,
 but shopkeeper only sells cards which are consecutively placed to each other. Mavis wants your help in finding
 the maximum total power-up that he can get with all unique consecutive cards

 Since he has entered into the shop so he will buy atleast one card

 Input:
 The first line of the input contains a single integer, N denoting the number of Playing Cards on the shop.
 The second line contains N space-separated integers. ith integer represent the power-up of ith card on the shop.


 Output:
 Print the maximum total power-up of the cards that he can buy, satisfying his uniqueness wish and shopkeeper’s
 requirement of consecutive cards.

 Constraints :
 1 ≤ N ≤ 105
 -109 ≤ Power-ups ≤ 109

 */
public class PowerUp {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        long arr[] = new long[N];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = in.nextInt();
        }

        boolean allNegative = true;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= 0) {
                allNegative = false;
            }
        }

        if (allNegative) {

            long min = arr[0];
            long maxEndingHere = arr[0];
            long maxSoFar = arr[0];
            Map<Long, Long> hashMap = new HashMap<>();
            hashMap.put(arr[0], arr[0]);
            boolean startAgain = false;

            for (int i = 1; i < arr.length; i++) {
                if (hashMap.containsKey(arr[i])) {
                    startAgain = true;
                    hashMap.clear();
                    hashMap.put(arr[i], arr[i]);
                } else {
                    hashMap.put(arr[i], arr[i]);
                }

                if (arr[i] < min) {
                    min = arr[i];
                }

                if (!startAgain) {
                    maxEndingHere += arr[i];
                } else {
                    maxEndingHere = arr[i];
                    startAgain = false;
                }

                if (maxEndingHere < min) {
                    startAgain = true;
                }

                if (maxEndingHere > maxSoFar) {
                    maxSoFar = maxEndingHere;
                }

            }

            System.out.println(maxSoFar);
        } else {
            long maxEndingHere = 0;
            long maxSoFar = 0;
            Map<Long, Integer> hashMap = new HashMap<>();

            for (int i = 0; i < arr.length; i++) {
                if (hashMap.containsKey(arr[i])) {
                    i = hashMap.get(arr[i]) + 1;
                    maxEndingHere = 0;
                    hashMap.clear();
                    hashMap.put(arr[i], i);
                } else {
                    hashMap.put(arr[i], i);
                }

                maxEndingHere += arr[i];

                if (maxEndingHere < 0) {
                    maxEndingHere = 0;
                    hashMap.clear();
                }

                if (maxEndingHere > maxSoFar) {
                    maxSoFar = maxEndingHere;
                }

            }

            System.out.println(maxSoFar);
        }

    }
}
