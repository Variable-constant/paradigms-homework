package expression.operations;

import expression.GenericExpression;
import expression.calculation.CalculationType;

public class Square<T> extends UnaryOperation<T> {
    public Square(GenericExpression<T> exp, CalculationType<T> type) {
        super(exp, type);
    }

    @Override
    protected T evaluate(T x) {
        return type.square(x);
    }

    @Override
    protected String getOperation() {
        return "square";
    }
}
