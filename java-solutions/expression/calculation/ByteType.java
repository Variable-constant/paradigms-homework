package expression.calculation;

import expression.exceptions.DBZException;

public class ByteType implements CalculationType<Byte>{
    @Override
    public Byte parse(String string) {
        return (byte) Integer.parseInt(string);
    }

    @Override
    public Byte add(Byte left, Byte right) {
        return (byte) (left + right);
    }

    @Override
    public Byte divide(Byte left, Byte right) {
        if (right == 0) {
            throw new DBZException(left + "/" + right);
        }
        return (byte) (left / right);
    }

    @Override
    public Byte multiply(Byte left, Byte right) {
        return (byte) (left * right);
    }

    @Override
    public Byte subtract(Byte left, Byte right) {
        return (byte) (left - right);
    }

    @Override
    public Byte negate(Byte exp) {
        return (byte) -exp;
    }

    @Override
    public Byte abs(Byte exp) {
        return (byte) (exp > 0 ? exp : -exp);
    }

    @Override
    public Byte square(Byte exp) {
        return (byte) (exp * exp);
    }

    @Override
    public Byte mod(Byte left, Byte right) {
        if (right == 0) {
            throw new DBZException(left + "/" + right);
        }
        return (byte) (left % right);
    }
}
