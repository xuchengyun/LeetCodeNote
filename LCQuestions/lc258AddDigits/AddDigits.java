package LCQuestions.lc258AddDigits;

public class AddDigits {
    public int addDigits(int num) {
        if (num <= 9 && num >= 0) return num;
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return addDigits(sum);
    }
    public int addDigits1(int num) {
        return (num - 1) % 9 + 1;
    }

}
