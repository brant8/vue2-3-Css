package IO;

import java.io.FileInputStream;
import java.io.IOException;

public class IOInputDemo2 {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("abc.txt");
        byte[] bytes = new byte[1024];
        int len = 0;
        while((len = fis.read()) != -1){
            System.out.println(new String(bytes,0,len));
        }
    }
}
