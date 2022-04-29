package Demo01;

import java.util.HashMap;
import java.util.Map;

public class HashMapDemo {
    public static void main(String[] args) {
        show01();
    }

    private static void show01() {
        Map<String, String> map = new HashMap<>();
        String v1 = map.put("lichen", "fanbb1");
        String v2 = map.put("lichen", "fanbb2");
        System.out.println("v2: "+v2);
        System.out.println(map);

        System.out.println(map.remove("lichen"));
    }
}
