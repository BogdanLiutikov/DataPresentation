package List.array;

import java.util.NoSuchElementException;

public class List implements ADT_List {

    private static final int SIZE = 5;
    private Message[] messages;
    //Первый свободный элемент
    private final Position last;

    public List() {
        last = new Position(0);
        messages = new Message[SIZE + 1];
    }

    @Override
    public void Insert(Position p, Message x) {
        int pos = p.getPos();

        if (pos < 0 || pos > last.getPos())
            return;
        if (p.getPos() == SIZE || last.getPos() == SIZE)
            return;

        moveright(pos);
        messages[pos] = x;
        last.setPos(last.getPos() + 1);
    }

    private void moveright(int pos) {
        for (int i = last.getPos(); i > pos; i--) {
            messages[i] = messages[i - 1];
        }
    }

    @Override
    public Position Locate(Message x) {
        if (x == null)
            throw new IllegalArgumentException();

        for (int i = 0; i < last.getPos(); i++) {
            if (x.equals(messages[i]))
                return new Position(i);
        }
        return last;
    }

    @Override
    public Message Retrieve(Position p) {
        int pos = p.getPos();
        if (pos < 0 || pos >= last.getPos())
            throw new NoSuchElementException();
        return messages[pos];
    }

    @Override
    public void Delete(Position p) {
        int pos = p.getPos();
        if (pos < 0 || pos >= last.getPos())
            return;

        moveleft(p.getPos());
        last.setPos(last.getPos() - 1);
        messages[last.getPos()] = null;
    }

    private void moveleft(int pos) {
        for (int i = pos; i < last.getPos() - 1; i++) {
            messages[i] = messages[i + 1];
        }
    }

    @Override
    public Position Next(Position p) {
        int pos = p.getPos();
        if (pos < 0 || pos >= last.getPos())
            throw new NoSuchElementException("Next position doesn't exist");

        return new Position(p.getPos() + 1);
    }

    @Override
    public Position Previous(Position p) {
        int pos = p.getPos();
        if (pos <= 0 || pos >= last.getPos())
            throw new NoSuchElementException("Previous position doesn't exist");

        return new Position(p.getPos() - 1);
    }

    @Override
    public Position First() {
        return new Position(0);
    }

    @Override
    public void MakeNull() {
        for (int i = 0; i < messages.length; i++) {
            messages[i] = null;
        }
        last.setPos(0);
    }

    @Override
    public void PrintList() {
        for (int i = 0; i < last.getPos(); i++) {
            System.out.print("Name: ");
            for (int j = 0; j < messages[i].getName().length; j++) {
                System.out.print(messages[i].getName()[j]);
            }
            System.out.println();
            System.out.print("Address: ");
            for (int j = 0; j < messages[i].getAddress().length; j++) {
                System.out.print(messages[i].getAddress()[j]);
            }
            System.out.println();
            System.out.println();
        }
    }

    @Override
    public Position End() {
        return last;
    }

}
