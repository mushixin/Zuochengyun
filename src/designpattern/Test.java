package designpattern;

import java.util.HashMap;

public class Test {
    public static void main(String[] args) {
        Father f = new Father();
        f.print(new HashMap());

        Son s = new Son();
        s.print(new HashMap());

        Father f2 = new Son();
        f2.print(new HashMap());


    }
}
