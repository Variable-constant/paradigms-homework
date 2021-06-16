package expression.operations;

import expression.GenericExpression;
import expression.calculation.CalculationType;

public final class Negate<T> extends UnaryOperation<T> {

    public Negate(GenericExpression<T> first, CalculationType<T> type) {
        super(first, type);
    }

    @Override
    protected T evaluate(T x) {
        return type.negate(x);
    }

    @Override
    protected String getOperation() {
        return "-";
    }
}