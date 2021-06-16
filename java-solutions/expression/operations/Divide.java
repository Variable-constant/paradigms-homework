package expression.operations;

import expression.GenericExpression;
import expression.calculation.CalculationType;

public class Divide<T> extends AbstractOperation<T>{

    public Divide(GenericExpression<T> left, GenericExpression<T> right, CalculationType<T> type) {
        super(left, right, type);
    }

    protected String getOperation() {
        return "/";
    }

    @Override
    protected T evaluate(T left, T right) {
        return type.divide(left, right);
    }
}
