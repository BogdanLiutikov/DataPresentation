package List.linkedList;

import java.util.NoSuchElementException;

public class List implements ADT_List {

    private Node head;

    public List() {
        head = null;
    }

    static class Node {
        private Message message;
        private Node next;

        private Node(Message m, Node n) {
            message = m;
            next = n;
        }
    }

    @Override
    public void Insert(Position p, Message x) {

        if (head == null) {
            head = new Node(x, null);
        }
        //Вставка в после последнего
        else if (p == null) {
            Node newnode = new Node(x, null);
            Node last = getlast();
            last.next = newnode;

        }
        // Вставка в начало
        else if (p.getNode() == head) {
            head = new Node(x, head);
        } else {
            Node prev = getprev(p);
            if (prev != null) {
                Node cur = prev.next;
                Node newnode = new Node(cur.message, cur.next);
                cur.message = x;
                cur.next = newnode;
            }
        }
    }

    private Node getlast() {
        Node last = null;
        Node cur = head;
        while (cur != null) {
            last = cur;
            cur = cur.next;
        }
        return last;
    }

    @Override
    public Position Locate(Message x) {
        return locate(x);
    }

    private Position locate(Message x){
        Node cur = head;
        while (cur != null) {
            if (cur.message.equals(x))
                return new Position(cur);
            cur = cur.next;
        }
        return null;
    }

    @Override
    public Message Retrieve(Position p) {
        if(p == null)
            throw new NoSuchElementException();

        if (p.getNode() == head)
            return head.message;

        Node prev = getprev(p);
        if (prev == null)
            throw new NoSuchElementException();

        Node n = prev.next;
        return n.message;
    }

    @Override
    public void Delete(Position p) {
        Node n = p.getNode();
        //Удаление головы
        if (n == head) {
            head = head.next;
            return;
        }
        Node prev = getprev(p);
        if (prev == null)
            return;
        prev.next = n.next;
        p.setNode(n.next);
    }

    @Override
    public Position Next(Position p) {
        if (p == null || (p.getNode() != head && getprev(p) == null))
            throw new IllegalArgumentException("Next position doesn't exist");
        return new Position(p.getNode().next);
    }

    @Override
    public Position Previous(Position p) {
        if (p == null || p.getNode() == head)
            throw new NoSuchElementException("Previous position doesn't exist");

        Node prev = getprev(p);
        if (prev == null)
            throw new NoSuchElementException("Previous position doesn't exist");

        return new Position(prev);
    }

    private Node getprev(Position p) {
        Node cur = head;
        Node prev = null;
        Node n = p.getNode();
        while (cur != null) {
            if (n == cur)
                return prev;
            prev = cur;
            cur = cur.next;
        }
        return null;
    }

    @Override
    public Position First() {
        return new Position(head);
    }

    @Override
    public void MakeNull() {
        head = null;
    }

    @Override
    public void PrintList() {
        Node n = head;
        while (n != null) {
            Message message = n.message;
            System.out.print("Name: ");
            for (int j = 0; j < message.getName().length; j++) {
                System.out.print(message.getName()[j]);
            }
            System.out.println();
            System.out.print("Address: ");
            for (int j = 0; j < message.getAddress().length; j++) {
                System.out.print(message.getAddress()[j]);
            }
            System.out.println();
            System.out.println();
            n = n.next;
        }
    }

    @Override
    public Position End() {
        return null;
    }
}
