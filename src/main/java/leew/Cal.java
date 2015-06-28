package leew;

import leew.Operation.Operation;
import leew.Operation.OperationFactory;

import java.util.*;

/**
 * Created by LeeW on 6/27/15.
 */
public class Cal {
    private static final String[] BINARY_OPERATOR = {"+", "-", "*", "/", "%", "^"};
    private static final Map<String, Integer> OP_PRIORITY;
    static {
        Map<String, Integer> opMap = new HashMap<>();
        opMap.put("^", 2);
        opMap.put("*", 3);
        opMap.put("/", 3);
        opMap.put("%", 3);
        opMap.put("+", 3);
        opMap.put("-", 3);
        OP_PRIORITY = Collections.unmodifiableMap(opMap);
    }
   
    public static Double calculate(Deque<String> equation) {
        List<String> postfixEq = infixToPostfix(equation);
        if (postfixEq.size() == 1) {
            return Double.parseDouble(postfixEq.get(0));
        } else {
            String operand1, operand2;
            Stack<String> stack = new Stack<>();
            for (String s : postfixEq) {
                if (!isBinOp(s)) {
                    stack.push(s);
                } else {
                    operand2 = stack.pop();
                    operand1 = stack.pop();

                    Operation oper;
                    oper = OperationFactory.createOperation(s);
                    oper.setFirstNum(Double.parseDouble(operand1));
                    oper.setSecondNum(Double.parseDouble(operand2));
                    stack.push(String.valueOf(oper.getResult()));
                }
            }
            return Double.parseDouble(stack.pop());
        }
    }

    /**
     * Turn equation to postfix and handle unary operations
     * @param equation
     * @return posfixResult
     */
    private static List<String> infixToPostfix(Deque<String> equation) {
        List<String> postfixResult = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        for (String token : equation) {
            if (isBinOp(token)) {
                while (!stack.isEmpty() &&
                        OP_PRIORITY.get(stack.peek()) <= OP_PRIORITY.get(token)) {
                    postfixResult.add(stack.pop());
                }
                stack.push(token);
            } else if (isNumeric(token)) {
                postfixResult.add(token);
            } else if (isUnaryOperation(token)) {
                postfixResult.add(operateUnaryOp(token).toString());
            }
        }

        while (!stack.isEmpty()) {
            postfixResult.add(stack.pop());
        }

        return postfixResult;
    }

    private static boolean isUnaryOperation(String operand) {
        if (operand.contains("factorial") || operand.contains("log") || operand.contains("sqrt"))
            return true;
        return false;
    }
    
    private static Double operateUnaryOp(String operand) {
        String operator = "";
        String prefix = operand.substring(0, operand.indexOf("("));
        switch (prefix) {
            case "factorial":
                operator = "!";
                break;
            case "log":
                operator = "log";
                break;
            case "sqrt":
                operator = "√";
                break;
        }

        operand = operand.substring(operand.indexOf('(') + 1, operand.lastIndexOf(')'));
        if (isUnaryOperation(operand)) {
            operand = operateUnaryOp(operand).toString();
        }

        Operation oper;
        oper = OperationFactory.createOperation(operator);
        oper.setFirstNum(Double.parseDouble(operand));

        return oper.getResult();
    }

    public static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isBinOp(String e) {
        return Arrays.asList(BINARY_OPERATOR).contains(e);
    }

    public static String invert(String e) {
        Double d = Double.parseDouble(e);
        return String.valueOf(-d);
    }

    public static boolean isPositive(String e) {
        Double d = Double.parseDouble(e);
        return d >= 0;
    }
}
