package expression.operations;

import expression.GenericExpression;

public class Const<T> implements GenericExpression<T> {
    private final T value;

    public Const(T x) {
        this.value = x;
    }

    public T evaluate(T x, T y, T z) {
        return this.value;
    }

    public String toString() {
        return "(" + this.value.toString() + ")";
    }
}
