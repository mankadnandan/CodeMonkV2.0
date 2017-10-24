package codemonk.disjointDataStructure.learnings;

/**
 * Created by Nandan Mankad on 04-Jul-17.
 *
 * This is an implementation of Union-Find algorithm for finding Disjoint Set or Connected Components, which implements
 * the idea of root and parent of each node.
 *
 * Time Complexity:
 *
 * find works by locating if the roots of both the elements that we are trying to check if connected are same. If they are then they are connected else not.
 *
 * union connects the root of both the elements by traversing through the root of both the elements to be connected and then setting
 * the root of one of them to another.
 *
 * Here since we connect the elements randomly, the traversal to find the root of an element would be as long as all the N elements.
 * Hence both find and union would take O(N) time to run. If this needs to run for all elements it would take O(N^2)
 *
 * Total Time Complexity: O(N^2)
 *
 */
public class DisjointSetUnionFindWithParent {
    public static void main(String[] args) {
        DisjointSetUnionFindWithParent dsufwp = new DisjointSetUnionFindWithParent();
        int arr[] = new int[10];
        dsufwp.initialize(arr);
        dsufwp.union(arr, 2, 1);
        dsufwp.union(arr, 4, 3);
        dsufwp.union(arr, 8, 4);
        dsufwp.union(arr, 9, 3);
        dsufwp.union(arr, 6, 5);
        System.out.println( "Are 9 and 4 connected? " + dsufwp.find(arr, 9, 4));
        System.out.println( "Are 8 and 3 connected? " + dsufwp.find(arr, 8, 3));
        System.out.println( "Are 6 and 4 connected? " + dsufwp.find(arr, 6, 4));
        System.out.println( "Are 0 and 7 connected? " + dsufwp.find(arr, 0, 7));
    }

    public void initialize (int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
    }

    public int root(int arr[], int a) {
        while (arr[a] != a) {
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

    public void union (int arr[], int a, int b) {
        int rootA = root(arr, a);
        int rootB = root(arr, b);
        arr[rootB] = rootA;
    }

}