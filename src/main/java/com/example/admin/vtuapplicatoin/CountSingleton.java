package com.example.admin.vtuapplicatoin;

/**
 * Created by Admin on 07-08-2016.
 */
public class CountSingleton {
    private static CountSingleton obj;
    public Integer count;

    private CountSingleton() {}

    public static CountSingleton getInstance() {
        if(obj == null) {
            obj = new CountSingleton();
            obj.count = new Integer("0");
        }
        return obj;
    }
}
