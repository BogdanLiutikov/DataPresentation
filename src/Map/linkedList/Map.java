package Map.linkedList;

import Map.ATD_Map;
import Map.Message;

public class Map implements ATD_Map {

    private static class Node {
        private Message message;
        private Node next;

        public Node(Message message, Node next) {
            this.message = message;
            this.next = next;
        }

        public Node(Message message) {
            this(message, null);
        }
    }

    private Node head;

    public Map() {
        head = null;
    }

    @Override
    public void makeNull() {
        head = null;
    }

    @Override
    public void assign(char[] domain, char[] range) {
        Node cur = locate(domain);

        if (cur == null)
            head = new Node(new Message(domain, range), head);
        else
            cur.message.setAddress(range);
    }

    @Override
    public boolean compute(char[] domain, char[] range) {

        Node cur = locate(domain);
        if (cur == null)
            return false;
        else
            cur.message.setAddress(range);
        return true;
    }

    private Node locate(char[] domain) {
        Node cur = head;
        while (cur != null) {
            if (cur.message.getName().equals(domain))
                return cur;
            cur = cur.next;
        }
        return null;
    }
}
