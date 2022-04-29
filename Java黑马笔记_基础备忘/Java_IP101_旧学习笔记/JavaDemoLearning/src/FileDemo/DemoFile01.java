package FileDemo;

import java.io.File;

public class DemoFile01 {
    public static void main(String[] args) {
        show02();
    }

    private static void show02() {
        File file = new File("C:\\Users\\Winbo\\IdeaProjects\\JavaDemoLearning");
        File[] files = file.listFiles();
        System.out.println(files);
        for(File f: files){
            System.out.println(f);
        }
    }

    private static void show01() {
        File file = new File("C:\\Users\\Winbo\\IdeaProjects\\JavaDemoLearning");
        String[] arr = file.list();
        System.out.println(arr);
        for(String filename: arr){
            System.out.println(filename);
        }
    }
}
