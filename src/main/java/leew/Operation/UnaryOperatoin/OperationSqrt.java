package leew.Operation.UnaryOperatoin;

/**
 * Created by LeeW on 6/27/15.
 */
public class OperationSqrt extends UnaryOperation {
    @Override
    public double getResult() {
        return Math.sqrt(firstNum);
    }
}
