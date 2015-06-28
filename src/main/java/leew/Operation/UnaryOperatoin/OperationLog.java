package leew.Operation.UnaryOperatoin;

/**
 * Created by LeeW on 6/27/15.
 */
public class OperationLog extends UnaryOperation {
    @Override
    public double getResult() {
        return Math.log(firstNum) / Math.log(10);
    }
}
