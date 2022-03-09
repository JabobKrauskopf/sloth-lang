package me.jakobkraus.slothlang.stack;

import java.util.ArrayList;
import java.util.List;

public class Stack {

    private final List<Integer> stack = new ArrayList<>();

    public int pop() {
        return this.stack.remove(stack.size() - 1);
    }

    public int[] pop(int n) {
        int[] returnArray = new int[n];
        for (int i = 0; i < n; i++) {
            returnArray[n - 1 - i] = this.pop();
        }
        return returnArray;
    }

    public void push(int n) {
        this.stack.add(n);
    }

    public void push(int[] n) {
        for (int i : n) {
            this.stack.add(i);
        }
    }

    public void print() {
        for (int i = 0; i < this.stack.size(); i++) {
            System.out.println("The " + i + "th Element in the stack is " + this.stack.get(i));
        }
    }
}
