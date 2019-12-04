package Microsoft.cardshuffle;

import java.util.Arrays;
import java.util.Random;

public class Card {
    Random rd = new Random();
    public int[] shuffle(int[] cards) {
        for (int i = 0; i < cards.length; i++) {
            int remain = cards.length - i;
            int rdIdx = rd.nextInt(remain - 1);
            int tmp = cards[i];
            cards[i] = cards[i + rdIdx];
            cards[i + rdIdx] = tmp;
        }
        return cards;
    }
}
