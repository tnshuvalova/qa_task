public class Minus extends BinaryOperation implements Evaluatable {
    static Const param = new Const(0);

    public Minus(Evaluatable a) {
        this(param, a);
    }

    public Minus(Evaluatable a, Evaluatable b){
        super(a,b);
    }

    public int evaluate(int value){

        return first.evaluate(value) - second.evaluate(value);
    }
}
