package Oracle;

import java.util.Random;
import java.util.function.IntBinaryOperator;

public class RandomInt {

    public static void main(String[] args) {
//        System.out.println(Integer.toBinaryString(Integer.MIN_VALUE));
//        int decimalValue = Integer.parseInt("100000000000000", 2);
//        System.out.println(decimalValue);
        Random rd = new Random();
        System.out.println(rd.nextInt());
    }
}


