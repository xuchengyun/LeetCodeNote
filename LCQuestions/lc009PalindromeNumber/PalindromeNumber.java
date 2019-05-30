package LCQuestions.lc009PalindromeNumber;

public class PalindromeNumber {
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        int res = 0;
        int curNum = x;
        while (curNum > 0) {
            res = res * 10 + curNum % 10;
            curNum /= 10;
        }
        return x == res;
    }

    public static void main(String[] args) {
        int a = 121;
        PalindromeNumber p = new PalindromeNumber();
        p.isPalindrome(a);
    }
}
