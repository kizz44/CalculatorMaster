import java.io.*;

class Calculator {
    public double add(double a, double b) {
        return a + b;
    }

    public double subtract(double a, double b) {
        return a - b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Делить на ноль нельзя");
        }
        return a / b;
    }
}

class ScientificCalculator extends Calculator {
    public double pow(double base, double exponent) {
        return Math.pow(base, exponent);
    }

    public long factorial(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Факториал не работает с отрицательными числами");
        }
        long result = 1;
        for (int i = 1; i <= number; i++) {
            result *= i;
        }
        return result;
    }
}

public class Main {
    public static void main(String[] args) {
        ScientificCalculator calculator = new ScientificCalculator();

        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                String operation = parts[0];
                double result;

                try {
                    switch (operation) {
                        case "add":
                            result = calculator.add(Double.parseDouble(parts[1]), Double.parseDouble(parts[2]));
                            break;
                        case "subtract":
                            result = calculator.subtract(Double.parseDouble(parts[1]), Double.parseDouble(parts[2]));
                            break;
                        case "multiply":
                            result = calculator.multiply(Double.parseDouble(parts[1]), Double.parseDouble(parts[2]));
                            break;
                        case "divide":
                            result = calculator.divide(Double.parseDouble(parts[1]), Double.parseDouble(parts[2]));
                            break;
                        case "pow":
                            result = calculator.pow(Double.parseDouble(parts[1]), Double.parseDouble(parts[2]));
                            break;
                        case "factorial":
                            result = calculator.factorial(Integer.parseInt(parts[1]));
                            break;
                        default:
                            writer.write("Неизвестная операция: " + operation);
                            writer.newLine();
                            continue;
                    }
                    writer.write("Результат " + operation + ": " + result);
                    writer.newLine();
                } catch (Exception e) {
                    writer.write("Ошибка при выполнении " + operation + ": " + e.getMessage());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения или записи файла: " + e.getMessage());
        }
    }
}
