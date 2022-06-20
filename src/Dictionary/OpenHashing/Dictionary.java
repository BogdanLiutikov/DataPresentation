package Dictionary.OpenHashing;

import Dictionary.ADT_Dictionary;

public class Dictionary implements ADT_Dictionary {

    // Количество классов
    private static final int B = 3;

    private static class Person {
        private char[] name; // value
        private Person next; //collision

        private Person(char[] name, Person next) {
            this.name = name;
            this.next = next;
        }

        private Person(char[] name) {
            this(name, null);
        }

        public Person() {
            this(null);
        }

        public boolean equals(char[] value) {
            for (int i = 0; i < value.length && i < 10; i++)
                if (name[i] != value[i])
                    return false;
            return true;
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
    }

    @Override
    public void insert(char[] value) {
        int key = hash(value);
        //Первый из элементов по этому key
        if (persons[key] == null)
            persons[key] = new Person(value);
            //Если же случиась коллизия
        else {
            if (!exist(persons[key], value)) {
                persons[key] = new Person(value, persons[key]);
            }
        }
    }

    private boolean exist(Person person, char[] value) {
        Person cur = person;
        while (cur != null) {
            if (cur.equals(value)) {
                return true;
            }
        }
        return false;
    }

    private Person getLast(int key) {
        Person last = persons[key];
        Person cur = persons[key].next;
        while (cur != null) {
            last = cur;
            cur = cur.next;
        }
        return last;
    }

    @Override
    public void delete(char[] value) {
        int key = hash(value);
        if (persons[key] != null) {
            if (persons[key].equals(value))
                persons[key] = persons[key].next;
            else {
                Person prev = getPrev(key, value);
                if (prev == null)
                    return;
                prev.next = prev.next.next;
            }
        }
    }

    //previous element or null if element is the first or element doesn't exist
    private Person getPrev(int key, char[] value) {
        Person cur = persons[key];
        Person prev = null;
        while (cur != null) {
            if (cur.equals(value))
                return prev;
            prev = cur;
            cur = cur.next;
        }
        return null;
    }

    @Override
    public boolean member(char[] value) {
        int key = hash(value);
        if (persons[key] != null) {
            if (persons[key].equals(value))
                return true;
            else {
                Person prev = getPrev(key, value);
                return prev != null;
            }
        }
        return false;
    }

    @Override
    public void makeNull() {
        for (int i = 0; i < persons.length; i++)
            persons[i] = null;
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
}