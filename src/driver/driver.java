package driver;

import java.util.Stack;

public class driver {

    public static double calculate_expression(String exp) {
        exp = exp.replaceAll("π", String.valueOf(Math.PI));
        exp = exp.replaceAll("Sin", "s");
        exp = exp.replaceAll("Cos", "c");
        exp = exp.replaceAll("√", "q"); // Raíz cuadrada = sqrt = q
        exp = exp.replaceAll("x10\\^", "p"); // Multiplicar por 10 = x10 = p
        exp = exp.replaceAll("log", "l"); // Logaritmo = log = l
        exp = exp.replaceAll("!", "f"); // Factorial = ! = f
        exp = exp.replaceAll("%", "o"); // Porcentaje = % = o
        return evalExp(exp);
    }

    // Método para evaluar una expresión matemática
    public static double evalExp(String exp) {
        // Pilas para almacenar números y operadores
        Stack<Double> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();

        // Iterar a través de la expresión
        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);
            if (Character.isDigit(c)) {
                // Si es un dígito, extraer el número y agregarlo a la pila de números
                StringBuilder num = new StringBuilder();
                while (i < exp.length() && (Character.isDigit(exp.charAt(i)) || exp.charAt(i) == '.')) {
                    num.append(exp.charAt(i));
                    i++;
                }
                i--;

                numbers.push(Double.parseDouble(num.toString()));
            } else if (c == '(') {
                // Si es un paréntesis de apertura, agregarlo a la pila de operadores
                operators.push(c);
            } else if (c == ')') {
                // Si es un paréntesis de cierre, desapilar operadores y realizar operaciones hasta encontrar el paréntesis de apertura correspondiente
                while (operators.peek() != '(') {
                    choose_operation(numbers, operators);
                }
                operators.pop();
            } else if (c == '+' || c == '-' || c == 'x' || c == '/') {
                // Si es un operador aritmético, verificar prioridad y realizar operaciones
                while (!operators.isEmpty() && priority(operators.peek()) >= priority(c)) {
                    choose_operation(numbers, operators);
                }
                operators.push(c);
            } else if (c == 's' || c == 'c' || c == 'q' || c == 'p' || c == 'l' || c == 'f' || c == 'o') {
                // Si es una función, agregarla a la pila de operadores
                StringBuilder function = new StringBuilder();
                while (i < exp.length() && Character.isLetter(exp.charAt(i))) {
                    function.append(exp.charAt(i));
                    i++;
                }
                i--;

                // Agregar la función a la pila de operadores
                switch (function.toString()) {
                    case "s" -> operators.push('s'); // Indica función seno
                    case "c" -> operators.push('c'); // Indica función coseno
                    case "q" -> operators.push('q'); // Indica función raíz cuadrada
                    case "p" -> operators.push('p'); // Indica función multiplicar por x10^
                    case "l" -> operators.push('l'); // Indica función logaritmo
                    case "f" -> operators.push('f'); // Indica función factorial
                    case "o" -> operators.push('o'); // Indica función porcentaje
                }
            }
        }

        // Desapilar cualquier operador que quede y realizar operaciones
        while (!operators.isEmpty()) {
            choose_operation(numbers, operators);
        }

        // El resultado se encuentra en la cima de la pila de números
        return numbers.pop();
    }

    private static int priority(char operator) {
        if (operator == '+' || operator == '-') {
            return 1;
        } else if (operator == 'x' || operator == '/') {
            return 2;
        } else if (operator == 's' || operator == 'c') {
            return 3; // Prioridad más alta para funciones trigonométricas
        } else if (operator == 'q' || operator == 'p' || operator == 'l') {
            return 4; //Prioridad para funciones especiales
        } else if (operator == 'f' || operator == 'o') {
            return 5; // Prioridad para funciones especiales
        }
        return 0;
    }

    // Método para calcular el factorial de un número
    private static double factorial(double num) {
        // Si el número es 0, el factorial es 1 por definición
        if (num == 0) {
            return 1;
        }
        // Si el número no es 0, calcula el factorial recursivamente multiplicando el número por el factorial del número decrementado
        return num * factorial(num - 1);
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
        } else if (operator == 'q') {
            // Calcular raíz cuadrada
            double num = numbers.pop();
            double result = Math.sqrt(num);
            numbers.push(result);
        } else if (operator == 'p') {
            //calcular multiplicar por 10
            double num1 = numbers.pop();
            double num2 = numbers.pop();
            double result = num1 * Math.pow(10, num2);
            numbers.push(result);
        } else if (operator == 'l') {
            //calcular logaritmo
            double num = numbers.pop();
            double result = Math.log(num);
            numbers.push(result);
        } else if (operator == 'f') {
            //calcular factorial
            double num = numbers.pop();
            double result = factorial(num);
            numbers.push(result);
        } else if (operator == 'o') {
            //calcular porcentaje
            double num = numbers.pop();
            double result = num / 100;
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
