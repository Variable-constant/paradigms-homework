package expression.exceptions;

public class DBZException extends EvaluationException {
    public DBZException(String message) {
        super(message + " - division by zero");
    }
}
