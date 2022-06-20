package Dictionary;

import Dictionary.OpenHashing.Dictionary;

public class Main {
    public static void main(String[] args) {
        Dictionary persons = new Dictionary();
        persons.insert("Bogdan".toCharArray()); // одинаковый
        persons.insert("Kirill".toCharArray());
        persons.insert("Nikita".toCharArray()); // hash
        persons.insert("Victor".toCharArray());
        System.out.println(persons.member("Nikita".toCharArray()));
        System.out.println(persons.member("Bogdan".toCharArray()));
        persons.delete("Victor".toCharArray());
        persons.delete("Bogdan".toCharArray());
        System.out.println(persons.member("Bogdan".toCharArray()));
        System.out.println(persons.member("Nikita".toCharArray()));
        persons.makeNull();
    }
}
