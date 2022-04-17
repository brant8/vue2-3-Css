package IO;

import java.io.FileReader;
import java.io.IOException;

public class ReverseDemo {
    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader("GBK.txt");
        int len = 0;
        while ((len = fr.read()) != -1){
            System.out.println(len);
        }
        fr.close();
    }
}
