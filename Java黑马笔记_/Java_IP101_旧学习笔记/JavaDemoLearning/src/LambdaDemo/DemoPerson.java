package LambdaDemo;

import java.util.Arrays;
import java.util.Comparator;

public class DemoPerson {
    public static void main(String[] args) {
        Person[] arr={
                new Person("柳岩",38),
                new Person("迪丽热巴",29),
                new Person("古力娜扎",40),
        };
        //对数组中的Person对象使用Arras的sort方法通过年龄进行升序（前边-后边）排序
        Arrays.sort(arr, new Comparator<Person>(){
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getAge()-o2.getAge();
            }
        });

        Arrays.sort(arr,(Person o1, Person o2)->{
            return o1.getAge()-o2.getAge();
        });

        for(Person p: arr){
            System.out.println(p);
        }
    }
}
