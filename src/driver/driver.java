package driver;

import java.util.Stack;

public class driver {

    public static double calculate_expression(String exp) {
        exp = exp.replaceAll("π", String.valueOf(Math.PI));
        exp = exp.replaceAll("Sin", "s");
        exp = exp.replaceAll("Cos", "c");
        return evalExp(exp);
    }

    public static double evalExp(String exp) {
        Stack<Double> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);
            if (Character.isDigit(c)) {
                StringBuilder num = new StringBuilder();
                while (i < exp.length() && (Character.isDigit(exp.charAt(i)) || exp.charAt(i) == '.')) {
                    num.append(exp.charAt(i));
                    i++;
                }
                i--;

                numbers.push(Double.parseDouble(num.toString()));
            } else if (c == '(') {
                operators.push(c);
            } else if (c == ')') {
                while (operators.peek() != '(') {
                    choose_operation(numbers, operators);
                }
                operators.pop();
            } else if (c == '+' || c == '-' || c == 'x' || c == '/') {
                while (!operators.isEmpty() && priority(operators.peek()) >= priority(c)) {
                    choose_operation(numbers, operators);
                }
                operators.push(c);
            } else if (c == 's' || c == 'c') {
                StringBuilder function = new StringBuilder();
                while (i < exp.length() && Character.isLetter(exp.charAt(i))) {
                    function.append(exp.charAt(i));
                    i++;
                }
                i--;

                if (function.toString().equals("s")) {
                    operators.push('s'); // Indica función seno
                } else if (function.toString().equals("c")) {
                    operators.push('c'); // Indica función coseno
                }
            }
        }

        while (!operators.isEmpty()) {
            choose_operation(numbers, operators);
        }

        return numbers.pop();
    }

    private static int priority(char operator) {
        if (operator == '+' || operator == '-') {
            return 1;
        } else if (operator == 'x' || operator == '/') {
            return 2;
        } else if (operator == 's' || operator == 'c') {
            return 3; // Prioridad más alta para funciones trigonométricas
        }
        return 0;
    }

    private static void choose_operation(Stack<Double> numbers, Stack<Character> operators) {
        char operator = operators.pop();
        if (operator == 's') {
            // Calcular seno
            double num = numbers.pop();
            double result = Math.sin((num) * (Math.PI / 180));

            numbers.push(result);
        } else if (operator == 'c') {
            // Calcular coseno
            double num = numbers.pop();
            double result = Math.cos((num) * (Math.PI / 180));
            numbers.push(result);
        } else {
            double num2 = numbers.pop();
            double num1 = numbers.pop();
            double result = switch (operator) {
                case '+' -> num1 + num2;
                case '-' -> num1 - num2;
                case 'x' -> num1 * num2;
                case '/' -> num1 / num2;
                default -> 0;
            };

            numbers.push(result);
        }
    }
}
