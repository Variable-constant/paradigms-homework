package expression.calculation;

public class DoubleType implements CalculationType<Double> {
    @Override
    public Double parse(String string) {
        return Double.parseDouble(string);
    }

    @Override
    public Double add(Double left, Double right) {
        return left + right;
    }

    @Override
    public Double divide(Double left, Double right) {
        return left / right;
    }

    @Override
    public Double multiply(Double left, Double right) {
        return left * right;
    }

    @Override
    public Double subtract(Double left, Double right) {
        return left - right;
    }

    @Override
    public Double negate(Double exp) {
        return -exp;
    }

    @Override
    public Double abs(Double exp) {
        return exp > 0 ? exp : -exp;
    }

    @Override
    public Double square(Double exp) {
        return exp * exp;
    }

    @Override
    public Double mod(Double left, Double right) {
        return left % right;
    }
}
