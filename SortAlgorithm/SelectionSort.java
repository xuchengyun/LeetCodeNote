package SortAlgorithm;

public class SelectionSort {
    public static void main(String[] args) {
        SelectionSort s = new SelectionSort();
        int[] arr = new int[]{4, 1, 6, 7, 3};
        s.sort(arr);
        for (int i : arr) {
            System.out.println(i);
        }
    }

    // 1.repeatedly find minimum in an array
    // 2.replace with the head element in the array
    void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
        }
    }

    void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
