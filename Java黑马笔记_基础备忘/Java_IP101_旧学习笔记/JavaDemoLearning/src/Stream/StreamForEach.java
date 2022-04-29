package Stream;

import java.util.ArrayList;
import java.util.stream.Stream;

public class StreamForEach {
    public static void main(String[] args) {
//        Stream<String> stream = Stream.of("zhang","du","bing","jia");
////        stream.forEach((String name)->{
////            System.out.println(name);
////        });
//        stream.forEach(name-> System.out.println(name));

        Stream<String> stream1 = Stream.of("zhang san","zhang wu ji","zhou zhi ruo","zhao min");
        Stream<String> stream2 = stream1.filter((String name)->{return name.startsWith("zhang");});
        System.out.println(stream2);
        stream2.forEach(name-> System.out.println(name));

        Stream<String> s= Stream.of("1","2","3","4");
        Stream<Integer> s2 = s.map((String st)->{
            return Integer.parseInt(st);
        });
        s2.forEach(i-> System.out.println(i));


        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        Stream<Integer> stream = list.stream();
        //stream.forEach(i-> System.out.println(i));
        long count = stream.count();
        System.out.println(count);

        String[] arr = {"a","b","c"};
        Stream<String> stream4 = Stream.of(arr);
        Stream<String> stream41 = stream4.limit(3);
        stream41.forEach(name-> System.out.print(name));
    }
}
