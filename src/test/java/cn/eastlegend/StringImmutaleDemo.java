package cn.eastlegend;

import javax.sound.midi.Soundbank;
import java.util.Random;

/**
 * @author java_shj
 * @desc  测试字符串的不可变性
 * @createTime 2020/1/10 14:37
 **/
public class StringImmutaleDemo {
    public static void main(String[] args) {
//        demo1();
        intInRange(10);
    }

    /**
     * 产生特定范围的正数
     * @param range 正数的最大值
     */
    private static void intInRange(int range) {
        Random random = new Random();
        for(int i =0; i <10; i++){
            int i1 = random.nextInt(range);
            System.out.println(i1);
        }
    }

    private static void demo1() {
        String s = "";
        for(int i=0; i < 4; i++){
            s = s + i;
            System.out.println(s);
        }
    }
}
