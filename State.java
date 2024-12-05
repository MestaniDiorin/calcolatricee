import java.util.Stack;

public class State {
    private double accumulator;
    private String currentOperator;
    private boolean clearOnNextInput;

    public State() {
        accumulator = 0;
        currentOperator = null;
        clearOnNextInput = false;
    }

    public String processInput(String input) {
        try {
            switch (input) {
                case "+":
                case "-":
                case "*":
                case "/":
                    currentOperator = input;
                    accumulator = currentOperator;
                    clearOnNextInput = true;
                    break;
                case "=":
                    break;
                case "C":
                    accumulator = 0;
                    currentOperator = null;
                    clearOnNextInput = true;
                    return "0";
                default:
                    if (clearOnNextInput) {
                        accumulator = 0;
                        clearOnNextInput = false;
                    }
                    double value = Double.parseDouble(input);
                    if (currentOperator != null) {
                        accumulator = calculate(accumulator, value, currentOperator);
                        currentOperator = null;
                    } else {
                        accumulator = value;
                    }
            }
            return String.valueOf(accumulator);
        } catch (NumberFormatException e) {
            return "Errore";
        }
    }

    private double calculate(double a, double b, String operator) {
        return switch (operator) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> b != 0 ? a / b : Double.NaN;
            default -> 0;
        };
    }
}
