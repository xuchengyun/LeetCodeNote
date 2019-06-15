package LCQuestions;

import java.util.PriorityQueue;

public class _215_KthLargestElementInArray {
    public static void main(String[] args) {
        _215_KthLargestElementInArray k = new _215_KthLargestElementInArray();
        int[] arr = new int[]{3, 2, 1, 5, 6, 4};
        System.out.println(k.findKthLargest1(arr, 2));
    }

    public int findKthLargest(int[] nums, int k) {
        // Priority Queue
        // put comparator into parameters
        PriorityQueue<Integer> pq = new PriorityQueue<>(k);
        for (int num : nums) {
            if (pq.size() < k) {
                pq.add(num);
            } else {
                if (num > pq.peek()) {
                    pq.poll();
                    pq.add(num);
                }
            }
        }
        return pq.peek();
    }

    public int findKthLargest1(int[] nums, int k) {
        int len = nums.length;
        int left = 0, right = len - 1;
        while (left <= right) {
            int index = partition(nums, left, right);
            if (index == k - 1) return nums[index];
            if (index < k - 1) {
                left = index + 1;
            } else {
                right = index - 1;
            }
        }

        throw new RuntimeException("index not found");
    }

    private int partition(int[] nums, int left, int right) {
        int cur = nums[right];
        int index = left;
        for (int i = left; i < right; i++) {
            if (nums[i] >= cur) {
                swap(nums, index, i);
                index++;
            }
        }
        swap(nums, index, right);
        return index;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
