package expression.operations;

import expression.GenericExpression;
import expression.calculation.CalculationType;

public class Subtract<T> extends AbstractOperation<T>{

    public Subtract(GenericExpression<T> left, GenericExpression<T> right, CalculationType<T> type) {
        super(left, right, type);
    }

    protected String getOperation() {
        return "-";
    }

    @Override
    protected T evaluate(T left, T right) {
        return type.subtract(left, right);
    }
}
