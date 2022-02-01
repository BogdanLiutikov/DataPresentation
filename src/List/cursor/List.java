package List.cursor;

import java.util.NoSuchElementException;


public class List implements ADT_List {

    private static final int SIZE = 5;
    private static final Node[] mem = new Node[SIZE];
    private static int SPACE = 0;
    private int START;

    static {
        for (int i = 0; i < SIZE - 1; i++) {
            mem[i] = new Node(i + 1);
        }
        mem[SIZE - 1] = new Node(-1);
    }

    public List() {
        START = -1;
    }

    static class Node {
        private Message message;
        private int next;

        private Node(Message m, int n) {
            message = m;
            next = n;
        }

        private Node(int n) {
            next = n;
        }
    }


    @Override
    public void Insert(Position p, Message x) {


        if (SPACE != -1) {

            int nextSpace = mem[SPACE].next;
            //пустой список
            if (START == -1) {

                START = SPACE;
                mem[START] = new Node(x, -1);
            }
            // после последнего
            else if (p.getPos() == -1) {
                int last = getLast();
                mem[SPACE] = new Node(x, -1);
                mem[last].next = SPACE;
            }
            // вставка в начало
            else if (p.getPos() == START) {
                mem[SPACE] = new Node(mem[START].message, mem[START].next);
                mem[START].message = x;
                mem[START].next = SPACE;
            } else {
                int pos = p.getPos();
                mem[SPACE] = new Node(mem[pos].message, mem[pos].next);
                mem[pos].message = x;
                mem[pos].next = SPACE;

            }
            SPACE = nextSpace;
        } else {
            System.out.println("Недостаточно места.");
        }
    }

    private int getPrev(Position p) {
        int pos = p.getPos();
        int cur = START;
        int prev = -1;
        while (cur != -1) {
            if (cur == pos)
                return prev;
            prev = cur;
            cur = mem[cur].next;
        }
        return -1;
    }

    private int getLast() {
        int cur = START;
        int last = -1;
        while (cur != -1) {
            last = cur;
            cur = mem[cur].next;
        }
        return last;
    }

    @Override
    public Position Locate(Message x) {
        return new Position(locate(x));
    }

    private int locate(Message x) {
        int cur = START;
        while (cur != -1) {
            if (mem[cur].message.equals(x))
                return cur;
            cur = mem[cur].next;
        }
        return -1;
    }

    @Override
    public Message Retrieve(Position p) {
        if (p == null)
            throw new NoSuchElementException();

        if (p.getPos() == START)
            return mem[START].message;

        int prev = getPrev(p);

        if (prev == -1)
            throw new NoSuchElementException();

        int cur = mem[prev].next;
        return mem[cur].message;
    }

    @Override
    public void Delete(Position p) {
        int pos = p.getPos();

        if (pos == START) {
            int next = mem[START].next;
            mem[START].message = null;
            mem[START].next = SPACE;
            SPACE = START;
            START = next;
            return;
        }

        int prev = getPrev(p);
        if (prev == -1)
            return;

        int cur = mem[prev].next;
        p.setPos(mem[cur].next);

        mem[prev].next = mem[cur].next;
        mem[cur].message = null;
        mem[cur].next = SPACE;
        SPACE = cur;
    }

    @Override
    public Position Next(Position p) {
        if (p.getPos() == START)
            return new Position(mem[START].next);

        int prev = getPrev(p);
        if (prev == -1)
            throw new NoSuchElementException("Next position doesn't exist");

        return new Position(mem[p.getPos()].next);
    }

    @Override
    public Position Previous(Position p) {
        if (p.getPos() == START)
            throw new NoSuchElementException("Previous position doesn't exist");

        int prev = getPrev(p);
        if (prev == -1)
            throw new NoSuchElementException("Previous position doesn't exist");

        return new Position(prev);
    }

    @Override
    public Position First() {
        return new Position(START);
    }

    @Override
    public void MakeNull() {
        int cur = START;
        while (cur != -1) {
            mem[cur].message = null;
            int nextCur = mem[cur].next;
            mem[cur].next = SPACE;
            SPACE = cur;
            cur = nextCur;
        }
        START = -1;
    }

    @Override
    public void PrintList() {
        int cur = START;
        while (cur != -1) {
            System.out.print("Name: ");
            for (int j = 0; j < mem[cur].message.getName().length; j++) {
                System.out.print(mem[cur].message.getName()[j]);
            }
            System.out.println();
            System.out.print("Address: ");
            for (int j = 0; j < mem[cur].message.getAddress().length; j++) {
                System.out.print(mem[cur].message.getAddress()[j]);
            }
            System.out.println();
            System.out.println();
            cur = mem[cur].next;
        }
    }

    @Override
    public Position End() {
        return new Position(-1);
    }
}
