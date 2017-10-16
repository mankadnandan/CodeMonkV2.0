package codemonk.heapandpriorityqueue.learnings;

/**
 * Created by Nandan Mankad on 26-Jun-17.
 */
public class MaxHeap {
    static int length = 0;
    public static void main(String[] args) {
        int arr[] = {1, 4, 3, 7, 8, 9, 10};
        length = arr.length;
        buildMaxHeap(arr);
        System.out.println(extractMaximum(arr));
        System.out.println(extractMaximum(arr));
        System.out.println(extractMaximum(arr));
        System.out.println(extractMaximum(arr));
        System.out.println(extractMaximum(arr));
        System.out.println(extractMaximum(arr));
        System.out.println(extractMaximum(arr));
        System.out.println(extractMaximum(arr));

        //heapSort(arr);

        /*for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }*/

    }

    public static void maxHeapify(int arr[], int i, int N) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int largest = i;

        if (left < N && arr[left] > arr[i]) {
            largest = left;
        }

        if (right < N && arr[right] > arr[largest]) {
            largest = right;
        }

        if (i != largest) {
            swap(i, largest, arr);
            maxHeapify(arr, largest, N);
        }

    }

    public static void buildMaxHeap (int arr[]) {

        int N = arr.length;

        for (int i = N / 2 - 1; i >= 0; i--) {
            maxHeapify(arr, i, N);
        }
    }

    private static void swap (int i, int j, int arr[]) {
        int temp;
        temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void heapSort(int arr[]) {
        buildMaxHeap(arr);
        int heapSize = arr.length;
        for (int i = arr.length-1; i >= 1; i--) {
            swap(0, i, arr);
            heapSize = heapSize - 1;
            maxHeapify(arr, 0, heapSize);
        }
    }

    public static int getMaximum(int arr[]) {
        return arr[0];
    }

    public static int extractMaximum(int arr[]) {
        int result = arr[0];
        if (length > 0) {
            arr[0] = arr[length - 1];
            length = length - 1;
            maxHeapify(arr, 0, length);

        } else {
            result = -1;
            System.out.println("No elements in priority queue");
        }
        return result;
    }
}