package FileDemo;

import java.io.File;

public class DemoFilter01 {
    public static void main(String[] args) {
        File f = new File("C:\\Users\\Winbo\\IdeaProjects\\JavaDemoLearning\\show05");
        getAllFile(f);
        System.out.println("======");
        getList(f);
    }

    private static void getList(File dir) {
        String[] files = dir.list();
        for(String f: files){

            System.out.println(f);
        }
    }

    private static void getAllFile(File dir) {
        File[] files = dir.listFiles(new FileFilterDemoImpl());
        for(File f: files){
            if(f.isDirectory() ){
                getAllFile(f);
            }else {
                System.out.println(f);
            }
        }
    }
}
