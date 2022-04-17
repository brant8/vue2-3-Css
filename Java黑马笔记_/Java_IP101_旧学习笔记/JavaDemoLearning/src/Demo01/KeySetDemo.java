package Demo01;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class KeySetDemo {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("zhao",32);
        map.put("qian",23);
        map.put("sun",43);

        Set<String> set = map.keySet();
        Iterator<String> it = set.iterator();
        while(it.hasNext()){
            String key = it.next();

            Integer value = map.get(key);
            System.out.println("key: " + key + "\\t tvalue: " + value);
        }
    }
}
