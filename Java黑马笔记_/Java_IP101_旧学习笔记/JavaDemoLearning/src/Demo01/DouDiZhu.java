package Demo01;

import com.sun.security.jgss.GSSUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class DouDiZhu {
    public static void main(String[] args) {
        //1.准备牌
        //组装好的牌
        HashMap<Integer,String> poker = new HashMap<>();
        //牌索引
        ArrayList<Integer> pokerIndex = new ArrayList<>();
        //花色和牌号
        List<String> colors = List.of("♠","♥","♣","♢");
        List<String> numbers = List.of("2","A","K","Q","J","10","9","8","7","6","5","4","3");
        int index = 0;
        poker.put(index,"大王");
        pokerIndex.add(index);
        index++;
        poker.put(index,"小王");
        pokerIndex.add(index);
        index++;
        for (String number : numbers){
            for(String color : colors){
                poker.put(index, color+number);
                pokerIndex.add(index);
                index++;
            }
        }
        System.out.println(poker);
        System.out.println(pokerIndex);
        //洗牌
        Collections.shuffle(pokerIndex);
        //发牌
        ArrayList<Integer> player01 = new ArrayList<>();
        ArrayList<Integer> player02 = new ArrayList<>();
        ArrayList<Integer> player03 = new ArrayList<>();
        ArrayList<Integer> diPai = new ArrayList<>();
        for (int i = 0; i < pokerIndex.size(); i++) {
            Integer in = pokerIndex.get(i);
            if (i >= 51) {
                diPai.add(in);
            }else if (i%3==0){
                player01.add(in);
            }else if (i%3==1){
                player02.add(in);
            }else if (i%3==2){
                player03.add(in);
            }
        }
        //排序牌
        Collections.sort(player01);
        Collections.sort(player02);
        Collections.sort(player03);
        Collections.sort(diPai);

        //看牌
        lookPoker("刘德华",poker, player01);
        lookPoker("周润发",poker, player02);
        lookPoker("周星驰",poker, player03);
        lookPoker("底牌",poker, diPai);
    }
//看牌
    public static void lookPoker(String name, HashMap<Integer,String> poker, ArrayList<Integer> list){
        System.out.print(name + ": ");
        for(Integer key: list){
            String value = poker.get(key);
            System.out.print(value + " ");
        }
        System.out.println();
    }
}
