package IO;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class InputStreamReaderDemo {
    public static void main(String[] args) throws IOException {
        write_utf_8();
        write_gbk();
    }

    private static void write_gbk() throws IOException {
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("abc3.txt"), "gbk");
        osw.write("你好");
        osw.flush();
        osw.close();
    }

    private static void write_utf_8() throws IOException {
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("abc4.txt"), "utf-8");
        osw.write("你好");
        osw.flush();
        osw.close();

    }
}
