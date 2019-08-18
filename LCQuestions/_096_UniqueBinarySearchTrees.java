package LCQuestions;

public class _096_UniqueBinarySearchTrees {
    /**
     dp
     Input: 3
     Output: 5
     Explanation:
     Given n = 3, there are a total of 5 unique BST's:

     1         3      3      2      1
     \        /     /      / \      \
      3     2     1      1   3      2
     /     /       \                 \
     2   1         2                 3
     n = 3
     root : 1 left : 0 right : 2 f(0) * f(2);
     root : 2 left : 1 right : 0 f(1) * f(1);
     root : 3 left : 2 right " 0 f(2) * f(0)
     f(n) = f(0)*f(n - 1) + f(1) * f(n - 2) + ... + f(n - 2) * f(1) + f(n - 1) * f(0)
     * @param n
     * @return
     */
    public int numTrees(int n) {
        int[] res = new int[n + 1];
        res[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                res[i] += res[j] * res[i - j - 1];
            }
        }
        return res[n];
    }
}
