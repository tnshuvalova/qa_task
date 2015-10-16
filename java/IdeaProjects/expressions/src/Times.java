public class Times extends BinaryOperation implements Evaluatable {

    public Times(Evaluatable a, Evaluatable b){
        super(a,b);
    }

    public int evaluate(int value){
        return this.first.evaluate(value) * this.second.evaluate(value);
    }
}
