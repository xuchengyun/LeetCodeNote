package LCQuestions;

/**
 * Created by xuchengyun on 12/27/19.
 */
public class _278_FirstBadVersion {
    public int firstBadVersion(int n) {
        int left = 0, right = n;
        while (left < right) {
            int mid = (left + right) / 2;
            if (isBadVersion(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    boolean isBadVersion(int version) {
        return true;
    }
}
