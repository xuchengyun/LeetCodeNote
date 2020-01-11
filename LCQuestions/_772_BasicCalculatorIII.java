package LCQuestions;

import java.util.*;

public class _772_BasicCalculatorIII {
    public int calculate(String s) {
        s = s.replaceAll(" ", "");
        if (s.length() == 0) return 0;
        Stack<Integer> stack = new Stack<>();
        char sign = '+';
        int res = 0, pre = 0, i = 0;
        while (i < s.length()) {
            char ch = s.charAt(i);
            //consecutive digits as a number, save in `pre`
            if (Character.isDigit(ch)) {
                pre = pre * 10+(ch - '0');
            }
            //recursively calculate results in parentheses
            if (ch == '(') {
                int j = findClosing(s.substring(i));
                pre = calculate(s.substring(i+1, i+j));
                i += j;
            }
            //for new signs, calculate with existing number/sign, then update number/sign
            if (i == s.length()-1 || !Character.isDigit(ch)) {
                switch (sign) {
                    case '+':
                        stack.push(pre); break;
                    case '-':
                        stack.push(-pre); break;
                    case '*':
                        stack.push(stack.pop()*pre); break;
                    case '/':
                        stack.push(stack.pop()/pre); break;
                }
                pre = 0;
                sign = ch;
            }
            i++;
        }
        while (!stack.isEmpty()) res += stack.pop();
        return res;
    }

    //Eliminate all "()" pairs, calculate the result in between and save in `pre`
    private int findClosing(String s) {
        int level = 0, i = 0;
        for (i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') level++;
            else if (s.charAt(i) == ')') {
                level--;
                if (level == 0) break;
            }
        }
        return i;
    }

