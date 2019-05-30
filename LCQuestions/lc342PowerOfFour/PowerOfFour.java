package LCQuestions.lc342PowerOfFour;

public class PowerOfFour {
    public boolean isPowerOfFour(int num) {
        return num > 0 && (num & 3) != 0;
    }
    public boolean isPowerOfFour1(int num) {
        if (num > 1) {
            while (num % 4 == 0) {
                num /= 4;
            }
        }
        return num == 1;
    }



    public static void main(String[] args) {
        PowerOfFour p = new PowerOfFour();
        p.isPowerOfFour(-2147483648);
    }
}
