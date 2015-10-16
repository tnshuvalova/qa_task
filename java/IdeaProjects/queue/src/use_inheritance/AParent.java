package use_inheritance;

import javax.xml.soap.SAAJResult;

public class AParent extends ACommon {
    public int ap;
    public int bp;
    public String info;
    public void getInfo() {
        System.out.println(this.info);
    }
    public AParent() {
        System.out.println("default constructor AParent");
        ap = 10;
        bp = 20;
    }
    public AParent(int a) {
        System.out.println("1 parameter constructor AParent");
    }
    public String aPHello() {
        return "a parent - hello";
    }
    public String hello() {
        return "hello";
    }
    public AParent returnTypeDiffers() {
        return new AParent();
    }
    public final void noInherite() {
        System.out.println("no inherite me");
    }
    public String toString() {
        return this.getClass().getName() + "[ap=" + this.ap + "; bp=" + this.bp + "]";
    }
}
