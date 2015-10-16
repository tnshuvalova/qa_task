package use_inheritance;

import java.util.Arrays;
public class InheritanceIntro {
    public static void main(String[] args) {
//        AChild a = new AChild();
//        ACommon b = new ACommon();
//        ACommon b = new AParent();
//        System.out.println(a + " hohooh");
//        System.out.println(b);
        int[] ar = {1,2,3};
        System.out.println(Arrays.toString(ar));
        A a = new A();
        B b = new B();
        A b2 = new B();
        a.h1();
        a.h2();
        b.h1();
        b.h2();
        b2.h1();
        b2.h2();

    }
    public String sayHi() {
        return "hello!!!!";
    }
}
