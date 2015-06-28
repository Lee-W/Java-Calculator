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
        opMap.put("+", 4);
        opMap.put("-", 4);
        OP_PRIORITY = Collections.unmodifiableMap(opMap);
    }
    
    public static Double calculate(Deque<String> equation) {
        List<String> postfixEq = infixToPostfix(equation);
        if (postfixEq.size() == 1)
            return Double.parseDouble(postfixEq.get(0));
        
        String operand1, operand2, result;
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
    
    private static Double unaryOperate(String operand) {
        String operator = "";
        if (operand.charAt(0) == 'f') {
            operator = "!";
        } else if (operand.charAt(0) == 'l') {
            operator = "log";
        } else if (operand.charAt(0) == 's') {
            operator = "√";
        }
        
        operand = operand.substring(operand.indexOf('(')+1, operand.lastIndexOf(')'));
        if (isUnaryOperation(operand)) {
            operand = unaryOperate(operand).toString();
        }
        
        Operation oper;
        oper = OperationFactory.createOperation(operator);
        oper.setFirstNum(Double.parseDouble(operand));
        
        
        return oper.getResult();
    }
    
    private static boolean isUnaryOperation(String operand) {
        if (operand.contains("factorial"))
            return true;
        else if (operand.contains("log"))
            return true;
        else if (operand.contains("sqrt"))
            return true;
        return false;
    }
    
    private static List<String> parse(String equation) {
        List<String> splitedEquation = new ArrayList<>();
        String tmpToken = "";
        for (Character e : equation.toCharArray()) {
            if (isNumeric(e.toString()) || e == '.') {
                tmpToken += e;
            } else if (isBinOp(e.toString())) {
                splitedEquation.add(tmpToken);
                splitedEquation.add(e.toString());
                tmpToken = "";
            }
        }
        splitedEquation.add(tmpToken);
        
        return splitedEquation; 
    }
    
    private static List<String> infixToPostfix(Deque<String> equation) {
        List<String> postFixResult = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        for (String token : equation) {
            if (isBinOp(token)) {
                while (!stack.isEmpty() &&
                        OP_PRIORITY.get(stack.peek()) <= OP_PRIORITY.get(token)) {
                    postFixResult.add(stack.pop());
                }
                stack.push(token);
            } else if (isNumeric(token)) {
                postFixResult.add(token);
            } else if (isUnaryOperation(token)) {
                postFixResult.add(unaryOperate(token).toString());
            }
        }
        
        while (!stack.isEmpty()) {
            postFixResult.add(stack.pop());
        }
        
        return postFixResult;
    }
    
    public static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch(NumberFormatException nfe) {
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
