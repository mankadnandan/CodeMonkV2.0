package codemonk.disjointDataStructure.learnings;

/**
 * Created by Nandan Mankad on 01-Jul-17.
 *
 * This is a most basic implementation of Union-Find algorithm for finding Disjoint Set or Connected Components.
 *
 * Time Complexity:
 *
 * find just sees if the two elements has same content, as if they are same they are connected. Hence it takes O(1).
 *
 * union sees for all the elements with content of one element for match, if it matches replaces with the content of other element.
 * In other words it links two subsets with each other. Hence it takes O(N) times to run. Hence performing it on all the elements takes O(N^2)
 *
 * Total Time Complexity: O(N^2)
 *
 */
public class DisjointSetUnionFind {

    public static void main(String[] args) {
        DisjointSetUnionFind dsuf = new DisjointSetUnionFind();
        int arr[] = new int[10];
        dsuf.initialize(arr);
        dsuf.union(arr, 2, 1);
        dsuf.union(arr, 4, 3);
        dsuf.union(arr, 8, 4);
        dsuf.union(arr, 9, 3);
        dsuf.union(arr, 6, 5);
        System.out.println( "Are 9 and 4 connected? " + dsuf.find(arr, 9, 4));
        System.out.println( "Are 8 and 3 connected? " + dsuf.find(arr, 8, 3));
        System.out.println( "Are 6 and 4 connected? " + dsuf.find(arr, 6, 4));
        System.out.println( "Are 0 and 7 connected? " + dsuf.find(arr, 0, 7));
    }

    public void initialize (int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
    }

    public boolean find (int arr[], int a, int b) {
        if (arr[a] == arr[b]) {
            return true;
        } else {
            return false;
        }
    }

    public void union (int arr[], int a, int b) {
        int temp = arr[a];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == temp) {
                arr[i] = arr[b];
            }
        }
    }
}