package bullerun;

public class TaskOne {
    public double cosTaylor(double x, int terms) {
        if (terms < 1) throw new IllegalArgumentException("Minimal number of terms is 1");

        x = x % (2 * Math.PI);
        if (x > Math.PI) x -= 2 * Math.PI;
        if (x < -Math.PI) x += 2 * Math.PI;

        double result = 0;
        for (int n = 0; n < terms; n++) {
            result += Math.pow(-1, n) * Math.pow(x, 2 * n) / factorial(2 * n);
        }
        return result;
    }


    public long factorial(int n) {
        if (n < 0) throw new IllegalArgumentException("Factorial is not defined for negative values");
        long fact = 1;
        for (int i = 2; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }
}