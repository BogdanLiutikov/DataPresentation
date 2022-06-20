package Dictionary.ClosedHashing;

import Dictionary.ADT_Dictionary;

public class Dictionary implements ADT_Dictionary {

    // Количество классов
    private static final int B = 3;

    private static class Person {
        private char[] name; // value

        private Person(char[] name) {
            this.name = name;
        }

        private Person() {
            this(null);
        }

        public boolean equals(char[] value) {
            for (int i = 0; i < value.length && i < 10; i++)
                if (name[i] != value[i])
                    return false;
            return true;
        }

        public boolean isDeleted() {
            return name[0] == '\u0000';
        }

        public void setName(char[] value) {
            int i = 0;
            for (i = 0; i < value.length; i++) {
                name[i] = value[i];
            }
            for (; i < 10; i++) {
                name[i] = '\u0000';
            }
        }
    }

    Person[] persons;

    public Dictionary() {
        persons = new Person[B];
        for (int i = 0; i < B; i++) {
            persons[i] = new Person();
        }
    }

    @Override
    public void insert(char[] value) {
        int key = hash(value);
        if (persons[key].name == null) {
            persons[key].name = new char[10];
            persons[key].setName(value);
        } else {
            int newKey = hash(key + 1);
            while (newKey != key) {
                if (persons[newKey].name == null) {
                    persons[newKey].name = new char[10];
                    persons[newKey].setName(value);
                    break;
                } else if (persons[key].equals(value)) {
                    return;
                } else if (persons[newKey].isDeleted()) {
                    persons[newKey].setName(value);
                    break;
                }
                newKey = hash(newKey + 1);
            }
        }
    }

    @Override
    //todo new char[]?
    public void delete(char[] value) {
        int key = hash(value);
        if (persons[key].equals(value)) {
            persons[key].name[0] = '\u0000';
        } else {
            int newKey = hash(key + 1);
            while (newKey != key) {
                if (persons[newKey].name == null)
                    return;
                if (persons[newKey].equals(value)) {
                    persons[newKey].name[0] = '\u0000';
                }
                newKey = hash(newKey + 1);
            }
        }
    }

    @Override
    public boolean member(char[] value) {
        int key = hash(value);
        if (persons[key].name != null) {
            if (persons[key].equals(value))
                return true;
            int newKey = hash(key + 1);
            while (newKey != key) {
                if (persons[newKey].name == null)
                    return false;
                if (persons[newKey].equals(value))
                    return true;
                newKey = hash(newKey + 1);
            }
        }
        return false;
    }

    @Override
    public void makeNull() {
        for (int i = 0; i < persons.length; i++)
            persons[i].name = null;
    }

    //return key
    private int hash(char[] value) {
        int sum = 0;
        for (char c : value
        ) {
            sum += c;
        }
        return sum % B;
    }

    private int hash(int key) {
        return key % B;
    }
}