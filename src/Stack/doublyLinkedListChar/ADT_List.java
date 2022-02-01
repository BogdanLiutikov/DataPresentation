package Stack.doublyLinkedListChar;

public interface ADT_List {

    public void Insert(Position p, char x);
    public Position Locate(char x);
    public char Retrieve(Position p);
    public void Delete(Position p);
    public Position Next(Position p);
    public Position Previous(Position p);
    public Position First();
    public void MakeNull();
    public void PrintList();
    public Position End();
}
