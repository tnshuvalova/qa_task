package use_inheritance;

public class AChild extends AParent {
    public int ac;
    public int[] bc = {1,3,4};

    public AChild() {
        super();
        System.out.println("default constructor AChild");
    }
    public AChild(int a) {
        this();
        System.out.println("1 parameter constructor AChild");
    }
    public String aChHello() {
        return "a child - hello";
    }
    public String hello() {
        return "goodbye";
    }
    public AChild returnTypeDiffers() {
        return new AChild();
    }
// does not work
//    public void noInherite() {
//
//    }
}
