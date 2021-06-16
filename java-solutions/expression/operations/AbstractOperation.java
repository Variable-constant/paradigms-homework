package expression.operations;

import expression.GenericExpression;
import expression.calculation.CalculationType;

public abstract class AbstractOperation<T> implements GenericExpression<T> {
    private final GenericExpression<T> left, right;
    protected final CalculationType<T> type;

    public AbstractOperation(GenericExpression<T> left, GenericExpression<T> right, CalculationType<T> type) {
        this.left = left;
        this.right = right;
        this.type = type;
    }

    protected abstract String getOperation();
    protected abstract T evaluate(T left, T right);

    @Override
    public T evaluate(T x, T y, T z) {
        return evaluate(left.evaluate(x, y, z), right.evaluate(x, y, z));
    }

    @Override
    public String toString() {
        return "(" + left + " " + getOperation() + " " + right + ")";
    }
}
