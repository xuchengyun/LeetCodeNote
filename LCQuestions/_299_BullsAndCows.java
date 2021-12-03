package LCQuestions;

import java.util.HashMap;
import java.util.Map;

public class _299_BullsAndCows {
    // 1 pass
    public String getHint(String secret, String guess) {
        int bulls = 0;
        int cows = 0;
        int[] numbers = new int[10];
        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) bulls++;
            else {
                if (numbers[secret.charAt(i)-'0']++ < 0) cows++;
                if (numbers[guess.charAt(i)-'0']-- > 0) cows++;
            }
        }
        return bulls + "A" + cows + "B";

    }

    // 2 pass
    public String getHint1(String secret, String guess) {
        if (secret == null || guess == null ||
                secret.length() != guess.length()) {
            throw new IllegalArgumentException("secret and guess are not valid input");
        }

        Map<Character, Integer> countMap = new HashMap<Character, Integer>();
        // Iterate each character and add count to map
        for (char c: secret.toCharArray()) {
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
        }

        int len = secret.length();
        int bulls = 0, cows = 0;
        for (int i = 0; i < len; i++) {
            // Get current guess character
            char c = guess.charAt(i);
            if (countMap.containsKey(c)) {
                // find bull
                if (secret.charAt(i) == c) {
                    bulls++;
                    // if cow from secret used up
                    if (countMap.get(c) <= 0) {
                        cows--;
                    }
                } else {
                    // find cows and remove char from hashMap
                    if (countMap.get(c) > 0) {
                        cows++;
                    }
                }

                countMap.put(c, countMap.get(c) - 1);
            }
        }
        return bulls + "A" + cows + "B";

    }

}
