package ru.geekbrains.valerych.lesson3;

import ru.geekbrains.valerych.lesson3.mystack.Stack;
import ru.geekbrains.valerych.lesson3.mystack.StackImpl;

public class StringReverser {
    public static void main(String[] args) {
        reverseString("А роза упала на лапу Азора!");
    }

    private static void reverseString(String string) {
        Stack<Character> stack = new StackImpl<>(string.length());
        char[] characters = string.toCharArray();
        for (char ch: characters) {
            stack.push(ch);
        }

        while (!stack.isEmpty()){
            System.out.print(stack.pop());
        }
    }
}
