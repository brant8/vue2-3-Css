package IO;

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class PrintStreamDemo {
    public static void main(String[] args) throws FileNotFoundException {
        PrintStream ps = new PrintStream("abc3.txt");
        ps.write(97);
        ps.print(9.9);


        ps.close();
    }
}
