package Queue.CyclicArray;

import Queue.ATD_Queue;

import java.util.NoSuchElementException;

public class Queue implements ATD_Queue {

    private int front;
    private int rear;
    private static final int SIZE = 6;
    private char[] queue;

    public Queue() {
        queue = new char[SIZE];
        front = 0;
        rear = SIZE - 1;
    }

    @Override
    public void enqueue(char x) {
        rear = nextPos(rear);
        queue[rear] = x;
    }

    @Override
    public char dequeue() {
        if (empty())
            throw new NoSuchElementException("Empty");

        char m = queue[front];
        front = nextPos(front);
        return m;
    }

    @Override
    public char front() {
        if (empty())
            throw new NoSuchElementException();

        return queue[front];
    }

    @Override
    public void makeNull() {
        while (!empty())
            front = nextPos(front);
    }

    @Override
    public boolean full() {
        return nextPos(nextPos(rear)) == front;
    }

    @Override
    public boolean empty() {
        return nextPos(rear) == front;
    }

    private int nextPos(int pos) {
        return (pos + 1) % SIZE;
    }
}
