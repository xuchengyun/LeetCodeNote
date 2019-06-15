package LCQuestions;

/**
 * Similar as
 * 875. Koko Eating Bananas
 * 774. Minimize Max Distance to Gas Station
 */
public class _1014_CapacityToShipPackagesWithinDDays {
    public static void main(String[] args) {
        _1014_CapacityToShipPackagesWithinDDays c = new _1014_CapacityToShipPackagesWithinDDays();
        int[] weights = {1, 2, 3, 1, 1};
        int days = 4;
        c.shipWithinDays2(weights, days);
    }

    // Binary Search
    public int shipWithinDays(int[] weights, int D) {
        int left = 0, right = 0;
        for (int w : weights) {
            left = Math.max(left, w);
            right += w;
        }
        while (left < right) {
            int mid = (left + right) / 2, need = 1, cur = 0;
            for (int w : weights) {
                if (cur + w > mid) {
                    need += 1;
                    cur = 0;
                }
                cur += w;
            }
            if (need > D) left = mid + 1;
            else right = mid;
        }
        return left;
    }

    // Brute force
    public int shipWithinDays1(int[] weights, int D) {
        int low = 0;
        int high = 0;
        for (int w : weights) {
            low = Math.max(low, w);
            high += w;
        }
        for (int i = low; i <= high; i++) {
            if (isValid(i, weights, D)) {
                return i;
            }
        }
        throw new RuntimeException("Cannot find valid bin capacity");
    }

    // BinarySearch
    public int shipWithinDays2(int[] weights, int D) {
        int low = 0;
        int high = 0;
        for (int w : weights) {
            low = Math.max(low, w);
            high += w;
        }

        while (low < high) {
            int mid = low + (high - low) / 2;
            if (isValid(mid, weights, D)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private boolean isValid(int capacity, int[] weights, int D) {
        int cur = 0;
        int dayNeeds = 1;
        for (int w : weights) {
            if (cur + w > capacity) {
                cur = 0;
                dayNeeds++;
            }
            cur += w;
        }
        return dayNeeds <= D;
    }
}
