package PriorityQueue;

import java.util.NoSuchElementException;

public class PriorityQueue implements ADT_PriorityQueue {


    //первый свободный
    private int free = 0;
    private static final int SIZE = 10;
    Entity[] queue = new Entity[SIZE];

    @Override
    public void insert(Entity entity) {
        if (free == SIZE)
            return;
        queue[free] = entity;
        shiftUP(free);
        free++;
    }


    private void shiftUP(int i) {
        while (queue[i].priority < queue[(i - 1) / 2].priority) {
            swap(i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    private void shiftDown(int i) {
        while (2 * i + 1 < free) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int j = left;
            if (right < free && queue[right].priority < queue[left].priority)
                j = right;
            if (queue[i].priority <= queue[j].priority)
                break;
            swap(i, j);
            i = j;
        }
    }

    @Override
    public int delete() {
        if (free == 0)
            throw new NoSuchElementException("Очередь пуста");
        int min = queue[0].priority;
        queue[0] = queue[--free];
        shiftDown(0);
        return min;
    }

    private void swap(int first, int second) {
        Entity temp = queue[first];
        queue[first] = queue[second];
        queue[second] = temp;
    }

    @Override
    public void makeNull() {
        for (int i = 0; i < SIZE; i++)
            queue[i] = null;
    }
}