    public int calculate1(String s) {
        List output = new ArrayList<>();
        Stack<Character> stack = new Stack<>();
        s = s.replaceAll(" ", "");
        if (s.length() == 0) {
            return 0;
        }
        s = s.replace(" ", "");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ((c == '+' || c == '-') && (i == 0 || s.charAt(i - 1) == '(')) {
                sb.append(0);
            }
            sb.append(c);
        }
        s = sb.toString();
        int n = s.length();
        int i = 0;
        int num = 0;
        while (i < n) {
            while (i < n && s.charAt(i) <= '9' && s.charAt(i) >= '0') {
                num = num * 10 + s.charAt(i) - '0';
                i++;
                if (i >= n || s.charAt(i) > '9' || s.charAt(i) < '0') {
                    output.add(num);
                    num = 0;
                }
            }
            if (i == n) {
                break;
            }
            char c = s.charAt(i);
            if (c == '(') {
                stack.push('(');
            } else if (c == ')') {
                while (!stack.isEmpty()) {
                    char top = stack.pop();
                    if (top == '(') {
                        break;
                    }
                    output.add(top);
                }
            } else {
                while (!stack.isEmpty()) {
                    char top = stack.peek();
                    if (top != '(' && compare(c, top) <= 0) {
                        output.add(stack.pop());
                    } else {
                        break;
                    }
                }
                stack.push(c);
            }
            i++;
        }
        while (!stack.empty()) {
            output.add(stack.pop());
        }
        return evalRPN(output);
    }

    public int calculate2(String s) {
        s = s.replaceAll(" ", "");
        int n = s.length();
        Stack<Integer> operand = new Stack<>();
        Stack<Character> operator = new Stack<>();
        int i = 0;
        Map<Character, Integer> priority = new HashMap<>();
        priority.put('+', 1);
        priority.put('-', 1);
        priority.put('/', 2);
        priority.put('*', 2);
        priority.put('(', 0);
        System.out.println(String.format("Original String: %s", s));
        while (i < n) {
            char c = s.charAt(i);
            System.out.println("Processing character:" + c);
            if (c <= '9' && c >= '0') {
                System.out.println("Character:" + c + " is digit");
                int num = s.charAt(i) - '0';
                while (i + 1 < n && Character.isDigit(s.charAt(i + 1))) {
                    num = num * 10 + s.charAt(i + 1) - '0';
                    i++;
                }
                System.out.println("final number is: " + num);
                operand.push(num);
            } else {
                if (c == '(') {
                    operator.push('(');
                } else if (c == ')') {
                    while (!operator.isEmpty() && operator.peek() != '(') {
                        char op = operator.pop();
                        int b = operand.pop();
                        int a = operand.pop();
                        operand.push(operate(a, b, op));
                    }
                    if (!operator.isEmpty() && operator.peek() == '(') {
                        operator.pop();
                    }
                } else {
                    while (!operator.isEmpty() && comparep(c, operator.peek(), priority) <= 0) {
                        int b = operand.pop();
                        int a = operand.pop();
                        operand.push(operate(a, b, operator.pop()));
                    }
                    operator.push(c);
                }
            }
            i++;
            printStack(operand, operator);
            System.out.println();
        }

        while (!operator.isEmpty()) {
            int b = operand.pop();
            int a = operand.pop();
            operand.push(operate(a, b, operator.pop()));
        }
        return operand.pop();
    }
    //(2+6*3+5-(3*14/7+2)*5)+3
    private void printStack(Stack<Integer> operand, Stack<Character> operator) {
        System.out.println();
        System.out.println("operand: ");
        for (int i : operand) {
            System.out.print(i);
            System.out.print(" ");
        }
        System.out.println();
        System.out.println("operator: ");
        for (char i : operator) {
            System.out.print("" + i);
            System.out.print(" ");
        }
    }

    private int comparep(char a, char b, Map<Character, Integer> map) {
        return map.get(a) - map.get(b);
    }

    private int operate(int a, int b, Character op) {
        if (op == '+') {
            return a + b;
        }
        if (op == '-') {
            return a - b;
        }
        if (op == '*') {
            return a * b;
        }
        return a / b;
    }



    private int evalRPN(List tokens) {
        if (tokens == null) throw new RuntimeException("");
        Stack<Integer> s = new Stack<>();
        for (int i = 0; i < tokens.size(); i++) {
            if (tokens.get(i) instanceof Integer) {
                s.push((Integer) tokens.get(i));
            } else if (tokens.get(i) instanceof Character) {
                char op = (char) tokens.get(i);
                if (op == '+') {
                    s.push(s.pop() + s.pop());
                } else if (op == '-') {
                    int a = s.pop();
                    int b = s.pop();
                    s.push(b - a);
                } else if (op == '*') {
                    s.push(s.pop() * s.pop());
                } else if (op == '/') {
                    int a = s.pop();
                    int b = s.pop();
                    s.push(b / a);
                }
            }
        }
        return s.pop();
    }

    private int compare(char c1, char c2) {
        Map<Character, Integer> priority = new HashMap<>();
        priority.put('+', 1);
        priority.put('-', 1);
        priority.put('*', 2);
        priority.put('/', 2);
        return priority.get(c1) - priority.get(c2);
    }

    public int calculate3(String s) {
        s = s.replaceAll(" ", "");
        char op = '+';
        int n = s.length();
        int curRes = 0;
        int total = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(s.charAt(i))) {
                int num = s.charAt(i) - '0';
                while (i + 1 < n && Character.isDigit(s.charAt(i + 1))) {
                    num = num * 10 + s.charAt(i + 1) - '0';
                    i++;
                }
                if (op == '+') {
                    curRes += num;
                } else if (op == '-') {
                    curRes -= num;
                } else if (op == '*') {
                    curRes *= num;
                } else {
                    curRes /= num;
                }
            } else {
                if (c == '(') {
                    int cnt = 0;
                    int j = i;
                    for (;i < n; i++) {
                        if (s.charAt(i) == '(') {
                            cnt++;
                        } else if (s.charAt(i) == ')') {
                            cnt--;
                        }
                        if (cnt == 0){
                            break;
                        }
                    }
                    int num = calculate3(s.substring(j + 1, i));
                    if (op == '+') {
                        curRes += num;
                    } else if (op == '-') {
                        curRes -= num;
                    } else if (op == '*') {
                        curRes *= num;
                    } else {
                        curRes /= num;
                    }
                } else if (c == '+' || c == '-') {
                    total += curRes;
                    curRes = 0;
                }
                if (c == '+' || c =='-' || c == '*' || c == '/') {
                    op = c;
                }
            }

        }
        return total + curRes;
    }

    public static void main(String[] args) {
        String test1 = "11 + 22*33 + (44 * 55 + 66) * 77";
        String test2 = "-1+4*3/3/3";
        String test3 = "1 + 1";
        String test4 = "(2+6* 3+5- (3*14/7+2)*5)+3";
        String test5 = "2*(5+5*2)/3+(6/2+8)";
        String test6 = "(2+6* 3+5- (3*14/7+2)*5)+3";
        String test7 = "3 * 14 / 7 + 2";
        _772_BasicCalculatorIII obj = new _772_BasicCalculatorIII();
        System.out.println(obj.calculate3(test7));
    }
}
