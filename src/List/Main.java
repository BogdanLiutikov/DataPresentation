package List;

import List.array.*;

public class Main {

    public static void main(String[] args) {

        Position p, q;

        List list = new List();

        init(list);
        list.MakeNull();
        init(list);
        list.PrintList();

        p = list.First();

        while (!p.equals(list.End())) {
            q = list.Next(p);
            while (!q.equals(list.End())) {
                if (list.Retrieve(p).equals(list.Retrieve(q)))
                    list.Delete(q);
                else
                    q = list.Next(q);
            }
            p = list.Next(p);
        }


        System.out.println("--------------------\n");
        list.PrintList();
    }

    private static void init(List list) {
        Message message1 = new Message("Bogdan", "Moscow");
        Message message2 = new Message("Bogdan", "SP");
        Message message3 = new Message("Bogdan", "ARH");
        Message message4 = new Message("Bogdan", "SP");
        Message message5 = new Message("Bogdan", "ARH");


        list.Insert(list.End(), message1);
        list.Insert(list.End(), message2);
        list.Insert(list.End(), message3);
        list.Insert(list.End(), message4);
        list.Insert(list.End(), message5);
    }
}
