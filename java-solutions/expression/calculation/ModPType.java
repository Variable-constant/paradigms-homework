package expression.calculation;

import expression.exceptions.DBZException;

public class ModPType implements CalculationType<Integer> {
    private final static int p = 1009;
    @Override
    public Integer parse(String string) {
        return mathMod(Integer.parseInt(string));
    }

    @Override
    public Integer add(Integer left, Integer right) {
        return mathMod(left + right);
    }

    @Override
    public Integer divide(Integer left, Integer right) {
        if (right == 0) {
            throw new DBZException(left + "/" + right);
        }
        return multiply(left, pow(right));
    }

    @Override
    public Integer multiply(Integer left, Integer right) {
        return mathMod(left * right);
    }

    @Override
    public Integer subtract(Integer left, Integer right) {
        return mathMod(left - right);
    }

    @Override
    public Integer negate(Integer exp) {
        return mathMod(-exp);
    }

    @Override
    public Integer abs(Integer exp) {
        return mathMod(exp > 0 ? exp : exp);
    }

    @Override
    public Integer square(Integer exp) {
        return mathMod(exp * exp);
    }

    @Override
    public Integer mod(Integer left, Integer right) {
        if (right == 0) {
            throw new DBZException(left + "/" + right);
        }
        return mathMod(left % right);
    }

    private static int mathMod(int x) {
        return ((x % p) + p) % p;
    }

    private int pow(int x) {
        int power = p - 2;
        int r = 1;
        while (power != 0) {
            if (power % 2 == 1) {
                r = multiply(r, x);
            }
            power /= 2;
            x = square(x);
        }
        return r;
    }
}
