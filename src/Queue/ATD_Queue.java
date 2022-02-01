package Queue;

public interface ATD_Queue {

    public void enqueue(char x);
    public char dequeue();
    public char front();
    public void makeNull();
    public boolean full();
    public boolean empty();
}
