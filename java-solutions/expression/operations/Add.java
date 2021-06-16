package expression.operations;

import expression.GenericExpression;
import expression.calculation.CalculationType;

public class Add<T> extends AbstractOperation<T>{

    public Add(GenericExpression<T> left, GenericExpression<T> right, CalculationType<T> type) {
        super(left, right, type);
    }

    protected String getOperation() {
        return "+";
    }

    @Override
    protected T evaluate(T left, T right) {
        return type.add(left, right);
    }
}
