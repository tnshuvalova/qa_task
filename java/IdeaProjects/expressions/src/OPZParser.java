import java.util.*;

public class OPZParser {
    private String parsed = "";

    public String parse(String expression) {
        String[] input = expression.split("\\s+");
        String op_notation = sortingStation(input);
        return op_notation;
    }

    private String sortingStation(String[] input) {
        Stack stack = new StackList();
        List<String> out = new ArrayList<String>();
        for (int i = 0; i < input.length; i++) {

        }
        return "";
    }
}
