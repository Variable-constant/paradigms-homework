package expression.calculation;

import expression.exceptions.DBZException;

// :NOTE: дублирование кода с IntegerType
public class UncheckedType implements CalculationType<Integer>{
    @Override
    public Integer parse(String string) {
        return Integer.parseInt(string);
    }

    @Override
    public Integer add(Integer left, Integer right) {
        return left + right;
    }

    @Override
    public Integer divide(Integer left, Integer right) {
        if (right == 0) {
            throw new DBZException(left + "/" + right);
        }
        return left / right;
    }

    @Override
    public Integer multiply(Integer left, Integer right) {
        return left * right;
    }

    @Override
    public Integer subtract(Integer left, Integer right) {
        return left - right;
    }

    @Override
    public Integer negate(Integer exp) {
        return -exp;
    }

    @Override
    public Integer abs(Integer exp) {
        return exp > 0 ? exp : -exp;
    }

    @Override
    public Integer square(Integer exp) {
        return multiply(exp, exp);
    }

    @Override
    public Integer mod(Integer left, Integer right) {
        if (right == 0) {
            throw new DBZException(left + "/" + right);
        }
        return left % right;
    }
}
