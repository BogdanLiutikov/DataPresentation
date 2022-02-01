package Stack;

public class Message {

    private char[] text;

    public Message(char[] message) {
        this.text = message;
    }

    public Message(String message) {
        this.text = message.toCharArray();
    }

    public char[] getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Message m = (Message) o;

        int i;
        for (i = 0; i < text.length; i++)
            if (text[i] != m.text[i])
                return false;

        return true;
    }
}
