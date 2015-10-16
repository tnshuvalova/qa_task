public class Main {
    static int depth = 0;
    public static void main(String[] args) {
        f(1);
    }

    public static void spaces() {
        for (int i = 0; i < depth; i++) {
            System.out.print(" ");
        }
    }

    public static void in(int x) {
        spaces();
        System.out.println("in x = " + x);
        depth++;
    }

    public static void out(int x) {
        depth--;
        spaces();
        System.out.println("out x = " + x);
    }

    public static boolean check(int x) {
        return x <= 15 ? true : false;
    }

    public static void f(int x) {
        in(x);
        if(check(x)) {
            f(x*2);
        } else {
            return;
        }
        out(x);
    }
}
