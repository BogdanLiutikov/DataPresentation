package Stack.ATD_List;

import Stack.ATD_Stack;
import Stack.doublyLinkedListChar.List;

import java.util.NoSuchElementException;

public class Stack implements ATD_Stack {

    List stack = new List();

    @Override
    public void push(char c) {
        stack.Insert(stack.First(), c);
    }

    @Override
    public char pop() {
        if (empty())
            throw new NoSuchElementException();

        char c = stack.Retrieve(stack.First());
        stack.Delete(stack.First());
        return c;
    }

    @Override
    public char top() {
        if (empty())
            throw new NoSuchElementException();

        return stack.Retrieve(stack.First());
    }

    @Override
    public void makeNull() {
        stack.MakeNull();
    }

    @Override
    public boolean full() {
        return false;
    }

    @Override
    public boolean empty() {
        return stack.First().getNode() == null;
    }
}
