package FileDemo;

import java.io.File;

public class FileDemo02 {
    public static void main(String[] args) {
//        show();
//        File parent = new File("c:\\");
//        File file = new File(parent,"hello.java");
//        System.out.println(file);
//        show03();
//        show04();
    show05();
    }

    private static void show05() {
        File f1 = new File("show05\\folder");
        boolean b1 = f1.mkdirs();
        System.out.println(b1);
    }

    private static void show04() {
        File f1 = new File("C:\\develp\\a\\1.jpg");
        long l1 = f1.length();
        System.out.println(f1.exists());

    }

    private static void show03() {
        File f1 = new File("C:\\Users\\..\\a.txt");
        String absolutePath = f1.getAbsolutePath();
        System.out.println(absolutePath);
        File f2= new File("a.txt");
        System.out.println(f2.getAbsolutePath());
        System.out.println(f1.getPath());
        String path2 = f2.getPath();
        System.out.println(path2);
    }

    private static void show() {
        File file = new File("C:\\abc\\def");
        System.out.println(file);
    }
}
