package codemonk.heapandpriorityqueue.examples;

import java.util.Scanner;

/**
 *
 * SOLVED SUCCESSFULLY
 *
 * Created by Nandan Mankad on 20-Dec-17.
 *
 * ###################################################################################################################################################################
 * Approach:
 *
 * Time Complexity:
 *
 * ###################################################################################################################################################################
 *
 Monk and his P-1 friends recently joined a college. He finds that N students have already applied for different courses before him.
 Courses are assigned numbers from 1 to C. He and his friends will follow the following conditions when choosing courses:-
 They will choose the course i (1 <= i <= C), for which the value of z is minimum. Here, z = x*c where c is the number of students already
 enrolled in the course i and x is the sum of IQ of the last two students who enrolled in that course. If a single student has applied for a course,
 then the value of x will be that student's IQ. If no student has enrolled for that course, then value of x will be 0.
 If the value of z is same for two courses, then they will choose the course with the minimum course number.
 You need to find which courses Monk and his friends should take after following the above conditions.
 Note: Each of them will choose their courses, one at a time. Monk will choose his course first followed by his friends.

 Input:
 The first line contains the numbers C, P and N where C denotes the number of courses in that college, P denotes Monk and his friends and N denotes the number of students who have already applied for the courses.
 The next line consists of N space separated integers Y[i] which denotes the IQ of the ith student. Here, the ith student chooses the ith course.
 The next line consists of P space separated integers X[i] which denotes the IQ of Monk and his friends.

 Output:
 Print P space separated integers in a line which denotes the course number which Monk and his friends have applied for.

 Constraints:
 1 <= C <= 100000
 1 <= P <= 100000
 1 <= N <= C
 1 <= Y[i],X[i] <= 100000
 *
 */
public class MonkAndIQ {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int C = in.nextInt();
        int P = in.nextInt();
        int N = in.nextInt();

        int iqN[] = new int[N];
        int iqP[] = new int[P];
        Z z[] = new Z[C+1];

        for (int i = 0; i < z.length; i++) {
            z[i] = new Z();
            z[i].courseNumber = i;
        }

        for (int i = 0; i < N; i++) {
            iqN[i] = in.nextInt();
            z[i+1].courseNumber = i+1;
            z[i+1].noOfStudentsEnrolled = 1;
            z[i+1].x2 = iqN[i];
        }

        for (int i = 0; i < P; i++) {
            try {
                iqP[i] = in.nextInt();
            } catch (Exception e) {
                break;
            }

        }

        buildMinHeap(z);

        for (int i = 0; i < P; i++) {
            System.out.print(z[1].courseNumber + " ");
            z[1].x1 = z[1].x2;
            z[1].x2 = iqP[i];
            z[1].noOfStudentsEnrolled++;
            minHeapify(z, 1);
        }

    }

    private static void buildMinHeap(Z z[]) {
        for (int i = (z.length - 1) / 2; i >= 1; i--) {
            minHeapify(z, i);
        }
    }

    private static void minHeapify(Z z[], int i) {
        int left = i * 2;
        int right = i * 2 + 1;
        int minimum = i;

        if (left < z.length && z[left].calcZValue() < z[minimum].calcZValue()) {
            minimum = left;
        } else if (left < z.length && z[left].calcZValue() == z[minimum].calcZValue()) {
            if (z[left].courseNumber < z[minimum].courseNumber) {
                minimum = left;
            }
        }
        if (right < z.length && z[right].calcZValue() < z[minimum].calcZValue()) {
            minimum = right;
        } else if (right < z.length && z[right].calcZValue() == z[minimum].calcZValue()) {
            if (z[right].courseNumber < z[minimum].courseNumber) {
                minimum = right;
            }
        }
        if (minimum != i) {
            swap(z, i, minimum);
            minHeapify(z, minimum);
        }
    }

    private static void swap(Z z[], int i, int j) {
        Z temp = z[i];
        z[i] = z[j];
        z[j] = temp;
    }

    private static class Z {
        int courseNumber;
        int x1; // Second Last
        int x2; // Last
        int noOfStudentsEnrolled;

        public int calcZValue() {
            return noOfStudentsEnrolled * (x1 + x2);
        }

    }

}
