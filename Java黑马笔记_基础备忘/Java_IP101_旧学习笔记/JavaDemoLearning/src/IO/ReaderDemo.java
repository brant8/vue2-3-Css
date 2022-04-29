package IO;

import java.io.FileReader;
import java.io.IOException;

public class ReaderDemo {
    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader("abc2.txt");
//        int len = 0;
//        while((len = fr.read()) != -1){
//            System.out.print((char)len);
//        }

        char[] cs = new char[1024];
        int len = 0;
        while((len = fr.read(cs)) != -1){
            //String(char[] value)
            System.out.println(new String(cs, 0, len));
        }

        fr.close();
    }
}
