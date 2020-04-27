package com.tutorial.collections;

import com.tutorial.models.Customer;

import java.util.HashMap;
import java.util.Map;

public class MapDemo {
    public static void show(){
         Map<String, Customer> map  = new HashMap<>();
         var c1 = new Customer("name1", "e1");
         var c2 = new Customer("name2","e2");
         map.put(c1.getEmail(), c1);
         map.put(c2.getEmail(), c2);
        System.out.println(map.get("e1"));
        var defaultCustomer = new Customer("unknown", "unknown");
        map.getOrDefault("e44", defaultCustomer);
        boolean found = map.containsKey("e1");
        System.out.println(found);
        map.replace("e1", defaultCustomer);
        System.out.println(map);

        for(var key : map.keySet()){
            System.out.println(key);
        }
        for(var entry : map.entrySet()){
            System.out.println(entry + " " +  entry.getKey() + " " +  entry.getValue());
        }

        for(var customer : map.values()){
            System.out.println(customer);
        }
    }
}
