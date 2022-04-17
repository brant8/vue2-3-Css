package Demo01;

import java.util.HashMap;
import java.util.Scanner;

public class DemoLinkHashMap {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String st = sc.next();
        HashMap<Character, Integer> map = new HashMap<>();
        for(char c : st.toCharArray()){
            System.out.print(c+"...");
            if(map.containsKey(c)){
                Integer value = map.get(c);
                System.out.println(c +" value " + value +".");
                value++;
                map.put(c,value);
            }else {
                map.put(c,1);
            }
        }
        for(Character key : map.keySet()){
            Integer value = map.get(key);
            System.out.println(key + "=" +value);
        }
    }
}
