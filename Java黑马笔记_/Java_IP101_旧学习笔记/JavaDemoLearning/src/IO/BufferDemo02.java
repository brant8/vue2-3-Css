package IO;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.Buffer;

public class BufferDemo02 {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("abc4.txt"));
        for(int i = 0; i< 10; i++){
            bw.write("船只博客");
            bw.write("\r\n");
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }
}
