package codemonk.disjointDataStructure.learnings;

/**
 * Created by Nandan Mankad on 05-Jul-17.
 *
 * This is an implementation of Union-Find algorithm for finding Disjoint Set or Connected Components, which implements
 * the idea of root and parent of each node. Though this implementation is an improvement on {@link WeightedDisjointSetUnionFindWithParent}.
 *
 * Here we run the operations of union and find exactly similar to {@link WeightedDisjointSetUnionFindWithParent}. The only difference is
 * while finding the root.
 *
 * While traversing up to find the root, if possible, we assign an element directly to its grandparent rather than parent.
 * This would be a compression in the for the next traversal which would help us on runtime stats.
 *
 * Time Complexity: The Asymptotic time complexity would remain O(Log N) as for {@link WeightedDisjointSetUnionFindWithParent} though
 * it might run faster than it due to path compression.
 */
public class WeightedDisjointSetUnionFindWithParentWithPathCompression {

    public static void main(String[] args) {
        WeightedDisjointSetUnionFindWithParentWithPathCompression wdsufwpwp = new WeightedDisjointSetUnionFindWithParentWithPathCompression();
        int arr[] = new int[10];
        int size[] = new int[10];
        wdsufwpwp.initialize(arr, size);
        wdsufwpwp.union(arr, size, 2, 1);
        wdsufwpwp.union(arr, size,4, 3);
        wdsufwpwp.union(arr, size,8, 4);
        wdsufwpwp.union(arr, size,9, 3);
        wdsufwpwp.union(arr, size,6, 5);
        System.out.println( "Are 9 and 4 connected? " + wdsufwpwp.find(arr, 9, 4));
        System.out.println( "Are 8 and 3 connected? " + wdsufwpwp.find(arr, 8, 3));
        System.out.println( "Are 6 and 4 connected? " + wdsufwpwp.find(arr, 6, 4));
        System.out.println( "Are 0 and 7 connected? " + wdsufwpwp.find(arr, 0, 7));
    }

    public void initialize (int arr[], int size[]) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
            size[i] = 1;
        }
    }

    public int root (int arr[], int a) {
        while (arr[a] != a) {
            arr[a] = arr[arr[a]]; // Path Compression. Assigning each element directly to its "possible" grand parent.
            a = arr[a];
        }
        return a;
    }

    public boolean find (int arr[], int a, int b) {
        if (root(arr, a) == root(arr, b)) {
            return true;
        } else {
            return false;
        }
    }

    public void union (int arr[], int size[], int a, int b) {
        int rootA = root(arr, a);
        int rootB = root(arr, b);

        if (size[rootA] > size[rootB]) {
            arr[rootB] = rootA;
            size[rootA] += size[rootB];
        } else {
            arr[rootA] = rootB;
            size[rootB] += size[rootA];
        }
    }
}