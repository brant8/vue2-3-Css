package IO;

import java.io.FileWriter;
import java.io.IOException;

public class WriterDemo {
    public static void main(String[] args) throws IOException {
        FileWriter fw = new FileWriter("abc3.txt");
        char[] cs = {'c','a','c','b'};
        fw.write(cs);
        fw.close();
    }
}
