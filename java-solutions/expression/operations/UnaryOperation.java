package expression.operations;

import expression.GenericExpression;
import expression.calculation.CalculationType;

public abstract class UnaryOperation<T> implements GenericExpression<T> {
    protected final GenericExpression<T> expression;
    protected final CalculationType<T> type;

    public UnaryOperation(GenericExpression<T> expression, CalculationType<T> type) {
        this.expression = expression;
        this.type = type;
    }

    protected abstract T evaluate(T x);
    protected abstract String getOperation();

    @Override
    public T evaluate(T x, T y, T z) {
        return evaluate(expression.evaluate(x, y, z));
    }

    @Override
    public String toString() {
        return "(" + getOperation() + expression + ")";
    }
}

