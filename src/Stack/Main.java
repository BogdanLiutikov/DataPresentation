package Stack;

import Stack.ATD_List.Stack;

public class Main {
    public static void main(String[] args) {


        Stack stack = new Stack();

        Message message = new Message("Bogdan Bogdan Bogdan");


        char[] chars = message.getText();
        for (int i = 0; i < chars.length; i++) {
            if(stack.full()){
                while (!stack.empty())
                    System.out.println(stack.pop());
                System.out.println();
            }
            stack.push(chars[i]);
        }
        while (!stack.empty())
            System.out.print(stack.pop());
    }
}
