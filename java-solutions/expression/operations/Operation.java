package expression.operations;
import java.util.Map;

public enum Operation {
    ADD, SUB, MUL, MOD, DIV, CONST, VAR;

    public static final Map<Operation, Integer> PRIORITIES = Map.of(
            ADD, 0,
            SUB, 0,
            MUL, 1,
            DIV, 1,
            MOD, 1,
            CONST, 2,
            VAR, 2
    );

    public static final Map<Character, Operation> SYMBOL = Map.of(
            'm', MOD,
            '+', ADD,
            '-', SUB,
            '*', MUL,
            '/', DIV
    );
}
