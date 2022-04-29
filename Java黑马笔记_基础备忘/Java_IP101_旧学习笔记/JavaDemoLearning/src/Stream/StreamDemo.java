package Stream;

import java.util.ArrayList;
import java.util.List;

public class StreamDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("周芷若");
        list.add("张无忌");
        list.add("赵敏");
        list.add("张三丰");
        list.add("老五");

        list.stream().filter(name->name.startsWith("张"))
                .filter(name->name.length()==3)
                .forEach(name-> System.out.println(name));
    }
}
