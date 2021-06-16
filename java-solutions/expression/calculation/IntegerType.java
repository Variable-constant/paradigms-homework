package expression.calculation;

import expression.exceptions.DBZException;
import expression.exceptions.OverflowException;

public class IntegerType extends UncheckedType {
    @Override
    public Integer parse(String string) {
        return Integer.parseInt(string);
    }

    @Override
    public Integer add(Integer left, Integer right) {
        if ((right > 0 && Integer.MAX_VALUE - right < left || right < 0 && Integer.MIN_VALUE - right > left)) {
            throw new OverflowException(left + " + " + right);
        }
        return super.add(left, right);
    }

    @Override
    public Integer divide(Integer left, Integer right) {
        if (left == Integer.MIN_VALUE && right == -1) {
            throw new OverflowException(left + "/" + right);
        }
        return super.divide(left, right);
    }

    @Override
    public Integer multiply(Integer left, Integer right) {
        if (left > 0 && (right > 0 && left > Integer.MAX_VALUE / right || right < 0 &&
                right < (Integer.MIN_VALUE / left)) || left < 0 && (right > 0 &&
                left < Integer.MIN_VALUE / right || right < 0 &&
                right < (Integer.MAX_VALUE / left))) {
            throw new OverflowException(left + " * " + right);
        }
        return super.multiply(left, right);
    }

    @Override
    public Integer subtract(Integer left, Integer right) {
        if (right > 0 && Integer.MIN_VALUE + right > left ||
                right < 0 && Integer.MAX_VALUE + right < left) {
            throw new OverflowException(left + " - " + right);
        }
        return super.subtract(left, right);
    }

    @Override
    public Integer negate(Integer exp) {
        if (exp == Integer.MIN_VALUE) {
            throw new OverflowException("Negate of Integer.MIN_VALUE");
        }
        return super.negate(exp);
    }

    @Override
    public Integer abs(Integer exp) {
        if (exp == Integer.MIN_VALUE) {
            throw new OverflowException("abs of Integer.MIN_VALUE");
        }
        return super.abs(exp);
    }
}
