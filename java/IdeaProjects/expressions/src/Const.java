public class Const implements Evaluatable {
    int value;
    
    public Const(int c){
        value = c;
    }

    public int evaluate(int v){
        return value;
    }
}
