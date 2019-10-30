package LCQuestions;

public class _702_SearchinaSortedArrayofUnknownSize {

    public int search(ArrayReader reader, int target) {
        int right = 1;
        while (reader.get(right) < target) {
            right = right << 1;
        }
        int left = right << 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (reader.get(mid) == target) {
                return mid;
            } else if (reader.get(mid) > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    private class ArrayReader {
        public int get(int right) {
            return 9;
        }
    }
}
