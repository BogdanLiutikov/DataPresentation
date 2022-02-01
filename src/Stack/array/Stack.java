package Stack.array;

import Stack.ATD_Stack;

import java.util.NoSuchElementException;

public class Stack implements ATD_Stack {

    private char[] stack;
    private static final int SIZE = 6;
    private int top;

    public Stack() {
        stack = new char[SIZE];
        top = -1;
    }

    @Override
    public void push(char c) {
        if (full())
            return;
        stack[++top] = c;
    }

    @Override
    public char pop() {
        if (empty())
            throw new NoSuchElementException();
        return stack[top--];
    }

    @Override
    public char top() {
        if (empty())
            throw new NoSuchElementException();
        return stack[top];
    }

    @Override
    public void makeNull() {
        top = -1;
    }

    @Override
    public boolean full() {
        return top == SIZE - 1;
    }

    @Override
    public boolean empty() {
        return top == -1;
    }
}
