package IO;

import java.io.*;

public class ConvertDemo {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(new FileInputStream("GBK.txt"), "gbk");
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("utf.txt"));
        int len= 0;
        while((len = isr.read()) != -1){
            osw.write(len);
        }
        isr.close();
        osw.close();
    }
}
