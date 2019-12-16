package LCQuestions;

public class _307_RangeSumQuery_Mutable {

    /**
     Binary Index Tree

     https://www.cnblogs.com/xudong-bupt/p/3484080.html
     1.求数组前n项和: sum = a[1] + ... + a[n];
     2.更新数组

     思想:
     1.每个元素是原数组中一个或者多个元素的和

     核心思想: 重新构建数组
     原数组 : a[1], a[2], a[3], a[4], a[5]
     BIT
　　　　    如果数字 i 的二进制表示中末尾有k个连续的0，则e[i]是a数组中2^k个元素的和，则e[i]=a[i-2^k+1]+a[i-2^k+2]+...+a[i-1]+a[i]。

　　　　如：4=100(2)　　e[4]=a[1]+a[2]+a[3]+a[4];

　　　　　　6=110(2)　　e[6]=a[5]+a[6]

　　　　　　7=111(2)　　e[7]=a[7]
     Binary Index Tree 解决前n项的和
     * @param nums
     */
    int[] tree;
    int[] nums;
    int size;

    public _307_RangeSumQuery_Mutable(int[] nums) {
        this.size = nums.length;
        this.tree = new int[size + 1];
        this.nums = new int[size];
        this.nums = nums;
        for (int i = 0; i < size; i++) {
            updateTree(i, nums[i]);
        }
    }

    public void updateTree(int i, int val) {
        i = i + 1;
        while (i <= size) {
            tree[i] += val;
            i += i & (-i); // the last set bit/ Two's complement
        }
    }

    public void update(int i, int val) {
        updateTree(i, val - nums[i]);
        nums[i] = val;
    }

    private int getSum(int i) {
        int sum = 0;
        i = i + 1;
        while (i > 0) {
            sum += tree[i];
            i -= i & (-i);// Another tree, go to the ancestor
        }
        return sum;
    }

    public int sumRange(int i, int j) {
        if (i == 0) return getSum(j);
        return getSum(j) - getSum(i - 1);
    }
}
