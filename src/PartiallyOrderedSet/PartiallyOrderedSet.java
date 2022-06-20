package PartiallyOrderedSet;

public class PartiallyOrderedSet implements ADT_PartiallyOrderedSet {

    Element head;

    public PartiallyOrderedSet() {
        head = null;
    }

    public void init(int[][] arr) {

        if (arr[0][0] == arr[0][1])
            return;

        Element first = new Element(arr[0][0]);
        Element second = new Element(arr[0][1]);
        first.id = second;
        first.next = new Trail(second, null);
        second.count++;

        head = first;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i][0] == arr[i][1])
                continue;
            first = getElement(arr[i][0]);
            second = getElement(arr[i][1]);
            if (first == null) {
                first = new Element(arr[i][0]);
                first.id = head;
                head = first;
            }
            if (second == null) {
                second = new Element(arr[i][1]);
                second.id = head;
                head = second;
            }

            first.next = new Trail(second, first.next);
            second.count++;
        }
    }

    private Element getElement(int value) {
        Element cur = head;
        while (cur != null) {
            if (cur.value == value)
                return cur;
            cur = cur.id;
        }
        return null;
    }

    @Override
    public void sort() {
        Element newhead = new Element(0);
        Element last = newhead;
        Element cur = head;
        while (cur != null) {
            if (cur.count == 0) {
                last.id = cur;
                last = last.id;
                delete(cur);
                cur = head;
            } else
                cur = cur.id;
        }
        if (head != null)
            System.out.println("Невозможно отсортировать");

        newhead = newhead.id;
        head = newhead;
    }

    private void delete(Element cur) {
        Trail trail = cur.next;
        while (trail != null) {
            trail.id.count--;
            trail = trail.next;
        }

        if (cur == head)
            head = head.id;
        else {
            Element prev = getPrev(cur);
            prev.id = cur.id;
        }
    }

    private Element getPrev(Element elem) {
        Element cur = head;
        Element prev = null;
        while (cur != null) {
            if (cur == elem)
                return prev;
            prev = cur;
            cur = cur.id;
        }
        return null;
    }

    public void print() {
        Element cur = head;
        System.out.print(cur.value);
        cur = cur.id;
        while (cur != null) {
            System.out.print(" < " + cur.value);
            cur = cur.id;
        }
        System.out.println();
    }
}
