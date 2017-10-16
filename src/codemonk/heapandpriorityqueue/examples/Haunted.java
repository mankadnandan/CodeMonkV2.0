package codemonk.heapandpriorityqueue.examples;

import java.util.*;

/**
 *
 * SOLVED SUCCESSFULLY
 *
 * Created by Nandan Mankad on 15-Oct-17.
 *
 * ###################################################################################################################################################################
 * Approach: Here at every iteration we want the details of a ghost (Ghost age which is actually id of ghost and # of times he has won the Ghost of the day title)
 * who has won the title of Ghost of the day MAXIMUM number of times. We can use MAX Heap Data Structure (add and sift up) to get the ghost with maximum number of
 * titles.
 *
 * This requires standard implementation of the compareTo where the logic of comparing two ghosts number of titles and if they are same the ghost ids are taken into
 * consideration.
 *
 * The main catch here is the way you access the ghost on a particular day as we would need the position of the ghost who won the title in the Heap and then sift
 * up the Ghost as required from that position.
 *
 * Finding the position by iterating on the Heap array would be expensive enough for the operation to time out. The trick is to use a MAP ghostIdToHeapPos,
 * to store the ghost id to position, to quickly access the position of the new winner of the title and sift the ghost up as required.
 *
 * Time Complexity: For each of the N days, there would be one winner and hence we might need to sift up one Ghost each day. Sift up might need us to sift from bottom to top of a
 * binary tree. Hence Sift up would take log(N) complexity.
 *
 * Total complexity of N log(N) in total.
 *
 * ###################################################################################################################################################################
 *
 *
 * The king of ghosts is really disappointed when he sees that all the human beings on Planet Earth have stopped fearing the ghost race. He knows the reason for this.
 * The existing ghost race has become really lazy and has stopped visiting Planet Earth to scare the human race. Hence, he decides to encourage the entire ghost race into
 * scaring the humans by holding a competition. The king, however, never visits Planet Earth.

 This competition will go on for N days. Currently, there are a total of M ghosts (apart from the king) existing in the ghost race such that :
 - The youngest ghost is 1 year old.
 - The oldest ghost is M years old.
 - No two ghosts have the same age.
 - The age of each and every ghost is a positive integer.

 On each day of the competition, ghosts have to visit Planet Earth to scare people. At the end of each day, a "Ghost of the Day" title is awarded to the ghost who
 scares the most number of humans on that particular day. However, the king of ghosts believes in consistency. Once this title has been given, the ghost who has won
 the most number of such titles until that particular moment is presented with a "Consistency Trophy". If there are many such ghosts, the oldest among them is
 given the trophy. Note that this "Title Giving" and "Trophy Giving" happens at the end of each day of the competition.

 You will be given the age of the ghost who won the "Ghost of the Day" title on each day of the competition. Your job is to find out the age of the ghost
 who was awarded with the "Consistency Trophy" on each day of the competition.

 Input
 The first line consists of 2 space separated integers N and M. The next line consists of N space separated integers such that the ith integer
 denotes the age of the ghost who was awarded with the "Ghost of the Day" title on the ith day of the competition.

 Output
 Print N lines. The ith line should contain 2 space separated integers such that the first integer denotes the age of the ghost who was awarded with the
 "Consistency Trophy" on the ith day and the second integer denotes the number of "Ghost of the Day" titles won by this ghost until the end of the ith day of the competition.

 Constraints
 1 ≤ N ≤ 105
 1 ≤ M ≤ 109
 *
 */
public class Haunted {
    Map<Integer, Integer> ghostIdToHeapPos = new HashMap<>();
    public static void main(String[] args) {
        Haunted h = new Haunted();
        h.run();
    }

    public void run () {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int M = in.nextInt();
        PriorityQueue<Ghost> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            int todaysWinner = in.nextInt();
            Integer pos = ghostIdToHeapPos.get(todaysWinner);
            if (pos != null) {
                pq.arr.get(pos).noOfGhostOfDayAwards++;
                pq.siftUp(pos);
            } else {
                Ghost g = new Ghost(todaysWinner);
                g.noOfGhostOfDayAwards = 1;
                pq.add(g);
            }
            System.out.println(pq.arr.get(1).getGhostId() + " " + pq.arr.get(1).noOfGhostOfDayAwards);
        }
    }

    class PriorityQueue<T extends Comparable> {

        int currentSize;
        List<T> arr;

        PriorityQueue () {
            currentSize = 0;
            arr = new ArrayList<>();
        }

        public void add(T ele) {
            currentSize++;
            if (currentSize == 1) {
                arr.add(ele);
            }
            arr.add(ele);
            siftUp(currentSize);
        }

        public void siftUp (int i) {
            int parent = i / 2;
            if (parent > 0 && arr.get(parent).compareTo(arr.get(i)) < 1) {
                while (parent > 0 && arr.get(parent).compareTo(arr.get(i)) < 1) {
                    swap(arr, parent, i);
                    i = parent;
                    parent = parent / 2;
                }
            } else {
                ghostIdToHeapPos.put(((Ghost)arr.get(i)).ghostId, i);
            }
        }

        public void swap(List<T> arr, int i, int j) {
            T temp = arr.get(i);
            arr.set(i, arr.get(j));
            arr.set(j, temp);
            ghostIdToHeapPos.put(((Ghost)arr.get(j)).ghostId, j);
            ghostIdToHeapPos.put(((Ghost)arr.get(i)).ghostId, i);

        }

        public T getMax () {
            return arr.get(1);
        }

    }

    class Ghost implements Comparable<Ghost>{

        int ghostId;
        int noOfGhostOfDayAwards;

        Ghost (int ghostId) {
            this.ghostId = ghostId;
        }

        public int getGhostId() {
            return ghostId;
        }

        public void setGhostId(int ghostId) {
            this.ghostId = ghostId;
        }

        public int getNoOfGhostOfDayAwards() {
            return noOfGhostOfDayAwards;
        }

        public void setNoOfGhostOfDayAwards(int noOfGhostOfDayAwards) {
            this.noOfGhostOfDayAwards = noOfGhostOfDayAwards;
        }

        @Override
        public int compareTo(Ghost o) {
            if (this.noOfGhostOfDayAwards > o.noOfGhostOfDayAwards) {
                return 1;
            } else if (this.noOfGhostOfDayAwards < o.noOfGhostOfDayAwards){
                return -1;
            } else { // Equal noOfGhostOfDayAwards
                if (this.ghostId > o.ghostId) {
                    return 1;
                } else {
                    return -1;
                }
            }

        }
    }
}