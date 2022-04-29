package IO;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputStreamReaderDemo2 {
    public static void main(String[] args) throws IOException {
        //gbkReader();
        readgbk();
    }

    private static void readgbk() throws IOException {
        InputStreamReader isr = new InputStreamReader(new FileInputStream("GBK.txt"),"gbk");
        int len =0;
        while((len = isr.read()) != -1){
            System.out.print((char)len);
        }
        isr.close();
    }

    private static void gbkReader() throws IOException {
        //InputStreamReader isr = new InputStreamReader(new FileInputStream("abc4.txt"));
        InputStreamReader isr = new InputStreamReader(new FileInputStream("abc3.txt"));
        int len =0;
        while((len = isr.read()) != -1){
            System.out.print((char)len);
        }
        isr.close();
    }
}
