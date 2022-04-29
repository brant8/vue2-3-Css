package IO;

import java.io.*;
import java.util.ArrayList;

public class ObjectOutputStreamDemo3 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ArrayList<Person> list = new ArrayList<>();
        list.add(new Person("张三",15));
        list.add(new Person("张四",17));
        list.add(new Person("张五",19));
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("abc4.txt"));
        oos.writeObject(list);
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("abc4.txt"));
        Object o = ois.readObject();
        ArrayList<Person> list2 = (ArrayList<Person>)o;
        for(Person l : list2){
            System.out.println(l);
        }
        ois.close();
        oos.close();
    }
}
