import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Calc {
    public static String[] parseArgs(String argsString){
        String[] args = argsString.split("\\s+");
        for (int i = 0; i < args.length; i++) {
            args[i].trim();
        }
        return args;
    }

    public static void main(String[] args) throws IOException{
        String function = "3 + 4";
        String[] functionSplitted = function.split("\\s+");
        Stack<String> out = new StackList<String>();
//        List<String> functionSymbols = new ArrayList<String>();
        for (int i = 0; i < functionSplitted.length; i++) {
            if(functionSplitted[i].equals("+")) {
                continue;
            } else {
                out.push(functionSplitted[i]);
            }
        }
        int result;
        while (!out.isEmpty()) {
            result =
        }

    }

}
