package expression.parser;

import expression.*;
import expression.calculation.CalculationType;
import expression.exceptions.ParseException;
import expression.operations.*;

public class ExpressionParser<T> extends BaseParser implements GenericParser {
    CalculationType<T> type;

    public ExpressionParser(CalculationType<T> type) {
        this.type = type;
    }

    @Override
    public GenericExpression<T> parse(String expression) throws ParseException {
        changeSource(new StringSource(expression));
        nextChar();
        return parseToken(0);
    }

    private GenericExpression<T> parseToken(int priority) throws ParseException {
        skipWhitespace();
        if (priority == Operation.PRIORITIES.get(Operation.CONST)) {
            return parseValue();
        }

        GenericExpression<T> token = parseToken(priority + 1);

        while (true) {
            skipWhitespace();
            final Operation curOperation = Operation.SYMBOL.get(ch);
            if (ch == 'm') {
                expect("mod");
            }
            if (curOperation == null || priority != Operation.PRIORITIES.get(curOperation)) {
                return token;
            }
            nextChar();
            token = buildOperation(token, parseToken(priority + 1), curOperation);
        }
    }

    private GenericExpression<T> parseValue() throws ParseException {
        if (test('(')) {
            GenericExpression<T> parsed = parseToken(0);
            skipWhitespace();
            expect(')');
            return parsed;
        } else if (test('a')) {
            return parseAbs();
        } else if (test('s')) {
            return parseSquare();
        } else if (test('-')) {
            skipWhitespace();
            if (between('0', '9')) {
                return parseConst(false);
            }
            return new Negate<>(parseValue(), type);
        } else if (between('0', '9')) {
            return parseConst(true);
        } else {
            return parseVariable();
        }
    }

    private GenericExpression<T> parseSquare() throws ParseException {
        expect("quare");
        skipWhitespace();
        return new Square<>(parseValue(), type);
    }

    private GenericExpression<T> parseAbs() throws ParseException {
        expect("bs");
        skipWhitespace();
        return new Abs<>(parseValue(), type);
    }

    private GenericExpression<T> buildOperation(GenericExpression<T> left, GenericExpression<T> right,
                                                Operation oper) {
        switch (oper) {
            case ADD: return new Add<>(left, right, type);
            case SUB: return new Subtract<>(left, right, type);
            case MOD: return new Mod<>(left, right, type);
            case MUL: return new Multiply<>(left, right, type);
            case DIV: return new Divide<>(left, right, type);
            default: return null;
        }
    }


    private GenericExpression<T> parseVariable() {
        skipWhitespace();
        final String variable = Character.toString(ch);
        nextChar();
        return new Variable<>(variable);
    }

    private GenericExpression<T> parseConst(boolean positive) {
        final StringBuilder sb = new StringBuilder();
        if (!positive) {
            sb.append('-');
        }
        copyInteger(sb);
        return new Const<>(type.parse(sb.toString()));
    }
}
