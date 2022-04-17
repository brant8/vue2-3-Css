package IO;

import java.io.*;
import java.nio.Buffer;
import java.util.HashMap;

public class ChuShiBiao {
    public static void main(String[] args) throws IOException {
        HashMap<String, String> map = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader("abc.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("abc4.txt"));
        String line;
        while((line = br.readLine()) != null){
            //分割获取序号和文本内容
            String[] arr = line.split("\\.");
            //储存到hashmap中（key是有序，自动排序）
            map.put(arr[0],arr[1]);
        }
        System.out.println(map);
        for(String key : map.keySet()){
            String value = map.get(key);
            line = key + "." + value;
            bw.write(line);
            bw.newLine();
        }
        bw.close();
        br.close();

    }
}
