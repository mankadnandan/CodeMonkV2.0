package codemonk.disjointDataStructure.learnings;

/**
 * Created by Nandan Mankad on 04-Jul-17.
 *
 * This is an implementation of Union-Find algorithm for finding Disjoint Set or Connected Components, which implements
 * the idea of root and parent of each node. Though this implementation is an improvement on {@link DisjointSetUnionFindWithParent}.
 *
 * Here we keep track of the size of each sub-component through an array.
 *
 * We initialize the size of each sub-component to 1. As and when we start doing union on two component, while linking one component to another,
 * we make sure that we always link the smaller sub-component to the larger one rather than the other way round.
 *
 * This way we make sure that the tree that we get is a "sort" of a balanced tree and finding the root of the element while performing
 * union or find operations WONT require O(N) everytime as the tree is balanced.
 *
 * Time Complexity: Maintaining a balance tree, will reduce complexity of the union-find function from N to log2N.
 *
 */
public class WeightedDisjointSetUnionFindWithParent {
    public static void main(String[] args) {
        WeightedDisjointSetUnionFindWithParent wdsufwp = new WeightedDisjointSetUnionFindWithParent();
        int arr[] = new int[10];
        int size[] = new int[10];
        wdsufwp.initialize(arr, size);
        wdsufwp.union(arr, size, 2, 1);
        wdsufwp.union(arr, size,4, 3);
        wdsufwp.union(arr, size,8, 4);
        wdsufwp.union(arr, size,9, 3);
        wdsufwp.union(arr, size,6, 5);
        System.out.println( "Are 9 and 4 connected? " + wdsufwp.find(arr, 9, 4));
        System.out.println( "Are 8 and 3 connected? " + wdsufwp.find(arr, 8, 3));
        System.out.println( "Are 6 and 4 connected? " + wdsufwp.find(arr, 6, 4));
        System.out.println( "Are 0 and 7 connected? " + wdsufwp.find(arr, 0, 7));
    }

    public void initialize (int arr[], int size[]) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
            size[i] = 1;
        }
    }

    public int root (int arr[], int a) {
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
