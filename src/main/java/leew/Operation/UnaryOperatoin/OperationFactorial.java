package leew.Operation.UnaryOperatoin;

/**
 * Created by LeeW on 6/27/15.
 */
public class OperationFactorial extends UniaryOperation {

    @Override
    public double getResult() {
        double result = 1;
        for (int i = 1; i < firstNum+1; i++)
            result *= i;
        return result;
    }
}
