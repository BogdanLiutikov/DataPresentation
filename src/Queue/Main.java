package Queue;

import Queue.CyclicArray.Queue;

public class Main {
    public static void main(String[] args) {

        Queue queue = new Queue();

        Message message = new Message("Bogdan Bogdan Bogdan");

        char[] chars = message.getText();
        for (int i = 0; i < chars.length; i++) {
            if (queue.full()) {
                while (!queue.empty())
                    System.out.println(queue.dequeue());
                System.out.println();
            }
            queue.enqueue(chars[i]);
        }
        while (!queue.empty())
            System.out.print(queue.dequeue());

    }
}
