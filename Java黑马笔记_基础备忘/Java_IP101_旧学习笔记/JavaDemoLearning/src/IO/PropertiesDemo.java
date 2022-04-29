package IO;

import java.util.Properties;
import java.util.Set;

public class PropertiesDemo {
    public static void main(String[] args) {
        Properties prop = new Properties();
        prop.setProperty("小赵","168");
        prop.setProperty("迪丽热巴","170");
        prop.setProperty("古力娜扎","164");
        //
        Set<String> set = prop.stringPropertyNames();

        for (String key:set){
            String value = prop.getProperty(key);
            System.out.println(key+" = " +value);
        }
    }
}
