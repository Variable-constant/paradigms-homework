package expression.generic;

import expression.GenericExpression;
import expression.calculation.*;
import expression.exceptions.EvaluationException;
import expression.exceptions.ParseException;
import expression.parser.ExpressionParser;

import java.util.Map;

public class GenericTabulator implements Tabulator {
    private static final Map<String, CalculationType<?>> MODS = Map.of(
            "i", new IntegerType(),
            "d", new DoubleType(),
            "bi", new BigIntegerType(),
            "u", new UncheckedType(),
            "b", new ByteType(),
            "p", new ModPType()
    );

    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws ParseException {
        CalculationType<?> type = MODS.get(mode);
        return fillTable(type, expression, x1, x2, y1, y2, z1, z2);
    }

    private <T> Object[][][] fillTable(CalculationType<T> type, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws ParseException {
        ExpressionParser<T> parser = new ExpressionParser<>(type);
        GenericExpression<T> exp = parser.parse(expression);
        int rangeX = x2 - x1 + 1;
        int rangeY = y2 - y1 + 1;
        int rangeZ = z2 - z1 + 1;
        Object[][][] res = new Object[rangeX][rangeY][rangeZ];
        for (int i = 0; i < rangeX; i++) {
            for (int j = 0; j < rangeY; j++) {
                for (int k = 0; k < rangeZ; k++) {
                    try {
                        res[i][j][k] = exp.evaluate(type.parse(Integer.toString(i + x1)), type.parse(Integer.toString(j + y1)), type.parse(Integer.toString(k + z1)));
                    } catch (EvaluationException ignored) {
                    }
                }
            }
        }
        return res;
    }
}
