package com.eastValley.demo;

/**
 * @author java_shj
 * @desc
 * @createTime 2019/9/27 10:59
 **/
public class demo {
    public static void main(String[] args) {
        RandomTest();
        //int2Str();
    }

    private static void RandomTest() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Math.random()*9);
        }
        int a = 100;
        System.out.println(a);
    }

   static void int2Str(){
        String s1 = "2";
        Integer i = Integer.valueOf(s1);
        System.out.println(i);
       int i1 = Integer.parseInt(s1);
       System.out.println(i1);

   }
}
