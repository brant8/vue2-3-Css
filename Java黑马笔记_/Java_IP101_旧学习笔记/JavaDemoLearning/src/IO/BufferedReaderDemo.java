package IO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferedReaderDemo {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("abc4.txt"));
        //String line = br.readLine();
        //line = br.read();
        //System.out.println(line);
        String line;
        while ((line = br.readLine()) != null){
            System.out.print(line);
        }

        br.close();
    }
}
