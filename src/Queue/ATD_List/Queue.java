package Queue.ATD_List;

import Queue.ATD_Queue;
import Queue.doublyLinkedListChar.List;

import java.util.NoSuchElementException;

public class Queue implements ATD_Queue {

    List queue = new List();

    @Override
    public void enqueue(char x) {
        queue.Insert(queue.End(), x);
    }

    @Override
    public char dequeue() {
        if (empty())
            throw new NoSuchElementException();

        char c = queue.Retrieve(queue.First());
        queue.Delete(queue.First());
        return c;
    }

    @Override
    public char front() {
        if (empty())
            throw new NoSuchElementException();

        return queue.Retrieve(queue.First());
    }

    @Override
    public void makeNull() {
        queue.MakeNull();
    }

    @Override
    public boolean full() {
        return false;
    }

    @Override
    public boolean empty() {
        return queue.First().getNode() == null;
    }
}
