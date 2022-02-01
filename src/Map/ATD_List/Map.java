package Map.ATD_List;

import Map.ATD_Map;
import Map.doublyLinkedListChar.List;
import Map.doublyLinkedListChar.Message;
import Map.doublyLinkedListChar.Position;

public class Map implements ATD_Map {

    List map = new List();

    @Override
    public void makeNull() {
        map.MakeNull();
    }

    @Override
    public void assign(char[] domain, char[] range) {
        Position pos = map.Locate(domain);
        if (pos == null)
            map.Insert(pos, new Message(domain, range));
        else {
            map.Insert(pos, new Message(domain, range));
            pos = map.Next(pos);
            map.Delete(pos);
        }
    }

    @Override
    public boolean compute(char[] domain, char[] range) {
        Position pos = map.Locate(domain);
        if (pos == null)
            return false;

        map.Insert(pos, new Message(domain, range));
        pos = map.Next(pos);
        map.Delete(pos);
        return true;

    }
}