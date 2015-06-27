package leew.Operation.UnaryOperatoin;

/**
 * Created by LeeW on 6/27/15.
 */
public class OperationLog extends UniaryOperation {
    @Override
    public double getResult() {
        return Math.log(firstNum);
    }
}
