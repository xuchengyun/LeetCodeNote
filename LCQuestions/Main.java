package LCQuestions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    // recursion
    public int countVowelPermutation(int n) {
        int[] count = new int[5];
        Arrays.fill(count, 1);
        long number = (long)(Math.pow(10, 9) + 7);

        while (n > 1) {
            n--;
            int temp0 = count[1] + count[2] + count[4];
            int temp1 = count[0] + count[2];
            int temp2 = count[1] + count[3];
            int temp3 = count[2];
            int temp4 = count[2] + count[3];
            count[0] = temp0;
            count[1] = temp1;
            count[2] = temp2;
            count[3] = temp3;
            count[4] = temp4;
        }
        int sum = 0;
        for(int i : count) {
            sum += i;
        }
        return sum;
    }

    public static void main(String[] args) {
        Main obj = new Main();
        int[][] gold = new int[][]{{0, 6, 0}, {5, 8, 7}, {0, 9, 0}};
        obj.countVowelPermutation(144);
    }

}
