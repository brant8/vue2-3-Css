package IO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class IOInputDemo {
    public static void main(String[] args) throws IOException {
//        FileInputStream fis = new FileInputStream("abc2.txt");
//        int len = 0;
//        while((len = fis.read()) != -1){
//            System.out.println((char) len);
//        }
//        fis.close();

        FileInputStream fis2 = new FileInputStream("abc.txt");
        byte[] bytes = new byte[2];
        int len2 = fis2.read(bytes);
        System.out.println(len2);
        System.out.println(Arrays.toString(bytes));
        System.out.println(new String(bytes));
        fis2.close();
    }
}
