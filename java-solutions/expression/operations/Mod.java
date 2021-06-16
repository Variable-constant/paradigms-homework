package expression.operations;

import expression.GenericExpression;
import expression.calculation.CalculationType;

public class Mod<T> extends AbstractOperation<T>{

    public Mod(GenericExpression<T> left, GenericExpression<T> right, CalculationType<T> type) {
        super(left, right, type);
    }

    protected String getOperation() {
        return "mod";
    }

    @Override
    protected T evaluate(T left, T right) {
        return type.mod(left, right);
    }
}
