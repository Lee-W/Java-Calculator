package leew.Operation.BinaryOperation;

/**
 * Created by LeeW on 6/27/15.
 */
public class OperationExp extends BinaryOperation{
    @Override
    public double getResult() {
        return Math.pow(firstNum, secondNum);
    }
}
