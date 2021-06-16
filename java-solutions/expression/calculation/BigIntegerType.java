package expression.calculation;

import expression.exceptions.DBZException;
import expression.exceptions.EvaluationException;

import java.math.BigInteger;

public class BigIntegerType implements CalculationType<BigInteger> {
    @Override
    public BigInteger parse(String string) {
        return new BigInteger(string);
    }

    @Override
    public BigInteger add(BigInteger left, BigInteger right) {
        return left.add(right);
    }

    @Override
    public BigInteger divide(BigInteger left, BigInteger right) {
        if (right.equals(BigInteger.ZERO)) {
            throw new DBZException(left + " / " + right);
        }
        return left.divide(right);
    }

    @Override
    public BigInteger multiply(BigInteger left, BigInteger right) {
        return left.multiply(right);
    }

    @Override
    public BigInteger subtract(BigInteger left, BigInteger right) {
        return left.subtract(right);
    }

    @Override
    public BigInteger negate(BigInteger exp) {
        return exp.negate();
    }

    @Override
    public BigInteger abs(BigInteger exp) {
        return exp.abs();
    }

    @Override
    public BigInteger square(BigInteger exp) {
        return multiply(exp, exp);
    }

    @Override
    public BigInteger mod(BigInteger left, BigInteger right) {
        if (right.equals(BigInteger.ZERO)) {
            throw new DBZException(left + " / " + right);
        }
        try {
            return left.mod(right);
        } catch (ArithmeticException e) {
            throw new EvaluationException(e.getMessage());
        }
    }
}
