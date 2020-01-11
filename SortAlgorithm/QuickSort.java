package SortAlgorithm;

public class QuickSort {
    public static void main(String[] args) {
        QuickSort s = new QuickSort();
        int[] arr = new int[]{4, 1, 1, 6, 7, 3};
        s.sort(arr, 0, 5);
        for (int i : arr) {
            System.out.println(i);
        }
    }

    /* This function takes last element as pivot,
   places the pivot element at its correct
   position in sorted array, and places all
   smaller (smaller than pivot) to left of
   pivot and all greater elements to right
   of pivot */
    int partition(int arr[], int low, int high) {
        int pivot = arr[high];
        int smallIndex = low; // index of smaller element
        for (int j = low; j < high; j++) {
            // If current element is smaller than or
            // equal to pivot
            if (arr[j] <= pivot) {
                // swap arr[i] and arr[j]
                int temp = arr[smallIndex];
                arr[smallIndex] = arr[j];
                arr[j] = temp;
                smallIndex++;
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[smallIndex];
        arr[smallIndex] = arr[high];
        arr[high] = temp;

        return smallIndex;
    }

    /* The main function that implements QuickSort()
      arr[] --> Array to be sorted,
      low  --> Starting index,
      high  --> Ending index */
    void sort(int arr[], int low, int high) {
        if (low < high) {
            /* pi is partitioning index, arr[pi] is
              now at right place */
            int pi = partition(arr, low, high);

            // Recursively sort elements before
            // partition and after partition
            sort(arr, low, pi - 1);
            sort(arr, pi + 1, high);
        }
    }
}
