package Queue.CyclicList;

import Queue.ATD_Queue;

import java.util.NoSuchElementException;

public class Queue implements ATD_Queue {

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

    private Node tail;

    public Queue() {
        this.tail = null;
    }

    @Override
    public void enqueue(char x) {
        if (tail == null) {
            tail = new Node(x);
            tail.next = tail;
        } else {
            Node newNode = new Node(x, tail.next);
            tail.next = newNode;
            tail = newNode;
        }
    }

    @Override
    public char dequeue() {
        if (empty())
            throw new NoSuchElementException("Empty");

        char c;
        if (tail == tail.next) {
            c = tail.c;
            tail = null;
            return c;
        }
        c = tail.next.c;
        tail.next = tail.next.next;
        return c;
    }

    @Override
    public char front() {
        if (empty())
            throw new NoSuchElementException("Empty");

        if (tail == tail.next)
            return tail.c;

        return tail.next.c;
    }

    @Override
    public void makeNull() {
        Node cur = tail;
        while (cur.next != tail) {
            cur = cur.next;
        }
        tail = null;
    }

    @Override
    public boolean full() {
        return false;
    }

    @Override
    public boolean empty() {
        return tail == null;
    }
}
