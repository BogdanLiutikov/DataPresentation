package PriorityQueue;

public class Main {
    public static void main(String[] args) {
        PriorityQueue queue = new PriorityQueue();
        queue.insert(new Entity(6));
        queue.insert(new Entity(5));
        queue.insert(new Entity(9));
        queue.insert(new Entity(9));
        queue.insert(new Entity(10));
        queue.insert(new Entity(18));
        queue.insert(new Entity(9));
        queue.insert(new Entity(8));
        queue.insert(new Entity(3));
        System.out.println(queue.delete());
        System.out.println(queue.delete());
        System.out.println(queue.delete());
        System.out.println(queue.delete());
        System.out.println(queue.delete());
        System.out.println(queue.delete());
        System.out.println(queue.delete());
        System.out.println(queue.delete());
        System.out.println(queue.delete());
        System.out.println(queue.delete());
    }
}
