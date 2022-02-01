package Map.doublyLinkedListChar;

public class Message {

    private char[] name;
    private char[] address;

    public char[] getName() {
        return name;
    }

    public char[] getAddress() {
        return address;
    }

    public Message(char[] name, char[] address) {
        this.name = name;
        this.address = address;
    }

    public Message(String name, String address) {
        this(name.toCharArray(), address.toCharArray());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Message message = (Message) o;
        char[] mname = message.getName();
        char[] maddress = message.getAddress();

        int i;
        for (i = 0; i < name.length; i++)
            if (name[i] != mname[i])
                return false;
        for (i = 0; i < address.length; i++)
            if (address[i] != maddress[i])
                return false;

        return true;
    }
}
