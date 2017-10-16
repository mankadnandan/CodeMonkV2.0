package codemonk.heapandpriorityqueue.examples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * SOLVED SUCCESSFULLY
 *
 * Created by Nandan Mankad on 06-Oct-17.
 *
 * ###################################################################################################################################################################
 * Approach:
 *
 * Finding top 5 trending topics based on Post share, like, comment etc. We can use heap here to extract maximum element one by one to get the top 5 trending topics.
 * Here since the maximum element is decided based on Z-Score which is a combination based on different score, we implemented a priority queue which is comparable.
 * The compareTo method, is the place where logic resides to decide the comparision between two elements.
 *
 * This problem has a proper implementation of priority queue based on a template, where our template's concrete implementation is Topic.
 *
 * Since we know the Z-Score upfront we first add all the elements in the array first and then run Build Max Heap once on it.
 *
 * Time Complexity:
 *
 * Build Max Heap runs in O(N). After building we extractMinimum just 5 times i.e. 5 * log (N).
 *
 * Total complexity O(N).
 *
 * ###################################################################################################################################################################
 *
 * Roy is trying to develop a widget that shows Trending Topics (similar to Facebook) on the home page of HackerEarth Academy.
 He has gathered a list of N Topics (their IDs) and their popularity score (say z-score) from the database. Now z-score change everyday according to the following rules:

 When a topic is mentioned in a 'Post', its z-score is increased by 50.
 A 'Like' on such a Post, increases the z-score by 5.
 A 'Comment' increases z-score by 10.
 A 'Share' causes an increment of 20.
 Now the Trending Topics are decided according to the change in z-score. One with the highest increment comes on top and list follows.
 Roy seeks your help to write an algorithm to find the top 5 Trending Topics.
 If change in z-score for any two topics is same, then rank them according to their ID (one with higher ID gets priority). It is guaranteed that IDs will be unique.

 Input format:
 First line contains integer N
 N lines follow
 Each contains 6 space separated numbers representing Topic ID, current z-score - Z, Posts - P, Likes - L, Comments - C, Shares - S

 Output format:
 Print top 5 Topics each in a new line.
 Each line should contain two space separated integers, Topic ID and new z-score of the topic.

 Constraints:
 1 ≤ N ≤ 106
 1 ≤ ID ≤ 109
 0 ≤ Z, P, L, C, S ≤ 109
 *
 */
public class RoyAndTrendingTopics {
    public static void main(String[] args) throws IOException {
        RoyAndTrendingTopics main = new RoyAndTrendingTopics();
        main.run();
    }

    private void run() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> arr1 = Arrays.asList(reader.readLine().split(" ")).stream().map(st -> Integer.parseInt(st)).collect(Collectors.toList());
        int N = arr1.get(0);
        PriorityQueue<Topic> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            arr1 = Arrays.asList(reader.readLine().split(" ")).stream().map(st -> Integer.parseInt(st)).collect(Collectors.toList());
            Topic topic = new Topic(arr1.get(0), arr1.get(1), arr1.get(2), arr1.get(3), arr1.get(4), arr1.get(5));
            pq.add(topic);
        }
        pq.buildMaxHeap(N);
        for (int i = 0; i < 5; i++) {
            Topic t = pq.extractMaximum();
            System.out.println(t.getId() + " " + t.getNewZScore());
        }
    }

    private class PriorityQueue<T extends Comparable> {

        List<T> arr;

        int currentSize;

        PriorityQueue() {
            arr = new ArrayList<>();
            currentSize = 0;
        }

        public void add (T t) {
            currentSize++;
            if (currentSize == 1) {
                arr.add(t);
            }
            arr.add(t);
        }

        private void buildMaxHeap(int N) {
            for (int i = N / 2; i >= 1; i--) {
                maxHeapify(i, N);
            }
        }
        private void maxHeapify(int i, int N) {
            int left = 2 * i;
            int right = 2 * i + 1;
            int largest = 0;

            if (left <= N && arr.get(left).compareTo(arr.get(i)) > 0) {
                largest = left;
            } else {
                largest = i;
            }

            if (right <= N && arr.get(right).compareTo(arr.get(largest)) > 0) {
                largest = right;
            }

            if (largest != i) {
                swap(i, largest);
                maxHeapify(largest, N);
            }
        }

        private void swap (int i, int j) {
            T temp = arr.get(i);
            arr.set(i ,arr.get(j));
            arr.set(j ,temp);
        }

        public T extractMaximum() {
            T result = arr.get(1);
            arr.set(1, arr.get(currentSize));
            currentSize--;
            maxHeapify(1, currentSize);
            return result;
        }
    }

    private class Topic implements Comparable<Topic> {

        long id;
        long zScore;
        long posts;
        long likes;
        long comments;
        long shares;
        long newZScore;
        long zScoreChange;

        Topic(long id, long zScore, long posts, long likes, long comments, long shares) {
            this.id = id;
            this.zScore = zScore;
            this.posts = posts;
            this.likes = likes;
            this.comments = comments;
            this.shares = shares;
            this.newZScore = computeNewZScore(posts, likes, comments, shares);
            this.zScoreChange = this.newZScore - this.zScore;
        }

        private long computeNewZScore(long posts, long likes, long comments, long shares) {
            return posts * 50 + likes * 5 + comments * 10 + shares * 20;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public long getzScore() {
            return zScore;
        }

        public void setzScore(long zScore) {
            this.zScore = zScore;
        }

        public long getPosts() {
            return posts;
        }

        public void setPosts(long posts) {
            this.posts = posts;
        }

        public long getLikes() {
            return likes;
        }

        public void setLikes(long likes) {
            this.likes = likes;
        }

        public long getComments() {
            return comments;
        }

        public void setComments(long comments) {
            this.comments = comments;
        }

        public long getShares() {
            return shares;
        }

        public void setShares(long shares) {
            this.shares = shares;
        }

        public long getNewZScore() {
            return newZScore;
        }

        public void setNewZScore(long newZScore) {
            this.newZScore = newZScore;
        }

        public long getzScoreChange() {
            return zScoreChange;
        }

        public void setzScoreChange(long zScoreChange) {
            this.zScoreChange = zScoreChange;
        }

        @Override
        public int compareTo(Topic o) {
            if (this.zScoreChange != o.zScoreChange) {
                if (this.zScoreChange > o.zScoreChange) {
                    return 1;
                } else {
                    return -1;
                }
            } else {
                if (this.id > o.id) {
                    return 1;
                } else return -1;
            }

        }
    }
}
