package Set.Circular;

import java.util.NoSuchElementException;

public class Set implements ADT_Set {

    private static class Node {
        private int value;
        private Node next;

        Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    Node tail;

    public Set() {
        tail = null;
    }

    private Set(int value) {
        tail = new Node(0, null);
        tail.next = tail;
    }

    public Set(Set copyFrom) {
        if (copyFrom.tail == null) {
            tail = null;
            return;
        }

        tail = new Node(copyFrom.tail.value, null);

        Node current = copyFrom.tail.next;
        Node copyPrev = tail;

        while (current != copyFrom.tail) {
            copyPrev.next = new Node(current.value, null);
            copyPrev = copyPrev.next;
            current = current.next;
        }

        copyPrev.next = tail;
    }

    @Override
    public void Insert(int x) {
        //Вставка единственного элемента
        if (tail == null) {
            tail = new Node(x, null);
            tail.next = tail;
        } else {
            Node prev = getPrev(x);
            //x > tail
            if (prev == null) {
                Node newNode = new Node(x, tail.next);
                tail.next = newNode;
                tail = newNode;
            } else {
                if (prev.next.value == x)
                    return;
                Node newNode = new Node(x, prev.next);
                prev.next = newNode;
            }
        }
    }

    private Node getPrev(int x) {
        Node prev = tail;
        Node cur = tail.next;
        while (cur != tail) {
            if (cur.value >= x)
                return prev;
            prev = cur;
            cur = cur.next;
        }

        if (tail.value >= x)
            return prev;

        return null;
    }

    @Override
    public void Delete(int x) {
        //Пустой список
        if (tail == null)
            return;
        //В списке всего один элемент
        if (tail == tail.next) {
            tail = null;
            return;
        }

        Node prev = getPrev(x);
        //нет такого элемента
        if (prev == null) {
            return;
            //если удаляем хвост
        } else if (prev.next == tail) {
            prev.next = prev.next.next;
            tail = prev;
        } else {
            prev.next = prev.next.next;
        }

    }

//    private Node getPrev(int x) {
//        Node prev = tail;
//        Node cur = tail.next;
//        while (cur != tail) {
//            if (cur.value == x)
//                return prev;
//            prev = cur;
//            cur = cur.next;
//        }
//
//        if (tail.value == x)
//            return prev;
//
//        return null;
//    }

    @Override
    public Set Intersection(Set other) {
        Set newSet = new Set(0);
        Node pointer1 = this.tail.next;
        Node pointer2 = other.tail.next;

        if (pointer1 == this.tail || pointer2 == other.tail) {
            if (pointer1.value == pointer2.value)
                newSet.append(pointer1.value);
            else if (pointer1 == this.tail && pointer1.value > pointer2.value) {
                newSet.appendList(pointer1, pointer2, other.tail);
            } else if (pointer2 == other.tail && pointer2.value > pointer1.value) {
                newSet.appendList(pointer2, pointer1, this.tail);
            }
            if (newSet.tail.next == newSet.tail)
                return null;
        } else {
            while (pointer1 != this.tail && pointer2 != other.tail) {
                while (pointer1 != this.tail && pointer1.value < pointer2.value)
                    pointer1 = pointer1.next;
                while (pointer2 != other.tail && pointer2.value < pointer1.value)
                    pointer2 = pointer2.next;

                //p2>=p1
                if (pointer1.value == pointer2.value) {
                    newSet.append(pointer1.value);
                    if (pointer1 != this.tail)
                        pointer1 = pointer1.next;
                    if (pointer2 != other.tail)
                        pointer2 = pointer2.next;
                }
            }

            //Зная что p2>=p1, после выхода из цикла можем проверять на <
            if (pointer1.value < pointer2.value && pointer2 == other.tail) {
                newSet.appendList(pointer2, pointer1, this.tail);
            }
        }

        newSet.tail.next = newSet.tail.next.next;
        return newSet;
    }

    public Set IntersectionV2(Set other) {
        Set newSet = new Set(0);
        Node pointer1 = this.tail.next;
        Node pointer2 = other.tail.next;

        //        //Если хотя бы один состоит из одного элемента
//        if (pointer1 == this.tail || pointer2 == other.tail) {
//            if (pointer1.value == pointer2.value)
//                newSet.append(pointer1.value);
//            else if (pointer1 == this.tail && pointer1.value > pointer2.value) {
//                if (other.findNodeByValue(pointer1.value) != null)
//                    newSet.append(pointer1.value);
//                else
//                    return null;
//            } else if (pointer2 == other.tail && pointer2.value > pointer1.value) {
//                if (this.findNodeByValue(pointer2.value) != null)
//                    newSet.append(pointer2.value);
//                else
//                    return null;
//            } else
//                return null;
//
//            newSet.tail.next = newSet.tail.next.next;
//            return newSet;
//        }

        //Далее пытаемся занести все совпадающие значения, пока не дойдем до одного из хвоста

        while (pointer1 != this.tail && pointer2 != other.tail) {
            if (pointer1.value == pointer2.value) {
                newSet.append(pointer1.value);
                pointer1 = pointer1.next;
                pointer2 = pointer2.next;
                continue;
            }

            if (pointer1.value < pointer2.value)
                pointer1 = pointer1.next;
            else
                pointer2 = pointer2.next;
        }

        //Так как один из списков кончился, попробуем найти во втором такое же значение
        if (pointer1 == this.tail) {
            if (other.findNodeByValue(pointer1.value) != null)
                newSet.append(pointer1.value);
        } else {
            if (this.findNodeByValue(pointer2.value) != null)
                newSet.append(pointer2.value);
        }

        //Если не было довешено ни одного элемента
        if (newSet.tail == newSet.tail.next)
            return null;

        newSet.tail.next = newSet.tail.next.next;
        return newSet;
    }

