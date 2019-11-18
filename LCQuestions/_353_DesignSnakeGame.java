package LCQuestions;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class _353_DesignSnakeGame {

    class Node {
        int x;
        int y;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return x * 31 + y;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Node) {
                Node c = (Node) obj;
                return c.y == this.y && c.x == this.x;
            }
            return false;
        }
    }

    Deque<Node> snake;
    Set<Node> set;
    int[][] food;
    int width;
    int height;
    int foodIndex;
    int score;
    /** Initialize your data structure here.
     @param width - screen width
     @param height - screen height
     @param food - A list of food positions
     E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
    public _353_DesignSnakeGame(int width, int height, int[][] food) {
        this.food = food;
        snake = new ArrayDeque<>();
        snake.offerLast(new Node(0, 0));
        set = new HashSet<>();
        set.add(new Node(0, 0));
        this.width = width;
        this.height = height;
        foodIndex = 0;
        score = 0;
    }

    /** Moves the snake.
     @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
     @return The game's score after the move. Return -1 if game over.
     Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
        Node curHead = snake.peekLast();
        int x = curHead.x;
        int y = curHead.y;
        switch (direction) {
            case "U" : {
                x--;
                break;
            }
            case "L" : {
                y--;
                break;
            }
            case "R" : {
                y++;
                break;
            }
            case "D" : {
                x++;
                break;
            }
        }
        Node head = new Node(x, y);
        set.remove(snake.peekFirst());
        if (x < 0 || x >= height || y < 0 || y >= width || set.contains(head)) {
            return -1;
        }
        snake.offerLast(head);
        set.add(head);
        if (foodIndex < food.length && x == food[foodIndex][0] && y == food[foodIndex][1]) {
            set.add(snake.peekFirst());
            foodIndex++;
            return ++score;
        } else {
            snake.pollFirst();
            return score;
        }
    }

    public static void main(String[] args) {
        _353_DesignSnakeGame obj = new _353_DesignSnakeGame(3, 3, new int[][]{
                {2, 0}, {0, 0}, {0, 2}, {2, 2}
        });

        obj.move("D");
        obj.move("D");
        obj.move("R");
        obj.move("U");
        obj.move("U");
        obj.move("L");
        obj.move("D");
        obj.move("R");
        obj.move("R");
        obj.move("U");
        obj.move("L");
        obj.move("D");




    }
}
