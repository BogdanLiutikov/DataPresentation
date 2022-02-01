package Stack.doublyLinkedListChar;

import java.util.NoSuchElementException;

public class List implements ADT_List {

    private Node head;
    private Node tail;

    public List() {
        head = null;
        tail = null;
    }

    static class Node {
        private Node prev;
        private char c;
        private Node next;

        private Node(Node p, char c, Node n) {
            prev = p;
            this.c = c;
            next = n;
        }
    }

    @Override
    public void Insert(Position p, char x) {

        //Вставка в пустой список
        if (head == null) {
            head = new Node(null, x, null);
            tail = head;
        }
        // Вставка в начало
        else if (p.getNode() == head) {
            head = new Node(null, x, head);
        }
        //Вставка в после последнего
        else if (p == null) {
            Node newnode = new Node(tail, x, null);
            tail.next = newnode;
            tail = newnode;
        }
        // Вставка в последний
        else if (p.getNode() == tail) {
            Node newnode = new Node(tail, tail.c, null);
            tail.c = x;
            tail.next = newnode;
            tail = newnode;
        } else {
            if (posExist(p)) {
                Node cur = p.getNode();
                Node newnode = new Node(cur, cur.c, cur.next);
                cur.c = x;
                cur.next = newnode;
            }
        }
    }

    @Override
    public Position Locate(char x) {
        return locate(x);
    }

    private Position locate(char x) {
        Node cur = head;
        while (cur != null) {
            if (cur.c == x)
                return new Position(cur);
            cur = cur.next;
        }
        return null;
    }

    @Override
    public char Retrieve(Position p) {
        if (p == null)
            throw new NoSuchElementException();

        if (p.getNode() == head)
            return head.c;

        if (p.getNode() == tail)
            return tail.c;

        if (!posExist(p))
            throw new NoSuchElementException();

        Node n = p.getNode();
        return n.c;
    }

    @Override
    public void Delete(Position p) {
        Node n = p.getNode();
        //Удаление единственного элемента
        if (head == tail) {
            head = null;
            tail = null;
            return;
        }
        //Удаление головы
        if (n == head) {
            head = head.next;
            head.prev = null;
            return;
        }
        //Удаление хвоста
        if (n == tail) {
            tail = tail.prev;
            tail.next = null;
            p.setNode(null);
            return;
        }

        if (!posExist(p))
            return;

        n.prev.next = n.next;
        n.next.prev = n.prev;
        p.setNode(n.next);
    }

    @Override
    public Position Next(Position p) {
        if (p == null || !posExist(p))
            throw new NoSuchElementException("Next position doesn't exist");
        return new Position(p.getNode().next);
    }

    @Override
    public Position Previous(Position p) {
        if (p == null || !posExist(p))
            throw new NoSuchElementException("Previous position doesn't exist");
        return new Position(p.getNode().prev);
    }


    private boolean posExist(Position p) {
        Node cur = head;
        Node n = p.getNode();
        while (cur != null) {
            if (n == cur)
                return true;
            cur = cur.next;
        }
        return false;
    }

    @Override
    public Position First() {
        return new Position(head);
    }

    @Override
    public void MakeNull() {
        head = null;
        tail = null;
    }

    @Override
    public void PrintList() {
        Node n = head;
        while (n != null) {
            System.out.print(n.c);
            n = n.next;
        }
    }

    @Override
    public Position End() {
        return null;
    }
}
