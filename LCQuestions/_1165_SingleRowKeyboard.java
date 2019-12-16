package LCQuestions;

import java.util.HashMap;
import java.util.Map;

public class _1165_SingleRowKeyboard {

    public int calculateTime(String keyboard, String word) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < keyboard.length(); i++) {
            map.put(keyboard.charAt(i), i);
        }

        int res = 0;
        int preIndex = 0;
        for (int i = 0; i < word.length(); i++) {
            int tmp = map.get(word.charAt(i));
            res += Math.abs(preIndex - tmp);
            preIndex = tmp;
        }
        return res;
    }
}
