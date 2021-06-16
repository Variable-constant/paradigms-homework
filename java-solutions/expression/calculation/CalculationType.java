package expression.calculation;

public interface CalculationType<T> {
    T parse(String string);
    T add(T left, T right);
    T divide(T left, T right);
    T multiply(T left, T right);
    T subtract(T left, T right);
    T negate(T exp);
    T abs(T exp);
    T square(T exp);
    T mod(T left, T right);
}
