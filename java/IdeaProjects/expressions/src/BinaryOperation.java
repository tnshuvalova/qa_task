public abstract class BinaryOperation implements Evaluatable {
    protected Evaluatable first;
    protected Evaluatable second;

    protected BinaryOperation(Evaluatable a, Evaluatable b){
        first = a;
        second = b;
    }
}
