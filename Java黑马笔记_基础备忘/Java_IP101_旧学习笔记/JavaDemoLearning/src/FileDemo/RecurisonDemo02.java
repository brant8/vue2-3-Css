package FileDemo;

import java.io.File;

public class RecurisonDemo02 {
    public static void main(String[] args) {
        File f = new File("C:\\Users\\Winbo\\IdeaProjects\\JavaDemoLearning\\show05");
        getAllFile(f);
    }

    private static void getAllFile(File dir) {
        File[] files = dir.listFiles();
        for(File f: files){
            if(f.isDirectory() ){
                getAllFile(f);
            }
            String name = f.getName();
            String path = f.getPath();
            String s = f.toString();

            boolean b =s.endsWith(".java");

            if(b){
                System.out.println("*"+s+"*");
            }

            System.out.println(f);
        }
    }
}
