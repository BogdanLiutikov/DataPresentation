package List.cursor;
public interface ADT_List {

    public void Insert(Position p, Message x);
    public Position Locate(Message x);
    public Message Retrieve(Position p);
    public void Delete(Position p);
    public Position Next(Position p);
    public Position Previous(Position p);
    public Position First();
    public void MakeNull();
    public void PrintList();
    public Position End();
}
