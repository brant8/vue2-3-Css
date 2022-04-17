package IO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class IOCopy {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("abc2.txt");
        FileOutputStream fos = new FileOutputStream("copy_abc.txt");
        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len=fis.read(bytes)) != -1){
            //System.out.println(len);
            fos.write(bytes,0,len);
        }
        fos.close();
        fis.close();
    }
}
