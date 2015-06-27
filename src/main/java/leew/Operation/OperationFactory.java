package leew.Operation;

import leew.Operation.BinaryOperation.*;
import leew.Operation.UnaryOperatoin.*;

/**
 * Created by LeeW on 6/27/15.
 */
public class OperationFactory {
    public static Operation createOperation(String operate) {
        Operation oper = null;
        switch (operate) {
            case "+/-":
                oper = new OperationInvert();
                break;
            case "/":
                oper = new OperationDiv();
                break;
            case "*":
                oper = new OperationMul();
                break;
            case "-":
                oper = new OperationSub();
                break;
            case "+":
                oper = new OperationAdd();
                break;
            case "√":
                oper = new OperationSqrt();
                break;
            case "%":
                oper = new OperationMod();
                break;
            case "!":
                oper = new OperationFactorial();
                break;
            case "log":
                oper = new OperationLog();
                break;
            case "exp":
                oper = new OperationExp();
                break;
            default:
                break;
        }
        return oper;
    }
}
