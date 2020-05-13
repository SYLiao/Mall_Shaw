package me.shaw.mall.test;

import lombok.Data;
import org.springframework.web.client.RestTemplate;

import javax.jnlp.IntegrationService;
import java.net.HttpURLConnection;
import java.util.*;

public class Problem {

    public static void main(String[] args){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(41);
        list.add(8);
        list.add(3);
        list.add(4);
        List<Integer> result = findNumber(list);
        for(int num : result){
            System.out.println(num);
        }
        char a = 'a';
    }

    static List<Integer> findNumber(ArrayList<Integer> numbers){
        Map<Integer, Integer> numMap = new HashMap<>();
        int maxNum = 0;
        for(int i = 0; i < numbers.size(); i++){
            if(numbers.get(i) > maxNum){
                maxNum = numbers.get(i);
            }
            numMap.put(numbers.get(i), 0);
        }
        int init1 = 1, init2 = 1;
        if(numMap.containsKey(1)){
            numMap.put(1, 1);
        }
        for(int i = 2; i <= maxNum; i++){
            int temp = init2;
            init2 += init1;
            init1 = temp;
            if(numMap.containsKey(i)){
                numMap.put(i, init2);
            }
        }
        List<Integer> result = new ArrayList<>();
        for(int num : numbers){
            if(num <= 0){
                result.add(0);
            }
            else{
                result.add(numMap.get(num));
            }
        }
        return result;
    }

}

