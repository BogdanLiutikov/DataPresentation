package Map;

import Map.linkedList.Map;

public class Main {

    public static void main(String[] args) {
        Map map = new Map();
        char[] domain = {'B', 'o', 'g', 'd', 'a', 'n'};
        char[] range = {'S', 'P'};
        map.assign(domain, range);
    }
}
