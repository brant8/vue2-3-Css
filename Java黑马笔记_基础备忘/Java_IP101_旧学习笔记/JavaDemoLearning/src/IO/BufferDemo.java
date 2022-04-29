package IO;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class BufferDemo {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("abc.txt");
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        bos.write("我把数据放进来了".getBytes());
        bos.flush();
        bos.close();

        FileInputStream fis = new FileInputStream("abc3.txt");
        BufferedInputStream bis = new BufferedInputStream(fis);
        bis.read(   );
        int len = 0;
        while ((len = bis.read())!= -1){
            System.out.print((char)len);
        }
        bis.close();
        //fis.close();
    }
}
