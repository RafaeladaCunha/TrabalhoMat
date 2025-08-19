import java.util.Scanner;

public class Bissecao {
    static final int maiorB = 1000;
    static final int maiorGrau = 10;
    static final int menorB = -1000;


    public static double avaliaPolinomio(double x, double[] coef) {
        double resultado = 0;
        for (int i = 0; i < coef.length; i++) {
            double pot = 1;
            for (int j = 0; j < i; j++) {
                pot *= x;
            }
            resultado += coef[i] * pot;
        }
        return resultado;
    }


    public static double[][] encontrarIntervalos(double[] coef) {
        int maxIntervalos = maiorB - menorB;
        double[][] intervalos = new double[maxIntervalos][2];
        int count = 0;

        for (int i = menorB; i < maiorB; i++) {
            double fa = avaliaPolinomio(i, coef);
            double fb = avaliaPolinomio(i + 1, coef);
            if (fa * fb < 0) {
                intervalos[count][0] = i;
                intervalos[count][1] = i + 1;
                count++;
            }
        }

       
        double[][] resultado = new double[count][2];
        for (int i = 0; i < count; i++) {
            resultado[i][0] = intervalos[i][0];
            resultado[i][1] = intervalos[i][1];
        }
        return resultado;
    }

    public static double[] bisseccao(double a, double b, double precisao, double[] coef) {
        int iter = 0;
        double erro = Double.MAX_VALUE;
        double media = 0;

        do {
            media = (a + b) / 2;
            double fMedia = avaliaPolinomio(media, coef);
            erro = (b - a) / 2;

            if (fMedia == 0.0 || erro < precisao) {
                break;
            }
            double fA = avaliaPolinomio(a, coef);
            if (fA * fMedia < 0) {
                b = media;
            } else {
                a = media;
            }
            iter++;
        } while ((b - a) / 2 > precisao);

        return new double[]{(a + b) / 2, erro, iter};
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o maior grau do polinômio ( " + maiorGrau + "): ");
        int grau = scanner.nextInt();
        if (grau > maiorGrau) {
            System.out.println("O maior grau permitido é " + maiorGrau);
            scanner.close();
            return;
        }

        double[] coef = new double[grau + 1];
        System.out.println(" Digite os coeficientes do termo de grau 0 até " + grau + ":");
        for (int i = 0; i <= grau; i++) {
            System.out.print("Coeficiente de x^" + i + ": ");
            coef[i] = scanner.nextDouble();
        }

        System.out.print("Digite a precisão que tu precisas (ex: 0.0001): ");
        double precisao = scanner.nextDouble();

        double[][] intervalos = encontrarIntervalos(coef);
        if (intervalos.length == 0) {
            System.out.println("Não foi encontrado nenhum intervalo com mudança de sinal");
            scanner.close();
            return;
        }

        System.out.println("\nIntevalos que foram econtrados :");
        for (int i = 0; i < intervalos.length; i++) {
            System.out.printf("[%.0f, %.0f]\n", intervalos[i][0], intervalos[i][1]);
        }

        System.out.println("\n--- Resultados ---");
        for (int i = 0; i < intervalos.length; i++) {
            double[] resultado = bisseccao(intervalos[i][0], intervalos[i][1], precisao, coef);
            System.out.printf("Raiz %d:\n", i + 1);
            System.out.printf(" Intervalo: [%.2f, %.2f]\n", intervalos[i][0], intervalos[i][1]);
            System.out.printf(" Aproximação: %.10f\n", resultado[0]);
            System.out.printf(" Erro estimado: %.10f\n", resultado[1]);
            System.out.printf(" Iterações: %d\n\n", (int) resultado[2]);
        }

        scanner.close();
    }
}
