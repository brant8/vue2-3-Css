package IO;

import jdk.jfr.SettingControl;

import java.io.*;
import java.util.Properties;
import java.util.Set;

public class PropDemo {
    public static void main(String[] args) throws IOException {

        show02();
    }

    private static void show02() throws IOException {
        Properties prop = new Properties();
        prop.load(new FileReader("abc3.txt"));
        Set<String> set = prop.stringPropertyNames();
        for (String key : set){
            String value = prop.getProperty(key);
            System.out.println(key + " - " + value);
        }
    }


    private static void show01() throws IOException {
        Properties prop = new Properties();
        prop.setProperty("迪丽热巴","167");
        prop.setProperty("古力娜扎","169");
        prop.setProperty("那儿扎哈","165");

        FileWriter fw = new FileWriter("abc3.txt");
        prop.store(fw,"save data");
        //#save data
        //#Tue Apr 27 13:48:18 EDT 2021
        //迪丽热巴=167
        //古力娜扎=169
        //那儿扎哈=165
        prop.store(new FileOutputStream("abc3.txt"), "here is comment");
        //\u8FEA\u4E3D\u70ED\u5DF4=167
        //\u53E4\u529B\u5A1C\u624E=169
        //\u90A3\u513F\u624E\u54C8=165
        fw.close();
    }
}
