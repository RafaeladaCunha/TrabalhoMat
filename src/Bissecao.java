import java.util.Scanner;

public class Bissecao {


    public static double pot(double b, int exp) {
        double result = 1;
        for (int i = 0; i < exp; i++) {
            result = b * result;
        }
        return result;
    }

    public static double abso(double valor) {
        if (valor < 0) {
            return -valor;
        }
        return valor;
    }

    public static double funcaoX(double x, double[] coeficientes) {
        double result = 0;
        int grau = coeficientes.length - 1;

        for (int i = 0; i <= grau; i++) {
            result =  result + coeficientes[i] * pot(x, grau - i);
        }
        return result;
    }

    public static double metodoBissecao(double a, double b, double erro, int iteracoes, double[] coeficientes) {
        double c = a;
        for (int i = 0; i < iteracoes; i++) {

            c = (a + b) / 2.0;


            double fc = funcaoX(c, coeficientes);
            double fa = funcaoX(a, coeficientes);


            if (abso(fc) < erro || abso(b - a) / 2.0 < erro) {
                return c;
            }


            if (fa * fc < 0) {
                b = c;
            } else {
                a = c;
            }
        }
        return c;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.print("Informe o grau do polinômio: ");
        int grau = scanner.nextInt();


        System.out.print("Informe o erro desejado: ");
        double erro = scanner.nextDouble();


        double[] coeficientes = new double[grau + 1];

        for (int i = 0; i <= grau; i++) {
            coeficientes[i] = scanner.nextDouble();
        }

    }
}