    private Node findNodeByValue(int value) {
        if (tail.value == value)
            return tail;

        Node cur = tail.next;
        while (cur != tail) {
            if (cur.value == value)
                return cur;
            cur = cur.next;
        }
        return null;
    }

    private void append(int value) {
        Node newNode = new Node(value, tail.next);
        tail.next = newNode;
        tail = newNode;
    }

    private void appendList(Node someTail, Node pointer, Node end) {
        while (pointer != end) {
            if (someTail.value == pointer.value) {
                Node newNode = new Node(pointer.value, tail.next);
                tail.next = newNode;
                tail = newNode;
            }
            pointer = pointer.next;
        }
        if (someTail.value == pointer.value) {
            Node newNode = new Node(pointer.value, tail.next);
            tail.next = newNode;
            tail = newNode;
        }
    }

    @Override
    public Set Union(Set other) {
        Set newSet = new Set(0);
        Node pointer1 = this.tail.next;
        Node pointer2 = other.tail.next;

        //Добавляем все элементы постепенно продвигаясь по спискам
        while (pointer1 != tail && pointer2 != tail) {
            if (pointer1.value < pointer2.value) {
                newSet.append(pointer1.value);
                pointer1 = pointer1.next;
            } else if (pointer2.value < pointer1.value) {
                newSet.append(pointer2.value);
                pointer2 = pointer2.next;
            } else {
                newSet.append(pointer1.value);
                pointer1 = pointer1.next;
                pointer2 = pointer2.next;
            }
        }

        //Довешиваем остаток
        if (pointer1 == this.tail) {
            newSet.appendList(pointer2, other.tail);
            newSet.insert(pointer1.value);
        } else if (pointer2 == other.tail) {
            newSet.appendList(pointer1, this.tail);
            newSet.insert(pointer2.value);
        }

        newSet.tail.next = newSet.tail.next.next;
        return newSet;
    }

    private void insert(int value) {
        Node prev = getPrev(value);
        //x > tail
        if (prev == null) {
            Node newNode = new Node(value, tail.next);
            tail.next = newNode;
            tail = newNode;
        } else {
            if (prev.value == value)
                return;
            Node newNode = new Node(value, prev.next);
            prev.next = newNode;
        }
    }

    private void appendList(Node pointer, Node end) {
        while (pointer != end) {
            append(pointer.value);
            pointer = pointer.next;
        }
        append(end.value);
    }


    @Override
    public Set Difference(Set other) {

        Set newSet = new Set(this);
        Node pointer1 = this.tail.next;
        Node pointer2 = other.tail.next;

        //Проходим основную часть
        while (pointer1 != this.tail && pointer2 != other.tail) {
            if (pointer1.value == pointer2.value) {
                newSet.Delete(pointer1.value);
                pointer1 = pointer1.next;
                pointer2 = pointer2.next;
                continue;
            }

            if (pointer1.value < pointer2.value)
                pointer1 = pointer1.next;
            else
                pointer2 = pointer2.next;
        }

        //Так как один из списков кончился, попробуем найти во втором такое же значение
        if (pointer1 == this.tail) {
            if (other.findNodeByValue(pointer1.value) != null)
                newSet.Delete(pointer1.value);
        } else {
            if (this.findNodeByValue(pointer2.value) != null)
                newSet.Delete(pointer2.value);
        }

        return newSet;
    }

    //todo
    @Override
    public Set Assign(Set other) {
        return null;
    }

    @Override
    public int Min() {
        if (tail == null)
            throw new NoSuchElementException();
        return tail.next.value;
    }

    @Override
    public int Max() {
        if (tail == null)
            throw new NoSuchElementException();
        return tail.value;
    }

    @Override
    public boolean Equal(Set other) {
        Node pointer1 = this.tail.next;
        Node pointer2 = other.tail.next;
        while (pointer1 != this.tail && pointer2 != other.tail) {
            if (pointer1.value != pointer2.value)
                return false;
            pointer1 = pointer1.next;
            pointer2 = pointer2.next;
        }
        return (pointer1 == this.tail && pointer2 == other.tail) &&
                (this.tail.value == other.tail.value);
    }


    @Override
    public void MakeNull() {
        tail = null;
    }

    @Override
    public boolean Member(int x) {
        Node pointer = tail.next;
        while (pointer != tail) {
            if (pointer.value == x)
                return true;
            pointer = pointer.next;
        }
        return tail.value == x;
    }

    @Override
    public Set Merge(Set other) {
//        if (isIntersection(other))
//            return null;

        if (!(this.tail.value < other.tail.next.value ||
                this.tail.next.value > other.tail.value))
            return null;

        Set newSet = new Set(0);
        Node pointer1 = this.tail.next;
        Node pointer2 = other.tail.next;

        if (pointer1.value > pointer2.value)
            newSet.merge(other, this);
        else
            newSet.merge(this, other);

        newSet.tail.next = newSet.tail.next.next;
        return newSet;
    }

    private void merge(Set set1, Set set2) {
        appendList(set1.tail.next, set1.tail);
        appendList(set2.tail.next, set2.tail);
    }

    private boolean isIntersection(Set other) {
        Node pointer = tail.next;
        while (pointer != tail) {
            if (other.Member(pointer.value))
                return true;
            pointer = pointer.next;
        }
        return other.Member(tail.value);
    }

    //todo
    @Override
    public Set Find(Set s, int x) {
        if (this.Member(x))
            return this;
        else if (s.Member(x))
            return s;
        return null;
    }
}
