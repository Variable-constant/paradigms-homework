package expression.operations;

import expression.GenericExpression;
import expression.calculation.CalculationType;

public class Abs<T> extends UnaryOperation<T> {
    public Abs(GenericExpression<T> exp, CalculationType<T> type) {
        super(exp, type);
    }

    @Override
    protected T evaluate(T x) {
        return type.abs(x);
    }

    @Override
    protected String getOperation() {
        return "Abs";
    }
}
