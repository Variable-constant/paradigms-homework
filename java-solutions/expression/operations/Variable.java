package expression.operations;

import expression.GenericExpression;

public class Variable<T> implements GenericExpression<T> {
    private final String variable;
    public Variable(String x) {
        this.variable = x;
    }

    public T evaluate(T x, T y, T z) {
        switch (variable) {
            case "x": return x;
            case "y": return y;
            case "z": return z;
            default: return null;
        }
    }

    public String toString() {
        return this.variable;
    }
}
