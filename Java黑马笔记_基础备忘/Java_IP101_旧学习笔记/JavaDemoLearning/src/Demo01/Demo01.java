package Demo01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Demo01 {
    public static void main(String[] args) {

        ArrayList<Person> list = new ArrayList<>();
        list.add(new Person("zhang san",38));
        list.add(new Person("zhang sen",13));
        list.add(new Person("zhang sun",35));
        System.out.println(list);

        Collections.sort(list);
        System.out.println(list);


        HashMap<Integer,String> poker = new HashMap<>();
        poker.put(1,"a");
        poker.put(12,"a1");
        poker.put(13,"a3");
        poker.put(14,"a5");
        poker.put(17,"a5");
        poker.put(11,"a5");
        System.out.println(poker);

        for (int i = 0; i < poker.size(); i++) {
            System.out.println(poker.get(i));
        }
    }
}
