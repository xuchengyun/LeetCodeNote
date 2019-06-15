package LCQuestions;

public class _189_RotateArray {
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0) return;
        int len = nums.length;
        int[] temp = new int[len];
        for (int i = 0; i < len; i++) {
            temp[(i + k) % len] = nums[i];
        }

        for (int i = 0; i < len; i++) {
            nums[i] = temp[i];
        }
    }

    //TODO reverse array
    //1.reverse whole array
    //2. reverse front part
    //3. reverse back part
    public void rotate2(int[] nums, int k) {

    }
}
