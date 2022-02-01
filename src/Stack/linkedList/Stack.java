package Stack.linkedList;

import Stack.ATD_Stack;

import java.util.NoSuchElementException;

public class Stack implements ATD_Stack {

    private static class Node {
        private char c;
        private Node next;

        public Node(char c, Node next) {
            this.c = c;
            this.next = next;
        }

        public Node(char c) {
            this(c, null);
        }
    }

    private Node head;

    public Stack() {
        head = null;
    }

    @Override
    public void push(char c) {
        if (head == null)
            head = new Node(c);
        else
            head = new Node(c, head);
    }

    @Override
    public char pop() {
        if (empty())
            throw new NoSuchElementException();
        char c = head.c;
        head = head.next;
        return c;
    }

    @Override
    public char top() {
        if (empty())
            throw new NoSuchElementException();
        return head.c;
    }

    @Override
    public void makeNull() {
        head = null;
    }

    @Override
    public boolean full() {
        return false;
    }

    @Override
    public boolean empty() {
        return head == null;
    }
}
