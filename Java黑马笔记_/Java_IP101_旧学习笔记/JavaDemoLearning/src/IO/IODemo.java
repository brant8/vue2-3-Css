package IO;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class IODemo {
    public static void main(String[] args) throws IOException {

        FileOutputStream fos = new FileOutputStream("C:\\Users\\Winbo\\IdeaProjects\\JavaDemoLearning\\abc.txt");
        fos.write('h');
        fos.write(49);
        fos.write(48);
        fos.close();

        FileOutputStream fos2 = new FileOutputStream("abc2.txt");
        fos2.write(49);
        fos2.write(48);
        fos2.write(48);

        byte[] bytes = "我还可以".getBytes();

        fos2.write(bytes);
        fos2.close();


    }
}
