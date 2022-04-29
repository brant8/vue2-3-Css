package Net;

import com.sun.security.jgss.GSSUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ByteDemoTest {
    public static void main(String[] args) throws IOException {
        byte[] bytes = new byte[1024];
        System.out.println(bytes);
        bytes = "hello".getBytes();
        System.out.println(bytes);
        System.out.println(new String(bytes));

        System.out.println("--------------------");
        for (int i = 0; i < bytes.length; i++) {
            System.out.println("索引"+ i + "是" +bytes[i]+ ";" + (char)(bytes[i]));
        }
        System.out.println("--------------------");

        FileInputStream is = new FileInputStream("abc2.txt");
        int len = 0;
        bytes = new byte[1024];
        while ((len = is.read()) != -1){
            System.out.println(len);
            System.out.println((char)len);
        }
        for (int i = 0; i < bytes.length; i++) {
            System.out.println("索引"+ i + "是" +bytes[i]+ ";" + (char)(bytes[i]));
        }
    }
}
