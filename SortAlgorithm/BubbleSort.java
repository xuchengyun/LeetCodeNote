package SortAlgorithm;

public class BubbleSort {
    public static void main(String[] args) {
        BubbleSort s = new BubbleSort();
        int[] arr = new int[]{4, 1, 6, 7, 3};
        s.sort(arr);
        for (int i : arr) {
            System.out.println(i);
        }
    }

    // swap adjacent element if they are in wrong order
    private void sort(int[] arr) {
        for (int j = 0; j < arr.length; j++) {
            for (int i = 0; i < arr.length - j - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                }
            }
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
