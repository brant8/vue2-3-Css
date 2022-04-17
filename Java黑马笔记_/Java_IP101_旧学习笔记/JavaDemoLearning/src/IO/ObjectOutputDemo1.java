package IO;

import java.io.*;

public class ObjectOutputDemo1 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("abc4.txt"));
        oos.writeObject(new Person("美女", 16));
        oos.close();

    }
}
