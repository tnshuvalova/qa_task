public class Plus extends BinaryOperation implements Evaluatable {

    public Plus(Evaluatable a, Evaluatable b){
        super(a, b);
    }

    public int evaluate(int value){
        return this.first.evaluate(value) + this.second.evaluate(value);
    }
}
