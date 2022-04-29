package Stream;

import java.util.ArrayList;
import java.util.stream.Stream;

public class ArrayListNamesOptimized {
    public static void main(String[] args) {
        ArrayList<String> one = new ArrayList<>();
        one.add("张三");
        one.add("张无忌");
        one.add("迪丽热巴");
        one.add("石破天");
        one.add("石中玉");
        one.add("老子");
        one.add("庄子");
        one.add("洪七公");
        Stream<String> oneStream = one.stream().filter(name->name.length() ==3).limit(3);

        ArrayList<String> two = new ArrayList<>();
        two.add("古力娜扎");
        two.add("赵丽颖");
        two.add("张三丰");
        two.add("张天爱");
        two.add("张丽");
        two.add("荀子");
        two.add("天子");
        two.add("欧阳锋");
        Stream<String> twoStream = two.stream().filter(name->name.startsWith("张")).skip(2);

        Stream.concat(oneStream,twoStream).map(name->new Person(name)).forEach(p-> System.out.println(p));

    }
}
