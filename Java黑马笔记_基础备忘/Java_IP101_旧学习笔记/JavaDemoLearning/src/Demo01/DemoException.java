package Demo01;

import java.io.IOException;

public class DemoException {
    public static void main(String[] args) {
        try{
            readFile("c:\\a.txtt");
            System.out.println("try...");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("catch...");
        }finally {
            System.out.println("before continued...");
        }
        System.out.println("continued...");
    }

    private static void readFile(String fileName) throws IOException {
        if(!fileName.endsWith(".txt")){
            throw new IOException("exception message");
        }
    }
}
