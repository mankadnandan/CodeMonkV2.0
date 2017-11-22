package codemonk.basicsofprogramming.basicsofio.examples;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * SOLVED SUCCESSFULLY
 *
 * Created by Nandan Mankad on 22-Nov-17.
 *
 * #####################################################################################################################################################################
 * Approach: Here the main logic is the circling one, where after the 26th letter we should start again from 1st.
 * We use modulo of 26 and modulo of 10 to circle through characters and numbers respectively.
 *
 * Time Complexity: We maintain the HashMap to map character and integer. Then we just iterate throughout the O(N) and modulo logic.
 * So, total time complexity is O(N).
 * #####################################################################################################################################################################
 *
 Indian army is going to do a surprise attack on one of its enemies country. The President of India, the Supreme Commander of
 the Indian Army will be sending an alert message to all its commanding centers. As enemy would be monitoring the message,
 Indian army is going to encrypt(cipher) the message using basic encryption technique. A decoding key 'K' (number) would be sent secretly.

 You are assigned to develop a cipher program to encrypt the message.
 Your cipher must rotate every character in the message by a fixed number making it unreadable by enemies.

 Given a single line of string 'S' containing alpha, numeric and symbols, followed by a number '0<=N<=1000'.
 Encrypt and print the resulting string.

 Note: The cipher only encrypts Alpha and Numeric. (A-Z, a-z, and 0-9) . All Symbols, such as - , ; %, remain unencrypted.

 Input:
 All-convoYs-9-be:Alert1.
 4

 Output:
 Epp-gsrzsCw-3-fi:Epivx5.

 *
 */
public class Cipher {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // A = 65 , Z = 90, a = 97, z = 122
        Map<Character, Integer> characterIntegerMap = new HashMap<>();
        Map<Integer, Character> integerCharacterMap = new HashMap<>();
        int j = 0;
        for (int i = 65; i <= 90; i++) {
            characterIntegerMap.put((char)i, j);
            integerCharacterMap.put(j, (char)i);
            j++;
        }

        Map<Character, Integer> characterIntegerMap2 = new HashMap<>();
        Map<Integer, Character> integerCharacterMap2 = new HashMap<>();
        int k = 0;
        for (int i = 97; i <= 122; i++) {
            characterIntegerMap2.put((char)i, k);
            integerCharacterMap2.put(k, (char)i);
            k++;
        }

        Map<Character, Integer> characterIntegerMap3 = new HashMap<>();
        Map<Integer, Character> integerCharacterMap3 = new HashMap<>();
        int l = 0;
        for (int i = 48; i <= 57; i++) {
            characterIntegerMap3.put((char)i, l);
            integerCharacterMap3.put(l, (char)i);
            l++;
        }

        char s[] = in.next().toCharArray();
        k = in.nextInt();
        for (int i = 0; i < s.length; i++) {
            if (s[i] >= 65 && s[i] <= 90) {
                int num = characterIntegerMap.get(s[i]);
                num = num + k;
                num = num % 26;
                s[i] = integerCharacterMap.get(num);
            } else if (s[i] >= 97 && s[i] <= 122) {
                int num = characterIntegerMap2.get(s[i]);
                num = num + k;
                num = num % 26;
                s[i] = integerCharacterMap2.get(num);
            } else if (s[i] >= 48 && s[i] <= 57) {
                int num = characterIntegerMap3.get(s[i]);
                num = num + k;
                num = num % 10;
                s[i] = integerCharacterMap3.get(num);
            }
        }
        System.out.println(new String(s));
    }
}
